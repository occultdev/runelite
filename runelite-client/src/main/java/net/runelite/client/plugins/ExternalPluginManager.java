package net.runelite.client.plugins;

import com.google.common.collect.ImmutableList;
import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;
import static net.runelite.client.RuneLite.PLUGIN_DIR;
import net.runelite.client.RuneLiteProperties;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.OpenOSRSConfig;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.events.ExternalPluginChanged;
import net.runelite.client.ui.RuneLiteSplashScreen;
import org.pf4j.DefaultPluginManager;
import org.pf4j.DependencyResolver;
import org.pf4j.JarPluginLoader;
import org.pf4j.JarPluginRepository;
import org.pf4j.ManifestPluginDescriptorFinder;
import org.pf4j.PluginAlreadyLoadedException;
import org.pf4j.PluginDependency;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginLoader;
import org.pf4j.PluginRepository;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;
import org.pf4j.update.DefaultUpdateRepository;
import org.pf4j.update.PluginInfo;
import org.pf4j.update.UpdateManager;
import org.pf4j.update.UpdateRepository;
import org.pf4j.update.VerifyException;

@Slf4j
@Singleton
public
class ExternalPluginManager
{
	private final PluginManager runelitePluginManager;
	private final org.pf4j.PluginManager externalPluginManager;

	@Getter(AccessLevel.PUBLIC)
	private final List<UpdateRepository> repositories = new ArrayList<>();
	private final OpenOSRSConfig openOSRSConfig;
	private final ConfigManager configManager;
	private final EventBus eventBus;

	@Getter(AccessLevel.PUBLIC)
	private UpdateManager updateManager;

	@Inject
	public ExternalPluginManager(
		PluginManager pluginManager,
		OpenOSRSConfig openOSRSConfig,
		ConfigManager configManager,
		EventBus eventBus)
	{
		this.runelitePluginManager = pluginManager;
		this.openOSRSConfig = openOSRSConfig;
		this.configManager = configManager;
		this.eventBus = eventBus;

		boolean debug = RuneLiteProperties.getLauncherVersion() == null && RuneLiteProperties.getPluginPath() != null;

		this.externalPluginManager = new DefaultPluginManager(debug ? Paths.get(RuneLiteProperties.getPluginPath() + File.separator + "release") : PLUGIN_DIR.toPath())
		{
			@Override
			protected PluginLoader createPluginLoader()
			{
				return new JarPluginLoader(this);
			}

			@Override
			protected PluginDescriptorFinder createPluginDescriptorFinder()
			{
				return new ManifestPluginDescriptorFinder();
			}

			@Override
			protected PluginRepository createPluginRepository()
			{
				return new JarPluginRepository(getPluginsRoot());
			}

			@Override
			public RuntimeMode getRuntimeMode()
			{
				return debug ? RuntimeMode.DEVELOPMENT : RuntimeMode.DEPLOYMENT;
			}
		};
	}

	public void startExternalPluginManager()
	{
		this.externalPluginManager.loadPlugins();
	}

	public void startExternalUpdateManager()
	{
		try
		{
			for (String keyval : openOSRSConfig.getExternalRepositories().split(";"))
			{
				String[] repository = keyval.split(":", 2);
				repositories.add(new DefaultUpdateRepository(repository[0], new URL(repository[1])));
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}

		this.updateManager = new UpdateManager(this.externalPluginManager, repositories);
	}

	public void addRepository(String owner, String name)
	{
		try
		{
			DefaultUpdateRepository respository = new DefaultUpdateRepository(owner, toRepositoryUrl(owner, name));
			updateManager.addRepository(respository);
			saveConfig();
		}
		catch (MalformedURLException e)
		{
			log.error("Repostitory could not be added");
		}
	}

	public void removeRepository(String owner)
	{
		updateManager.removeRepository(owner);
		saveConfig();
	}

	private void saveConfig()
	{
		StringBuilder config = new StringBuilder();

		for (UpdateRepository repository : updateManager.getRepositories())
		{
			config.append(repository.getId());
			config.append(":");
			config.append(repository.getUrl().toString());
			config.append(";");
		}
		config.deleteCharAt(config.lastIndexOf(";"));

		openOSRSConfig.setExternalRepositories(config.toString());
	}

	private static URL toRepositoryUrl(String owner, String name) throws MalformedURLException
	{
		return new URL("https://raw.githubusercontent.com/" + owner + "/" + name + "/master/");
	}

	public static boolean testRepository(String owner, String name)
	{
		final List<UpdateRepository> repositories = new ArrayList<>();
		try
		{
			repositories.add(new DefaultUpdateRepository("github", new URL("https://raw.githubusercontent.com/" + owner + "/" + name + "/master/")));
		}
		catch (MalformedURLException e)
		{
			return false;
		}
		DefaultPluginManager testPluginManager = new DefaultPluginManager(PLUGIN_DIR.toPath());
		UpdateManager updateManager = new UpdateManager(testPluginManager, repositories);

		return updateManager.getPlugins().size() > 0;
	}

	private static void deleteMappedFilesIfExists(Path path) throws IOException
	{
		while (true)
		{
			try
			{
				Files.deleteIfExists(path);
				break;
			}
			catch (AccessDeniedException e)
			{
				System.gc();
			}
		}
	}

	private void instantiatePlugin(Plugin plugin) throws PluginInstantiationException
	{
		List<Plugin> scannedPlugins = new ArrayList<>(runelitePluginManager.getPlugins());
		Class<? extends Plugin> clazz = plugin.getClass();

		net.runelite.client.plugins.PluginDependency[] pluginDependencies = clazz.getAnnotationsByType(net.runelite.client.plugins.PluginDependency.class);
		List<Plugin> deps = new ArrayList<>();
		for (net.runelite.client.plugins.PluginDependency pluginDependency : pluginDependencies)
		{
			Optional<Plugin> dependency =  scannedPlugins.stream().filter(p -> p.getClass() == pluginDependency.value()).findFirst();
			if (!dependency.isPresent())
			{
				throw new PluginInstantiationException("Unmet dependency for " + clazz.getSimpleName() + ": " + pluginDependency.value().getSimpleName());
			}
			deps.add(dependency.get());
		}

		Module pluginModule = (Binder binder) ->
		{
			//noinspection unchecked
			binder.bind((Class<Plugin>) plugin.getClass()).toInstance(plugin);
			binder.install(plugin);

			for (Plugin p : deps)
			{
				Module p2 = (Binder binder2) ->
				{
					//noinspection unchecked
					binder2.bind((Class<Plugin>) p.getClass()).toInstance(p);
					binder2.install(p);
				};
				binder.install(p2);
			}
		};
		Injector pluginInjector = RuneLite.getInjector().createChildInjector(pluginModule);
		pluginInjector.injectMembers(plugin);
		plugin.injector = pluginInjector;

		// Initialize default configuration
		Injector injector = plugin.getInjector();

		for (Key<?> key : injector.getAllBindings().keySet())
		{
			Class<?> type = key.getTypeLiteral().getRawType();
			if (Config.class.isAssignableFrom(type))
			{
				Config config = (Config) injector.getInstance(key);
				configManager.setDefaultConfiguration(config, false);
			}
		}

		try
		{
			runelitePluginManager.startPlugin(plugin);
		}
		catch (PluginInstantiationException ex)
		{
			log.warn("unable to start plugin", ex);
			return;
		}

		runelitePluginManager.add(plugin);
		eventBus.post(ExternalPluginChanged.class, new ExternalPluginChanged(plugin, true));
	}

	public void loadPlugins()
	{
		this.externalPluginManager.startPlugins();
		List<PluginWrapper> startedPlugins = this.externalPluginManager.getStartedPlugins();
		int index = 1;

		for (PluginWrapper plugin : startedPlugins)
		{
			RuneLiteSplashScreen.stage(.90, 1, "Starting external plugins", index++, startedPlugins.size());
			loadPlugin(plugin.getPluginId());
		}
	}

	private void loadPlugin(String pluginId)
	{
		List<PluginWrapper> startedPlugins = externalPluginManager.getStartedPlugins();

		for (PluginWrapper pluginWrapper : startedPlugins)
		{
			if (pluginWrapper.getDescriptor().getPluginId().equals(pluginId))
			{
				List<Plugin> extensions = externalPluginManager.getExtensions(Plugin.class, pluginId);
				for (Plugin plugin : extensions)
				{
					try
					{
						instantiatePlugin(plugin);
					}
					catch (PluginInstantiationException e)
					{
						log.warn("Error instantiating plugin!", e);
						return;
					}
				}
			}
		}
	}

	private void stopPlugins()
	{
		List<PluginWrapper> startedPlugins = ImmutableList.copyOf(externalPluginManager.getStartedPlugins());

		for (PluginWrapper pluginWrapper : startedPlugins)
		{
			String pluginId = pluginWrapper.getDescriptor().getPluginId();
			List<Plugin> extensions = externalPluginManager.getExtensions(Plugin.class, pluginId);

			for (Plugin plugin : runelitePluginManager.getPlugins())
			{
				if (!extensions.get(0).getClass().getName().equals(plugin.getClass().getName()))
				{
					continue;
				}

				try
				{
					runelitePluginManager.stopPlugin(plugin);
					runelitePluginManager.remove(plugin);

					eventBus.post(ExternalPluginChanged.class, new ExternalPluginChanged(plugin, false));
				}
				catch (PluginInstantiationException ex)
				{
					log.warn("unable to stop plugin", ex);
					return;
				}
			}
		}
	}

	private Path stopPlugin(String pluginId)
	{
		List<PluginWrapper> startedPlugins = ImmutableList.copyOf(externalPluginManager.getStartedPlugins());

		for (PluginWrapper pluginWrapper : startedPlugins)
		{
			if (!pluginId.equals(pluginWrapper.getDescriptor().getPluginId()))
			{
				continue;
			}

			List<Plugin> extensions = externalPluginManager.getExtensions(Plugin.class, pluginId);

			for (Plugin plugin : runelitePluginManager.getPlugins())
			{
				if (!extensions.get(0).getClass().getName().equals(plugin.getClass().getName()))
				{
					continue;
				}

				try
				{
					runelitePluginManager.stopPlugin(plugin);
					runelitePluginManager.remove(plugin);

					eventBus.post(ExternalPluginChanged.class, new ExternalPluginChanged(plugin, false));

					return pluginWrapper.getPluginPath();
				}
				catch (PluginInstantiationException ex)
				{
					log.warn("unable to stop plugin", ex);
					return null;
				}
			}
		}
		return null;
	}

	public void install(String pluginId) throws VerifyException
	{
		// Null version defaults to latest
		try
		{
			updateManager.installPlugin(pluginId, null);
			loadPlugin(pluginId);
		}
		catch (DependencyResolver.DependenciesNotFoundException ex)
		{
			for (String dep : ex.getDependencies())
			{
				updateManager.installPlugin(dep, null);
				externalPluginManager.unloadPlugin(dep);
			}
			externalPluginManager.unloadPlugin(pluginId);
			stopPlugins();
			try
			{
				externalPluginManager.loadPlugins();
			}
			catch (PluginAlreadyLoadedException ignored)
			{
			}
			loadPlugins();
		}
	}

	public void uninstall(String pluginId)
	{
		Path pluginPath = stopPlugin(pluginId);

		if (pluginPath == null)
		{
			return;
		}

		while (true)
		{
			try
			{
				externalPluginManager.stopPlugin(pluginId);
				break;
			}
			catch (IllegalArgumentException ignored)
			{
			}
		}

		externalPluginManager.stopPlugin(pluginId);
		updateManager.uninstallPlugin(pluginId);
		try
		{
			deleteMappedFilesIfExists(pluginPath);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		externalPluginManager.unloadPlugin(pluginId);
	}

	public void update()
	{
		if (updateManager.hasUpdates())
		{
			List<PluginInfo> updates = updateManager.getUpdates();
			for (PluginInfo plugin : updates)
			{
				PluginInfo.PluginRelease lastRelease = updateManager.getLastPluginRelease(plugin.id);
				String lastVersion = lastRelease.version;
				boolean updated = updateManager.updatePlugin(plugin.id, lastVersion);
				if (!updated)
				{
					log.warn("Cannot update plugin '{}'", plugin.id);
				}
			}
		}
	}

	public Set<String> getDependencies()
	{
		Set<String> deps = new HashSet<>();
		List<PluginWrapper> startedPlugins = externalPluginManager.getStartedPlugins();

		for (PluginWrapper pluginWrapper : startedPlugins)
		{
			for (PluginDependency pluginDependency : pluginWrapper.getDescriptor().getDependencies())
			{
				deps.add(pluginDependency.getPluginId());
			}
		}

		return deps;
	}
}

/*
 * Copyright (c) 2018, Unmoon <https://github.com/Unmoon>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.tithefarm;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GameObject;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@PluginDescriptor(
		name = "Tithe Farm",
		description = "Show timers for the farming patches within the Tithe Farm minigame",
		tags = {"farming", "minigame", "overlay", "skilling", "timers"},
		type = PluginType.MINIGAME
)
@Singleton
public class TitheFarmPlugin extends Plugin {
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private TitheFarmPlantOverlay titheFarmOverlay;

	@Inject
	private TitheFarmPluginConfig config;

	@Getter(AccessLevel.PACKAGE)
	private final Set<TitheFarmPlant> plants = new HashSet<>();

	@Getter(AccessLevel.PACKAGE)
	private Color getColorUnwatered;
	@Getter(AccessLevel.PACKAGE)
	private Color getColorWatered;
	@Getter(AccessLevel.PACKAGE)
	private Color getColorGrown;

	@Provides
	TitheFarmPluginConfig getConfig(ConfigManager configManager) {
		return configManager.getConfig(TitheFarmPluginConfig.class);
	}

	@Override
	protected void startUp() {
		updateConfig();

		overlayManager.add(titheFarmOverlay);
		titheFarmOverlay.updateConfig();
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(titheFarmOverlay);
	}

	@Subscribe
	private void onConfigChanged(ConfigChanged event) {
		if (event.getGroup().equals("tithefarmplugin")) {
			updateConfig();

			titheFarmOverlay.updateConfig();
		}
	}

	@Subscribe
	private void onGameTick(final GameTick event) {
		plants.removeIf(plant -> plant.getPlantTimeRelative() == 1);
	}

	@Subscribe
	private void onGameObjectSpawned(GameObjectSpawned event) {
		GameObject gameObject = event.getGameObject();

		TitheFarmPlantType type = TitheFarmPlantType.getPlantType(gameObject.getId());
		if (type == null) {
			return;
		}

		TitheFarmPlantState state = TitheFarmPlantState.getState(gameObject.getId());

		TitheFarmPlant newPlant = new TitheFarmPlant(state, type, gameObject);
		TitheFarmPlant oldPlant = getPlantFromCollection(gameObject);

		if (oldPlant == null && newPlant.getType() != TitheFarmPlantType.EMPTY) {
			log.debug("Added plant {}", newPlant);
			plants.add(newPlant);
		} else if (newPlant.getType() == TitheFarmPlantType.EMPTY) {
			log.debug("Removed plant {}", oldPlant);
			plants.remove(oldPlant);
		} else if (oldPlant != null && oldPlant.getGameObject().getId() != newPlant.getGameObject().getId()) {
			if (oldPlant.getState() != TitheFarmPlantState.WATERED && newPlant.getState() == TitheFarmPlantState.WATERED) {
				log.debug("Updated plant (watered)");
				newPlant.setPlanted(oldPlant.getPlanted());
				plants.remove(oldPlant);
				plants.add(newPlant);
			} else {
				log.debug("Updated plant");
				plants.remove(oldPlant);
				plants.add(newPlant);
			}
		}
	}

	private TitheFarmPlant getPlantFromCollection(GameObject gameObject) {
		WorldPoint gameObjectLocation = gameObject.getWorldLocation();
		for (TitheFarmPlant plant : plants) {
			if (gameObjectLocation.equals(plant.getWorldLocation())) {
				return plant;
			}
		}
		return null;
	}

	private void updateConfig() {
		this.getColorUnwatered = config.getColorUnwatered();
		this.getColorWatered = config.getColorWatered();
		this.getColorGrown = config.getColorGrown();
	}
}

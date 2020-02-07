/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package net.runelite.client.plugins.corp;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static net.runelite.api.MenuOpcode.MENU_ACTION_DEPRIORITIZE_OFFSET;
import static net.runelite.api.MenuOpcode.NPC_SECOND_OPTION;

@PluginDescriptor(
		name = "Corporeal Beast",
		description = "Show damage statistics and highlight dark energy cores",
		tags = {"bosses", "combat", "pve", "overlay"},
		type = PluginType.PVM
)
@Slf4j
public class CorpPlugin extends Plugin {
	private static final String ATTACK = "Attack";
	private static final String DARK_ENERGY_CORE = "Dark energy core";

	@Getter(AccessLevel.PACKAGE)
	private NPC corp;

	@Getter(AccessLevel.PACKAGE)
	private NPC core;

	private int yourDamage;

	@Getter(AccessLevel.PACKAGE)
	private int totalDamage;

	@Getter(AccessLevel.PACKAGE)
	private final Set<Actor> players = new HashSet<>();

	@Inject
	private Client client;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CorpDamageOverlay corpOverlay;

	@Inject
	private CoreOverlay coreOverlay;

	@Inject
	private CorpConfig config;

	private boolean leftClickCore;
	@Getter(AccessLevel.PACKAGE)
	private boolean showDamage;

	@Provides
	CorpConfig getConfig(ConfigManager configManager) {
		return configManager.getConfig(CorpConfig.class);
	}

	@Override
	protected void startUp() {
		updateConfig();

		overlayManager.add(corpOverlay);
		overlayManager.add(coreOverlay);
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(corpOverlay);
		overlayManager.remove(coreOverlay);

		corp = core = null;
		yourDamage = 0;
		totalDamage = 0;
		players.clear();
	}

	@Subscribe
	private void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOADING) {
			players.clear();
		}
	}

	@Subscribe
	private void onNpcSpawned(NpcSpawned npcSpawned) {
		NPC npc = npcSpawned.getNpc();

		switch (npc.getId()) {
			case NpcID.CORPOREAL_BEAST:
				log.debug("Corporeal beast spawn: {}", npc);
				corp = npc;
				yourDamage = 0;
				totalDamage = 0;
				players.clear();
				break;
			case NpcID.DARK_ENERGY_CORE:
				core = npc;
				break;
		}
	}

	@Subscribe
	private void onNpcDespawned(NpcDespawned npcDespawned) {
		NPC npc = npcDespawned.getNpc();

		if (npc == corp) {
			log.debug("Corporeal beast despawn: {}", npc);
			corp = null;
			players.clear();

			if (npc.isDead()) {
				// Show kill stats
				String message = new ChatMessageBuilder()
						.append(ChatColorType.NORMAL)
						.append("Corporeal Beast: Your damage: ")
						.append(ChatColorType.HIGHLIGHT)
						.append(Integer.toString(yourDamage))
						.append(ChatColorType.NORMAL)
						.append(", Total damage: ")
						.append(ChatColorType.HIGHLIGHT)
						.append(Integer.toString(totalDamage))
						.build();

				chatMessageManager.queue(QueuedMessage.builder()
						.type(ChatMessageType.CONSOLE)
						.runeLiteFormattedMessage(message)
						.build());
			}
		} else if (npc == core) {
			core = null;
		}
	}

	@Subscribe
	private void onHitsplatApplied(HitsplatApplied hitsplatApplied) {
		Actor actor = hitsplatApplied.getActor();

		if (actor != corp) {
			return;
		}

		int myDamage = client.getVar(Varbits.CORP_DAMAGE);
		// sometimes hitsplats are applied after the damage counter has been reset
		if (myDamage > 0) {
			yourDamage = myDamage;
		}
		totalDamage += hitsplatApplied.getHitsplat().getAmount();
	}

	@Subscribe
	private void onInteractingChanged(InteractingChanged interactingChanged) {
		Actor source = interactingChanged.getSource();
		Actor target = interactingChanged.getTarget();

		if (target != corp) {
			return;
		}

		players.add(source);
	}

	@Subscribe
	private void onMenuEntryAdded(MenuEntryAdded event) {
		if (event.getOpcode() != NPC_SECOND_OPTION.getId()
				|| !this.leftClickCore || !event.getOption().equals(ATTACK)) {
			return;
		}

		final int npcIndex = event.getIdentifier();
		final NPC npc = client.getCachedNPCs()[npcIndex];
		if (npc == null || !npc.getName().equals(DARK_ENERGY_CORE)) {
			return;
		}

		event.setOpcode(NPC_SECOND_OPTION.getId() + MENU_ACTION_DEPRIORITIZE_OFFSET);
		event.setModified();
	}

	@Subscribe
	private void onConfigChanged(ConfigChanged configChanged) {
		if (configChanged.getGroup().equals("corp")) {
			updateConfig();
		}
	}

	private void updateConfig() {
		this.leftClickCore = config.leftClickCore();
		this.showDamage = config.showDamage();
	}
}

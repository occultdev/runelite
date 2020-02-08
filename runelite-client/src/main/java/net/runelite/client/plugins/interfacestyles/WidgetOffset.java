/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
 * Copyright (c) 2018, Raqes <j.raqes@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.interfacestyles;

import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.widgets.WidgetInfo;

@Getter(AccessLevel.PACKAGE)
enum WidgetOffset {
	RESIZABLE_2010_COMBAT_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_COMBAT_TAB, -4, 1, null, null),
	RESIZABLE_2010_COMBAT_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_COMBAT_ICON, 5, null, null, null),
	RESIZABLE_2010_STATS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_STATS_TAB, 35, 1, null, null),
	RESIZABLE_2010_STATS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_STATS_ICON, 35, null, null, null),
	RESIZABLE_2010_QUESTS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_QUESTS_TAB, 69, 1, 33, null),
	RESIZABLE_2010_QUESTS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_QUESTS_ICON, 70, 1, 33, null),
	RESIZABLE_2010_INVENTORY_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB, 103, 1, null, null),
	RESIZABLE_2010_INVENTORY_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_ICON, null, null, null, null),
	RESIZABLE_2010_EQUIPMENT_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_EQUIPMENT_ICON, null, 3, null, null),
	RESIZABLE_2010_EQUIPMENT_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_EQUIPMENT_TAB, null, 1, null, null),
	RESIZABLE_2010_PRAYER_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_PRAYER_TAB, 171, 1, null, null),
	RESIZABLE_2010_PRAYER_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_PRAYER_ICON, 172, 1, null, null),
	RESIZABLE_2010_MAGIC_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_MAGIC_TAB, 205, 1, null, null),
	RESIZABLE_2010_MAGIC_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_MAGIC_ICON, 206, null, null, null),
	RESIZABLE_2010_CLAN_CHAT_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_CLAN_CHAT_TAB, -4, 1, null, null),
	RESIZABLE_2010_CLAN_CHAT_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_CLAN_CHAT_ICON, 2, 1, null, null),
	RESIZABLE_2010_FRIENDS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_FRIENDS_TAB, 35, 1, null, null),
	RESIZABLE_2010_FRIENDS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_FRIENDS_ICON, 37, 1, null, null),
	RESIZABLE_2010_IGNORES_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_IGNORES_TAB, 69, 1, null, null),
	RESIZABLE_2010_IGNORES_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_IGNORES_ICON, 71, null, null, null),
	RESIZABLE_2010_LOGOUT_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_LOGOUT_TAB, 103, 1, null, null),
	RESIZABLE_2010_LOGOUT_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_LOGOUT_ICON, 104, 2, null, null),
	RESIZABLE_2010_OPTIONS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_OPTIONS_TAB, null, 1, null, null),
	RESIZABLE_2010_OPTIONS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_OPTIONS_ICON, 138, null, null, null),
	RESIZABLE_2010_EMOTES_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_EMOTES_TAB, 171, 1, null, null),
	RESIZABLE_2010_EMOTES_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_EMOTES_ICON, 172, 2, null, null),
	RESIZABLE_2010_MUSIC_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_MUSIC_TAB, 205, 1, null, null),
	RESIZABLE_2010_MUSIC_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_MUSIC_ICON, 204, 3, null, null),

	RESIZABLE_BOTTOM_2010_COMBAT_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_COMBAT_ICON, 2, null, null, null),
	RESIZABLE_BOTTOM_2010_STATS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_STATS_ICON, 32, null, null, null),
	RESIZABLE_BOTTOM_2010_QUESTS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_QUESTS_ICON, null, 1, null, null),
	RESIZABLE_BOTTOM_2010_EQUIPMENT_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_EQUIPMENT_ICON, 132, 3, null, null),
	RESIZABLE_BOTTOM_2010_PRAYERS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_PRAYER_ICON, 165, 1, null, null),
	RESIZABLE_BOTTOM_2010_LOGOUT_BUTTON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_LOGOUT_BUTTON, 185, null, null, null),
	RESIZABLE_BOTTOM_2010_MAGIC_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MAGIC_ICON, null, 2, null, null),
	RESIZABLE_BOTTOM_2010_FRIEND_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_FRIEND_ICON, null, 3, null, null),
	RESIZABLE_BOTTOM_2010_FRIEND_CHAT_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_FRIEND_CHAT_ICON, null, 2, null, null),
	RESIZABLE_BOTTOM_2010_OPTIONS_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_OPTIONS_ICON, null, 2, null, null),
	RESIZABLE_BOTTOM_2010_EMOTES_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_EMOTES_ICON, null, 3, null, null),
	RESIZABLE_BOTTOM_2010_MUSIC_ICON(Skin.AROUND_2010, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MUSIC_ICON, null, 3, null, null),

	FIXED_2010_COMBAT_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_COMBAT_TAB, 2, 1, null, null),
	FIXED_2010_COMBAT_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_COMBAT_ICON, 11, null, null, null),
	FIXED_2010_STATS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_STATS_TAB, 41, 1, null, null),
	FIXED_2010_STATS_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_STATS_ICON, 41, null, null, null),
	FIXED_2010_QUESTS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_QUESTS_TAB, 75, 1, 33, null),
	FIXED_2010_QUESTS_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_QUESTS_ICON, 75, 0, null, null),
	FIXED_2010_INVENTORY_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB, 109, 1, null, null),
	FIXED_2010_INVENTORY_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_INVENTORY_ICON, 111, -1, null, null),
	FIXED_2010_EQUIPMENT_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_EQUIPMENT_ICON, 143, 2, null, null),
	FIXED_2010_PRAYER_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_PRAYER_TAB, 177, 1, null, null),
	FIXED_2010_PRAYER_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_PRAYER_ICON, 178, 1, null, null),
	FIXED_2010_MAGIC_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB, 211, 1, null, null),
	FIXED_2010_MAGIC_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_MAGIC_ICON, 212, 1, null, null),
	FIXED_2010_CLAN_CHAT_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_CLAN_CHAT_TAB, 0, 1, null, null),
	FIXED_2010_CLAN_CHAT_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_CLAN_CHAT_ICON, 5, null, null, null),
	FIXED_2010_FRIENDS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_FRIENDS_TAB, 38, 1, 33, null),
	FIXED_2010_FRIENDS_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_FRIENDS_ICON, 40, null, null, null),
	FIXED_2010_IGNORES_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_IGNORES_TAB, 72, 1, null, null),
	FIXED_2010_IGNORES_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_IGNORES_ICON, 74, null, null, null),
	FIXED_2010_LOGOUT_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_LOGOUT_TAB, 106, 1, null, null),
	FIXED_2010_LOGOUT_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_LOGOUT_ICON, 107, 2, null, null),
	FIXED_2010_OPTIONS_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_OPTIONS_TAB, 140, 1, null, null),
	FIXED_2010_OPTIONS_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_OPTIONS_ICON, 143, null, null, null),
	FIXED_2010_EMOTES_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_EMOTES_TAB, 174, 1, null, null),
	FIXED_2010_EMOTES_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_EMOTES_ICON, 177, 2, null, null),
	FIXED_2010_MUSIC_HIGHLIGHT(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_MUSIC_TAB, 208, 1, null, null),
	FIXED_2010_MUSIC_ICON(Skin.AROUND_2010, WidgetInfo.FIXED_VIEWPORT_MUSIC_ICON, 209, 2, null, null),

	RESIZABLE_2005_QUESTS_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_QUESTS_ICON, 72, 0, null, null),
	RESIZABLE_2005_LOGOUT_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_LOGOUT_ICON, null, null, null, null),
	RESIZABLE_2005_OPTIONS_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_OPTIONS_ICON, 137, null, null, null),
	RESIZABLE_2005_EMOTE_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_EMOTES_ICON, 173, 1, null, null),
	RESIZABLE_2005_INVENTORY_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_ICON, null, -2, null, null),
	RESIZABLE_2005_EQUIPMENT_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_EQUIPMENT_ICON, null, 2, null, null),
	RESIZABLE_2005_MUSIC_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_MUSIC_ICON, null, 3, null, null),

	RESIZABLE_BOTTOM_2005_INVENTORY_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_ICON, 98, 2, null, null),
	RESIZABLE_BOTTOM_2005_QUESTS_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_QUESTS_ICON, 67, 0, null, null),
	RESIZABLE_BOTTOM_2005_EQUIPMENT_ICON(Skin.AROUND_2005, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_EQUIPMENT_ICON, 132, 2, null, null),

	FIXED_2005_ROOT_INTERFACE_CONTAINER(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_ROOT_INTERFACE_CONTAINER, null, null, 197, null),
	FIXED_2005_INTERFACE_CONTAINER(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_INTERFACE_CONTAINER, 7, null, null, null),
	FIXED_2005_BANK_CONTAINER(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_BANK_CONTAINER, 7, null, null, null),
	FIXED_2005_COMBAT_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_COMBAT_TAB, 19, 2, null, null),
	FIXED_2005_COMBAT_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_COMBAT_ICON, 28, 1, null, null),
	FIXED_2005_STATS_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_STATS_TAB, 55, null, 30, null),
	FIXED_2005_STATS_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_STATS_ICON, 51, null, null, null),
	FIXED_2005_QUESTS_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_QUESTS_TAB, 82, 1, 30, null),
	FIXED_2005_QUESTS_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_QUESTS_ICON, 80, null, null, null),
	FIXED_2005_INVENTORY_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB, null, null, 45, null),
	FIXED_2005_INVENTORY_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_INVENTORY_ICON, 113, 1, null, null),
	FIXED_2005_EQUIPMENT_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_EQUIPMENT_TAB, 153, 1, 30, null),
	FIXED_2005_EQUIPMENT_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_EQUIPMENT_ICON, 151, 4, null, null),
	FIXED_2005_PRAYER_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_PRAYER_TAB, 180, null, 32, null),
	FIXED_2005_PRAYER_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_PRAYER_ICON, 178, null, null, null),
	FIXED_2005_MAGIC_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB, 209, 1, 30, null),
	FIXED_2005_MAGIC_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_MAGIC_ICON, 206, 2, null, null),
	FIXED_2005_CLAN_CHAT_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_CLAN_CHAT_TAB, 15, null, null, null),
	FIXED_2005_CLAN_CHAT_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_CLAN_CHAT_ICON, 22, 0, null, null),
	FIXED_2005_FRIENDS_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_FRIENDS_TAB, 51, null, 30, null),
	FIXED_2005_FRIENDS_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_FRIENDS_ICON, 49, -1, null, null),
	FIXED_2005_IGNORES_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_IGNORES_TAB, 79, null, 30, null),
	FIXED_2005_IGNORES_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_IGNORES_ICON, 78, null, null, null),
	FIXED_2005_LOGOUT_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_LOGOUT_TAB, 107, 1, 45, null),
	FIXED_2005_LOGOUT_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_LOGOUT_ICON, 112, null, null, null),
	FIXED_2005_OPTIONS_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_OPTIONS_TAB, 150, null, 30, null),
	FIXED_2005_OPTIONS_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_OPTIONS_ICON, 148, -1, null, null),
	FIXED_2005_EMOTES_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_EMOTES_TAB, 178, null, 30, null),
	FIXED_2005_EMOTES_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_EMOTES_ICON, 178, 1, null, null),
	FIXED_2005_MUSIC_HIGHLIGHT(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_MUSIC_TAB, 206, null, 30, null),
	FIXED_2005_MUSIC_ICON(Skin.AROUND_2005, WidgetInfo.FIXED_VIEWPORT_MUSIC_ICON, 202, 2, null, null);

	private Skin skin;
	private WidgetInfo widgetInfo;
	private Integer offsetX;
	private Integer offsetY;
	private Integer width;
	private Integer height;

	WidgetOffset(Skin skin, WidgetInfo widgetInfo, Integer offsetX, Integer offsetY, Integer width, Integer height) {
		this.skin = skin;
		this.widgetInfo = widgetInfo;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}
}

/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.client.plugins.sounds;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.SoundEffectPlayed;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.http.api.sounds.SoundsClient;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;

@PluginDescriptor(
		name = "Sounds",
		hidden = true
)
@Slf4j
public class SoundsPlugin extends Plugin {
	private final SoundsClient soundsClient = new SoundsClient();

	private HashMap<Integer, int[]> sounds;
	@Inject
	private Client client;

	{
		try {
			sounds = soundsClient.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Subscribe
	private void onSoundEffectPlayed(SoundEffectPlayed event) {
		if (event.getNpcid() != -1) {
			if (ArrayUtils.contains(sounds.get(event.getNpcid()), event.getSoundId())) {
				return;
			}
			int[] newSounds = ArrayUtils.add(sounds.get(event.getNpcid()), event.getSoundId());
			sounds.put(event.getNpcid(), newSounds);
			soundsClient.submit(event.getNpcid(), event.getSoundId());
		}
	}
}

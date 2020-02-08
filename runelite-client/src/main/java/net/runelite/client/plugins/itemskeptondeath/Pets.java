/*
 * Copyright (c) 2018 Abex
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
package net.runelite.client.plugins.itemskeptondeath;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static net.runelite.api.ItemID.*;

final class Pets {
	private Pets() {
	}

	private static final Set<Integer> PETS = ImmutableSet.of(
			//Cats
			HELL_CAT,
			HELLKITTEN,
			LAZY_CAT,
			LAZY_CAT_6550,
			LAZY_CAT_6551,
			LAZY_CAT_6552,
			LAZY_CAT_6553,
			LAZY_CAT_6554,
			LAZY_HELL_CAT,
			OVERGROWN_HELLCAT,
			PET_CAT,
			PET_CAT_1562,
			PET_CAT_1563,
			PET_CAT_1564,
			PET_CAT_1565,
			PET_CAT_1566,
			PET_CAT_1567,
			PET_CAT_1568,
			PET_CAT_1569,
			PET_CAT_1570,
			PET_CAT_1571,
			PET_CAT_1572,
			PET_KITTEN,
			PET_KITTEN_1556,
			PET_KITTEN_1557,
			PET_KITTEN_1558,
			PET_KITTEN_1559,
			PET_KITTEN_1560,
			WILY_CAT,
			WILY_CAT_6556,
			WILY_CAT_6557,
			WILY_CAT_6558,
			WILY_CAT_6559,
			WILY_CAT_6560,
			WILY_HELLCAT,

			//ClueScroll Pets
			BLOODHOUND,

			//PVM Pets
			ABYSSAL_ORPHAN,
			BABY_MOLE,
			CALLISTO_CUB,
			CORRUPTED_YOUNGLLEF,
			HELLPUPPY,
			IKKLE_HYDRA,
			IKKLE_HYDRA_22748,
			IKKLE_HYDRA_22750,
			IKKLE_HYDRA_22752,
			JALNIBREK,
			KALPHITE_PRINCESS,
			KALPHITE_PRINCESS_12654,
			LIL_ZIK,
			MIDNIGHT,
			NOON,
			OLMLET,
			PET_CHAOS_ELEMENTAL,
			PET_CORPOREAL_CRITTER,
			PET_DAGANNOTH_PRIME,
			PET_DAGANNOTH_REX,
			PET_DAGANNOTH_SUPREME,
			PET_DARK_CORE,
			PET_GENERAL_GRAARDOR,
			PET_KRAKEN,
			PET_KREEARRA,
			PET_KRIL_TSUTSAROTH,
			PET_PENANCE_QUEEN,
			PET_SMOKE_DEVIL,
			PET_SMOKE_DEVIL_22663,
			PET_SNAKELING,
			PET_SNAKELING_12939,
			PET_SNAKELING_12940,
			PET_ZILYANA,
			PRINCE_BLACK_DRAGON,
			PUPPADILE,
			SCORPIAS_OFFSPRING,
			SKOTOS,
			SMOLCANO,
			SRARACHA,
			TEKTINY,
			TZREKJAD,
			TZREKZUK,
			VANGUARD,
			VASA_MINIRIO,
			VENENATIS_SPIDERLING,
			VESPINA,
			VETION_JR,
			VETION_JR_13180,
			VORKI,
			YOUNGLLEF,

			//Skilling Pets
			BABY_CHINCHOMPA,
			BABY_CHINCHOMPA_13324,
			BABY_CHINCHOMPA_13325,
			BABY_CHINCHOMPA_13326,
			BEAVER,
			CHOMPY_CHICK,
			GIANT_SQUIRREL,
			HERBI,
			HERON,
			PHOENIX,
			RIFT_GUARDIAN,
			RIFT_GUARDIAN_20667,
			RIFT_GUARDIAN_20669,
			RIFT_GUARDIAN_20671,
			RIFT_GUARDIAN_20673,
			RIFT_GUARDIAN_20675,
			RIFT_GUARDIAN_20677,
			RIFT_GUARDIAN_20679,
			RIFT_GUARDIAN_20681,
			RIFT_GUARDIAN_20683,
			RIFT_GUARDIAN_20685,
			RIFT_GUARDIAN_20687,
			RIFT_GUARDIAN_20689,
			RIFT_GUARDIAN_20691,
			RIFT_GUARDIAN_21990,
			ROCK_GOLEM,
			ROCK_GOLEM_21187,
			ROCK_GOLEM_21188,
			ROCK_GOLEM_21189,
			ROCK_GOLEM_21190,
			ROCK_GOLEM_21191,
			ROCK_GOLEM_21192,
			ROCK_GOLEM_21193,
			ROCK_GOLEM_21194,
			ROCK_GOLEM_21195,
			ROCK_GOLEM_21196,
			ROCK_GOLEM_21197,
			ROCK_GOLEM_21340,
			ROCK_GOLEM_21358,
			ROCK_GOLEM_21359,
			ROCK_GOLEM_21360
	);

	public static boolean isPet(int id) {
		return PETS.contains(id);
	}
}

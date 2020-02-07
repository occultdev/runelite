package net.runelite.client.ui.overlay.arrow;

import net.runelite.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ArrowUtil {
	static List<GameObject> getObjects(final Client client, Set<Integer> objectIDs) {
		final Scene scene = client.getScene();
		final Tile[][] tiles = scene.getTiles()[client.getPlane()];
		final ArrayList<GameObject> found = new ArrayList<>();

		for (Tile[] tiles2 : tiles) {
			for (Tile tile : tiles2) {
				for (GameObject object : tile.getGameObjects()) {
					if (object == null) {
						continue;
					}

					if (objectIDs.contains(object.getId())) {
						found.add(object);
						continue;
					}

					// Check impostors
					final ObjectDefinition comp = client.getObjectDefinition(object.getId());
					final ObjectDefinition impostor = comp.getImpostorIds() != null ? comp.getImpostor() : comp;

					if (impostor != null && objectIDs.contains(impostor.getId())) {
						found.add(object);
					}
				}
			}
		}

		return found;
	}
}

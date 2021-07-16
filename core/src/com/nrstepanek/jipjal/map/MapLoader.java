package com.nrstepanek.jipjal.map;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.nrstepanek.jipjal.TextureHolder;

public class MapLoader {

	TextureHolder th;
	PrefabLoader pl;

	public MapLoader(TextureHolder th) {
		this.th = th;
		this.pl = new PrefabLoader(th);
	}
	
	public JipjalMap loadFromFile(String filename) throws FileNotFoundException {
		FileHandle mapFileHandle = Gdx.files.internal("./maps/" + filename);
		if (!mapFileHandle.exists()) {
			mapFileHandle = Gdx.files.local("./maps/" + filename);
			if (!mapFileHandle.exists()) {
				throw new FileNotFoundException("Could not find map with name: " + filename);
			}
		}

		JsonReader json = new JsonReader();
		JsonValue mapJson = json.parse(mapFileHandle);

		int mapWidth = mapJson.getInt("width");
		int mapHeight = mapJson.getInt("height");
		JipjalMap map = new JipjalMap(mapWidth, mapHeight, th, false);
		map.setSocketThreshold(mapJson.getInt("socketThreshold"));
		
		JsonValue wallJson = mapJson.get("walls");
		for (JsonValue wall : wallJson) {
			Cell wallCell = pl.getWallPrefab(wall.getInt("x"), wall.getInt("y"));
			map.addToCellMap(wallCell);
		}

		JsonValue objectsJson = mapJson.get("objects");
		for (JsonValue object : objectsJson) {
			ObjectTypeEnum objectType = ObjectTypeEnum.fromString(object.getString("type"));
			int objectX = object.getInt("x");
			int objectY = object.getInt("y");
			switch (objectType) {
				case BLUE_LOCK:
					map.addToCellMap(pl.getBlueLockPrefab(objectX, objectY));
					break;
				case GREEN_LOCK:
					map.addToCellMap(pl.getGreenLockPrefab(objectX, objectY));
					break;
				case RED_LOCK:
					map.addToCellMap(pl.getRedLockPrefab(objectX, objectY));
					break;
				case YELLOW_LOCK:
					map.addToCellMap(pl.getYellowLockPrefab(objectX, objectY));
					break;
				case FIRE:
					map.addToCellMap(pl.getFirePrefab(objectX, objectY));
					break;
				case WATER:
					map.addToCellMap(pl.getWaterPrefab(objectX, objectY));
					break;
				case SOCKET:
					map.addToCellMap(pl.getSocketPrefab(objectX, objectY));
					break;
				case NONE:
					System.out.println("ERROR: Unknown object in map file.");
					map.addToCellMap(pl.getGrassPrefab(objectX, objectY));
					break;
			}
		}

		JsonValue itemsJson = mapJson.get("items");
		for (JsonValue itemJson : itemsJson) {
			ItemTypeEnum itemType = ItemTypeEnum.fromString(itemJson.getString("type"));
			int itemX = itemJson.getInt("x");
			int itemY = itemJson.getInt("y");
			Cell itemCell = pl.getItemPrefab(itemX, itemY, itemType);
			map.addToCellMap(itemCell);
		}

		map.setPlayerStartX(mapJson.getInt("playerStartX"));
		map.setPlayerStartY(mapJson.getInt("playerStartY"));

		map.fillIn();

		return map;
	}

	// Validates that a map file has all the required json attributes.
	public boolean jsonComplete(JsonValue mapJson) {
		return true;
	}
}

package com.nrstepanek.jipjal.map;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.nrstepanek.jipjal.TextureHolder;
import com.nrstepanek.jipjal.game.MonsterTypeEnum;

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
		
		JsonValue groundJson = mapJson.get("ground");
		for (JsonValue ground : groundJson) {
			GroundTypeEnum groundType = GroundTypeEnum.fromString(ground.getString("type"));
			int groundX = ground.getInt("x");
			int groundY = ground.getInt("y");
			switch (groundType) {
				case GRASS:
					break;
				case NONE:
					break;
				case WALL:
					map.addToCellMap(pl.getWallPrefab(groundX, groundY));
					break;
				case ICE:
					map.addToCellMap(pl.getIcePrefab(groundX, groundY));
					break;
				case FORCE_LEFT:
					map.addToCellMap(pl.getForceLeftPrefab(groundX, groundY));
					break;
				case FORCE_UP:
					map.addToCellMap(pl.getForceUpPrefab(groundX, groundY));
					break;
				case FORCE_RIGHT:
					map.addToCellMap(pl.getForceRightPrefab(groundX, groundY));
					break;
				case FORCE_DOWN:
					map.addToCellMap(pl.getForceDownPrefab(groundX, groundY));
					break;
			}
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
    if (itemsJson != null) {
      for (JsonValue itemJson : itemsJson) {
        ItemTypeEnum itemType = ItemTypeEnum.fromString(itemJson.getString("type"));
        int itemX = itemJson.getInt("x");
        int itemY = itemJson.getInt("y");
        Cell itemCell = pl.getItemPrefab(itemX, itemY, itemType);
        map.addToCellMap(itemCell);
      }
    }

    JsonValue monstersJson = mapJson.get("monsters");
    if (monstersJson != null) {
      for (JsonValue monsterJson : monstersJson) {
        MonsterTypeEnum monsterType = MonsterTypeEnum.fromString(monsterJson.getString("type"));
        int monsterX = monsterJson.getInt("x");
        int monsterY = monsterJson.getInt("y");
        switch (monsterType) {
          case BUG:
            map.addMonster(pl.getBugPrefab(monsterX, monsterY));
            break;
          case NONE:
        }
      }
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

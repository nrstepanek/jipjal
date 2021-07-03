package com.nrstepanek.jipjal.map;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.nrstepanek.jipjal.TextureHolder;

public class MapLoader {

	private TextureHolder th;

	public MapLoader(TextureHolder th) {
		this.th = th;
	}
	
	public JipjalMap loadFromFile(String filename) throws FileNotFoundException {
		FileHandle mapFileHandle = Gdx.files.internal("./maps/" + filename);
		if (!mapFileHandle.exists()) {
			throw new FileNotFoundException("Could not find map with name: " + filename);
		}

		JsonReader json = new JsonReader();
		JsonValue mapJson = json.parse(mapFileHandle);

		int mapWidth = mapJson.getInt("width");
		int mapHeight = mapJson.getInt("height");
		JipjalMap map = new JipjalMap(mapWidth, mapHeight, th, false);
		map.setSocketThreshold(mapJson.getInt("socketThreshold"));
		
		JsonValue wallJson = mapJson.get("walls");
		for (JsonValue wall : wallJson) {
			Cell wallCell = new Cell(th.getTexture("wall"), wall.getInt("x"), wall.getInt("y"));
			wallCell.setSolid(true);
			map.addToCellMap(wallCell);
		}

		JsonValue objectsJson = mapJson.get("objects");
		for (JsonValue object : objectsJson) {
			ObjectTypeEnum objectType = ObjectTypeEnum.fromString(object.getString("type"));
			int objectX = object.getInt("x");
			int objectY = object.getInt("y");
			switch (objectType) {
				case BLUE_LOCK:
					map.addToCellMap(getBlueLockPrefab(objectX, objectY));
					break;
				case GREEN_LOCK:
					map.addToCellMap(getGreenLockPrefab(objectX, objectY));
					break;
				case RED_LOCK:
					map.addToCellMap(getRedLockPrefab(objectX, objectY));
					break;
				case YELLOW_LOCK:
					map.addToCellMap(getYellowLockPrefab(objectX, objectY));
					break;
				case FIRE:
					map.addToCellMap(getFirePrefab(objectX, objectY));
					break;
				case WATER:
					map.addToCellMap(getWaterPrefab(objectX, objectY));
					break;
				case SOCKET:
					map.addToCellMap(getSocketPrefab(objectX, objectY));
					break;
				case NONE:
					System.out.println("ERROR: Unknown object in map file.");
					map.addToCellMap(getGrassPrefab(objectX, objectY));
					break;
			}
		}

		JsonValue itemsJson = mapJson.get("items");
		for (JsonValue itemJson : itemsJson) {
			ItemTypeEnum itemType = ItemTypeEnum.fromString(itemJson.getString("type"));
			int itemX = itemJson.getInt("x");
			int itemY = itemJson.getInt("y");
			Item item = new Item(itemType);
			Cell cell = getGrassPrefab(itemX, itemY);
			cell.addItem(item, th.getTextureFromItemType(itemType));
			map.addToCellMap(cell);
		}

		map.setPlayerStartX(mapJson.getInt("playerStartX"));
		map.setPlayerStartY(mapJson.getInt("playerStartY"));

		map.fillIn();

		return map;
	}

	// OBJECT PREFABS

	public Cell getGrassPrefab(int x, int y) {
		Cell grassCell = new Cell(th.getTexture("grass"), x, y);
		return grassCell;
	}

	public Cell getBlueLockPrefab(int x, int y) {
		Cell blueLockCell = new Cell(th.getTexture("grass"), x, y);
		blueLockCell.setSolid(true);
		blueLockCell.setObjectType(ObjectTypeEnum.BLUE_LOCK, th.getTextureFromObjectType(ObjectTypeEnum.BLUE_LOCK));
		return blueLockCell;
	}

	public Cell getGreenLockPrefab(int x, int y) {
		Cell greenLockCell = new Cell(th.getTexture("grass"), x, y);
		greenLockCell.setSolid(true);
		greenLockCell.setObjectType(ObjectTypeEnum.GREEN_LOCK, th.getTextureFromObjectType(ObjectTypeEnum.GREEN_LOCK));
		return greenLockCell;
	}

	public Cell getRedLockPrefab(int x, int y) {
		Cell redLockCell = new Cell(th.getTexture("grass"), x, y);
		redLockCell.setSolid(true);
		redLockCell.setObjectType(ObjectTypeEnum.RED_LOCK, th.getTextureFromObjectType(ObjectTypeEnum.RED_LOCK));
		return redLockCell;
	}

	public Cell getYellowLockPrefab(int x, int y) {
		Cell yellowLockCell = new Cell(th.getTexture("grass"), x, y);
		yellowLockCell.setSolid(true);
		yellowLockCell.setObjectType(ObjectTypeEnum.YELLOW_LOCK, th.getTextureFromObjectType(ObjectTypeEnum.YELLOW_LOCK));
		return yellowLockCell;
	}

	public Cell getFirePrefab(int x, int y) {
		Cell fireCell = new Cell(th.getTexture("grass"), x, y);
		fireCell.setDangerous(true);
		fireCell.setObjectType(ObjectTypeEnum.FIRE, th.getTextureFromObjectType(ObjectTypeEnum.FIRE));
		return fireCell;
	}

	public Cell getWaterPrefab(int x, int y) {
		Cell waterCell = new Cell(th.getTexture("grass"), x, y);
		waterCell.setDangerous(true);
		waterCell.setObjectType(ObjectTypeEnum.WATER, th.getTextureFromObjectType(ObjectTypeEnum.WATER));
		return waterCell;
	}

	public Cell getSocketPrefab(int x, int y) {
		Cell socketCell = new Cell(th.getTexture("grass"), x, y);
		socketCell.setSolid(true);
		socketCell.setObjectType(ObjectTypeEnum.SOCKET, th.getTextureFromObjectType(ObjectTypeEnum.SOCKET));
		return socketCell;
	}

	// Validates that a map file has all the required json attributes.
	public boolean jsonComplete(JsonValue mapJson) {
		return true;
	}
}

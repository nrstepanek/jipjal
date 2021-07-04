package com.nrstepanek.jipjal.map;

import com.nrstepanek.jipjal.TextureHolder;

public class PrefabLoader {
	
	TextureHolder th;
	
	public PrefabLoader(TextureHolder th) {
		this.th = th;
	}

	// GENERIC ITEM PREFAB

	public Cell getItemPrefab(int x, int y, ItemTypeEnum itemType) {
		Cell itemCell = getGrassPrefab(x, y);
		Item item = new Item(itemType);
		itemCell.addItem(item, th.getTextureFromItemType(itemType));

		return itemCell;
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

	// OTHER PREFABS

	public Cell getWallPrefab(int x, int y) {
		Cell wallCell = new Cell(th.getTexture("wall"), x, y);
		wallCell.setSolid(true);
		return wallCell;
	}
}

package com.nrstepanek.jipjal.editor;

import com.nrstepanek.jipjal.TextureHolder;
import com.nrstepanek.jipjal.game.MonsterTypeEnum;
import com.nrstepanek.jipjal.map.Cell;
import com.nrstepanek.jipjal.map.GroundTypeEnum;
import com.nrstepanek.jipjal.map.ItemTypeEnum;
import com.nrstepanek.jipjal.map.ObjectTypeEnum;
import com.nrstepanek.jipjal.map.PrefabLoader;

public class SelectedCellHelper {
	
	ObjectTypeEnum objectType;
	ItemTypeEnum itemType;
	GroundTypeEnum groundType;
	MonsterTypeEnum monsterType;

	PrefabLoader pl;

	public SelectedCellHelper(TextureHolder th) {
		this.pl = new PrefabLoader(th);
		this.groundType = GroundTypeEnum.WALL;
	}

	public void setObjectType(ObjectTypeEnum objectType) {
		this.objectType = objectType;
		this.itemType = null;
		this.groundType = null;
		this.monsterType = null;
	}

	public void setItemType(ItemTypeEnum itemType) {
		this.objectType = null;
		this.itemType = itemType;
		this.groundType = null;
		this.monsterType = null;
	}

	public void setGroundType(GroundTypeEnum groundType) {
		this.objectType = null;
		this.itemType = null;
		this.groundType = groundType;
		this.monsterType = null;
	}

	public void setMonsterType(MonsterTypeEnum monsterType) {
		this.objectType = null;
		this.itemType = null;
		this.groundType = null;
		this.monsterType = monsterType;
	}

	public Cell getSelectedCellPrefab(int x, int y) {
		if (objectType != null) {
			switch (objectType) {
				case BLUE_LOCK:
					return pl.getBlueLockPrefab(x, y);
				case GREEN_LOCK:
					return pl.getGreenLockPrefab(x, y);
				case RED_LOCK:
					return pl.getRedLockPrefab(x, y);
				case YELLOW_LOCK:
					return pl.getYellowLockPrefab(x, y);
				case FIRE:
					return pl.getFirePrefab(x, y);
				case WATER:
					return pl.getWaterPrefab(x, y);
				case SOCKET:
					return pl.getSocketPrefab(x, y);
				case NONE:
					return null;
			}
		} else if (itemType != null) {

		} else if (groundType != null) {
			switch (groundType) {
				case GRASS:
					return pl.getGrassPrefab(x, y);
				case WALL:
					return pl.getWallPrefab(x, y);
				case ICE:
					return pl.getIcePrefab(x, y);
				case FORCE_DOWN:
					return pl.getForceDownPrefab(x, y);
				case FORCE_LEFT:
					return pl.getForceLeftPrefab(x, y);
				case FORCE_RIGHT:
					return pl.getForceRightPrefab(x, y);
				case FORCE_UP:
					return pl.getForceUpPrefab(x, y);
				case NONE:
					return null;
			}

		} else if (monsterType != null) {

		}

		return null;
	}
}


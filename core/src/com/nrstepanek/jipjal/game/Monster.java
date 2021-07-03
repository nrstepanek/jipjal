package com.nrstepanek.jipjal.game;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.CoordHelper;
import com.nrstepanek.jipjal.GridDrawable;
import com.nrstepanek.jipjal.map.Cell;
import com.nrstepanek.jipjal.map.JipjalMap;

public class Monster extends GridDrawable {

	private MonsterTypeEnum type;

	private DirectionEnum facing;

	private float speed;
	private float timeSinceLastUpdate;
	
	public Monster(Texture t, int x, int y, MonsterTypeEnum type) {
		super(t, x, y);
		this.type = type;
		this.speed = MonsterTypeEnum.getSpeedFromType(type);
		this.facing = DirectionEnum.UP;
	}

	public MonsterTypeEnum getEnemyType() {
		return type;
	}

	public void setEnemyType(MonsterTypeEnum type) {
		this.type = type;
	}

	public float getSpeed() {
		return this.speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public DirectionEnum getFacing() {
		return this.facing;
	}

	// Returns true if it was time for the monster to update.
	public boolean update(float dt, JipjalMap map) {
		// timeSinceLastUpdate += dt;
		//if (timeSinceLastUpdate >= speed) {
		//	timeSinceLastUpdate -= speed;
			switch (type) {
			case BUG:
				bugLogic(map);
				return true;
			case NONE:
				return false;
			}
		//}

		return false;
	}

	public void bugLogic(JipjalMap map) {
		DirectionEnum leftDirection = DirectionEnum.getLeftDirection(facing);
		List<Integer> leftCellCoords = CoordHelper.getCoordsFromDirection(this.getX(), this.getY(), leftDirection);
		int leftCellX = leftCellCoords.get(0);
		int leftCellY = leftCellCoords.get(1);

		Cell newCell = map.getCell(leftCellX, leftCellY);

		// Turn left if the cell to our left is empty.
		if (!newCell.getSolid()) {
			this.facing = leftDirection;
		}

	}
}

package com.nrstepanek.jipjal.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.GridDrawable;
import com.nrstepanek.jipjal.game.DirectionEnum;
import com.nrstepanek.jipjal.game.MonsterTypeEnum;
import com.nrstepanek.jipjal.map.JipjalMap;

public class Monster extends GridDrawable {

	private MonsterTypeEnum type;

	DirectionEnum facing;

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
	public void update(float dt, JipjalMap map) {
		timeSinceLastUpdate += dt;
		if (timeSinceLastUpdate >= speed) {
			timeSinceLastUpdate -= speed;
			this.logic(map);
		}
	}

  // To be overriden by specific monster subclasses.
  void logic(JipjalMap map) { }
}

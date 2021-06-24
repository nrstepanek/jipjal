package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.GridDrawable;

public class Monster extends GridDrawable {

	private MonsterTypeEnum type;

	private float speed;
	
	public Monster(Texture t, int x, int y, MonsterTypeEnum type) {
		super(t, x, y);
		this.type = type;
		this.speed = MonsterTypeEnum.getSpeedFromType(type);
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
}

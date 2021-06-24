package com.nrstepanek.jipjal.game;

public enum MonsterTypeEnum {
	NONE,
	BUG;

	public static float getSpeedFromType(MonsterTypeEnum type) {
		switch (type) {
		case NONE:
			return 0.5f;
		case BUG:
			return 0.2f;
		}

		return 0.5f;
	}
}

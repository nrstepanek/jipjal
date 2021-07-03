package com.nrstepanek.jipjal.game;

public enum DirectionEnum {
	NONE,
	LEFT,
	UP,
	RIGHT,
	DOWN;

	public static DirectionEnum getLeftDirection(DirectionEnum direction) {
		switch (direction) {
		case LEFT:
			return DOWN;
		case DOWN:
			return RIGHT;
		case RIGHT:
			return UP;
		case UP:
			return LEFT;
		case NONE:
			return NONE;
		}

		return NONE;
	}
}

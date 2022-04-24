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

  public static DirectionEnum getRightDirection(DirectionEnum direction) {
		switch (direction) {
		case LEFT:
			return UP;
		case DOWN:
			return LEFT;
		case RIGHT:
			return DOWN;
		case UP:
			return RIGHT;
		case NONE:
			return NONE;
		}

		return NONE;
	}

  public static DirectionEnum getBackwardsDirection(DirectionEnum direction) {
		switch (direction) {
		case LEFT:
			return RIGHT;
		case DOWN:
			return UP;
		case RIGHT:
			return LEFT;
		case UP:
			return DOWN;
		case NONE:
			return NONE;
		}

		return NONE;
	}
}

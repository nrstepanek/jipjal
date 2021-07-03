package com.nrstepanek.jipjal.map;

public enum ObjectTypeEnum {
	NONE,
	BLUE_LOCK,
	GREEN_LOCK,
	RED_LOCK,
	YELLOW_LOCK,
	FIRE,
	WATER,
	SOCKET;

	public static ObjectTypeEnum fromString(String objectType) {
		switch (objectType) {
			case "blue_lock":
				return BLUE_LOCK;
			case "green_lock":
				return GREEN_LOCK;
			case "red_lock":
				return RED_LOCK;
			case "yellow_lock":
				return YELLOW_LOCK;
			case "fire":
				return FIRE;
			case "water":
				return WATER;
			case "socket":
				return SOCKET;
			default:
				return NONE;
		}
	}
}
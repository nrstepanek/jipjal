package com.nrstepanek.jipjal.map;

import java.util.ArrayList;
import java.util.List;

public enum ObjectTypeEnum {
	NONE,
	BLUE_LOCK,
	GREEN_LOCK,
	RED_LOCK,
	YELLOW_LOCK,
	FIRE,
	WATER,
	SOCKET;

	public static String toString(ObjectTypeEnum objectType) {
		switch (objectType) {
			case NONE:
				return "none";
			case BLUE_LOCK:
				return "blue_lock";
			case GREEN_LOCK:
				return "green_lock";
			case RED_LOCK:
				return "red_lock";
			case YELLOW_LOCK:
				return "yellow_lock";
			case FIRE:
				return "fire";
			case WATER:
				return "water";
			case SOCKET:
				return "socket";
		}

		return "none";
	}

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

	public static List<String> getAllStrings() {
		List<String> allList = new ArrayList<>();
		for (ObjectTypeEnum objectType : ObjectTypeEnum.values()) {
			if (objectType != NONE) {
				allList.add(toString(objectType));
			}
		}

		return allList;
	}
}
package com.nrstepanek.jipjal.game;

import java.util.ArrayList;
import java.util.List;

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

	public static String toString(MonsterTypeEnum groundType) {
		switch (groundType) {
			case NONE:
				return "";
			case BUG:
				return "bug";
			default:
				return "";
		}
	}

	public static MonsterTypeEnum fromString(String monsterType) {
		switch (monsterType) {
			case "bug":
				return BUG;
			default:
				return NONE;
		}
	}

	public static List<String> getAllStrings() {
		List<String> allList = new ArrayList<>();
		for (MonsterTypeEnum monsterType : MonsterTypeEnum.values()) {
			if (monsterType != NONE) {
				allList.add(toString(monsterType));
			}
		}

		return allList;
	}
}

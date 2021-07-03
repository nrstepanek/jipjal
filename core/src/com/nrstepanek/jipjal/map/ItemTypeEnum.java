package com.nrstepanek.jipjal.map;

public enum ItemTypeEnum {
	NONE,
	CHIP,
	BLUE_KEY,
	GREEN_KEY,
	RED_KEY,
	YELLOW_KEY;

	public static ItemTypeEnum fromString(String itemType) {
		switch (itemType) {
			case "chip":
				return CHIP;
			case "blue_key":
				return BLUE_KEY;
			case "green_key":
				return GREEN_KEY;
			case "red_key":
				return RED_KEY;
			case "yellow_key":
				return YELLOW_KEY;
			default:
				return NONE;
		}
	}
}
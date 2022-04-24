package com.nrstepanek.jipjal.map;

import java.util.ArrayList;
import java.util.List;

public enum ItemTypeEnum {
    NONE,
    CHIP,
    BLUE_KEY,
    GREEN_KEY,
    RED_KEY,
    YELLOW_KEY;

    public static String toString(ItemTypeEnum itemType) {
        switch (itemType) {
            case NONE:
                return "none";
            case CHIP:
                return "chip";
            case BLUE_KEY:
                return "blue_key";
            case GREEN_KEY:
                return "green_key";
            case RED_KEY:
                return "red_key";
            case YELLOW_KEY:
                return "yellow_key";
        }

        return "none";
    }


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

    public static List<String> getAllStrings() {
        List<String> allList = new ArrayList<>();
        for (ItemTypeEnum itemType : ItemTypeEnum.values()) {
            if (itemType != NONE) {
                allList.add(toString(itemType));
            }
        }

        return allList;
    }
}
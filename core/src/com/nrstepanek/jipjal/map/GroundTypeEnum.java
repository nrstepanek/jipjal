package com.nrstepanek.jipjal.map;

import java.util.ArrayList;
import java.util.List;

public enum GroundTypeEnum {
    NONE,
    GRASS,
    WALL,
    ICE,
    FORCE_LEFT,
    FORCE_UP,
    FORCE_RIGHT,
    FORCE_DOWN;

    public static String toString(GroundTypeEnum groundType) {
        switch (groundType) {
            case GRASS:
                return "grass";
            case WALL:
                return "wall";
            case ICE:
                return "ice";
            case FORCE_LEFT:
                return "force_left";
            case FORCE_UP:
                return "force_up";
            case FORCE_RIGHT:
                return "force_right";
            case FORCE_DOWN:
                return "force_down";
            case NONE:
                return "";
        }

        return "";
    }

    public static GroundTypeEnum fromString(String groundType) {
        switch (groundType) {
            case "grass":
                return GRASS;
            case "wall":
                return WALL;
            case "ice":
                return ICE;
            case "force_left":
                return FORCE_LEFT;
            case "force_up":
                return FORCE_UP;
            case "force_right":
                return FORCE_RIGHT;
            case "force_down":
                return FORCE_DOWN;
            default:
                return NONE;
        }
    }

    public static List<String> getAllStrings() {
        List<String> allList = new ArrayList<>();
        for (GroundTypeEnum groundType : GroundTypeEnum.values()) {
            if (groundType != NONE) {
                allList.add(toString(groundType));
            }
        }

        return allList;
    }
}

package com.nrstepanek.jipjal;

import java.util.ArrayList;
import java.util.List;

import com.nrstepanek.jipjal.game.DirectionEnum;

public final class CoordHelper {
	
	private CoordHelper() {}

	public static DirectionEnum getDirectionFromCoords(int oldX, int oldY, int newX, int newY) {
		if (newX - oldX == 1 && newY == oldY) {
			return DirectionEnum.RIGHT;
		}
		if (newX - oldX == -1 && newY == oldY) {
			return DirectionEnum.LEFT;
		}
		if (newX == oldX && newY - oldY == 1) {
			return DirectionEnum.UP;
		}
		if (newX == oldX && newY - oldY == -1) {
			return DirectionEnum.DOWN;
		}

		return DirectionEnum.NONE;
	}

	public static List<Integer> getCoordsFromDirection(int oldX, int oldY, DirectionEnum direction) {
		int newX = oldX;
		int newY = oldY;

		switch (direction) {
			case DOWN:
				newY -= 1;
				break;
			case RIGHT:
				newX += 1;
				break;
			case LEFT:
				newX -= 1;
				break;
			case UP:
				newY += 1;
				break;
			case NONE:

		}

		List<Integer> coords = new ArrayList<>();
		coords.add(newX);
		coords.add(newY);

		return coords;
	}
}

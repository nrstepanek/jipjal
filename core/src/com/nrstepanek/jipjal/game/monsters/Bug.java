package com.nrstepanek.jipjal.game.monsters;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.CoordHelper;
import com.nrstepanek.jipjal.game.DirectionEnum;
import com.nrstepanek.jipjal.game.MonsterTypeEnum;
import com.nrstepanek.jipjal.map.Cell;
import com.nrstepanek.jipjal.map.JipjalMap;

public class Bug extends Monster {
  public Bug(Texture t, int x, int y) {
    super(t, x, y, MonsterTypeEnum.BUG);
  }

  @Override
  void logic(JipjalMap map) {
    DirectionEnum leftDirection = DirectionEnum.getLeftDirection(facing);

		List<Integer> leftCellCoords = CoordHelper.getCoordsFromDirection(this.getX(), this.getY(), leftDirection);
		Cell leftCell = map.getCell(leftCellCoords);
		// Turn left if the cell to our left is empty.
		if (!leftCell.getSolid()) {
			this.facing = leftDirection;
		}
    else {
      // Otherwise, make sure the cell ahead of us is empty.
      List<Integer> forwardCellCoords = CoordHelper.getCoordsFromDirection(this.getX(), this.getY(), facing);
      Cell forwardCell = map.getCell(forwardCellCoords);
      // If it isn't, try turning right.
      if (forwardCell.getSolid()) {
        DirectionEnum rightDirection = DirectionEnum.getRightDirection(facing);
        List<Integer> rightCellCoords = CoordHelper.getCoordsFromDirection(this.getX(), this.getY(), rightDirection);
        Cell rightCell = map.getCell(rightCellCoords);
        // If we can't turn right either, turn around.
        if (rightCell.getSolid()) {
          DirectionEnum backwardsDirection = DirectionEnum.getBackwardsDirection(facing);
          this.facing = backwardsDirection;
        }
        else {
          this.facing = rightDirection;
        }
      }
    }

    // Finally, turn around.

    int oldX = this.getX();
		int oldY = this.getY();
		List<Integer> newCoords = CoordHelper.getCoordsFromDirection(oldX, oldY, this.getFacing());
		this.setPosition(newCoords.get(0), newCoords.get(1));
  }
}

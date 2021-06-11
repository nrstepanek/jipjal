package com.nrstepanek.jipjal;

public class JipjalLogic {

	Player player;
	GameMap gameMap;

	public JipjalLogic(Player player, GameMap gameMap) {
		this.player = player;
		this.gameMap = gameMap;
	}

	public void playerMove(int direction) {
		int newX = player.getX();
		int newY = player.getY();

		if (direction == 0)
            newX--;
        else if (direction == 2)
            newX++;
        else if (direction == 1)
            newY++;
        else if (direction == 3)
            newY--;

		Cell newCell = gameMap.getCell(newX, newY);

		if (!newCell.getSolid()) {
			System.out.println("Moving");
			player.setPosition(newX, newY);
		}
	}
}
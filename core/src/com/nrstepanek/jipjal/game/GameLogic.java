package com.nrstepanek.jipjal.game;

import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.map.Cell;
import com.nrstepanek.jipjal.map.JipjalMap;
import com.nrstepanek.jipjal.map.ItemType;
import com.nrstepanek.jipjal.map.ObjectType;
import com.nrstepanek.jipjal.menu.MenuScreen;

public class GameLogic {

	Player player;
	JipjalMap gameMap;
	JipjalGame game;

	public GameLogic(Player player, JipjalMap gameMap, JipjalGame game) {
		this.player = player;
		this.gameMap = gameMap;
		this.game = game;
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

		if (newCell.hasItem()) {
			player.inventory.addItem(newCell.getItem());
			newCell.removeItem();
		}

		// Check death
		if (newCell.getDangerous()) {
			game.setScreen(new MenuScreen(game));
		}

		// Check goal
		if (newCell.getIsGoal()) {
			game.setScreen(new MenuScreen(game));
		}

		if (!newCell.getSolid()) {
			player.setPosition(newX, newY);
		} else {
			// DOOR CHECK BEGIN
			if (doorLogic(newCell)) {
				player.setPosition(newX, newY);
			}
		}
	}

	// Returns true if a door was unlocked.
	public boolean doorLogic(Cell newCell) {
		boolean unlockDoor = false;
		ItemType keyType = null;
		if (newCell.getObjectType() == ObjectType.BLUE_LOCK) {
			if (player.hasItem(ItemType.BLUE_KEY)) {
				unlockDoor = true;
				keyType = ItemType.BLUE_KEY;
			}
		}
		if (newCell.getObjectType() == ObjectType.GREEN_LOCK) {
			if (player.hasItem(ItemType.GREEN_KEY)) {
				unlockDoor = true;
				keyType = ItemType.GREEN_KEY;
			}
		}
		if (newCell.getObjectType() == ObjectType.RED_LOCK) {
			if (player.hasItem(ItemType.RED_KEY)) {
				unlockDoor = true;
				keyType = ItemType.RED_KEY;
			}
		}
		if (newCell.getObjectType() == ObjectType.YELLOW_LOCK) {
			if (player.hasItem(ItemType.YELLOW_KEY)) {
				unlockDoor = true;
				keyType = ItemType.YELLOW_KEY;
			}
		}

		if (unlockDoor) {
			int keyIndex = player.getItemIndex(keyType);
			player.removeItemAtIndex(keyIndex);
			newCell.destroyObject();
			return true;
		}

		return false;
	}
}
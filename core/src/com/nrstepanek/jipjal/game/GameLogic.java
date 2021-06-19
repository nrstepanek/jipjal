package com.nrstepanek.jipjal.game;

import com.nrstepanek.jipjal.map.Cell;
import com.nrstepanek.jipjal.map.GroundTypeEnum;
import com.nrstepanek.jipjal.map.JipjalMap;
import com.nrstepanek.jipjal.map.ItemTypeEnum;
import com.nrstepanek.jipjal.map.ObjectTypeEnum;

public class GameLogic {

	Player player;
	JipjalMap gameMap;
	GameScreen gameScreen;

	public GameLogic(Player player, JipjalMap gameMap, GameScreen gameScreen) {
		this.player = player;
		this.gameMap = gameMap;
		this.gameScreen = gameScreen;
	}

	// Returns whether the game is awaiting input.
	public boolean canMove() {
		return !player.getSliding();
	}

	// Updates time based state for all entities in the game.
	public void updateEntities() {
		if (player.getSliding()) {
			playerMove(player.getSlipDirection());
		}
	}

	public void playerMove(DirectionEnum direction) {
		int newX = player.getX();
		int newY = player.getY();

		if (direction == DirectionEnum.LEFT)
            newX--;
        else if (direction == DirectionEnum.RIGHT)
            newX++;
        else if (direction == DirectionEnum.UP)
            newY++;
        else if (direction == DirectionEnum.DOWN)
            newY--;

		Cell newCell = gameMap.getCell(newX, newY);

		if (newCell.hasItem()) {
			player.inventory.addItem(newCell.getItem());
			newCell.removeItem();
		}

		// Check death
		if (newCell.getDangerous()) {
			gameScreen.gameOver();
		}

		// Check goal
		if (newCell.getIsGoal()) {
			gameScreen.gameOver();
		}

		if (!newCell.getSolid()) {
			movePlayer(newX, newY);
		} else {
			// DOOR CHECK BEGIN
			if (doorLogic(newCell)) {
				movePlayer(newX, newY);
			}
		}
	}

	public void movePlayer(int newX, int newY) {
		int oldX = player.getX();
		int oldY = player.getY();
		player.setPosition(newX, newY);
		slidingLogic(oldX, oldY);
		gameScreen.updateCamera();
	}

	public void slidingLogic(int oldX, int oldY) {
		Cell playerCell = gameMap.getCell(player.getX(), player.getY());
		if (playerCell.getGroundType() == GroundTypeEnum.ICE) {
			player.setSliding(true);
			player.setForceSliding(false);
			player.setSlipDirection(getDirectionFromCoords(oldX, oldY, player.getX(), player.getY()));
		} else if (playerCell.isForceTile()) {
			player.setSliding(true);
			player.setForceSliding(true);
			player.setSlipDirection(getDirectionFromForceTile(playerCell.getGroundType()));
		} else {
			if (player.getForceSliding()) {
				Cell nextCell = getCellInDirection(player.getX(), player.getY(), player.getSlipDirection());
				if (nextCell.getSolid()) {
					player.setSliding(false);
					player.setForceSliding(false);
					player.setSlipDirection(DirectionEnum.NONE);
				}
			}
			else {
				player.setSliding(false);
				player.setForceSliding(false);
				player.setSlipDirection(DirectionEnum.NONE);
			}
		}
	}

	// Returns true if a door was unlocked.
	public boolean doorLogic(Cell newCell) {
		boolean unlockDoor = false;
		ItemTypeEnum keyType = null;
		if (newCell.getObjectType() == ObjectTypeEnum.BLUE_LOCK) {
			if (player.hasItem(ItemTypeEnum.BLUE_KEY)) {
				unlockDoor = true;
				keyType = ItemTypeEnum.BLUE_KEY;
			}
		}
		if (newCell.getObjectType() == ObjectTypeEnum.GREEN_LOCK) {
			if (player.hasItem(ItemTypeEnum.GREEN_KEY)) {
				unlockDoor = true;
				keyType = ItemTypeEnum.GREEN_KEY;
			}
		}
		if (newCell.getObjectType() == ObjectTypeEnum.RED_LOCK) {
			if (player.hasItem(ItemTypeEnum.RED_KEY)) {
				unlockDoor = true;
				keyType = ItemTypeEnum.RED_KEY;
			}
		}
		if (newCell.getObjectType() == ObjectTypeEnum.YELLOW_LOCK) {
			if (player.hasItem(ItemTypeEnum.YELLOW_KEY)) {
				unlockDoor = true;
				keyType = ItemTypeEnum.YELLOW_KEY;
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

	public DirectionEnum getDirectionFromCoords(int oldX, int oldY, int newX, int newY) {
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

	public DirectionEnum getDirectionFromForceTile(GroundTypeEnum groundType) {
		if (groundType == GroundTypeEnum.FORCE_DOWN) {
			return DirectionEnum.DOWN;
		}
		if (groundType == GroundTypeEnum.FORCE_UP) {
			return DirectionEnum.UP;
		}
		if (groundType == GroundTypeEnum.FORCE_RIGHT) {
			return DirectionEnum.RIGHT;
		}
		if (groundType == GroundTypeEnum.FORCE_LEFT) {
			return DirectionEnum.LEFT;
		}

		return DirectionEnum.NONE;
	}

	public Cell getCellInDirection(int x, int y, DirectionEnum direction) {
		if (direction == DirectionEnum.RIGHT) {
			x += 1;
		}
		if (direction == DirectionEnum.LEFT) {
			x -= 1;
		}
		if (direction == DirectionEnum.DOWN) {
			y -= 1;
		}
		if (direction == DirectionEnum.UP) {
			y += 1;
		}

		return gameMap.getCell(x, y);
	}
}
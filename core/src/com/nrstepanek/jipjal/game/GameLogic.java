package com.nrstepanek.jipjal.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.CoordHelper;
import com.nrstepanek.jipjal.game.monsters.Monster;
import com.nrstepanek.jipjal.map.Cell;
import com.nrstepanek.jipjal.map.GroundTypeEnum;
import com.nrstepanek.jipjal.map.Item;
import com.nrstepanek.jipjal.map.JipjalMap;
import com.nrstepanek.jipjal.map.ItemTypeEnum;
import com.nrstepanek.jipjal.map.ObjectTypeEnum;

public class GameLogic {

	Player player;
	JipjalMap gameMap;
	GameScreen gameScreen;

	float timeSinceLastSlideUpdate = 0f;

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
	public void updateEntities(float dt) {
		timeSinceLastSlideUpdate += dt;
		if (timeSinceLastSlideUpdate >= Configuration.SLIDE_SPEED)
		{
			timeSinceLastSlideUpdate -= Configuration.SLIDE_SPEED;
			if (player.getSliding()) {
				playerMove(player.getSlipDirection());
			}
		}

		List<Monster> monsters = gameMap.getMonsters();
		for (Monster monster : monsters) {
			monster.update(dt, this.gameMap);
		}

		if (checkPlayerMonsterCollission()) {
			gameScreen.gameOver();
		}
	}

	public void pause() {
		Skin uiSkin = new Skin(Gdx.files.internal(Configuration.SKIN_FILE_LOCATION));
		PauseDialog pauseDialog = new PauseDialog("Warning", uiSkin, gameScreen);
		pauseDialog.show(gameScreen.menuStage);
		Gdx.input.setInputProcessor(gameScreen.menuStage);
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

		// Don't do anything if the new cell is out of bounds.
		if (!gameMap.isWithinBounds(newX, newY)) {
			return ;
		}

		Cell newCell = gameMap.getCell(newX, newY);

		if (newCell.hasItem()) {
			Item item = newCell.getItem();
			if (item.getItemType() == ItemTypeEnum.CHIP) {
				player.getInventory().addChip();
			} else {
				player.getInventory().addItem(item);
			}
			newCell.removeItem();
		}

		// Check death
		if (newCell.getDangerous()) {
			gameScreen.gameOver();
		}
		if (gameMap.hasMonster(newX, newY)) {
			gameScreen.gameOver();
		}

		// Check goal
		if (newCell.getIsGoal()) {
			gameScreen.gameOver();
		}

		if (!newCell.getSolid()) {
			movePlayer(newX, newY);
		} else {
			if (doorLogic(newCell)) {
				movePlayer(newX, newY);
			}
			else if (socketLogic(newCell)) {
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
			player.setSlipDirection(CoordHelper.getDirectionFromCoords(oldX, oldY, player.getX(), player.getY()));
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

	private boolean socketLogic(Cell newCell) {
		if (newCell.getObjectType() == ObjectTypeEnum.SOCKET) {
			if (player.getInventory().getChips() >= gameMap.getSocketThreshold()) {
				newCell.destroyObject();
				return true;
			}
		}

		return false;
	}

	public boolean checkPlayerMonsterCollission() {
		for (Monster monster : gameMap.getMonsters()) {
			if (player.getX() == monster.getX() && player.getY() == monster.getY())
				return true;
		}

		return false;
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
package com.nrstepanek.jipjal.map;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.io.Serializable;
import java.lang.Math;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nrstepanek.jipjal.TextureHolder;
import com.nrstepanek.jipjal.game.Monster;
import com.nrstepanek.jipjal.game.MonsterTypeEnum;

public class JipjalMap implements Serializable {

    private int width;
    private int height;

	private int playerStartX;
	private int playerStartY;

	private int socketThreshold;

    private Map<Integer, Cell> cellMap;

	private List<Monster> monsters;

    private TextureHolder th;

    public JipjalMap(int width, int height, TextureHolder textureHolder, boolean random) {
        this.width = width;
        this.height = height;
        this.th = textureHolder;
        this.cellMap = new HashMap<>();
		this.monsters = new ArrayList<>();
		if (random) {
        	randomGenerate();
		}
    }

	public JipjalMap(TextureHolder textureHolder) {
		this.width = 10;
		this.height = 10;
		this.th = textureHolder;
		this.cellMap = new HashMap<>();
		this.socketThreshold = 2;
		this.monsters = new ArrayList<>();
		generateTestMap();
	}

    public void randomGenerate() {
        Random rand = new Random();

        for (int i = 0; i < width * height; i++) {
            Texture texture;
            boolean solid = false;

            if (rand.nextFloat() > 0.1f) {
                texture = th.getTexture("grass");
            } else {
                texture = th.getTexture("wall");
                solid = true;
            }

            Cell newCell = new Cell(texture, i % width, (int) Math.floor(i / height));
			newCell.setSolid(solid);

            cellMap.put(i, newCell);
        }
    }

	public void generateTestMap() {
		Cell cell1 = new Cell(th.getTexture("wall"), 0, 0);
		cell1.setSolid(true);
		addToCellMap(cell1);
		Cell cell2 = new Cell(th.getTexture("wall"), 0, 1);
		cell2.setSolid(true);
		addToCellMap(cell2);
		Cell cell3 = new Cell(th.getTexture("wall"), 1, 0);
		cell3.setSolid(true);
		addToCellMap(cell3);

		Cell cell4 = new Cell(th.getTexture("grass"), 5, 5);
		Item blueKeyItem = new Item(ItemTypeEnum.BLUE_KEY);
		cell4.addItem(blueKeyItem, th.getTextureFromItemType(ItemTypeEnum.BLUE_KEY));
		addToCellMap(cell4);

		Cell cell5 = new Cell(th.getTexture("grass"), 1, 5);
		cell5.setSolid(true);
		cell5.setObjectType(ObjectTypeEnum.BLUE_LOCK, th.getTextureFromObjectType(ObjectTypeEnum.BLUE_LOCK));
		addToCellMap(cell5);

		Cell fireCell = new Cell(th.getTexture("grass"), 1, 6);
		fireCell.setDangerous(true);
		fireCell.setObjectType(ObjectTypeEnum.FIRE, th.getTextureFromObjectType(ObjectTypeEnum.FIRE));
		addToCellMap(fireCell);

		Cell waterCell = new Cell(th.getTexture("grass"), 0, 6);
		waterCell.setDangerous(true);
		waterCell.setObjectType(ObjectTypeEnum.WATER, th.getTextureFromObjectType(ObjectTypeEnum.WATER));
		addToCellMap(waterCell);

		Cell goalCell = new Cell (th.getTexture("goal"), 5, 8);
		goalCell.setIsGoal(true);
		addToCellMap(goalCell);

		Cell iceCell = new Cell(th.getTexture("ice"), 3, 1);
		iceCell.setGroundType(GroundTypeEnum.ICE);
		addToCellMap(iceCell);
		Cell iceCell2 = new Cell(th.getTexture("ice"), 4, 1);
		iceCell2.setGroundType(GroundTypeEnum.ICE);
		addToCellMap(iceCell2);

		Cell forceUpCell = new Cell(th.getTexture("force_up"), 5, 0);
		forceUpCell.setGroundType(GroundTypeEnum.FORCE_UP);
		addToCellMap(forceUpCell);
		Cell forceLeftCell = new Cell(th.getTexture("force_left"), 9, 0);
		forceLeftCell.setGroundType(GroundTypeEnum.FORCE_LEFT);
		addToCellMap(forceLeftCell);
		Cell forceDownCell = new Cell(th.getTexture("force_down"), 9, 4);
		forceDownCell.setGroundType(GroundTypeEnum.FORCE_DOWN);
		addToCellMap(forceDownCell);
		Cell forceRightCell = new Cell(th.getTexture("force_right"), 6, 4);
		forceRightCell.setGroundType(GroundTypeEnum.FORCE_RIGHT);
		addToCellMap(forceRightCell);

		Cell forceLeftCell2 = new Cell(th.getTexture("force_left"), 8, 5);
		forceLeftCell2.setGroundType(GroundTypeEnum.FORCE_LEFT);
		addToCellMap(forceLeftCell2);

		for (int i = 0; i < width * height; i++) {
			if (!cellMap.containsKey(i)) {
				Cell newCell = new Cell(th.getTexture("grass"), i % width, (int) Math.floor(i / height));
				addToCellMap(newCell);
			}
		}

		Cell chipCell = new Cell(th.getTexture("grass"), 1, 8);
		Item chip = new Item(ItemTypeEnum.CHIP);
		chipCell.addItem(chip, th.getTextureFromItemType(ItemTypeEnum.CHIP));
		addToCellMap(chipCell);

		Cell chipCell2 = new Cell(th.getTexture("grass"), 1, 9);
		Item chip2 = new Item(ItemTypeEnum.CHIP);
		chipCell2.addItem(chip2, th.getTextureFromItemType(ItemTypeEnum.CHIP));
		addToCellMap(chipCell2);

		Cell socketCell = new Cell(th.getTexture("grass"), 4, 8);
		socketCell.setSolid(true);
		socketCell.setObjectType(ObjectTypeEnum.SOCKET, th.getTextureFromObjectType(ObjectTypeEnum.SOCKET));
		addToCellMap(socketCell);

		Monster bug = new Monster(th.getTextureFromMonsterType(MonsterTypeEnum.BUG), 4, 4, MonsterTypeEnum.BUG);
		addMonster(bug);

		this.playerStartX = 1;
		this.playerStartY = 1;
	}

	public void addToCellMap(Cell cell) {
		cellMap.put(getCellLoc(cell), cell);
	}

	public void removeCellAt(int x, int y) {
		int cellId = getCellId(x, y);
		cellMap.remove(cellId);
	}

    public void drawCells(SpriteBatch batch) {
        for (Cell cell : cellMap.values()) {
            cell.getSprite().draw(batch);
        }
    }

	// Fills in all empty cell locations with grass cells.
	public void fillIn() {
		for (int i = 0; i < width * height; i++) {
			if (!cellMap.containsKey(i)) {
				Cell newCell = new Cell(th.getTexture("grass"), i % width, (int) Math.floor(i / height));
				addToCellMap(newCell);
			}
		}
	}

	public int getCellLoc(Cell cell) {
		return cell.getX() + cell.getY() * this.width;
	}

	// Converts 2d coords to 1d map location.
	public int getCellId(int x, int y) {
		return y * width + x;
	}

	public Cell getCell(int x, int y) {
		return cellMap.get(getCellId(x, y));
	}

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

	public boolean isWithinBounds(int x, int y) {
		if (x < this.width && x >= 0 && y < this.height && y >= 0)
			return true;
		return false;
	}

	public int getSocketThreshold() {
		return this.socketThreshold;
	}
	
	public void setSocketThreshold(int socketThreshold) {
		this.socketThreshold = socketThreshold;
	}

	public List<Monster> getMonsters() {
		return this.monsters;
	}

	public void addMonster(Monster monster) {
		this.monsters.add(monster);
	}

	// Check if the cell at (x,y) has a monster.
	public boolean hasMonster(int x, int y) {
		for (Monster monster : monsters) {
			if (monster.getX() == x && monster.getY() == y)
				return true;
		}

		return false;
	}

	public void setPlayerStartX(int x) {
		this.playerStartX = x;
	}

	public int getPlayerStartX() {
		return this.playerStartX;
	}

	public void setPlayerStartY(int y) {
		this.playerStartY = y;
	}

	public int getPlayerStartY() {
		return this.playerStartY;
	}
}
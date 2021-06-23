package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.GridDrawable;
import com.nrstepanek.jipjal.map.Item;
import com.nrstepanek.jipjal.map.ItemTypeEnum;

public class Player extends GridDrawable {

	private Inventory inventory;

	private boolean sliding;
	private boolean forceSliding;
	private DirectionEnum slipDirection;

    public Player(Texture t, int x, int y) {
        super(t, x, y);
		this.inventory = new Inventory();
    }

	public boolean hasItem(ItemTypeEnum itemType) {
		return inventory.hasItem(itemType);
	}

	public int getItemIndex(ItemTypeEnum itemType) {
		return inventory.getItemIndex(itemType);
	}

	public Item removeItemAtIndex(int index) {
		return inventory.removeItemAtIndex(index);
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public boolean getSliding() {
		return this.sliding;
	}

	public void setSliding(boolean sliding) {
		this.sliding = sliding;
	}

	public boolean getForceSliding() {
		return this.forceSliding;
	}

	public void setForceSliding(boolean forceSliding) {
		this.forceSliding = forceSliding;
	}

	public DirectionEnum getSlipDirection() {
		return this.slipDirection;
	}

	public void setSlipDirection(DirectionEnum slipDirection) {
		this.slipDirection = slipDirection;
	}
}
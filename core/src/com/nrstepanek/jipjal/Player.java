package com.nrstepanek.jipjal;

import com.badlogic.gdx.graphics.Texture;

public class Player extends GridDrawable {

	Inventory inventory;

    public Player(Texture t, int x, int y) {
        super(t, x, y);
		this.inventory = new Inventory();
    }

	public boolean hasItem(ItemType itemType) {
		return inventory.hasItem(itemType);
	}

	public int getItemIndex(ItemType itemType) {
		return inventory.getItemIndex(itemType);
	}

	public Item removeItemAtIndex(int index) {
		return inventory.removeItemAtIndex(index);
	}
}
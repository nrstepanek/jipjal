package com.nrstepanek.jipjal;

public class Item {

	ItemType itemType;

	public Item(ItemType itemType) {
		this.itemType = itemType;
	}

	public ItemType getItemType() {
		return this.itemType;
	}
}

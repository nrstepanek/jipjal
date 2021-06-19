package com.nrstepanek.jipjal.map;

public class Item {

	ItemTypeEnum itemType;

	public Item(ItemTypeEnum itemType) {
		this.itemType = itemType;
	}

	public ItemTypeEnum getItemType() {
		return this.itemType;
	}
}

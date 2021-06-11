package com.nrstepanek.jipjal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cell extends GridDrawable {

    private boolean solid;
	private ObjectType objectType;

	private Sprite emptySprite;

	private Item item;

    public Cell(Texture t, int x, int y) {
        super(t, x, y);
        this.solid = false;
		this.objectType = ObjectType.NONE;
    }

    public boolean getSolid() {
        return this.solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

	public ObjectType getObjectType() {
		return this.objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}

	public void addItem(Item item, Texture texture) {
		this.item = item;
		this.emptySprite = this.getSprite();
		this.setSprite(new Sprite(texture));
	}

	public Item removeItem() {
		Item temp = this.item;
		this.setSprite(this.emptySprite);
		this.item = null;
		return temp;
	}

	public Item getItem() {
		return this.item;
	}

	public boolean hasItem() {
		return this.item != null;
	}
}

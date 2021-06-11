package com.nrstepanek.jipjal;

import com.badlogic.gdx.graphics.Texture;

public class Cell extends GridDrawable {

    private boolean solid;
	private ObjectType objectType;

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
}

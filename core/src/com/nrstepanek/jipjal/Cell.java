package com.nrstepanek.jipjal;

import com.badlogic.gdx.graphics.Texture;

public class Cell extends GridDrawable {

    private boolean solid;

    public Cell(Texture t, int x, int y) {
        super(t, x, y);
        this.solid = false;
    }

    public boolean getSolid() {
        return this.solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
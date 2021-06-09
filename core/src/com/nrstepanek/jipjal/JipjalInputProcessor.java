package com.nrstepanek.jipjal;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

import com.nrstepanek.jipjal.Configuration;

public class JipjalInputProcessor extends InputAdapter {

    private Player player;

    public JipjalInputProcessor(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.LEFT)
            player.setX(player.getX() - Configuration.GRID_WIDTH);
        else if (keycode == Keys.RIGHT)
            player.setX(player.getX() + Configuration.GRID_WIDTH);
        else if (keycode == Keys.UP)
            player.setY(player.getY() + Configuration.GRID_HEIGHT);
        else if (keycode == Keys.DOWN)
            player.setY(player.getY() - Configuration.GRID_HEIGHT);

        return true;
    }
}
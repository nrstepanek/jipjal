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
            player.setX(player.getX() - 1);
        else if (keycode == Keys.RIGHT)
            player.setX(player.getX() + 1);
        else if (keycode == Keys.UP)
            player.setY(player.getY() + 1);
        else if (keycode == Keys.DOWN)
            player.setY(player.getY() - 1);

        return true;
    }
}
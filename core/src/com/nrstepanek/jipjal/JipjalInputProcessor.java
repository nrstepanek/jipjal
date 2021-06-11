package com.nrstepanek.jipjal;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

import com.nrstepanek.jipjal.Configuration;

public class JipjalInputProcessor extends InputAdapter {

    JipjalLogic logic;

    public JipjalInputProcessor(JipjalLogic logic) {
        this.logic = logic;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.LEFT)
            logic.playerMove(0);
        else if (keycode == Keys.RIGHT)
            logic.playerMove(2);
        else if (keycode == Keys.UP)
            logic.playerMove(1);
        else if (keycode == Keys.DOWN)
            logic.playerMove(3);

        return true;
    }
}
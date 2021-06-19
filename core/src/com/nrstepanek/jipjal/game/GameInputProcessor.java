package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class GameInputProcessor extends InputAdapter {

    GameLogic logic;

    public GameInputProcessor(GameLogic logic) {
        this.logic = logic;
    }

    @Override
    public boolean keyDown(int keycode) {
		if (logic.canMove()) {
        	if (keycode == Keys.LEFT)
            	logic.playerMove(DirectionEnum.LEFT);
        	else if (keycode == Keys.RIGHT)
            	logic.playerMove(DirectionEnum.RIGHT);
        	else if (keycode == Keys.UP)
            	logic.playerMove(DirectionEnum.UP);
        	else if (keycode == Keys.DOWN)
            	logic.playerMove(DirectionEnum.DOWN);
		}

        return true;
    }
}
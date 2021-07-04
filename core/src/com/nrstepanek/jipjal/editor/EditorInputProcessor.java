package com.nrstepanek.jipjal.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.nrstepanek.jipjal.map.JipjalMap;

public class EditorInputProcessor extends InputAdapter {

	JipjalMap map;

	EditorLogic logic;

	public EditorInputProcessor(EditorLogic logic) {
		this.logic = logic;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			logic.convertScreenCoords(screenX, screenY);
		}

		return true;
	}

	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Input.Keys.LEFT) {
			logic.leftDown = true;
		}
		if (keyCode == Input.Keys.RIGHT) {
			logic.rightDown = true;
		}
		if (keyCode == Input.Keys.DOWN) {
			logic.downDown = true;
		}
		if (keyCode == Input.Keys.UP) {
			logic.upDown = true;
		}

		return true;
	}

	@Override
	public boolean keyUp(int keyCode) {
		if (keyCode == Input.Keys.LEFT) {
			logic.leftDown = false;
		}
		if (keyCode == Input.Keys.RIGHT) {
			logic.rightDown = false;
		}
		if (keyCode == Input.Keys.DOWN) {
			logic.downDown = false;
		}
		if (keyCode == Input.Keys.UP) {
			logic.upDown = false;
		}

		return true;
	}

}
package com.nrstepanek.jipjal.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.nrstepanek.jipjal.map.JipjalMap;

public class EditorInputProcessor extends InputAdapter {

	JipjalMap map;
	EditorLogic logic;
	EditorState state;

	public EditorInputProcessor(EditorLogic logic, EditorState editorState) {
		this.logic = logic;
		this.state = editorState;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			logic.modifyCell(screenX, screenY);
		}

		return true;
	}

	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Input.Keys.LEFT) {
			state.leftDown = true;
		}
		if (keyCode == Input.Keys.RIGHT) {
			state.rightDown = true;
		}
		if (keyCode == Input.Keys.DOWN) {
			state.downDown = true;
		}
		if (keyCode == Input.Keys.UP) {
			state.upDown = true;
		}

		return true;
	}

	@Override
	public boolean keyUp(int keyCode) {
		if (keyCode == Input.Keys.LEFT) {
			state.leftDown = false;
		}
		if (keyCode == Input.Keys.RIGHT) {
			state.rightDown = false;
		}
		if (keyCode == Input.Keys.DOWN) {
			state.downDown = false;
		}
		if (keyCode == Input.Keys.UP) {
			state.upDown = false;
		}

		return true;
	}

}
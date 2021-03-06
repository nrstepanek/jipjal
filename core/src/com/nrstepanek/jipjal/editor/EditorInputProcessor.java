package com.nrstepanek.jipjal.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.nrstepanek.jipjal.map.GroundTypeEnum;
import com.nrstepanek.jipjal.map.ObjectTypeEnum;

public class EditorInputProcessor extends InputAdapter {

    EditorLogic logic;
    EditorState state;
    SelectorTable selectorTable;

    public EditorInputProcessor(EditorLogic logic, EditorState editorState, SelectorTable selectorTable) {
        this.logic = logic;
        this.state = editorState;
        this.selectorTable = selectorTable;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!selectorTable.checkClick(screenX, screenY, state)) {
            if (button == Input.Buttons.LEFT) {
                logic.modifyCell(screenX, screenY);
            }
        }

        return true;
    }

    @Override
    public boolean keyDown(int keyCode) {
        // ARROW KEYS
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

        // MODIFIER KEYS
        if (keyCode == Input.Keys.SHIFT_LEFT || keyCode == Input.Keys.SHIFT_RIGHT) {
            state.shiftDown = true;
        }

        // GROUND TYPES
        if (keyCode == Input.Keys.ESCAPE) {
            if (state.selectedCell.onGrass()) {
                logic.pause();
            }
            else {
                state.selectedCell.setGroundType(GroundTypeEnum.GRASS);
            }
        }
        if (keyCode == Input.Keys.W) {
            state.selectedCell.setGroundType(GroundTypeEnum.WALL);
        }
        if (keyCode == Input.Keys.I) {
            state.selectedCell.setGroundType(GroundTypeEnum.ICE);
        }


        if (keyCode == Input.Keys.F) {
            state.selectedCell.setObjectType(ObjectTypeEnum.FIRE);
        }

        // Player start.
        if (keyCode == Input.Keys.P) {
            state.selectedCell.setPlayerStart();
        }

        // Save.
        if (keyCode == Input.Keys.S) {
            logic.openSaveDialog();
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
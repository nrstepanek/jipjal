package com.nrstepanek.jipjal.editor;

import com.nrstepanek.jipjal.TextureHolder;

public class EditorState {
    // Keeps track of whether arrow keys are pressed or not.
    boolean leftDown;
    boolean rightDown;
    boolean upDown;
    boolean downDown;

    boolean shiftDown;

    SelectedCellHelper selectedCell;

    public EditorState(TextureHolder th) {
        this.selectedCell = new SelectedCellHelper(th);
    }
}

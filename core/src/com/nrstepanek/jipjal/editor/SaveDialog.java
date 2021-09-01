package com.nrstepanek.jipjal.editor;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SaveDialog extends Dialog {

	private EditorScreen editorScreen;

	private TextField mapNameField;

	public SaveDialog(Skin skin, EditorScreen editorScreen) {
		super("Save", skin);
		this.editorScreen = editorScreen;
		mapNameField = new TextField("map name", skin);
		this.add(mapNameField);
		this.button("Save", true);
		this.button("Cancel", false);
	}

	@Override
	protected void result(Object object) {
		super.result(object);
		if (object.equals(true)) {
			this.remove();
			editorScreen.saveMap(mapNameField.getText());
		}
		if (object.equals(false)) {
			this.remove();
			editorScreen.setInputProcessor();
		}
	}
}

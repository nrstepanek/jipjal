package com.nrstepanek.jipjal.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ExitDialog extends Dialog {

	EditorScreen editorScreen;

	public ExitDialog(String title, Skin skin, EditorScreen editorScreen) {
		super(title, skin);
		this.text("Are you sure you want to quit?");
		this.button("Yes", true);
		this.button("No", false);
		this.key(Input.Keys.ENTER, true);

		this.editorScreen = editorScreen;
	}

	@Override
	protected void result(Object object) {
		super.result(object);
		System.out.println(object);
		if (object.equals(true)) {
			editorScreen.mainMenu();
		}
		else if (object.equals(false)) {
			this.remove();
			editorScreen.setInputProcessor();
		}
	}
}

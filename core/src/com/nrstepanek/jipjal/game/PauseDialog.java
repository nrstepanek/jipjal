package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PauseDialog extends Dialog {

    GameScreen gameScreen;

    public PauseDialog(String title, Skin skin, GameScreen gameScreen) {
        super(title, skin);
        this.text("Pause");
        this.button("Restart", "restart");
        this.button("Main Menu", "main menu");
        this.button("Cancel", "cancel");

        this.gameScreen = gameScreen;
    }

    @Override
    protected void result(Object object) {
        super.result(object);
        if (object.equals("restart")) {
            gameScreen.restart();
        }
        else if (object.equals("cancel")) {
            this.remove();
            gameScreen.setInputProcessor();
        }
        else if (object.equals("main menu")) {
            gameScreen.mainMenu();
        }
    }
}

package com.nintendont.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DialogClass extends ScreenAdapter {
    private Stage stage;
    private Skin skin;

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage = new Stage());
        skin = new Skin();

        new Dialog("Test", skin)
        {
            {
                text("Test dialog");
                button("YES");
                button("NO");
            }
        }.show(stage);
    }
}
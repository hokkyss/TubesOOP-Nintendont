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

        StarterDialog starterDialog = new StarterDialog("Test",skin);
        starterDialog.show(stage);
    }

    public static class StarterDialog extends Dialog{

        public StarterDialog(String title, Skin skin){
            super(title,skin);
        }

        {
            text("Test dialog");
            button("Yes");
            button("No");
        }

        public void result(Object object){
        }

    }
}
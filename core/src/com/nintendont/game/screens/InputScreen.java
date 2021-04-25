package com.nintendont.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.nintendont.game.util.OnSubmitHandler;
import java.awt.event.KeyEvent;

public class InputScreen extends OverlayScreen {
    private Label label;
    private TextField input;
    private OnSubmitHandler onSubmitHandler;

    public InputScreen(String label, OnSubmitHandler onSubmitHandler) { this(720f, 150f, label, onSubmitHandler); }

    public InputScreen(float w, float h, String label, OnSubmitHandler onSubmitHandler) {
        super(w, h);
        this.label = new Label(label, this.getSkin());
        this.label.setColor(0,0,0,1);
        this.onSubmitHandler = onSubmitHandler;

        this.input = new TextField("", this.getSkin());
        this.input.setSize(600f, 50f);
        this.input.setTextFieldListener(new TextFieldListener() {
            private InputScreen screen;

            @Override
            public void keyTyped(TextField textField, char key) {
                if (key == '\n') onSubmitHandler.onSubmit(this.screen.input.getText());
            }

            private TextFieldListener init(InputScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        this.add(label).expand().size(720f, 50f).align(Align.center).pad(10f);
        this.add(input).expand().size(720f, 50f).align(Align.center).pad(10f);
    }

    public void onChange(int keycode){
        String strKey = Input.Keys.toString(keycode);
        int n = this.input.getText().length();

        if (keycode == Input.Keys.ENTER) {
            this.onSubmitHandler.onSubmit(this.input.getText());
        } else if(keycode == Input.Keys.BACKSPACE && n > 0) {
            this.input.setText(input.getText().substring(0,n-1));
        } else {
            if(keycode == Input.Keys.SPACE){
                strKey = " ";
            }
            System.out.println(Input.Keys.toString(keycode));
            this.input.setText(input.getText() + strKey);
        }
    }
}


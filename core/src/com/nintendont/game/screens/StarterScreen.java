package com.nintendont.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StarterScreen implements Screen {
    private Stage stage;
    private Label outputLabel;

    public Game game;

    public StarterScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("Skin/glassy-ui.json"));
        outputLabel = new Label("Press a Button", mySkin);
        outputLabel.setColor(255, 0, 0, 1);
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height*2);
        outputLabel.setPosition(0, Gdx.graphics.getHeight()-row_height*2);
        stage.addActor(outputLabel);

        ImageButton button3 = new ImageButton(mySkin);
        button3.setSize(col_width*4,(float)(row_height*2));
        button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("cat.jpg"))));
        button3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg"))));
        button3.setPosition(col_width,Gdx.graphics.getHeight()-row_height*6);
        button3.addListener(new InputListener(){
            private StarterScreen s;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Image Button");
                this.s.GoToMainScreen();
                return true;
            }
            private InputListener init(StarterScreen ss){
                s = ss;
                return this;
            }
        }.init(this));
        stage.addActor(button3);
    }

    public void GoToMainScreen() {
        this.game.setScreen(new MainScreen());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

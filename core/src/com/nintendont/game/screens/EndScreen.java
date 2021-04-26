package com.nintendont.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Species;

public class EndScreen implements Screen{
    private Stage stage;

    public Game game;

    private static final int COLUMN_SIZE = 12;
    private static final int ROW_SIZE = 12;

    public EndScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int row_height = Gdx.graphics.getHeight() / ROW_SIZE;
        int col_width = Gdx.graphics.getWidth() / COLUMN_SIZE;

        Skin mySkin = new Skin(Gdx.files.internal("Skin/craftacular-ui.json"));

        //title "GAME OVER"
        Label titleLabel = new Label("GAME OVER!", mySkin);
        titleLabel.setFontScale(2);
        titleLabel.setColor(255, 0, 0, 1);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height*2);
        titleLabel.setPosition(0, Gdx.graphics.getHeight()-row_height*2);
        titleLabel.setAlignment(Align.center);

        //description game over
        Label descLabel = new Label("You have lost all your engimon!\nPlease try again in a few more minute!", mySkin);
        descLabel.setColor(255, 0, 0, 1);
        descLabel.setSize(Gdx.graphics.getWidth(),row_height);
        descLabel.setPosition(0, Gdx.graphics.getHeight()-row_height*4);
        descLabel.setAlignment(Align.center);

        //Engimon Button
        TextButton button = new TextButton("Start new game", mySkin);
        button.setSize(Gdx.graphics.getWidth()/2,row_height);
        button.setPosition(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()-row_height*8);
        button.addListener(new InputListener(){
            private EndScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("KEKLIK BANG!");
                this.screen.GoToStarterScreen(this.screen.game);
                return true;
            }
            private InputListener init(EndScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        stage.addActor(titleLabel);
        stage.addActor(descLabel);
//        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(new Texture(Gdx.files.internal("Backgrounds/starter.png")), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
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
        stage.dispose();
    }


    public void GoToStarterScreen(Game game){
        this.game.setScreen(new StarterScreen(game));
    }
}

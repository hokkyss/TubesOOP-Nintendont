package com.nintendont.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nintendont.game.InGameHelper;
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Species;

import java.util.Objects;

public class StarterScreen implements Screen {
    private Stage stage;

    public Game game;

    private static final int COLUMN_SIZE = 13;
    private static final int ROW_SIZE = 13;

    private String engimonName;
    private final String[] starterEngimon = new String[] {
            "Emberon",
            "Hailon",
            "Soliust",
            "Bulbmon",
            "Aquaron"
    };
    private int selectedEngimon;


    public StarterScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int row_height = Gdx.graphics.getHeight() / ROW_SIZE;
        int col_width = Gdx.graphics.getWidth() / COLUMN_SIZE;

        Skin mySkin = new Skin(Gdx.files.internal("Skin/craftacular-ui.json"));

        // Title
        Label titleLabel = new Label("Welcome to Nintendon't, pick your starter engimon!", mySkin);
        titleLabel.setColor(255, 0, 0, 1);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(
                0,
                Gdx.graphics.getHeight()-row_height);

        // Engimon Button 1
        ImageButton engimon1 = new ImageButton(mySkin, "starterEngimon1");
        engimon1.setSize(col_width*3, row_height*2);
        engimon1.getStyle().imageUp = getBattleSprite(starterEngimon[0]);
        engimon1.getStyle().imageDown = getBattleSprite(starterEngimon[0]);
        engimon1.setPosition(col_width,Gdx.graphics.getHeight()-row_height*4);
        engimon1.addListener(new InputListener(){
            private StarterScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                this.screen.setSelectedEngimon(0);
                return true;
            }
            private InputListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        // Engimon Button 2
        ImageButton engimon2 = new ImageButton(mySkin, "starterEngimon2");
        engimon2.setSize(col_width*3, row_height*2);
        engimon2.getStyle().imageUp = getBattleSprite(starterEngimon[1]);
        engimon2.getStyle().imageDown = getBattleSprite(starterEngimon[1]);
        engimon2.setPosition(col_width*5,Gdx.graphics.getHeight()-row_height*4);
        engimon2.addListener(new InputListener(){
            private StarterScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                this.screen.setSelectedEngimon(1);
                return true;
            }
            private InputListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        // Engimon Button 3
        ImageButton engimon3 = new ImageButton(mySkin, "starterEngimon3");
        engimon3.setSize(col_width*3, row_height*2);
        engimon3.getStyle().imageUp = getBattleSprite(starterEngimon[2]);
        engimon3.getStyle().imageDown = getBattleSprite(starterEngimon[2]);
        engimon3.setPosition(col_width * 9,Gdx.graphics.getHeight()-row_height*4);
        engimon3.addListener(new InputListener(){
            private StarterScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                this.screen.setSelectedEngimon(2);
                return true;
            }
            private InputListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        // Engimon Button 4
        ImageButton engimon4 = new ImageButton(mySkin, "starterEngimon4");
        engimon4.setSize(col_width*3, row_height*2);
        engimon4.getStyle().imageUp = getBattleSprite(starterEngimon[3]);
        engimon4.getStyle().imageDown = getBattleSprite(starterEngimon[3]);
        engimon4.setPosition(col_width*3,Gdx.graphics.getHeight()-row_height*7);
        engimon4.addListener(new InputListener(){
            private StarterScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                this.screen.setSelectedEngimon(3);
                return true;
            }
            private InputListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        // Engimon Button 5
        ImageButton engimon5 = new ImageButton(mySkin, "starterEngimon5");
        engimon5.setSize(col_width*3, row_height*2);
        engimon5.getStyle().imageUp = getBattleSprite(starterEngimon[4]);
        engimon5.getStyle().imageDown = getBattleSprite(starterEngimon[4]);
        engimon5.setPosition(col_width * 7,Gdx.graphics.getHeight()-row_height*7);
        engimon5.addListener(new InputListener(){
            private StarterScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                this.screen.setSelectedEngimon(4);
                return true;
            }
            private InputListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        ButtonGroup<Button> buttonGroup = new ButtonGroup<Button>(engimon1, engimon2, engimon3, engimon4, engimon5);
        engimon1.setChecked(true);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);
        buttonGroup.setUncheckLast(true);

        // Label name
        Label nameLabel = new Label("Would you like to give your engimon a name?", mySkin);
        nameLabel.setColor(255, 0, 0, 1);
        nameLabel.setSize(Gdx.graphics.getWidth(),row_height);
        nameLabel.setAlignment(Align.center);
        nameLabel.setPosition(
                0,
                row_height*4);

        // Engimon input name
        TextField engimonNameInput = new TextField("", mySkin);
        engimonNameInput.setSize(col_width * 3, row_height);
        engimonNameInput.setPosition(col_width * 5, row_height*3);
        engimonNameInput.setTextFieldListener(new TextFieldListener(){
            private StarterScreen screen;
            @Override
            public void keyTyped(TextField textField, char key) {
                this.screen.setEngimonName(textField.getText());
            }
            private TextFieldListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        // Start Button
        TextButton startBtn = new TextButton("Start", mySkin);
        startBtn.setSize(col_width * 5, row_height);
        startBtn.setPosition(col_width * 4, row_height);
        startBtn.addListener(new InputListener(){
            private StarterScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (this.screen.engimonName != null) {
                    Engimon starter = new Engimon(
                            this.screen.engimonName,
                            Species.get(starterEngimon[this.screen.selectedEngimon]),
                            1
                    );
                    this.screen.GoToMainScreen(starter, game);
                }
                return true;
            }
            private InputListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        // Load Button
        TextButton loadBtn = new TextButton("Load game", mySkin);
        loadBtn.setSize(col_width * 5, row_height);
        loadBtn.setPosition(col_width * 4, 0);
        loadBtn.addListener(new InputListener(){
            private StarterScreen screen;
            @Override
            public void touchUp (InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                InGameHelper.loadGame(this.screen);
                return true;
            }
            private InputListener init(StarterScreen screen){
                this.screen = screen;
                return this;
            }
        }.init(this));

        // add buttons to stage
        stage.addActor(titleLabel);
        stage.addActor(engimon1);
        stage.addActor(engimon2);
        stage.addActor(engimon3);
        stage.addActor(engimon4);
        stage.addActor(engimon5);
        stage.addActor(nameLabel);
        stage.addActor(engimonNameInput);
        stage.addActor(startBtn);
        stage.addActor(loadBtn);
    }

    private TextureRegionDrawable getBattleSprite(String speciesName) {
        return new TextureRegionDrawable(
                new TextureRegion(
                        new Texture(
                                Objects.requireNonNull(Species.get(speciesName))
                                        .getPathBattleSprite()
                        )
                )
        );
    }

    public void GoToMainScreen() {
        this.game.setScreen(new MainScreen());
    }

    public void GoToMainScreen(Engimon starter, Game game) {
        this.game.setScreen(new MainScreen(starter, game));
    }

    public void setEngimonName(String name) {
        this.engimonName = name;
    }

    public void setSelectedEngimon(int index) {
        this.selectedEngimon = index;
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
}

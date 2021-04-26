package com.nintendont.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.nintendont.game.InGameHelper;
import com.nintendont.game.Sounds;
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Player;

import java.util.stream.Collectors;

public class EngimonScreen extends Table implements InputProcessor {
    private Player player;
    private MainScreen main;
    private Engimon engimon;

    public EngimonScreen(Player player, MainScreen main, Engimon e) {
        super(new Skin(Gdx.files.internal("Skin/craftacular-ui.json")));
        Texture bg = new Texture(Gdx.files.internal("Backgrounds/details2.png"));
        TextureRegionDrawable temp = new TextureRegionDrawable(new TextureRegion(bg));
        this.setBackground(temp);

        this.player = player;
        this.main = main;
        this.engimon = e;

        double playerEngimonPower = InGameHelper.getBattlePower(e);

        Image spriteBattleEngimon = new Image(
                new Texture(
                        Gdx.files.internal(this.engimon.getSpecies().getPathBattleSprite())
                )
        );

        this.add("ID: " + String.valueOf(e.getID())).align(Align.topLeft).pad(10f);
        this.row();
        this.add(spriteBattleEngimon).expand();

        Table details = new Table(new Skin(Gdx.files.internal("Skin/craftacular-ui.json")));

        details.setTransform(true);
        details.setScale(0.85f);

        details.pad(30);
        details.add(e.getName()).align(Align.topLeft).padBottom(60);
        details.row();
        details.add("Species: " + e.getSpecies().getSpecies()).align(Align.left);
        details.row();

        details.add("Elements: " + e.getSpecies().getElements().stream().map(Enum::name).collect(Collectors.joining(", ")))
                .expandX()
                .align(Align.left);
        details.row();
        details.add("Level: " + String.valueOf(e.getLevel())).align(Align.left);
        details.row();
        details.add("EXP : (" + e.getExp() + "/100)").align(Align.left);
        details.row();
        details.add("Total EXP : " + e.getCumExp()).align(Align.left);
        details.row();
        details.add("Unique Skill: " + e.getSpecies().getUniqueSkill().skillName).align(Align.left);
        details.row();
        details.add("Skills: ").align(Align.left);
        details.row();

        Table skills = new Table(new Skin(Gdx.files.internal("Skin/craftacular-ui.json")));
        e.getSkills().stream().forEach(S -> {
                details.row();
                skills.add("- " + S.skillName + " (Lv." + S.getMasteryLevel() + ")").align(Align.left).padLeft(45);
                skills.row();
                skills.add("Base power: " + S.basePower).align(Align.left).padLeft(45);
                skills.row();
                details.row();
        });

        details.add(skills).top().left();
        details.row();

        if (e.getParents() != null && e.getParents().size() == 2) {
            details.add("Parents : " + e.getParents().entrySet().stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue()).collect(Collectors.joining(" <3 "))).align(Align.left);
        } else {
            details.add("Parents : -").align(Align.left);
        }

        this.add(details).align(Align.top).expand();
        this.setFillParent(true);
        main.getUiStage().addActor(this);

        Gdx.input.setInputProcessor(this);
    }

    public void open(){
        this.setVisible(true);
    }

    public void close()
    {
        for (Actor actor : main.getUiStage().getActors()) {
            if (actor instanceof EngimonScreen) {
                actor.addAction(Actions.removeActor());
            }
        }
        this.setVisible(false);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            this.close();
            Gdx.input.setInputProcessor(player);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

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
import com.nintendont.game.entities.Skill;

import java.util.ArrayList;
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

        Label idLabel = new Label("ID: " + String.valueOf(e.getID()), getSkin());

        idLabel.setPosition(20f, 570f);
        this.add(idLabel).align(Align.left);
        this.row();
        this.add(spriteBattleEngimon).expand();

        Table details = new Table(new Skin(Gdx.files.internal("Skin/craftacular-ui.json")));

        details.setTransform(true);


        String res = e.getName() + "\n\n";
        res += "Species: " + e.getSpecies().getSpecies();
        res += "\n";
        res += "Elements: " + e.getSpecies().getElements().stream().map(Enum::name).collect(Collectors.joining(", "));
        res += "\n";
        res += "Level: " + String.valueOf(e.getLevel());
        res += "\n";
        res += "EXP : (" + e.getExp() + "/100)";
        res += "\n";
        res += "Total EXP : " + e.getCumExp();
        res += "\n";
        res += "Unique Skill: " + e.getSpecies().getUniqueSkill().skillName;
        res += "\n";
        res += "Skills: ";
        Label detailLabel = new Label(res, getSkin());
        detailLabel.setFontScale(0.9f);

        String res2 = "";
        Label skillDetailLabel = new Label("", getSkin());
        ArrayList<Skill> skillEngimon = e.getSkills();
        for(Skill s : skillEngimon){
            res2 += "- " + s.skillName + " (Lv." + s.getMasteryLevel() + ")";
            res2 += "\n";
            res2 += "Base power: " + s.basePower;
            res2 += "\n";
        }

        res2 = res2.trim();

        Label skillLabel = new Label(res2, getSkin());
        skillLabel.setFontScale(0.9f);

        String res3 = "";
        if (e.getParents() != null && e.getParents().size() == 2) {
            res3 +="Parents : " + e.getParents().entrySet().stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue()).collect(Collectors.joining(" <3 "));
        } else {
            res3 += "Parents : -";
        }

        Label parentLabel = new Label(res3, getSkin());
        parentLabel.setFontScale(0.9f);

        details.add(detailLabel).align(Align.left).expand();
        details.row();
        details.add(skillLabel).align(Align.left).expand().pad(0, 30f, 0 ,0);
        details.row();
        details.add(parentLabel).align(Align.left).expand().pad(0,0f,0,0);
        this.add(details).align(Align.center).expand();
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

package com.nintendont.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nintendont.game.InGameHelper;
import com.nintendont.game.Logger;
import com.nintendont.game.Sounds;
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.EngimonLiar;
import com.nintendont.game.entities.Player;
import com.nintendont.game.entities.SkillItem;

public class BattleScreen extends Table implements InputProcessor {
    private Player player;
    private MainScreen main;
    private Engimon playerEngimon;
    private EngimonLiar enemyEngimon;

    public BattleScreen(Player player, MainScreen main, Engimon playerEngimon, EngimonLiar enemyEngimon) {
        super(new Skin(Gdx.files.internal("Skin/craftacular-ui.json")));
        Texture bg = new Texture(Gdx.files.internal("Battlebacks/forest_bg.png"));
        TextureRegionDrawable temp = new TextureRegionDrawable(new TextureRegion(bg));
        this.setBackground(temp);

        this.player = player;
        this.main = main;
        this.playerEngimon = playerEngimon;
        this.enemyEngimon = enemyEngimon;

        double playerEngimonPower = InGameHelper.getBattlePower(playerEngimon);
        double enemyEngimonPower = InGameHelper.getBattlePower(enemyEngimon);

        int winner = playerEngimonPower >= enemyEngimonPower ? 1 : 2;

        StringBuilder res = new StringBuilder();

        if (winner == 1) {
            Sounds.defaultMusic.stop();
            Sounds.battleWin.setLooping(true);
            Sounds.battleWin.setVolume(0.25f);
            Sounds.battleWin.play();

            res.append(playerEngimon.getName() + " won the battle!\n\n");
            int expWon = enemyEngimon.getLevel() * Player.EXP_MULT;
            playerEngimon.addExp(expWon);

            try {
                if (playerEngimon.isDead()) {
                    Logger.EngimonDeadByLevel(playerEngimon);
                    MainScreen.player.engimonList.remove(playerEngimon);
                } else {
                    res.append("You get new Engimon: " + enemyEngimon.getName() + "\n");
                    Engimon rewardEngimon = new Engimon(enemyEngimon.getName(), enemyEngimon.getSpecies(), enemyEngimon.getLevel());
                    MainScreen.player.engimonList.insert(rewardEngimon);

                    SkillItem rewardSkillItem = SkillItem.getRandomSkillItem(enemyEngimon.getElements());
                    MainScreen.player.skillItemList.insert(rewardSkillItem);
                    res.append("You get new SkillItem: \n" + rewardSkillItem.display());
                }
            } catch (Exception err) {
                Logger.print(err.getMessage());
            }
        } else {
            Sounds.engimonFaint.play(0.25f);

            res.append(playerEngimon.getName() + " lost the battle!\n\n");
            res.append(playerEngimon.faint());

            if (playerEngimon.getLife() == 0) {
                try {
                    MainScreen.player.engimonList.remove(playerEngimon);
                } catch(Exception e) {}
            }
        }

        Image spritePlayerEngimon = new Image(
                new Texture(
                        Gdx.files.internal(playerEngimon.getSpecies().getPathBattleSprite())
                )
        );
        Image vs = new Image(
                new Texture(
                        Gdx.files.internal("Battlebacks/vs.png")
                )
        );
        Image spriteEnemyEngimon = new Image(
                new Texture(
                        Gdx.files.internal(enemyEngimon.getSpecies().getPathBattleSprite())
                )
        );

        main.dialog(res.toString(), 1f);

        this.add(playerEngimon.getName() + " (" + playerEngimonPower + ")").height(200).colspan(2);
        this.add().colspan(2);
        this.add(enemyEngimon.getName() + " (" + enemyEngimonPower + ")").height(200).colspan(2);

        this.row().colspan(4);
        this.add(spritePlayerEngimon).colspan(2).expand();
        this.add(vs).colspan(2).expand();
        this.add(spriteEnemyEngimon).colspan(2).expand();

        this.row().colspan(6);
        this.add(main.getDialogueScreen()).minHeight(300);

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
            if (actor instanceof BattleScreen) {
                actor.addAction(Actions.removeActor());
            }
        }
        Sounds.defaultMusic.setLooping(true);
        Sounds.defaultMusic.setVolume(0.15f);
        Sounds.defaultMusic.play();
        Sounds.battleWin.stop();
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

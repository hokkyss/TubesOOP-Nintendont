package com.nintendont.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nintendont.game.Logger;
import com.nintendont.game.Util;
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.EngimonLiar;
import com.nintendont.game.entities.Player;
import com.nintendont.game.entities.SkillItem;

public class BattleScreen extends Table {
    private Player player;
    private MainScreen main;
    private Engimon playerEngimon;
    private EngimonLiar enemyEngimon;

    public BattleScreen(Player player, MainScreen main, Engimon playerEngimon, EngimonLiar enemyEngimon) {
        super(new Skin(Gdx.files.internal("Skin/craftacular-ui.json")));
        this.player = player;
        this.main = main;
        this.playerEngimon = playerEngimon;
        this.enemyEngimon = enemyEngimon;

        int winner = Util.handleBattle(playerEngimon, enemyEngimon);

        StringBuilder res = new StringBuilder();
        res.append(playerEngimon.getName() + " VS " + enemyEngimon.getName() + "\n");

        if (winner == 1) {
            res.append(playerEngimon.getName() + " won the battle!\n");
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
                    res.append("You get new SkillItem: " + rewardSkillItem + "\n");
                }
            } catch (Exception err) {
                Logger.print(err.getMessage());
            }
        } else {
            Logger.print(playerEngimon.getName() + " lost the battle!");
            playerEngimon.faint();

            if (playerEngimon.getLife() == 0) {
                try {
                    MainScreen.player.engimonList.remove(playerEngimon);
                } catch(Exception e) {}
            }
        }

        this.main.dialog(res.toString());
    }

    public void open(){
        this.setVisible(true);
    }

    public void close()
    {
        this.setVisible(false);
    }
}

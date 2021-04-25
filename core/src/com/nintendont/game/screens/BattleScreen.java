package com.nintendont.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.EngimonLiar;
import com.nintendont.game.entities.Player;

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

        try {
            player.battle(enemyEngimon);
        } catch (Exception e) {
            main.dialog(e.getMessage());
        }

    }

    public void open(){
        this.setVisible(true);
    }

    public void close()
    {
        this.setVisible(false);
    }
}

package com.nintendont.game;

import com.nintendont.game.entities.*;
import com.nintendont.game.entities.saveable.SaveableEngimon;
import com.nintendont.game.entities.saveable.SaveableEngimonLiar;
import com.nintendont.game.exceptions.InputTooLargeException;
import com.nintendont.game.screens.MainScreen;

import java.util.ArrayList;

public class GameData {
    private int turn = 0;

    private Position pos;
    private Position activeEngimonPos;

    private int activeEngimonIdx;
    private Inventory<SaveableEngimon> engimonList;
    private Inventory<SkillItem> skillItemList;

    private ArrayList<SaveableEngimonLiar> wildEngimons;

    public GameData() {

    }

    public GameData(MainScreen screen, Player player) {
        this.turn = MainScreen.turn;

        this.pos = player.getPosition();
        this.activeEngimonPos = player.activeEngimonPos;

        this.activeEngimonIdx = player.getActiveEngimonIdx();

        this.engimonList = new Inventory<SaveableEngimon>();
        for(Engimon e : player.engimonList.invenList) {
            this.engimonList.insert(
                    new SaveableEngimon(e)
            );
        }

        this.skillItemList = new Inventory<SkillItem>();
        for (SkillItem si : player.skillItemList.invenList) {
            this.skillItemList.insert(
                    si
            );
        }

        this.wildEngimons = new ArrayList<SaveableEngimonLiar>();
        for (EngimonLiar e: MainScreen.wildEngimons) {
            this.wildEngimons.add(
                    new SaveableEngimonLiar(e)
            );
        }
    }

    public void load() throws InputTooLargeException {
        MainScreen.turn = this.turn;

        MainScreen.player.setPosition(this.pos);

        Inventory.nCapacity = 0;

        MainScreen.player.engimonList = new Inventory<Engimon>();
        for(SaveableEngimon e : this.engimonList.invenList) {
            MainScreen.player.engimonList.insert(
                    e.toEngimon()
            );
        }

        MainScreen.player.activeEngimonPos = this.activeEngimonPos;
        MainScreen.player.switchActiveEngimon(this.activeEngimonIdx);

        MainScreen.player.skillItemList = new Inventory<SkillItem>();
        for (SkillItem si : this.skillItemList.invenList) {
            MainScreen.player.skillItemList.insert(
                    si
            );
        }

        MainScreen.wildEngimons = new ArrayList<EngimonLiar>();
        for (SaveableEngimonLiar e: this.wildEngimons) {
            MainScreen.wildEngimons.add(
                    e.toEngimonLiar()
            );
        }
    }
}

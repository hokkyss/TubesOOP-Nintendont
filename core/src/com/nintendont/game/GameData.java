package com.nintendont.game;

import com.nintendont.game.comparators.EngimonComparator;
import com.nintendont.game.entities.*;
import com.nintendont.game.comparators.SkillItemComparator;
import com.nintendont.game.entities.saveable.SaveableEngimon;
import com.nintendont.game.entities.saveable.SaveableEngimonLiar;
import com.nintendont.game.exceptions.InputTooLargeException;
import com.nintendont.game.screens.MainScreen;

import java.util.ArrayList;
import java.util.Map;

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

        Inventory.nCapacity = 0;

        this.engimonList = new Inventory<SaveableEngimon>();
        for(Engimon e : player.engimonList.invenList) {
            this.engimonList.insert(
                    new SaveableEngimon(e)
            );
        }

        Engimon activeEngimon = player.getActiveEngimon();
        MainScreen.player.engimonList.invenList.sort(new EngimonComparator());
        this.activeEngimonIdx = player.engimonList.find(activeEngimon);

        this.skillItemList = new Inventory<SkillItem>();
        player.skillItemList.countInven.entrySet().stream().forEach(
                si -> {
                    try {
                        this.skillItemList.insert(
                                SkillItem.getByItemName(
                                        si.getKey().toString()
                                ), si.getValue());
                    } catch (InputTooLargeException e) {
                        e.printStackTrace();
                    }
                }
        );

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

        MainScreen.player.engimonList = new Inventory<>();
        for(SaveableEngimon e : this.engimonList.invenList) {
            MainScreen.player.engimonList.insert(
                    e.toEngimon()
            );
        }

        MainScreen.player.engimonList.invenList.sort(new EngimonComparator());

        MainScreen.player.activeEngimonPos = this.activeEngimonPos;
        MainScreen.player.switchActiveEngimon(this.activeEngimonIdx);

        MainScreen.player.skillItemList = new Inventory<SkillItem>();

        this.skillItemList.countInven.entrySet().stream().forEach(
                si -> {
                    try {
                        MainScreen.player.skillItemList.insert(
                                SkillItem.getByItemName(
                                        String.valueOf(si.getKey())
                                ), si.getValue());
                    } catch (InputTooLargeException e) {
                        e.printStackTrace();
                    }
                }
        );

        MainScreen.player.skillItemList.invenList.sort(new SkillItemComparator());

        MainScreen.wildEngimons = new ArrayList<EngimonLiar>();
        for (SaveableEngimonLiar e: this.wildEngimons) {
            MainScreen.wildEngimons.add(
                    e.toEngimonLiar()
            );
        }
    }
}

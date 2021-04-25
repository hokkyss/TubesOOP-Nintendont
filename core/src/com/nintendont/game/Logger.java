package com.nintendont.game;

import com.nintendont.game.entities.Engimon;
import java.util.Random;

public class Logger {
    public static Random randomize = new Random();

    public static void print()
    {
        System.out.println();
    }
    public static void print(Object s) {
        System.out.println(s.toString());
    }

    public static void EngimonLevelUp(Engimon e, int exp) {
        int changeLevel = (exp / Engimon.EXP_PER_LEVEL);
        print("Congratulations!!");
        print(e.getName() + " has leveled up to level : " + (e.getLevel() + changeLevel));
    }

    public static String EngimonLoseLife(Engimon e) {
        return "Ouch!\n" + e.getName() + " has fainted!\nIt has lose one life and have " + e.getLife() + " live(s) remaining!";
    }

    public static String EngimonDead(Engimon e) {
        return "Too bad!\n" + e.getName() + " has lost all it's life!\nIt will be removed from your inventory";
    }

    public static void EngimonDeadByLevel(Engimon e) {
        print("Too bad!");
        print(e.getName() + " has reached its peak level! It will be removed from your inventory");
    }
}

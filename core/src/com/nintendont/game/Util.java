package com.nintendont.game;

import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.io.FileInputStream;
import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.nintendont.game.entities.*;
import com.nintendont.game.screens.MainScreen;
import org.apache.poi.ss.usermodel.*;

public class Util {

    // Const
    public static final int RESPAWN_TURN = 10;
    public static final int WILD_ENGIMON_MOVE_TURN = 5;
    public static final int MAX_WILD_ENGIMON = 15;
    public static final int LEVEL_BIG_WILD_ENGIMON = 15;

    // Attributes
//    private static char[][] arrPeta;
//    private static ArrayList<EngimonLiar> wildEngimons = new ArrayList<EngimonLiar>();
//    public static Player player;

    public Util() {

    }

    public static void loadSpecies() throws Exception {
        String filePath = System.getProperty("user.dir") + "/data/Engimon.xlsx";

        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));

        Sheet sheet = workbook.getSheetAt(0);

        Species.listOfSpecies = new ArrayList<>();

        int i = 0;
        for (Row row : sheet) {
            if (i != 0) { // not column name
                String name = row.getCell(0).toString();
                ArrayList<Element> elements = Element.constructElements(row.getCell(1).toString());
                Skill uniqueSkill = Skill.getBySkillName(row.getCell(2).toString());
                if (uniqueSkill == null) {
                    System.out.println(row.getCell(2).toString());
                }
                ArrayList<String> responses = new ArrayList<String>();
                responses.add(row.getCell(3).toString());
                responses.add(row.getCell(4).toString());

                String pathBattleSprite = row.getCell(5).toString();
                String pathIconSprite = row.getCell(6).toString();

                Species s = new Species(name, elements, uniqueSkill, responses, pathBattleSprite, pathIconSprite);
                Species.listOfSpecies.add(s);
            }
            i++;
        }

        workbook.close();
    }

    public static void printHelp() {
        Logger.print("Command   | Usage");
        Logger.print("legend    | Show Map Legends");
        Logger.print("w,a,s,d   | Movement");
        Logger.print("show      | Show Active Engimon Stats");
        Logger.print("change    | Change Active Engimon");
        Logger.print("inventory | Access Inventory");
        Logger.print("use       | Use Skill Item To Teach Engimon");
        Logger.print("battle    | Battle Nearby Wild Engimon");
        Logger.print("interact  | Interract With Active Engimon");
        Logger.print("exit      | Leave the game");
    }

    public static void printLegend(){
        Logger.print("Legends:");
        Logger.print("W/w    | Water Engimon");
        Logger.print("I/i    | Ice Engimon");
        Logger.print("F/f    | FIre Engimon");
        Logger.print("G/g    | Ground Engimon");
        Logger.print("E/e    | Electric Engimon");
        Logger.print("S/s    | Engimon that have more than 1 element");
        Logger.print("");
        Logger.print("P      | You");
        Logger.print("X      | Your Engimon");
        Logger.print("");
        Logger.print("-      | Grassland");
        Logger.print("o      | Sea");
        Logger.print("A      | Mountain");
        Logger.print("T      | Tundra");
    }

//    public static void printInventory(Scanner scanner) {
//        int opt;
//
//        Logger.print("1. Engimon");
//        Logger.print("2. Skill Item");
//        Logger.print("Choose a number (1-2): ");
//        opt = scanner.nextInt();
//
//        if(opt == 1) player.showAllEngimon();
//        else player.showSkillItem();
//    }

    public static EngimonLiar getEngimonInCell(Position p) {
        for (EngimonLiar e : MainScreen.wildEngimons) {
            if (e.getPosition().equals(p)) return e;
        }

        return null;
    }

//    public static int chooseEngimon(Scanner scanner) {
//        int opt;
//
//        player.showAllEngimon();
//        Logger.print("Choose Engimon(1 - " + player.engimonList.invenList.size() + ") $");
//        opt = scanner.nextInt();
//
//        while (opt < 1 || opt > player.engimonList.invenList.size()) {
//            Logger.print("You chose an invalid option!\n");
//            opt = scanner.nextInt();
//        }
//
//        return opt;
//    }

//    public static void useItem(Scanner scanner) throws Exception {
//        int opt;
//        player.showSkillItem();
//
//        Logger.print("Choose an item(1 - " + player.skillItemList.invenList.size() + ") $");
//        opt = scanner.nextInt();
//
//        while (opt < 1 || opt>player.skillItemList.invenList.size()){
//            Logger.print("You chose an invalid option!\n");
//            opt = scanner.nextInt();
//        }
//        SkillItem skillChosen = player.skillItemList.invenList.get(opt-1);
//
//        Logger.print("Choose Engimon to give the " + skillChosen.itemName);
//        Logger.print("Make sure the skills elements are compatible.");
//
//        opt = chooseEngimon(scanner);
//
//        Engimon engiChosen = player.engimonList.invenList.get(opt - 1);
//        try {
//            player.useSkillItem(engiChosen, skillChosen);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    public static void changeActiveEngimon(Scanner scanner) {
//        int opt;
//        Logger.print("Choose new Active Engimon ");
//
//        opt = chooseEngimon(scanner);
//
//        player.switchActiveEngimon(opt-1);
//        Logger.print("Successfully switched " + player.getActiveEngimon().getName() + " into active!");
//    }
//
//    public static void breed(Scanner scanner) throws Exception {
//        int opt1,opt2;
//        player.showAllEngimon();
//        Logger.print("Choose 2 Engimon to breed");
//        opt1 = chooseEngimon(scanner);
//        opt2 = chooseEngimon(scanner);
//
//        player.breed(player.engimonList.invenList.get(opt1-1),player.engimonList.invenList.get(opt2-1));
//    }

    public static boolean isElementSame(ArrayList<Element> e1, ArrayList<Element> e2) {
        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
        counter.put(0, 0);
        counter.put(1, 0);
        counter.put(2, 0);
        counter.put(3, 0);
        counter.put(4, 0);
        for (Element e : e1) {
            counter.put(e.idx, counter.get(e.idx) + 1);
        }
        for (Element e : e2) {
            counter.put(e.idx, counter.get(e.idx) - 1);
        }
        for (int key : counter.keySet()) {
            if (counter.get(key) != 0) {
                return false;
            }
        }
        return true;
    }

    // Get element advantage
    public static double getElmtAdv(ArrayList<Element> elmtList1, ArrayList<Element> elmtList2){
        double res = -1;
        for (Element e1 : elmtList1){
            for (Element e2 : elmtList2){
                res = Element.getAdvantage(e1,e2)>res ? Element.getAdvantage(e1,e2):res;
            }
        }

        return res;
    }
}

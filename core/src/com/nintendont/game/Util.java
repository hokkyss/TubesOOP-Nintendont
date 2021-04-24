package com.nintendont.game;

import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.io.FileInputStream;
import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.nintendont.game.entities.*;
import org.apache.poi.ss.usermodel.*;

public class Util {
    // Const
    public static final int RESPAWN_TURN = 10;
    public static final int WILD_ENGIMON_MOVE_TURN = 5;
    public static final int MAX_WILD_ENGIMON = 15;
    public static final int LEVEL_BIG_WILD_ENGIMON = 15;

    // Attributes
    private static char[][] arrPeta;
    private static ArrayList<EngimonLiar> wildEngimons = new ArrayList<EngimonLiar>();
    public static Player player;

//    public static void initPlayer(Scanner scanner) throws Exception {
//        Engimon starter = pickStarterEngimon(scanner);
//        player = new Player(starter);
//    }

    public static void initPeta(String filePath) throws Exception {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
            int rows = lines.size(), columns = lines.get(0).length();

            arrPeta = new char[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    arrPeta[i][j] = lines.get(i).charAt(j);
                }
            }
        } catch (IOException e) {
            throw e;
        }
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
                ArrayList<String> responses = new ArrayList<String>() {{
                    add(row.getCell(3).toString());
                    add(row.getCell(4).toString());
                }};
                String pathBattleSprite = row.getCell(5).toString();
                String pathIconSprite = row.getCell(6).toString();

                Species s = new Species(name, elements, uniqueSkill, responses, pathBattleSprite, pathIconSprite);
                Species.listOfSpecies.add(s);
            }
            i++;
        }

        workbook.close();
    }

    public static void printPeta() {
        for (int i = -1; i <= arrPeta.length; i++) {
            for (int j = -1; j <= arrPeta[0].length; j++) {
                Position currPos = new Position(j, i);
                EngimonLiar e = getEngimonInCell(currPos);

                if (i == -1 || j == -1 || i == arrPeta.length || j == arrPeta[0].length)
                    System.out.print("#");
//                else if (player.getPosition().equals(currPos))
//                    System.out.print("P");
                else if (player.activeEngimonPos.equals(currPos))
                    System.out.print("X");
                else if (e != null)
                    printEngimonInPeta(e);
                else
                    System.out.print(arrPeta[i][j]);
            }
            Logger.print("");
        }
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

    public static void printEngimonInPeta(EngimonLiar e) {
        ArrayList<Element> elmts = e.getElements();
        String elmtChar = "";

        if (elmts.size() >= 2) {
            elmtChar = "s";
        } else {
            switch (elmts.get(0)) {
                case WATER:
                    elmtChar = "w";
                    break;
                case FIRE:
                    elmtChar = "f";
                    break;
                case ICE:
                    elmtChar = "i";
                    break;
                case GROUND:
                    elmtChar = "g";
                    break;
                case ELECTRIC:
                    elmtChar = "e";
                    break;
            }
        }

        System.out.print((e.getLevel() >= LEVEL_BIG_WILD_ENGIMON) ? elmtChar.toUpperCase() : elmtChar);
    }

    public static Engimon pickStarterEngimon(Scanner scanner) {
        String opt, starterName;

        Logger.print("<<< Welcome to the World of Engimon! Ready to Catch 'em All? >>>");
        Logger.print("Here's a list for your starter: ");
        Logger.print("1. Emberon (Fire)");
        Logger.print("2. Hailon (Ice)");
        Logger.print("3. Soliust (Ground)");
        Logger.print("4. Bulbmon (Electric)");
        Logger.print("5. Aquaron (Water)");

        while (true) {
            System.out.print("Choose a number (1-5) for your starter: ");
            opt = scanner.next();

            switch (opt) {
                case "1":
                    System.out.print("Give it a name: ");
                    starterName = scanner.next();

                    Logger.print(starterName);

                    return new Engimon(starterName, Species.get("Emberon"), 1);
                case "2":
                    System.out.print("Give it a name: ");
                    starterName = scanner.next();

                    return new Engimon(starterName, Species.get("Hailon"), 1);
                case "3":
                    System.out.print("Give it a name: ");
                    starterName = scanner.next();

                    return new Engimon(starterName, Species.get("Soliust"), 1);
                case "4":
                    System.out.print("Give it a name: ");
                    starterName = scanner.next();

                    return new Engimon(starterName, Species.get("Bulbmon"), 1);
                case "5":
                    System.out.print("Give it a name: ");
                    starterName = scanner.next();

                    return new Engimon(starterName, Species.get("Aquaron"), 1);
                default:
                    Logger.print("You chose an invalid option!\n");
            }
        }
    }

    public static void printInventory(Scanner scanner) {
        int opt;

        Logger.print("1. Engimon");
        Logger.print("2. Skill Item");
        Logger.print("Choose a number (1-2): ");
        opt = scanner.nextInt();

        if(opt == 1) player.showAllEngimon();
        else player.showSkillItem();
    }

    public static int chooseEngimon(Scanner scanner) {
        int opt;

        player.showAllEngimon();
        Logger.print("Choose Engimon(1 - " + player.engimonList.invenList.size() + ") $");
        opt = scanner.nextInt();

        while (opt < 1 || opt > player.engimonList.invenList.size()) {
            Logger.print("You chose an invalid option!\n");
            opt = scanner.nextInt();
        }

        return opt;
    }

    public static void useItem(Scanner scanner) throws Exception {
        int opt;
        player.showSkillItem();

        Logger.print("Choose an item(1 - " + player.skillItemList.invenList.size() + ") $");
        opt = scanner.nextInt();

        while (opt < 1 || opt>player.skillItemList.invenList.size()){
            Logger.print("You chose an invalid option!\n");
            opt = scanner.nextInt();
        }
        SkillItem skillChosen = player.skillItemList.invenList.get(opt-1);

        Logger.print("Choose Engimon to give the " + skillChosen.itemName);
        Logger.print("Make sure the skills elements are compatible.");

        opt = chooseEngimon(scanner);

        Engimon engiChosen = player.engimonList.invenList.get(opt - 1);
        try {
            player.useSkillItem(engiChosen, skillChosen);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void changeActiveEngimon(Scanner scanner) {
        int opt;
        Logger.print("Choose new Active Engimon ");

        opt = chooseEngimon(scanner);

        player.switchActiveEngimon(opt-1);
        Logger.print("Successfully switched " + player.getActiveEngimon().getName() + " into active!");
    }

    public static void battleNearby() throws Exception {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        EngimonLiar enemy = null;
        boolean found = false;

        for (int i = y - 1; i <= y + 1 && !found; i++) {
            for (int j = x - 1; j <= x + 1 && !found; j++) {
                if ((i == y || j == x) && !(i == y && j == x)) {
                    enemy = getEngimonInCell(new Position(j, i));
                    if (enemy != null){
                        found = true;
                    }
                }
            }
        }

        if (enemy == null){
            throw new Exception("No enemy nearby!");
        }

        wildEngimons.remove(enemy);
        player.battle(enemy);
    }

    public static void breed(Scanner scanner) throws Exception {
        int opt1,opt2;
        player.showAllEngimon();
        Logger.print("Choose 2 Engimon to breed");
        opt1 = chooseEngimon(scanner);
        opt2 = chooseEngimon(scanner);

        player.breed(player.engimonList.invenList.get(opt1-1),player.engimonList.invenList.get(opt2-1));
    }

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

    // Handle battle between Engimon and Wild Engimon
    public static int handleBattle(Engimon e1, EngimonLiar e2) {
        double e1Power = e1.getLevel() * getElmtAdv(e1.getElements(), e2.getElements()) + e1.getSkillPower();
        double e2Power = e2.getLevel() * getElmtAdv(e2.getElements(), e1.getElements()) + e2.getSkillPower();

        Logger.print(e1.getName() + " power = " + e1Power);
        Logger.print(e2.getName() + " power = " + e2Power);

        return e1Power >= e2Power ? 1 : 2;
    }

    public static EngimonLiar getEngimonInCell(Position p) {
        for (EngimonLiar e : wildEngimons) {
            if (e.getPosition().equals(p)) return e;
        }

        return null;
    }

    public static boolean isCellOccupied(Position p) {
        if (player.getPosition().equals(p))
            return true;
        if (player.activeEngimonPos.equals(p))
            return true;

        for (EngimonLiar e : wildEngimons) {
            if (e.getPosition().equals(p))
                return true;
        }

        return false;
    }

    // set the obj position to the new position if valid, also returns its old position
    public static <C extends Creature> Position move(C obj, Direction dir) throws Exception {
        int x = obj.getPosition().getX();
        int y = obj.getPosition().getY();
        Position p = obj.getPosition();

        if (dir == Direction.UP) {
            y--;
        } else if (dir == Direction.DOWN) {
            y++;
        } else if (dir == Direction.LEFT) {
            x--;
        } else if (dir == Direction.RIGHT) {
            x++;
        }

        if ((y >= arrPeta.length || y < 0) || (x >= arrPeta[0].length || x < 0) || isCellOccupied(new Position(x, y))) {
            throw new Exception("You\'ve hit something!");
        } else {
            obj.setPosition(new Position(x, y));
        }

        return p;
    }

    public static void moveWildEngimon() {
        for (EngimonLiar engimonLiar : wildEngimons) {
            Position currPos = null;

            try {
                currPos = move(engimonLiar, Direction.randomize());
            } catch (Exception e) {}

            ArrayList<Element> elements = engimonLiar.getElements();

            boolean seaEngimon = elements.contains(Element.WATER);
            boolean grasslandEngimon = elements.contains(Element.GROUND) || elements.contains(Element.ELECTRIC);
            boolean mountainEngimon = elements.contains(Element.FIRE);
            boolean tundraEngimon = elements.contains(Element.ICE);

            if (currPos != null){
                Position p = engimonLiar.getPosition();

                // if found a position that is not occupied and not out of bounds but not its habitat
                if (!((seaEngimon && arrPeta[p.getY()][p.getX()] == 'o') ||
                        (grasslandEngimon && arrPeta[p.getY()][p.getX()] == '-') ||
                        (mountainEngimon && arrPeta[p.getY()][p.getX()] == 'A') ||
                        (tundraEngimon && arrPeta[p.getY()][p.getX()] == 'T')))
                    engimonLiar.setPosition(currPos);
            }
        }
    }
}

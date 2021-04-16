import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class Util {
    // Const
    public static final int RESPAWN_TURN = 10;
    public static final int WILD_ENGIMON_MOVE_TURN = 5;
    public static final int MAX_WILD_ENGIMON = 10;
    public static final int LEVEL_BIG_WILD_ENGIMON = 15;
    
    // Attributes
    private static char[][] arrPeta;
    private static ArrayList<EngimonLiar> wildEngimons = new ArrayList<EngimonLiar>();
    public static Player player;
    
    public static void initPlayer(Scanner scanner) throws Exception {
        Engimon starter = pickStarterEngimon(scanner);
        player = new Player(starter);
    }

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
        String filePath = "./input/Engimon.xlsx";

        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));

        Sheet sheet = workbook.getSheetAt(0);

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

                Species s = new Species(name, elements, uniqueSkill, responses);
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
                else if (player.getPosition().equals(currPos))
                    System.out.print("P");
                else if (e != null)
                    printEngimonInPeta(e);
                else
                    System.out.print(arrPeta[i][j]);
            }

            System.out.println();
        }
    }

    public static void printHelp() {
        System.out.println("Command   | Usage");
        System.out.println("legend    | Show Map Legends");
        System.out.println("w,a,s,d   | Movement");
        System.out.println("show      | Show Active Engimon Stats");
        System.out.println("change    | Change Active Engimon");
        System.out.println("inventory | Access Inventory");
        System.out.println("use       | Use Skill Item To Teach Engimon");
        System.out.println("battle    | Battle Nearby Wild Engimon");
        System.out.println("interact  | Interract With Active Engimon");
        System.out.println("exit      | Leave the game");
    }
    
    public static void printLegend(){
        System.out.println("Legends:");
        System.out.println("W/w    | Water Engimon");
        System.out.println("I/i    | Ice Engimon");
        System.out.println("F/f    | FIre Engimon");
        System.out.println("G/g    | Ground Engimon");
        System.out.println("E/e    | Electric Engimon");
        System.out.println("S/s    | Engimon that have more than 1 element");
        System.out.println();
        System.out.println("P      | You");
        System.out.println("X      | Your Engimon");
        System.out.println();
        System.out.println("-      | Grassland");
        System.out.println("o      | Sea");
        System.out.println("A      | Mountain");
        System.out.println("T      | Tundra");
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

    public static Engimon pickStarterEngimon(Scanner scanner) {
        String opt, starterName;

        System.out.println("<<< Welcome to the World of Engimon! Ready to Catch 'em All? >>>");
        System.out.println("Here's a list for your starter: ");
        System.out.println("1. Emberon (Fire)");
        System.out.println("2. Hailon (Ice)");
        System.out.println("3. Soliust (Ground)");
        System.out.println("4. Bulbmon (Electric)");
        System.out.println("5. Aquaron (Water)");
        
        while (true) {
            System.out.print("Choose a number (1-5) for your starter: ");
            opt = scanner.next();

            switch (opt) {
            case "1":
                System.out.print("Give it a name: ");
                starterName = scanner.next();

                System.out.println(starterName);

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
                System.out.println("You chose an invalid option!\n");
            }
        }
    }

    public static void spawnWildEngimons() {
        Random rand = new Random();
        wildEngimons.clear();

        for (int i = 0; i < MAX_WILD_ENGIMON; i++) {
            Species s = Species.listOfSpecies.get(rand.nextInt(Species.listOfSpecies.size()));
            ArrayList<Element> elements = s.getElements();
            Position p = new Position(rand.nextInt(arrPeta[0].length), rand.nextInt(arrPeta.length));

            boolean seaEngimon = elements.contains(Element.WATER);
            boolean grasslandEngimon = elements.contains(Element.GROUND) || elements.contains(Element.ELECTRIC);
            boolean mountainEngimon = elements.contains(Element.FIRE);
            boolean tundraEngimon = elements.contains(Element.ICE);

            if (!isCellOccupied(p) && (seaEngimon && arrPeta[p.getY()][p.getX()] == 'o')
                    || (grasslandEngimon && arrPeta[p.getY()][p.getX()] == '-')
                    || (mountainEngimon && arrPeta[p.getY()][p.getX()] == 'A')
                    || (tundraEngimon && arrPeta[p.getY()][p.getX()] == 'T'))
                wildEngimons.add(new EngimonLiar(s.getSpecies(), s, rand.nextInt(player.getActiveEngimon().getLevel()) + 1, p));
        }
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
    
    public static <C extends Creature> Position move(C obj, Direction dir) {
        int x = obj.getPosition().getX();
        int y = obj.getPosition().getY();
        Position p = obj.getPosition();
    
        if (dir == Direction.UP) {
            y--;
        } else if (dir == Direction.DOWN) {
            y++;
        } else if (dir == Direction.LEFT) {
            x--;
        } else if (dir == Direction.RIGHT){
            x++;
        }
    
        if ((y >= arrPeta.length || y < 0) || (x >= arrPeta[0].length || x < 0) || isCellOccupied(new Position(x, y))) {
            return null;
        } else {
            obj.setPosition(new Position(x, y));
        }
        
        return p;
    }
}
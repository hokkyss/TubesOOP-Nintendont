import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* Var */
        Scanner scanner = new Scanner(System.in);
        String command;
        int turn = 0;
        boolean inited = false;

        /* Init Phase */
        try {
            Util.loadSpecies();
            Util.initPeta("input/contoh.txt");
            Util.initPlayer(scanner);

            inited = true;
        } catch (Exception e) {
            Logger.print("An error occured!");
            e.printStackTrace();
        }

        if (inited) Util.printPeta();

        /* In Game Phase */
        while (inited) {
            command = scanner.nextLine();
            Position currPos = Util.player.getPosition();
            
            try {
                switch (command) {
                case "w":
                    currPos = Util.move(Util.player, Direction.UP);
                    Util.player.activeEngimonPos = currPos;
                    break;
                case "a":
                    currPos = Util.move(Util.player, Direction.LEFT);
                    Util.player.activeEngimonPos = currPos;
                    break;
                case "s":
                    currPos = Util.move(Util.player, Direction.DOWN);
                    Util.player.activeEngimonPos = currPos;
                    break;
                case "d":
                    currPos = Util.move(Util.player, Direction.RIGHT);
                    Util.player.activeEngimonPos = currPos;
                    break;
                case "show":
                    Util.player.getActiveEngimon().showDetails();
                    break;
                case "interact":
                    Util.player.getActiveEngimon().interact();
                    break;
                case "inventory":
                    Util.printInventory(scanner);
                    break;
                case "use":
                    Util.useItem(scanner);
                    break;
                case "change":
                    Util.changeActiveEngimon(scanner);
                    break;
                case "battle":
                    Util.battleNearby();
                    break;
                case "breed":
                    Util.breed(scanner);
                    break;
                case "cheat item":
                    Util.player.skillItemList.insert(SkillItem.TM01);
                    break;
                case "cheat engimon":
                    Util.player.engimonList.insert(new Engimon("cheat1",Species.get("Emberon"), 5));
                    Util.player.engimonList.insert(new Engimon("cheat2",Species.get("Bulbmon"), 5));
                    break;
                case "exit":
                    Logger.print("Thank you for playing!");
                    scanner.close();
                    return;
                default:
                    Logger.print("Invalid Command!");
                    Logger.print("Type \'help\' to show all available commands\n");
                    break;
                }
            } catch (EngimonRanOutException e) {
                Logger.print(e.getMessage());
                Logger.print("GAME OVER...");
                return;
            } catch (Exception e) {
                Logger.print(e.getMessage());
                e.printStackTrace();
            }
            
            if (turn % Util.RESPAWN_TURN == 0) {
                Util.spawnWildEngimons();
                Logger.print("The Wild Engimons have respawned!\n");
            } else if (turn % Util.WILD_ENGIMON_MOVE_TURN == 0) {
                Util.moveWildEngimon();
                Logger.print("The Wild Engimons have moved!\n");
            }

            Util.printPeta();
            
            turn++;
        }
    }
}

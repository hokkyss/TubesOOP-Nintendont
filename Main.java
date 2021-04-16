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
            System.out.println("An error occured!");
            e.printStackTrace();
        }

        /* In Game Phase */
        while (inited) {
            Util.player.activeEngimonPos = Util.player.getPosition();
            command = scanner.next();

            switch (command) {
            case "w":

                break;
            case "exit":
                System.out.println("Thank you for playing!");
                scanner.close();
                return;
            default:
                System.out.println("Invalid Command!");
                System.out.println("Type \'help\' to show all available commands\n");
                break;
            }
            
            if (turn % Util.RESPAWN_TURN == 0) {
                Util.spawnWildEngimons();
                System.out.println("The Wild Engimons have respawned!\n");
            } else if (turn % Util.WILD_ENGIMON_MOVE_TURN == 0) {

                System.out.println("The Wild Engimons have moved!\n");
            }

            Util.printPeta();
            
            turn++;
        }
    }
}

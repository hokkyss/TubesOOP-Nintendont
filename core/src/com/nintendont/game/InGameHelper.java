package com.nintendont.game;

import com.nintendont.game.entities.*;
import com.nintendont.game.maps.*;
import com.nintendont.game.screens.BattleScreen;
import com.nintendont.game.screens.MainScreen;

import java.util.ArrayList;
import java.util.Random;

public class InGameHelper {
    public static final int LEVEL_BIG_WILD_ENGIMON = 30;
    public static final int MAX_WILD_ENGIMON = 75;
    public static final int RESPAWN_TURN = 60;
    public static final int WILD_ENGIMON_MOVE_TURN = 5;

    public static void spawnWildEngimons() {
        Random rand = new Random();
        MainScreen.wildEngimons.clear();

        for (int i = 0; i < MAX_WILD_ENGIMON; i++) {
            Species s = Species.listOfSpecies.get(rand.nextInt(Species.listOfSpecies.size()));
            ArrayList<Element> elements = s.getElements();
            Position p = new Position(rand.nextInt(47) + 9, rand.nextInt(45) + 10);

            boolean seaEngimon = elements.contains(Element.WATER);
            boolean grasslandEngimon = elements.contains(Element.GROUND) || elements.contains(Element.ELECTRIC);
            boolean mountainEngimon = elements.contains(Element.FIRE);
            boolean tundraEngimon = elements.contains(Element.ICE);

            Terrain t = MainScreen.mapLoader.getTerrain(p.getX(), p.getY());

            if (MainScreen.mapLoader.isWalkable(p.getX(), p.getY()) &&
                    ((seaEngimon && t == Terrain.SEA) ||
                    (grasslandEngimon && t == Terrain.GRASSLAND) ||
                    (mountainEngimon && t == Terrain.MOUNTAIN) ||
                    (tundraEngimon && t == Terrain.TUNDRA)))
                MainScreen.wildEngimons.add(new EngimonLiar(s.getSpecies(), s, rand.nextInt(MainScreen.player.getActiveEngimon().getLevel()) + 1, p));
        }
    }

    // set the obj position to the new position if valid, also returns its old position
    public static <C extends Creature> Position move(C obj, Direction dir) throws Exception {
        int x = obj.getPosition().getX();
        int y = obj.getPosition().getY();
        int dx = 0;
        int dy = 0;
        Position p = obj.getPosition();

        if (dir == Direction.UP) {
            dy--;
        } else if (dir == Direction.DOWN) {
            dy++;
        } else if (dir == Direction.LEFT) {
            dx--;
        } else if (dir == Direction.RIGHT) {
            dx++;
        }

        if (!MainScreen.mapLoader.isWalkable(x, y, dx, dy)) {
            throw new Exception("You\'ve hit something!");
        } else {
            obj.setPosition(new Position(x + dx, y + dy));
        }

        return p;
    }


    public static void moveWildEngimon() {
        for (EngimonLiar engimonLiar : MainScreen.wildEngimons) {
            Position currPos = null;

            try {
                currPos = move(engimonLiar, Direction.randomize());
            } catch (Exception e) {}

            // if found a new position for wild engimon
            if (currPos != null){
                Position newPos = engimonLiar.getPosition();
                ArrayList<Element> elements = engimonLiar.getElements();
                Terrain t = MainScreen.mapLoader.getTerrain(newPos.getX(), newPos.getY());

                boolean seaEngimon = elements.contains(Element.WATER);
                boolean grasslandEngimon = elements.contains(Element.GROUND) || elements.contains(Element.ELECTRIC);
                boolean mountainEngimon = elements.contains(Element.FIRE);
                boolean tundraEngimon = elements.contains(Element.ICE);

                // if found a position that is not occupied and not out of bounds but not its habitat
                if (!((seaEngimon && t == Terrain.SEA) ||
                    (grasslandEngimon && t == Terrain.GRASSLAND) ||
                    (mountainEngimon && t == Terrain.MOUNTAIN) ||
                    (tundraEngimon && t == Terrain.TUNDRA)))
                    engimonLiar.setPosition(currPos);
            }
        }
    }

    public static void battleNearbyEngimon(Player player, MainScreen mainScreen) throws Exception {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        EngimonLiar enemy = null;
        boolean found = false;

        for (int i = y - 1; i <= y + 1 && !found; i++) {
            for (int j = x - 1; j <= x + 1 && !found; j++) {
                if ((i == y || j == x) && !(i == y && j == x)) {
                    enemy = Util.getEngimonInCell(new Position(j, i));
                    if (enemy != null){
                        found = true;
                    }
                }
            }
        }

        if (enemy == null){
            throw new Exception("No enemy nearby!");
        }

        MainScreen.wildEngimons.remove(enemy);
        BattleScreen battleScreen = new BattleScreen(
                player,
                mainScreen,
                player.getActiveEngimon(),
                enemy
        );
//        player.battle(enemy);
    }
}

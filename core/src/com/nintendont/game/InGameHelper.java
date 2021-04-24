package com.nintendont.game;

import com.nintendont.game.entities.*;
import com.nintendont.game.maps.*;
import com.nintendont.game.screens.MainScreen;

import java.util.ArrayList;
import java.util.Random;

public class InGameHelper {
    public static final int LEVEL_BIG_WILD_ENGIMON = 30;
    public static final int MAX_WILD_ENGIMON = 75;

    public static void spawnWildEngimons() {
        Random rand = new Random();

        for (int i = 0; i < MAX_WILD_ENGIMON; i++) {
            Species s = Species.listOfSpecies.get(rand.nextInt(Species.listOfSpecies.size()));
            ArrayList<Element> elements = s.getElements();
            Position p = new Position(rand.nextInt(47) + 9, rand.nextInt(45) + 10);

            boolean seaEngimon = elements.contains(Element.WATER);
            boolean grasslandEngimon = elements.contains(Element.GROUND) || elements.contains(Element.ELECTRIC);
            boolean mountainEngimon = elements.contains(Element.FIRE);
            boolean tundraEngimon = elements.contains(Element.ICE);

            Terrain t = MainScreen.mapLoader.getTerrain(p.getX(), p.getY());

            if (!isCellOccupied(p) &&
                    ((seaEngimon && t == Terrain.SEA) ||
                    (grasslandEngimon && t == Terrain.GRASSLAND) ||
                    (mountainEngimon && t == Terrain.MOUNTAIN) ||
                    (tundraEngimon && t == Terrain.TUNDRA)))
                MainScreen.wildEngimons.add(new EngimonLiar(s.getSpecies(), s, rand.nextInt(MainScreen.player.getActiveEngimon().getLevel()) + 1, p));
        }
    }

    public static boolean isCellOccupied(Position p) {
        if (MainScreen.player.getPosition().equals(p))
            return true;
        if (MainScreen.player.activeEngimonPos.equals(p))
            return true;
        if (!MainScreen.mapLoader.isWalkable(p.getX(), p.getY())) {
            return true;
        }

        for (EngimonLiar e : MainScreen.wildEngimons) {
            if (e.getPosition().equals(p))
                return true;
        }

        return false;
    }
}

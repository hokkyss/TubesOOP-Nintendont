package com.nintendont.game.entities;

import com.nintendont.game.Logger;
import java.util.ArrayList;

public class Species {
    private String species;
    private ArrayList<Element> elements;
    private Skill uniqueSkill;
    private ArrayList<String> response;

    public static ArrayList<Species> listOfSpecies;

    public Species(String species, ArrayList<Element> elements, Skill uniqueSkill, ArrayList<String> response) {
        this.species = species;
        this.elements = elements;
        elements.sort(Element.comparator);
        this.uniqueSkill = uniqueSkill;
        this.response = response;
    }

    public Species(Species target) {
        this.species = target.species;
        this.elements = target.elements;
        this.uniqueSkill = target.uniqueSkill;
        this.response = target.response;
    }

    public String getSpecies() {
        return this.species;
    }

    public ArrayList<Element> getElements() {
        return this.elements;
    }

    public Skill getUniqueSkill() {
        return this.uniqueSkill;
    }

    public String interact() {
        int target = Logger.randomize.nextInt(response.size());
        return (response.get(target));
    }

    public static Species get(String name) {
        for (Species s : Species.listOfSpecies) {
            if (name.equalsIgnoreCase(s.getSpecies())) {
                return s;
            }
        }
        return null;
    }

    public static Species getSpeciesByName(Species s) {
        return Species.get(s.getSpecies());
    }

    public static Species getSpeciesByElement(ArrayList<Element> elements) {
        for (Species s : Species.listOfSpecies) {
            boolean haveAllElements = true;
            for (Element el : elements) {
                if (!s.elements.contains(el)) {
                    haveAllElements = false;
                }
            }

            if (haveAllElements) {
                return s;
            }
        }
        return null;
    }
}

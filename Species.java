import java.util.ArrayList;

public class Species {
    private String species;
    private ArrayList<Element> elements;
    private Skill uniqueSkill;
    private ArrayList<String> response;
    public static ArrayList<Species> listOfSpecies = new ArrayList<Species>();

    Species(String species, ArrayList<Element> elements, Skill uniqueSkill, ArrayList<String> response){
        this.species = species;
        this.elements = elements;
        this.uniqueSkill = uniqueSkill;
        this.response = response;
    }

    Species(Species target){
        this.species = target.species;
        this.elements = target.elements;
        this.uniqueSkill = target.uniqueSkill;
        this.response = target.response;
    }

    public String getSpecies(){
        return this.species;
    }

    public ArrayList<Element> getElements(){
        return this.elements;
    }

    public Skill getUniqueSkill(){
        return this.uniqueSkill;
    }
}
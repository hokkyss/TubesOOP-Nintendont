import java.util.ArrayList;
import java.util.Random;

public class Species {
    private String species;
    private ArrayList<Element> elements;
    private Skill uniqueSkill;
    private ArrayList<String> response;
    public static ArrayList<Species> listOfSpecies = new ArrayList<Species>();

    private Species(String species, ArrayList<Element> elements, Skill uniqueSkill, ArrayList<String> response) {
        this.species = species;
        this.elements = elements;
        this.uniqueSkill = uniqueSkill;
        this.response = response;
    }

    <<<<<<<HEAD

    public Species(Species target) {
        this.species = target.species;
        this.elements = target.elements;
        this.uniqueSkill = target.uniqueSkill;
        this.response = target.response;
    }

    public String getSpecies() {
=======

    public String getSpecies(){
>>>>>>> edc43f823a1b6f18c1ab7a981427882ac9308fb3
        return this.species;
    }

    public ArrayList<Element> getElements() {
        return this.elements;
    }

    public Skill getUniqueSkill() {
        return this.uniqueSkill;
    }

    public String interact() {
        Random r = new Random();
        int target = r.nextInt(response.size());
        return (response.get(target));
    }

    // enumerasi semua species pake public static final
    public static final Species EMBERON = new Species("Emberon", new ArrayList<Element>(), Skill.FLAMETHROWER,
            new ArrayList<String>());
}
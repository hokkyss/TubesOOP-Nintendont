import java.util.*;
import java.util.stream.Collectors;

public class Engimon {
    public static final int EXP_PER_LEVEL = 100;
    public static final int MAX_LEVEL = 50;
    public static int EngimonCount;

    private int idEngimon;
    private String name;
    private final Species species;
    private int level;
    private int exp;
    private int cumExp;
    private int life;
    private ArrayList<Skill> skills;
    private HashMap<String, String> parents;

    Engimon(String name, Species species, int level, HashMap<String, String> parents) {
        this.idEngimon = EngimonCount + 1;
        this.name = name;
        this.species = species;
        this.level = level;
        this.exp = 0;
        this.cumExp = (level - 1) * EXP_PER_LEVEL;
        this.life = 3;
        this.skills = new ArrayList<Skill>();
        this.skills.add(species.getUniqueSkill());
        this.parents = parents;
        EngimonCount++;
    }

    Engimon(String name, Species species, int level) {
        this(name, species, level, null);
    }

    public int getID() {
        return this.idEngimon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getLife() {
        return this.life;
    }

    public Species getSpecies() {
        return this.species;
    }

    public ArrayList<Element> getElements() {
        return this.species.getElements();
    }

    public ArrayList<Skill> getSkills() {
        return this.skills;
    }

    public void addExp(int exp) {
        if (this.exp + exp >= EXP_PER_LEVEL && !(this instanceof EngimonLiar)) {
            Logger.EngimonLevelUp(this, this.exp + exp);
        }
        this.cumExp += exp;
        this.exp = (this.exp + exp) % EXP_PER_LEVEL;
        this.level = (this.cumExp / EXP_PER_LEVEL) + 1;
    }

    public boolean isDead() {
        boolean isDead = this.cumExp >= EXP_PER_LEVEL * (MAX_LEVEL - 1);
        if (isDead) {
            Logger.EngimonDeadByLevel(this);
        }
        return isDead;
    }

    public void showDetails() {
        System.out.println("=======ENGIMON'S DETAIL=======");
        System.out.println("ID Engimon : " + this.idEngimon);
        System.out.println("Nama : " + this.name);
        System.out.println("Species : " + this.species.getSpecies());
        System.out.println("Elements : "
                + this.species.getElements().stream().map(e -> e.name()).collect(Collectors.joining(", ")));
        System.out.println("Level : " + this.level);
        System.out.println("EXP : (" + this.exp + "/100)");
        System.out.println("Total EXP : " + this.cumExp);
        System.out.println("Unique Skill : " + this.species.getUniqueSkill().skillName);

        // TODO: Print skill level, mastery level, etc.
        System.out.println(
                "Skills : " + this.getSkills().stream().map(s -> s.skillName).collect(Collectors.joining(", ")));

        if (this.parents != null && this.parents.size() == 2) {
            System.out.println("Parents : " + this.parents.entrySet().stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue()).collect(Collectors.joining(" <3 ")));
        } else {
            System.out.println("Parents : -");
        }

        System.out.println("==============================");
    }

    public void faint() {
        if (this.life > 0) {
            this.life--;
            Logger.EngimonLoseLife(this);
        } else {
            Logger.EngimonDead(this);
        }
    }

    private boolean isSkillCompatible(Skill s) {
        ArrayList<Element> target = s.learnableBy;
        for (Element e : this.getElements()) {
            if (target.contains(e)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasLearnt(Skill s) {
        return this.skills.contains(s);
    }

    public void learnSkill(Skill s) {
        if (isSkillCompatible(s) && !hasLearnt(s)) {
            // jangan langsung add(s).
            // ntar kalau masterynya naik, itu naiknya dari enumerasinya.
            // cek MainSkill untuk lebih detail.
            this.skills.add(new Skill(s));
        } else {
            System.out.println("Skill not compatible or engimon has learnt the skill");
        }
    }

    public void learnSkill(SkillItem si) {
        learnSkill(si.containedSkill);
    }
}

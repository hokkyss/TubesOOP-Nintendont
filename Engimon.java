import java.util.*;

public class Engimon {
    public static final int EXP_PER_LEVEL = 100;
    public static final int MAX_LEVEL = 50;
    public static int EngimonID;
    private int idEngimon;
    private String name;
    private Species species;
    private int level;
    private int exp;
    private int cumExp;
    private int life;
    private ArrayList<Skill> skills;

    Engimon(String name, Species species, int level){
        this.idEngimon = EngimonID+1;
        this.name = name;
        this.species = species;
        this.level = level;
        this.cumExp = level * EXP_PER_LEVEL;
        this.life = 3;
        EngimonID++;
    }

    public int getID(){
        return this.idEngimon;
    }

    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public int getLevel(){
        return this.level;
    }

    public int getLife(){
        return this.life;
    }

    public Species getSpecies(){
        return this.species;
    }

    public ArrayList<Element> getElements(){
        return this.species.getElements();
    }

    public ArrayList<Skill> getSkills(){
        return this.skills;
    }

    public void addExp(int exp){
        if(this.exp + exp >= EXP_PER_LEVEL){
            Logger.EngimonLevelUp(this, this.exp+exp);
        }
        this.cumExp += exp;
        this.exp = (this.exp+exp)%EXP_PER_LEVEL;
        this.level = this.cumExp/EXP_PER_LEVEL;
    }

    public boolean isDead(){
        return this.cumExp>=EXP_PER_LEVEL*MAX_LEVEL;
    }

    public void faint(){
        if(this.life>0){
            this.life--;
            Logger.EngimonLoseLife(this);
        }else{
            Logger.EngimonDead(this);
        }
    }

    private boolean isSkillCompatible(Skill s){
        ArrayList<Element> target = s.learnableBy;
        for(Element e : this.getElements()){
            if(target.contains(e)){
                return true;
            }
        }
        return false;
    }

    private boolean hasLearnt(Skill s){
        return this.skills.contains(s);
    }

    public void learnSkill(Skill s){
        if(isSkillCompatible(s) && !hasLearnt(s)){
            this.skills.add(s);
        } //else ngapain
    }
}

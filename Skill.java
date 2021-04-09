import java.util.ArrayList;
import java.util.Arrays;

public class Skill {
    public final String skillName;
    public final int basePower;
    private byte masteryLevel;
    public final ArrayList<Element> learnableBy;
    public static ArrayList<Skill> listOfSkill = new ArrayList<>();

    Skill(String skillName, int basePower, Element e) {
        this.skillName = skillName;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.learnableBy = new ArrayList<>(Arrays.asList(e));
        Skill.listOfSkill.add(this);
    }

    Skill(String skillName, int basePower, Element e1, Element e2) {
        this.skillName = skillName;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.learnableBy = new ArrayList<>(Arrays.asList(e1, e2));
        Skill.listOfSkill.add(this);
    }

    Skill(String skillName, int basePower, Element e1, Element e2, Element e3) {
        this.skillName = skillName;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.learnableBy = new ArrayList<>(Arrays.asList(e1, e2, e3));
        Skill.listOfSkill.add(this);
    }

    Skill(String skillName, int basePower, Element e1, Element e2, Element e3, Element e4) {
        this.skillName = skillName;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.learnableBy = new ArrayList<>(Arrays.asList(e1, e2, e3, e4));
        Skill.listOfSkill.add(this);
    }

    Skill(String skillName, int basePower, Element e1, Element e2, Element e3, Element e4, Element e5) {
        this.skillName = skillName;
        this.basePower = basePower;
        this.masteryLevel = 1;
        this.learnableBy = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));
        Skill.listOfSkill.add(this);
    }

    Skill(Skill s) {
        this.skillName = s.skillName;
        this.basePower = s.basePower;
        this.masteryLevel = s.getMasteryLevel();
        this.learnableBy = new ArrayList<>(s.learnableBy);
    }

    public void increaseMasteryLevel() {
        if (this.masteryLevel == 3)
            return;
        this.masteryLevel++;
    }

    public void setMasteryLevel(byte masteryLevel) {
        this.masteryLevel = masteryLevel;

        if (this.masteryLevel >= 3)
            this.masteryLevel = 3;
    }

    public byte getMasteryLevel() {
        return this.masteryLevel;
    }

    public String toString() {
        String s = "";
        s = s + "Power : " + this.basePower + "\n";
        s = s + "Level : " + this.basePower + "\n";
        s = s + "Learnable by: \n" + this.learnableBy.toString();
        return s;
    }

    public boolean equals(Skill s) {
        return this.skillName.equals(s.skillName);
    }

    public static Skill getBySkillName(String skillName) {
        for (Skill s : Skill.listOfSkill) {
            if (s.skillName.equals(skillName))
                return s;
        }
        return null;
    }

    public int compare(Skill s) {
        int res = 0;
        if (this.basePower < s.basePower)
            res = -1;
        else if (this.basePower == s.basePower)
            res = 0;
        else if (this.basePower > s.basePower)
            res = 1;

        return res;
    }
}

package com.nintendont.game.entities;

import com.nintendont.game.Logger;
import java.util.ArrayList;

// learn skill belum karena bakal butuh engimon
public class SkillItem implements Comparable<SkillItem> {
    // atribut skill item
    public final String itemName;
    public final Skill containedSkill;

    // daftar SkillItem
    public static ArrayList<SkillItem> listOfSkillItem = new ArrayList<>();

    public SkillItem() {
        this.itemName = "";
        this.containedSkill = null;
    }

    private SkillItem(String itemName, Skill containedSkill) {
        this.itemName = itemName;
        this.containedSkill = containedSkill;
        SkillItem.listOfSkillItem.add(this);
    }

    public static SkillItem getRandomSkillItem() {
        // 0 sampai dengan size - 1
        int i = Logger.randomize.nextInt(SkillItem.listOfSkillItem.size());
        return listOfSkillItem.get(i);
    }

    public static SkillItem getRandomSkillItem(ArrayList<Element> e) {
        ArrayList<SkillItem> temp = new ArrayList<>();
        for (SkillItem si : SkillItem.listOfSkillItem) {
            for (Element elements : si.containedSkill.learnableBy) {
                if (e.contains(elements))
                    temp.add(si);
            }
        }
        int i = Logger.randomize.nextInt(temp.size());
        return temp.get(i);
    }

    public static SkillItem getByItemName(String itemName) {
        for (SkillItem si : SkillItem.listOfSkillItem) {
            if (si.itemName.equals(itemName))
                return si;
        }
        return null;
    }

    public int compareTo(SkillItem si) {
        return this.containedSkill.compareTo(si.containedSkill);
    }

    public String toString() {
        return this.itemName;
    }

    public String display()
    {
        String s = "";
        s = s + this.itemName + ": ";
        s = s + this.containedSkill.display() + "\n";
        s = s + "Learnable by: " + this.containedSkill.learnableBy.toString();
        return s;
    }

    // enumerasi semua SkillItem yang ada
    public static final SkillItem TM01 = new SkillItem("TM01", Skill.GIGAIMPACT);
    public static final SkillItem TM02 = new SkillItem("TM02", Skill.BLASTBURN);
    public static final SkillItem TM03 = new SkillItem("TM03", Skill.SUBZEROSLAMMER);
    public static final SkillItem TM04 = new SkillItem("TM04", Skill.TECTONICRAGE);
    public static final SkillItem TM05 = new SkillItem("TM05", Skill.GIGAVOLTHAVOC);
    public static final SkillItem TM06 = new SkillItem("TM06", Skill.HYDROCANNON);
    public static final SkillItem TM07 = new SkillItem("TM07", Skill.ICEHAMMER);
    public static final SkillItem TM08 = new SkillItem("TM08", Skill.MAXHAILSTORM);
    public static final SkillItem TM09 = new SkillItem("TM09", Skill.FREEZEJOLT);
    public static final SkillItem TM10 = new SkillItem("TM10", Skill.FREEZINGFLAME);
    public static final SkillItem TM11 = new SkillItem("TM11", Skill.THUNDER);
    public static final SkillItem TM12 = new SkillItem("TM12", Skill.MUDFLOOD);
    public static final SkillItem TM13 = new SkillItem("TM13", Skill.STEAMBLAST);
    public static final SkillItem TM14 = new SkillItem("TM14", Skill.THUNDERBLAST);
    public static final SkillItem TM15 = new SkillItem("TM15", Skill.SHOCKANDBURN);
    public static final SkillItem TM16 = new SkillItem("TM16", Skill.ERUPTION);
    public static final SkillItem TM17 = new SkillItem("TM17", Skill.WEATHERBALL);
    public static final SkillItem TM18 = new SkillItem("TM18", Skill.TRIATTACK);
    public static final SkillItem TM19 = new SkillItem("TM19", Skill.EARTHPOWER);
    public static final SkillItem TM20 = new SkillItem("TM20", Skill.KORSLET);
    public static final SkillItem TM21 = new SkillItem("TM21", Skill.MELTTHEGROUND);
    public static final SkillItem TM22 = new SkillItem("TM22", Skill.SHOCKTHEFLAME);
    public static final SkillItem TM23 = new SkillItem("TM23", Skill.FREEZEDRY);
    public static final SkillItem TM24 = new SkillItem("TM24", Skill.ELECTROLYSIS);
    public static final SkillItem TM25 = new SkillItem("TM25", Skill.COLDREFRIGERATOR);
    public static final SkillItem TM26 = new SkillItem("TM26", Skill.CONTRADICTINGSHOCK);
}

package com.nintendont.game.comparators;

import com.nintendont.game.entities.Skill;
import java.util.Comparator;

public class SkillComparator implements Comparator<Skill> {
    public int compare(Skill s1, Skill s2) {
        return s2.getMasteryLevel() - s1.getMasteryLevel();
    }
}
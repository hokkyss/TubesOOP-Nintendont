import java.util.Comparator;

public class SkillComparator implements Comparator<Skill> {
    public int compare(Skill s1, Skill s2) {
        return s1.getMasteryLevel() - s2.getMasteryLevel();
    }
}
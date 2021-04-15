import java.util.Comparator;

public class SkillComparator implements Comparator<Skill> {
    public int compare(Skill s1, Skill s2){
        int res = 0;
        if(s1.getMasteryLevel() < s2.getMasteryLevel()){
            res = -1;
        } else if(s1.getMasteryLevel() == s2.getMasteryLevel()){
            res = 0;
        } else if(s1.getMasteryLevel() > s2.getMasteryLevel()){
            res = 1;
        }
        return res;
    }
}
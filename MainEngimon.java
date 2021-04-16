import java.util.*;

public class MainEngimon {
    public static void main(String[] args) {
        ArrayList<Element> e1 = new ArrayList<>();
        e1.add(Element.FIRE);
        e1.add(Element.WATER);

        ArrayList<String> r1 = new ArrayList<>();
        r1.add("BAMBANG");
        r1.add("KENTANG");
        Species x = Species.EMBERON;

        HashMap<String, String> parents = new HashMap<String, String>();
        parents.put("Pege", "Emberon");
        parents.put("Stella", "Pege");

        // Species mirip dengan SkillItem
        Engimon eng1 = new Engimon("Nama1", x, 5, parents);
        eng1.addExp(200);
        eng1.learnSkill(Skill.EARTHPOWER);
        eng1.showDetails();
    }
}

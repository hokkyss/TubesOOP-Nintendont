import java.util.*;

public class MainEngimon {
    public static void main(String[] args) {
        ArrayList<Element> e1 = new ArrayList<>();
        e1.add(Element.FIRE);
        e1.add(Element.WATER);

        ArrayList<String> r1 = new ArrayList<>();
        r1.add("BAMBANG");
        r1.add("KENTANG");

        try {
            Util.loadSpecies();
        } catch (Exception err) {
            System.out.println("Failed to load engimon");
        }


        Species x = Species.get("Emberon");

        HashMap<String, String> parents = new HashMap<String, String>();
        parents.put("Pege", "Emberon");
        parents.put("Stella", "Pege");

        // Species mirip dengan SkillItem
        Engimon eng1 = new Engimon("Nama1", x, 5, parents);
        eng1.addExp(200);
        try {
            eng1.learnSkill(Skill.EARTHPOWER);
        } catch (SkillNotCompatibleException err) {
            System.out.println("not compatible");
        }

        eng1.showDetails();
    }
}

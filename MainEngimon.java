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

        // Species mirip dengan SkillItem
        Engimon eng1 = new Engimon("Nama1", x, 1);
        eng1.addExp(200);
    }
}

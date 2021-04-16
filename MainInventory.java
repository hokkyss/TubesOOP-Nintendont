import java.util.*;

public class MainInventory {
    public static void main(String[] args){
        ArrayList<Element> e1 = new ArrayList<>();
        e1.add(Element.FIRE);
        e1.add(Element.ELECTRIC);
        ArrayList<Element> e2 = new ArrayList<>();
        e2.add(Element.FIRE);
        e2.add(Element.GROUND);
        ArrayList<Element> e3 = new ArrayList<>();
        e3.add(Element.FIRE);
        e3.add(Element.ELECTRIC);
        ArrayList<Element> e4 = new ArrayList<>();
        e4.add(Element.FIRE);

        ArrayList<String> s1 = new ArrayList<>();
        s1.add("wassap");

        // Butuh ctor Species yg public
        Species x = new Species("Species1",e1,Skill.BURNANDSHOCK,s1);
        Species y = new Species("Species2",e2,Skill.ERUPTION,s1);
        Species z = new Species("Species3",e3,Skill.BURNANDSHOCK,s1);
        Species z1 = new Species("Species4",e4,Skill.FLAMETHROWER,s1);

        // Species mirip dengan SkillItem
        Engimon eng1 = new Engimon("Nama1", x, 1);
        eng1.addExp(200);
        Engimon eng2 = new Engimon("Nama2", y, 1);
        Engimon eng3 = new Engimon("Nama3", z, 1);
        Engimon eng4 = new Engimon("Nama4", z1, 1);
        eng4.addExp(200);

        Inventory<SkillItem> sl = new Inventory<SkillItem>();
        Inventory<Engimon> el = new Inventory<Engimon>();

        el.insert(eng1);
        el.insert(eng2);
        el.insert(eng3);
        el.insert(eng4);
        sl.insert(SkillItem.TM01);
        sl.insert(SkillItem.TM01);
        sl.insert(SkillItem.TM02);

        System.out.println(el);
        Inventory.sortInventory(sl, true);
        Inventory.sortInventory(el);
    }
}

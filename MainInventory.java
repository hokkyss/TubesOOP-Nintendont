import java.util.*;

public class MainInventory {
    public static void main(String[] args){
        // Butuh ctor Species yg public
        Species x = Species.get("Emberon"); 
        Species y = Species.get("Hailon"); 

        // Species mirip dengan SkillItem
        Engimon eng1 = new Engimon("Nama1", x, 1);
        eng1.addExp(200);
        Engimon eng2 = new Engimon("Nama2", y, 1);
        Engimon eng3 = new Engimon("Nama3", x, 1);
        Engimon eng4 = new Engimon("Nama4", y, 1);
        eng4.addExp(200);

        Inventory<SkillItem> sl = new Inventory<SkillItem>();
        Inventory<Engimon> el = new Inventory<Engimon>();

        try{
            el.insert(eng1);
            el.insert(eng2);
            el.insert(eng3);
            el.insert(eng4);
            sl.insert(SkillItem.TM01);
            sl.insert(SkillItem.TM01);
            sl.insert(SkillItem.TM02,2);
            sl.remove(SkillItem.TM02);
        }catch (InputTooLargeException err){
            Logger.print("Kegedean");
        }catch (ItemNotFoundException err){
            Logger.print("Kaga ada");
        }

        System.out.println(el);
        Inventory.sortInventory(sl, true);
        Inventory.sortInventory(el);
    }
}

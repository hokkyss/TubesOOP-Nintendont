import java.util.ArrayList;

public class Player {
    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;
    private Position pos;

    public Player(Engimon starter){
        this.activeEngimonIdx = 0;
        engimonList.insert(starter);
    }

    public void showAllEngimon(){
        Inventory.sortInventory(engimonList);
    }

    public Engimon getActiveEngimon(){
        return this.engimonList.get(this.activeEngimonIdx);
    }

    public void showSkillItem(){
        Inventory.sortInventory(skillItemList, true);
    }

    public void useSkillItem(Engimon e1, SkillItem si){
        try{
            e1.learnSkill(s1);
            skillItemList.remove(si);
        }catch(SkillNotCompatible err){
            // throw err;
            Logger.print("Skill not compatible!");
        }catch(ItemNotFound err){
            // throw err;
            Logger.print("Skill Item not found!");
        }
    }

    public void throwSkillItem(int amount, SkillItem si){
        try{
            int n = skillItemList.getCount(si);
            if(n >= amount) skillItemList.setCount(si,n-amount);
            // else throw new InputTooLarge();
        }catch(InputTooLarge err){
            Logger.print("Thrown Item too much!");
        }
    }

    public void releaseEngimon(Engimon e){
        engimonList.remove(e);
    }

    public String toString(){
        String s = "";
        s += "activeEngimonIdx: " + this.activeEngimonIdx + "\n";
        s += "engimonList: " + this.engimonList.toString() + "\n";
        s += "skillItemList: " + this.skillItemList.toString() + "\n";
        s += "pos: " + this.pos.toString();

        return s;
    }

    public ArrayList<Element> inheritElmt(Engimon A, Engimon B){
        Element elmtA = A.getElements().get(0);
        Element elmtB = B.getElements().get(0);
        ArrayList<Element> inheritedElmt = new ArrayList<Element>();
        if(elmtA == elmtB){
            inheritedElmt.add(elmtA);
        } else if(elmtA != elmtB){
            if
        }
    }
}

import java.util.ArrayList;

public class Player {
    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;
    private Position pos;

    public Player(Engimon starter){
        this.activeEngimon = starter;
    }

    public void showAllEngimon() {
        Inventory.sortInventory(engimonList);
    }

    public Engimon getActiveEngimon() {
        return this.engimonList.get(this.activeEngimonIdx);
    }

    public void showSkillItem() {
        Inventory.sortInventory(skillItemList, true);
    }

    public void useSkillItem(Engimon e1, SkillItem si) throws SkillNotCompatibleException, ItemNotFoundException {
        try {
            e1.learnSkill(si);
            skillItemList.remove(si);
        } catch (Exception err) {
            Logger.print(err.getMessage());
        }
    }

    public void throwSkillItem(int amount, SkillItem si) throws InputTooLargeException, ItemNotFoundException {
        this.skillItemList.remove(si, amount);
    }

    public void releaseEngimon(Engimon e) {
        engimonList.remove(e);
    }

    public String toString() {
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
            if(Element.getAdvantage(elmtA, elmtB) > Element.getAdvantage(elmtB, elmtA)){
                inheritedElmt.add(elmtA);
            } else if(Element.getAdvantage(elmtA, elmtB) < Element.getAdvantage(elmtB, elmtA)){
                inheritedElmt.add(elmtB);
            } else{
                inheritedElmt.add(elmtA);
                inheritedElmt.add(elmtB);
            }
        }
        return inheritedElmt;
    }

    public void breed(Engimon A, Engimon B){
        if(!engimonList.isFull()){
            if(A.getLevel() >= 4 && B.getLevel() >= 4){
                String childname;
                
            }
        }
    }
}

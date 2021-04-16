import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player {
    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;
    private Position pos;

    public Player(Engimon starter){
        this.activeEngimonIdx = starter.getID();
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
        try{
            this.skillItemList.remove(si, amount);
        } catch (Exception err){
            Logger.print(err.getMessage());
        }
    }

    public void releaseEngimon(Engimon e) throws InputTooLargeException, ItemNotFoundException {
        try{
            engimonList.remove(e);
        } catch (Exception err){
            Logger.print(err.getMessage());
        }
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

    public ArrayList<Skill> inheritSkill(Engimon A, Engimon B, Skill uniqueSkill){
        ArrayList<Skill> inheritedSkill = new ArrayList<Skill>();
        inheritedSkill.add(uniqueSkill);

        ArrayList<Skill> parentSkills = new ArrayList<Skill>(A.getSkills());
        parentSkills.addAll(B.getSkills());
        parentSkills.sort(new SkillComparator());
        for(int i = 0; inheritedSkill.size() < 4; i++){
            if(!inheritedSkill.contains(parentSkills.get(i))){
                inheritedSkill.add(parentSkills.get(i));
            }
        }
        return inheritedSkill;
    }

    public void breed(Engimon A, Engimon B){
        if(!engimonList.isFull()){
            if(A.getLevel() >= 4 && B.getLevel() >= 4){
                Scanner input = new Scanner(System.in);
                System.out.println("Enter your new Engimon's name: ");
                String childName = input.nextLine();
                
                ArrayList<Element> childElmt = inheritElmt(A, B);
                Species childSpecies = new Species(A.getSpecies());
                if(isElementSame(childElmt, A.getElements())){
                    childSpecies = getSpeciesByName(A.getSpecies());
                } else if(isElementSame(childElmt, B.getElements())){
                    childSpecies = getSpeciesByName(B.getSpecies());
                } else{
                    childSpecies = getSpeciesByElement(childElmt);
                }

                Skill childUniqueSkill = childSpecies.getUniqueSkill();
                ArrayList<Skill> childSkill = inheritSkill(A, B, childUniqueSkill);

                HashMap<String, String> parents = new HashMap<String, String>();
                parents.put(A.getName(), A.getSpecies().getSpecies());
                parents.put(B.getName(), B.getSpecies().getSpecies());

                Engimon child = new Engimon(childName, childSpecies, 0, parents);

                child.setSkill(childSkill);
                engimonList.insert(child);

                A.setLevel(A.getLevel()-3);
                B.setLevel(B.getLevel()-3);

                System.out.println("Breeding successful!");
                System.out.println(childName + " is in inventory.");
            }
        }
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player implements Creature{
    private final int EXP_MULT = 15;

    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;
    private Position pos;
    public Position activeEngimonPos;

    public Player(Engimon starter) throws InputTooLargeException {
        this.activeEngimonIdx = 0;
        try {
            this.engimonList.insert(starter);
        } catch (Exception err) {
            throw err;
        }
    }
    
    @Override
    public Position getPosition() { return pos; }
    
    @Override
    public void setPosition(Position p) { pos = p; }

    public Engimon getActiveEngimon() {
        return this.engimonList.get(this.activeEngimonIdx);
    }

    public void showAllEngimon() {
        Engimon currActive = this.getActiveEngimon();
        Inventory.sortInventory(engimonList);
        this.activeEngimonIdx = engimonList.find(currActive);
    }

    public void showSkillItem() {
        Inventory.sortInventory(skillItemList, true);
    }

    public void useSkillItem(Engimon e1, SkillItem si) throws 
        InputTooLargeException, SkillNotCompatibleException, ItemNotFoundException {
        try {
            e1.learnSkill(si);
            skillItemList.remove(si);
        } catch (Exception err) {
            throw err;
        }
    }

    public void throwSkillItem(int amount, SkillItem si) throws InputTooLargeException, ItemNotFoundException {
        try{
            this.skillItemList.remove(si, amount);
        } catch (Exception err){
            throw err;
        }
    }

    public void releaseEngimon(Engimon e) throws InputTooLargeException, ItemNotFoundException {
        try{
            if(engimonList.size()==1){ 
                Logger.print("You only have 1 Engimon! Release failed");
                return;
            }

            if(engimonList.find(e) == this.activeEngimonIdx){
                this.activeEngimonIdx = 0;
            }

            engimonList.remove(e);
        } catch (Exception err){
            throw err;
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

    public void breed(Engimon A, Engimon B) throws 
        InputTooLargeException, ParentLevelException{
        try{
            if(A.getLevel() >= 4 && B.getLevel() >= 4){
                Scanner input = new Scanner(System.in);
                System.out.println("Enter your new Engimon's name: ");
                String childName = input.nextLine();
                
                ArrayList<Element> childElmt = inheritElmt(A, B);
                Species childSpecies = new Species(A.getSpecies());
                if(Util.isElementSame(childElmt, A.getElements())){
                    childSpecies = Species.getSpeciesByName(A.getSpecies());
                } else if(Util.isElementSame(childElmt, B.getElements())){
                    childSpecies = Species.getSpeciesByName(B.getSpecies());
                } else{
                    childSpecies = Species.getSpeciesByElement(childElmt);
                }

                Skill childUniqueSkill = childSpecies.getUniqueSkill();
                ArrayList<Skill> childSkill = inheritSkill(A, B, childUniqueSkill);

                HashMap<String, String> parents = new HashMap<String, String>();
                parents.put(A.getName(), A.getSpecies().getSpecies());
                parents.put(B.getName(), B.getSpecies().getSpecies());

                Engimon child = new Engimon(childName, childSpecies, 0, parents);

                child.setSkills(childSkill);
                engimonList.insert(child);

                A.setLevel(A.getLevel()-3);
                B.setLevel(B.getLevel()-3);

                System.out.println("Breeding successful!");
                System.out.println(childName + " is in inventory.");
            }else throw new ParentLevelException();
        }catch(Exception err){
            throw err;
        }
    }

    public void battle(EngimonLiar enemy) throws 
        ItemNotFoundException, InputTooLargeException, EngimonRanOutException{
        Engimon myEngimon = this.getActiveEngimon();
        int winner =  Util.handleBattle(myEngimon, enemy);
        
        Logger.print(myEngimon.getName()+" VS "+enemy.getName());

        if(winner==1){
            Logger.print(myEngimon.getName()+" Won the battle!");
            int expWon = enemy.getLevel() * EXP_MULT;
            myEngimon.addExp(expWon);

            try{
                if(myEngimon.isDead()){
                    Logger.EngimonDeadByLevel(myEngimon);
                    this.releaseEngimon(myEngimon);
                }else{
                    Logger.print("You get new Engimon: " + enemy.getName());
                    Engimon rewardEngimon = new Engimon(enemy.getName(),enemy.getSpecies(),enemy.getLevel());
                    this.engimonList.insert(rewardEngimon);

                    SkillItem rewardSkillItem = SkillItem.getRandomSkillItem(enemy.getElements());
                    this.skillItemList.insert(rewardSkillItem);
                    Logger.print("You get new SkillItem: \n" + rewardSkillItem);
                }
            }catch(Exception err){
                throw err;
            }
        }else {
            Logger.print(myEngimon.getName()+" Lost the battle!");
            myEngimon.faint();

            if(myEngimon.getLife()==0){
                this.releaseEngimon(myEngimon);
            }
        }

        if(engimonList.size()==0){
            throw new EngimonRanOutException();
        }
    }
}

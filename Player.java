import javax.xml.stream.events.StartElement;

import jdk.vm.ci.meta.Constant;

public class Player {
    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;
    private Position pos;

    public Player(Engimon starter){
        this.activeEngimon = starter;
    }

    public void showAllEngimon(){
        this.engimonList.printInventory(engimonList);
    }

    public Engimon getActiveEngimon(){
        
    }
}

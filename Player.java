public class Player {
    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;
    private Position pos;

    public Player(Engimon starter) {
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
        this.skillItemList.remove(si, amount);
    }

    public void releaseEngimon(Engimon e) {
        try {
            engimonList.remove(e);
        } catch (Exception err) {
            // do something
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
}

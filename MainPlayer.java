public class MainPlayer {
    public static void main(String[] args){
        // Butuh ctor Species yg public
        Species x = Species.get("Emberon"); 
        Species y = Species.get("Hailon"); 
    
        // Species mirip dengan SkillItem
        Engimon eng1 = new Engimon("Nama1", x, 1);
        eng1.addExp(200);

        Player p = new Player(eng1);

        p.getActiveEngimon().showDetails();
        p.showAllEngimon();

    }
}

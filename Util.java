import java.util.*;

public class Util {
    public static boolean isElementSame(ArrayList<Element> e1, ArrayList<Element> e2) {
        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
        counter.put(0, 0);
        counter.put(1, 0);
        counter.put(2, 0);
        counter.put(3, 0);
        counter.put(4, 0);
        for (Element e : e1) {
            counter.put(e.idx, counter.get(e.idx) + 1);
        }
        for (Element e : e2) {
            counter.put(e.idx, counter.get(e.idx) - 1);
        }
        for (int key : counter.keySet()) {
            if (counter.get(key) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void loadSpecies() {
        // Species Emberon = new Species("Emberon", [Element.FIRE], null, ["Beron!"]);
        // Species.listOfSpecies.add(Emberon);
    }

    // Get element advantage
    public static double getElmtAdv(ArrayList<Element> elmtList1, ArrayList<Element> elmtList2){
        double res = -1;
        for (Element e1 : elmtList1){
            for (Element e2 : elmtList2){
                res = Element.getAdvantage(e1,e2)>res ? Element.getAdvantage(e1,e2):res;
            }
        }
        
        return res;
    }

    // Handle battle between Engimon and Wild Engimon
    public static int handleBattle(Engimon e1, EngimonLiar e2){
        double e1Power = e1.getLevel() * getElmtAdv(e1.getElements(),e2.getElements()) + e1.getSkillPower();
        double e2Power = e2.getLevel() * getElmtAdv(e2.getElements(),e1.getElements()) + e2.getSkillPower();

        Logger.print(e1.getName() + " power = " + e1Power);
        Logger.print(e2.getName() + " power = " + e2Power);

        return e1Power>=e2Power ? 1 : 2; 
    }
}
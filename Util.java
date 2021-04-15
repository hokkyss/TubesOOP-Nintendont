import java.util.*;

public class Util{
    public static boolean isElementSame(ArrayList<Element> e1, ArrayList<Element> e2){
        HashMap <Integer, Integer> counter = new HashMap<Integer,Integer>();
        counter.put(0,0);
        counter.put(1,0);
        counter.put(2,0);
        counter.put(3,0);
        counter.put(4,0);
        for(Element e : e1){
            counter.put(e.idx, counter.get(e.idx)+1);
        }
        for(Element e : e2){
            counter.put(e.idx, counter.get(e.idx)-1);
        }
        for(int key : counter.keySet()){
            if(counter.get(key)!=0){
                return false;
            }
        }
        return true;
    }

    public static void loadSpecies(){
        // Species Emberon = new Species("Emberon", [Element.FIRE], null, ["Beron!"]);
        // Species.listOfSpecies.add(Emberon);
    }
}
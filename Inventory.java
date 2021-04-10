import java.util.*;

public class Inventory<T> {
    public ArrayList<T> invenList;
    public Map<T, Integer> countInven;
    public static int nCapacity=0;
    final public int maxCapacity=20;

    public Inventory(){
        invenList = new ArrayList<T>();
        countInven = new HashMap<T,Integer>();
    }

    public void insert(T el){
        if(nCapacity<maxCapacity){ 
            if(countInven.containsKey(el)){
                int newCount = countInven.get(el) + 1;
                countInven.put(el,newCount);
            }else{    
                invenList.add(el);
                countInven.put(el,1);
            }
            
            nCapacity++;
        }
    }

    public void remove(T el){
        if(countInven.get(el)==1){
            countInven.remove(el);
            invenList.remove(el);
        }else {
            int newCount = countInven.get(el) - 1;
            countInven.put(el,newCount);
        }
        nCapacity--;
    }

    public static void printInventory(Inventory<SkillItem> al, boolean type){
        PriorityQueue<SkillItem> sortedList = new PriorityQueue<SkillItem>(new SkillItemComparator());

        al.invenList.stream().forEach(el -> sortedList.add(el));

        sortedList.stream().forEach(el -> System.out.println(el.containedSkill.skillName + "-" + el.containedSkill.basePower + "-" + al.countInven.get(el)));
    }

    public static void printInventory(Inventory<Engimon> al){
        PriorityQueue<Engimon> sortedList = new PriorityQueue<Engimon>(new EngimonComparator());

        al.invenList.stream().forEach(el -> sortedList.add(el));

        sortedList.stream().forEach(el -> System.out.println(el.getName()));
    }
}

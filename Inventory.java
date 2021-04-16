import java.util.*;

public class Inventory<T> {
    // atribut Inventory
    public final int maxCapacity = 20;
    public static int nCapacity = 0;
    public ArrayList<T> invenList;
    public Map<T, Integer> countInven;

    public Inventory() {
        invenList = new ArrayList<T>();
        countInven = new HashMap<T, Integer>();
    }

    // getter
    public T get(int i){
        return invenList.get(i);
    }

    public int getCount(T el){
        return countInven.get(el);
    }

    // setter
    public void setCount(T el, int n){
        int nPrev = countInven.get(el);
        
        if (n == 0){ 
            nCapacity -= nPrev;
            countInven.remove(el);
            invenList.remove(el);
        }

        if (nPrev>n) nCapacity -= (nPrev-n); 
        else nCapacity += (n-nPrev);

        countInven.put(el, n);
    }

    public void insert(T el) {
        if (nCapacity < maxCapacity) {
            if (countInven.containsKey(el)) {
                int newCount = countInven.get(el) + 1;
                countInven.put(el, newCount);
            } else {
                invenList.add(el);
                countInven.put(el, 1);
            }

            nCapacity++;
        }
    }

    public void remove(T el) {
        if (countInven.get(el) == 1) {
            countInven.remove(el);
            invenList.remove(el);
        } else {
            int newCount = countInven.get(el) - 1;
            countInven.put(el, newCount);
        }
        nCapacity--;
    }

    public String toString(){
        String s = "";

        for (T el : invenList){
            s += ("{\nelement:{\n" + el.toString() + "\n},\ncount:" + countInven.get(el) + "\n}");
            
            if (! el.equals(invenList.get(invenList.size()-1))) s += ",\n";
            else s += "\n"; 
        }

        return s;
    }

    public static void sortInventory(Inventory<SkillItem> al, boolean type) {
        al.invenList.sort(new SkillItemComparator());

        al.invenList.stream().forEach(el -> Logger
                .print(el.containedSkill.skillName + "-" + el.containedSkill.basePower + "-" + al.countInven.get(el)));
    }

    public static void sortInventory(Inventory<Engimon> al) {
        al.invenList.sort(new EngimonComparator());

        al.invenList.stream().forEach(el -> Logger.print(el.getName()));
    }
}

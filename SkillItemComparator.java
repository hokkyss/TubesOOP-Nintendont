import java.util.Comparator;

// PriorityQueue nya dari yang paling besar
// nanti tinggal PriorityQueue<SkillItem> nama = new PriorityQueue<>(new SkillItemComparator());
public class SkillItemComparator implements Comparator<SkillItem> {
    public int compare(SkillItem si1, SkillItem si2) {
        return -si1.compare(si2);
    }
}

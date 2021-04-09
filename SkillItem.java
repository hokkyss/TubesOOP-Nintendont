import java.util.ArrayList;
import java.util.Random;

// learn skill belum karena bakal butuh engimon
public class SkillItem {
    public final String itemName;
    public final Skill containedSkill;
    public static ArrayList<SkillItem> listOfSkillItem = new ArrayList<>();
    private static Random random = new Random();

    SkillItem(String itemName, Skill containedSkill) {
        this.itemName = itemName;
        this.containedSkill = new Skill(containedSkill);
        SkillItem.listOfSkillItem.add(this);
    }

    public static SkillItem getRandomSkillItem() {
        // 0 sampai dengan size - 1
        int i = random.nextInt(SkillItem.listOfSkillItem.size());
        return listOfSkillItem.get(i);
    }

    public static SkillItem getByItemName(String itemName) {
        for (SkillItem si : SkillItem.listOfSkillItem) {
            if (si.itemName.equals(itemName))
                return si;
        }
        return null;
    }

    public int compare(SkillItem si) {
        return this.containedSkill.compare(si.containedSkill);
    }
}

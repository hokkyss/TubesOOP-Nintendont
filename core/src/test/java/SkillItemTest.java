import com.nintendont.game.entities.Element;
import com.nintendont.game.entities.SkillItem;
import org.junit.Assert;
import org.junit.Test;

public class SkillItemTest {
    @Test
    public void test_compare()
    {
        SkillItem si1 = SkillItem.TM11;
        SkillItem si2 = SkillItem.TM02;
        Assert.assertTrue(si1.compareTo(si2) < 0);
    }

    @Test
    public void test_get_by_name()
    {
        SkillItem si1 = SkillItem.getByItemName("TM04");
        Assert.assertEquals(SkillItem.TM04, si1);
    }

    @Test
    public void test_get_random()
    {
        SkillItem si1 = SkillItem.getRandomSkillItem(Element.constructElements("FIRE"));
        Assert.assertTrue(si1.containedSkill.learnableBy.contains(Element.FIRE));

        SkillItem si2 = SkillItem.getRandomSkillItem(Element.constructElements("FIRE, ICE"));
        Assert.assertTrue(si2.containedSkill.learnableBy.contains(Element.FIRE) || si2.containedSkill.learnableBy.contains(Element.ICE));
    }
}

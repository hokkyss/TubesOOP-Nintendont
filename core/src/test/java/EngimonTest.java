import com.nintendont.game.entities.Skill;
import com.nintendont.game.entities.SkillItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.nintendont.game.entities.Engimon;
import com.nintendont.game.Util;
import com.nintendont.game.entities.Species;

public class EngimonTest {
    /*
    @Before
    public void construct_species
    {
        try
        {
            Util.loadSpecies();
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void test_contruct()
    {
        Assert.assertEquals(0, Engimon.EngimonCount);

        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 1);
        Assert.assertEquals(1, a.getLevel());
        Assert.assertEquals("Embirun", a.getName());
        Assert.assertEquals(3, a.getLife());
        Assert.assertEquals(Engimon.EngimonCount - 1, a.getID());
    }

    @Test
    public void test_get_set_level()
    {
        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 5);
        Assert.assertEquals(5, a.getLevel());
        a.setLevel(49);
        Assert.assertEquals(49, a.getLevel());
    }

    @Test
    public void test_exp()
    {
        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 5);
        a.addExp(50);
        Assert.assertEquals(5, a.getLevel());

        a.addExp(50);
        Assert.assertEquals(6, a.getLevel());
    }

    @Test
    public void test_dead()
    {
        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 5);
        a.addExp(50);
        Assert.assertEquals(5, a.getLevel());
        Assert.assertFalse(a.isDead());

        a.addExp(50);
        Assert.assertEquals(6, a.getLevel());
        Assert.assertFalse(a.isDead());

        a.setLevel(500);
        Assert.assertTrue(a.isDead());
    }

    @Test
    public void test_skill_compatible()
    {
        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 1);
        try
        {
            // seharusnya masuk ke dalam catch
            a.learnSkill(Skill.ELECTROLYSIS);
        }
        catch (Exception e)
        {
            Assert.assertTrue(true);
        }

        try
        {
            a.learnSkill(Skill.FLAMETHROWER);
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.assertTrue(false);
        }

        try
        {
            a.learnSkill(SkillItem.TM02);
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_faint()
    {
        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 1);

        Assert.assertEquals(3, a.getLife());
        a.faint();
        Assert.assertEquals(2, a.getLife());
        a.faint();
        Assert.assertEquals(1, a.getLife());
    }
    */
}

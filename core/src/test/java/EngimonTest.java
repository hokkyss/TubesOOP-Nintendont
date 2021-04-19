import com.nintendont.game.entities.Skill;
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
    */
    @Test
    public void test_skill_compatible()
    {
        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 1);
        try
        {
            a.learnSkill(Skill.ELECTROLYSIS);
        }
        catch (Exception e)
        {

        }
    }
}

/*
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import java.util.ArrayList;
import java.util.Arrays;

import com.nintendont.game.entities.Element;
import com.nintendont.game.entities.Skill;
import com.nintendont.game.entities.Species;
import com.nintendont.game.Util;

public class SpeciesTest {
    Species a;
    @Before
    public void construct()
    {
        try {
            Util.loadSpecies();
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            Assert.assertFalse(false);
        }

        a = Species.get("Emberon");
    }

    @Test
    public void test_get_species_name()
    {
        Assert.assertEquals("Emberon", a.getSpecies());
    }

    @Test
    public void test_get_uniqueSkill()
    {
        Assert.assertTrue(a.getUniqueSkill().equals(Skill.FIREPLEDGE));
    }

    @Test
    public void test_get_element()
    {
        Assert.assertEquals(Element.FIRE, a.getElements().get(0));
    }

    @Test
    public void test_get_by_element()
    {
        Species b = Species.getSpeciesByElement(new ArrayList<>(Arrays.asList(Element.FIRE)));
        Assert.assertEquals(a, b);
    }
}
*/
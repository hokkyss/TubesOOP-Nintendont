import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import com.nintendont.game.entities.Element;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementTest {
    Element e1, e2, e3, e4, e5;

    @Before
    public void construct()
    {
        e1 = Element.FIRE;
        e2 = Element.ICE;
        e3 = Element.GROUND;
        e4 = Element.ELECTRIC;
        e5 = Element.WATER;
    }

    @Test
    public void test_constructor()
    {
        Assert.assertEquals(0, e1.idx);
        Assert.assertEquals(1, e2.idx);
        Assert.assertEquals(2, e3.idx);
        Assert.assertEquals(3, e4.idx);
        Assert.assertEquals(4, e5.idx);
    }

    @Test
    public void test_advantage()
    {
        // Fire to everything
        Assert.assertEquals(1, Element.getAdvantage(e1, e1), 0.00001);
        Assert.assertEquals(2, Element.getAdvantage(e1, e2), 0.00001);
        Assert.assertEquals(0.5, Element.getAdvantage(e1, e3), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e1, e4), 0.00001);
        Assert.assertEquals(0, Element.getAdvantage(e1, e5), 0.00001);

        // Ice to everything
        Assert.assertEquals(0, Element.getAdvantage(e2, e1), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e2, e2), 0.00001);
        Assert.assertEquals(2, Element.getAdvantage(e2, e3), 0.00001);
        Assert.assertEquals(0.5, Element.getAdvantage(e2, e4), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e2, e5), 0.00001);

        // Ground to everything
        Assert.assertEquals(1.5, Element.getAdvantage(e3, e1), 0.00001);
        Assert.assertEquals(0, Element.getAdvantage(e3, e2), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e3, e3), 0.00001);
        Assert.assertEquals(2, Element.getAdvantage(e3, e4), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e3, e5), 0.00001);

        // Electric to everything
        Assert.assertEquals(1, Element.getAdvantage(e4, e1), 0.00001);
        Assert.assertEquals(1.5, Element.getAdvantage(e4, e2), 0.00001);
        Assert.assertEquals(0, Element.getAdvantage(e4, e3), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e4, e4), 0.00001);
        Assert.assertEquals(2, Element.getAdvantage(e4, e5), 0.00001);

        // Water to everything
        Assert.assertEquals(2, Element.getAdvantage(e5, e1), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e5, e2), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e5, e3), 0.00001);
        Assert.assertEquals(0, Element.getAdvantage(e5, e4), 0.00001);
        Assert.assertEquals(1, Element.getAdvantage(e5, e5), 0.00001);
    }

    @Test
    public void test_construct_elements()
    {
        ArrayList<Element> al = Element.constructElements("WATER, FIRE, ELECTRIC");

        Assert.assertTrue(al.contains(e1));
        Assert.assertTrue(!al.contains(e2));
        Assert.assertTrue(!al.contains(e3));
        Assert.assertTrue(al.contains(e4));
        Assert.assertTrue(al.contains(e5));
        Assert.assertEquals(al, new ArrayList<>(Arrays.asList(e1, e4, e5)));
    }
}

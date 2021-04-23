import org.junit.Test;
import org.junit.Assert;

import com.nintendont.game.entities.Position;

public class PositionTest {
    @Test
    public void test_get_set()
    {
        Position p = new Position(10, 9);
        Assert.assertEquals(10, p.getX());
        Assert.assertEquals(9, p.getY());

        p.setPosition(5, 100);
        Assert.assertEquals(5, p.getX());
        Assert.assertEquals(100, p.getY());
    }

    @Test
    public void test_equals()
    {
        Position p = new Position(10, 9);
        Position p2 = new Position(9, 10);
        Position p3 = new Position(10, 9);
        Assert.assertTrue(p.equals(p));
        Assert.assertFalse(p.equals(p2));
        Assert.assertTrue(p.equals(p3));
        Assert.assertFalse(p2.equals(p));
        Assert.assertTrue(p2.equals(p2));
        Assert.assertFalse(p2.equals(p3));
        Assert.assertTrue(p3.equals(p));
        Assert.assertFalse(p3.equals(p2));
        Assert.assertTrue(p3.equals(p3));
    }
}

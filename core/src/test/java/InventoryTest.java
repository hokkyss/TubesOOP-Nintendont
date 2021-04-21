import com.nintendont.game.entities.Inventory;
import com.nintendont.game.entities.Species;
import com.nintendont.game.exceptions.InputTooLargeException;
import com.nintendont.game.exceptions.ItemNotFoundException;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import com.nintendont.game.entities.SkillItem;
import com.nintendont.game.entities.Engimon;

import java.util.ArrayList;

public class InventoryTest {
    Inventory<Engimon> engimonInventory;
    Inventory<SkillItem> skillItemInventory;
    @Before
    public void construct()
    {
        engimonInventory = new Inventory<>();
        skillItemInventory = new Inventory<>();
    }

    @Test
    public void test_get()
    {
        skillItemInventory.insert(SkillItem.TM24);
        Assert.assertEquals(SkillItem.TM24, skillItemInventory.get(0));
        Assert.assertEquals(1, skillItemInventory.getCount(SkillItem.TM24));
        Assert.assertEquals(null, skillItemInventory.getCount(SkillItem.TM01));
    }

    @Test
    public void test_remove()
    {
        try
        {
            // seharusnya masuk ke dalam blok catch
            skillItemInventory.remove(SkillItem.TM13);
            Assert.assertTrue(false);
        }
        catch (ItemNotFoundException e)
        {
            Assert.assertTrue(true);
        }

        try
        {
            // seharusnya masuk ke dalam blok catch
            skillItemInventory.insert(SkillItem.TM24, 5);
            skillItemInventory.remove(SkillItem.TM24, 10);
            Assert.assertTrue(false);
        }
        catch (InputTooLargeException e)
        {
            Assert.assertTrue(true);
        }
        catch (ItemNotFoundException e)
        {
            Assert.assertTrue(false);
        }

        try
        {
            skillItemInventory.insert(SkillItem.TM13, 10);
            skillItemInventory.remove(SkillItem.TM13, 5);
            Assert.assertTrue(true);
        }
        catch (InputTooLargeException e)
        {
            // seharusnya berada di dalam blok try
            Assert.assertTrue(false);
        }
        catch (ItemNotFoundException e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_full()
    {
        try
        {
            skillItemInventory.insert(SkillItem.TM26, 10);
            Assert.assertFalse(Inventory.isFull());
            engimonInventory.insert(new Engimon("Embirun", Species.get("Emberon"), 1), 10);
            Assert.assertTrue(Inventory.isFull());
        }
        catch(InputTooLargeException e)
        {
            Assert.assertTrue(false);
        }
    }
}

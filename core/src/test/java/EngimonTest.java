import com.nintendont.game.entities.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EngimonTest {
    public void load()
    {
        String filePath = System.getProperty("user.dir") + "/../data/Engimon.xlsx";

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(filePath));
        } catch (IOException e) {
        }

        Sheet sheet = workbook.getSheetAt(0);

        Species.listOfSpecies = new ArrayList<>();

        int i = 0;
        for (Row row : sheet) {
            if (i != 0) { // not column name
                String name = row.getCell(0).toString();
                ArrayList<Element> elements = Element.constructElements(row.getCell(1).toString());
                Skill uniqueSkill = Skill.getBySkillName(row.getCell(2).toString());
                if (uniqueSkill == null) {
                    System.out.println(row.getCell(2).toString());
                }
                ArrayList<String> responses = new ArrayList<>();
                responses.add(row.getCell(3).toString());
                responses.add(row.getCell(4).toString());

                Species s = new Species(name, elements, uniqueSkill, responses);
                Species.listOfSpecies.add(s);
            }
            i++;
        }

        try {
            workbook.close();
        } catch (IOException e) {

        }
    }

    @Test
    public void test_contruct()
    {
        load();
        Assert.assertEquals(0, Engimon.EngimonCount);

        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 1, true);
        Assert.assertEquals(1, a.getLevel());
        Assert.assertEquals("Embirun", a.getName());
        Assert.assertEquals(3, a.getLife());
        Assert.assertEquals(Engimon.EngimonCount, a.getID());
    }

    @Test
    public void test_get_set_level()
    {
        load();

        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 5, true);
        Assert.assertEquals(5, a.getLevel());
        a.setLevel(49);
        Assert.assertEquals(49, a.getLevel());
    }

    @Test
    public void test_exp()
    {
        load();

        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 5, true);
        a.addExp(50);
        Assert.assertEquals(5, a.getLevel());

        a.addExp(50);
        Assert.assertEquals(6, a.getLevel());
    }

    @Test
    public void test_dead()
    {
        load();

        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 5, true);
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
        load();

        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 1, true);
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
        load();

        Engimon a = new Engimon("Embirun", Species.get("Emberon"), 1, true);

        Assert.assertEquals(3, a.getLife());
        a.faint();
        Assert.assertEquals(2, a.getLife());
        a.faint();
        Assert.assertEquals(1, a.getLife());
    }
}

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.nintendont.game.entities.Element;
import com.nintendont.game.entities.Skill;
import com.nintendont.game.entities.Species;

public class SpeciesTest {
    Species a;

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
                ArrayList<String> responses = new ArrayList<String>();
                responses.add(row.getCell(3).toString());
                responses.add(row.getCell(4).toString());

                String pathBattleSprite = row.getCell(5).toString();
                String pathIconSprite = row.getCell(6).toString();

                Species s = new Species(name, elements, uniqueSkill, responses);
                Species.listOfSpecies.add(s);
            }
            i++;
        }

        try {
            workbook.close();
        } catch (IOException e) {

        }
        a = Species.get("Emberon");
    }

    @Test
    public void test_get_species_name()
    {
        load();
        Assert.assertNotNull(a);
        Assert.assertEquals("Emberon", a.getSpecies());
    }
    @Test
    public void test_get_uniqueSkill()
    {
        load();
        Assert.assertNotNull(a);
        Assert.assertTrue(a.getUniqueSkill().equals(Skill.FIREPLEDGE));
    }
    @Test
    public void test_get_element()
    {
        load();
        Assert.assertNotNull(a);
        Assert.assertEquals(Element.FIRE, a.getElements().get(0));
    }

    @Test
    public void test_get_by_element()
    {
        load();
        Assert.assertNotNull(a);
        Species b = Species.getSpeciesByElement(new ArrayList<>(Arrays.asList(Element.FIRE)));
        Assert.assertNotNull(b);
        Assert.assertEquals(a, b);
    }
}
import java.util.*;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

public class Util {
    public static boolean isElementSame(ArrayList<Element> e1, ArrayList<Element> e2) {
        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
        counter.put(0, 0);
        counter.put(1, 0);
        counter.put(2, 0);
        counter.put(3, 0);
        counter.put(4, 0);
        for (Element e : e1) {
            counter.put(e.idx, counter.get(e.idx) + 1);
        }
        for (Element e : e2) {
            counter.put(e.idx, counter.get(e.idx) - 1);
        }
        for (int key : counter.keySet()) {
            if (counter.get(key) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void loadSpecies() throws Exception {
        String filePath = ".\\input\\Engimon.xlsx";

        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));

        Sheet sheet = workbook.getSheetAt(0);

        int i = 0;
        for (Row row : sheet) {
            if (i != 0) { // not column name
                String name = row.getCell(0).toString();
                ArrayList<Element> elements = Element.constructElements(row.getCell(1).toString());
                Skill uniqueSkill = Skill.getBySkillName(row.getCell(2).toString());
                ArrayList<String> responses = new ArrayList<String>() {{
                    add(row.getCell(3).toString());
                    add(row.getCell(4).toString());
                }};

                Species s = new Species(name, elements, uniqueSkill, responses);
                Species.listOfSpecies.add(s);
            }
            i++;
        }

        workbook.close();
    }
}
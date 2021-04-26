package com.nintendont.game;

import java.util.*;
import java.io.FileInputStream;
import com.nintendont.game.entities.*;
import com.nintendont.game.screens.MainScreen;
import org.apache.poi.ss.usermodel.*;

public class Util {

    public Util() {

    }

    public static void loadSpecies() throws Exception {
        String filePath = System.getProperty("user.dir") + "/data/Engimon.xlsx";

        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));

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

                Species s = new Species(name, elements, uniqueSkill, responses, pathBattleSprite, pathIconSprite);
                Species.listOfSpecies.add(s);
            }
            i++;
        }

        workbook.close();
    }

    public static EngimonLiar getEngimonInCell(Position p) {
        for (EngimonLiar e : MainScreen.wildEngimons) {
            if (e.getPosition().equals(p)) return e;
        }

        return null;
    }

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

    // Get element advantage
    public static double getElmtAdv(ArrayList<Element> elmtList1, ArrayList<Element> elmtList2){
        double res = -1;
        for (Element e1 : elmtList1){
            for (Element e2 : elmtList2){
                res = Element.getAdvantage(e1,e2)>res ? Element.getAdvantage(e1,e2):res;
            }
        }

        return res;
    }
}

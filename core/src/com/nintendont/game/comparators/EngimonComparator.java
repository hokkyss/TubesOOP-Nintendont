package com.nintendont.game.comparators;

import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Element;
import java.util.Comparator;
import java.util.ArrayList;

public class EngimonComparator implements Comparator<Engimon> {
    public int compare(Engimon e1, Engimon e2) {
        int res=0;
        ArrayList<Element> elements1 = e1.getElements();
        ArrayList<Element> elements2 = e2.getElements();

        if(elements1.size() != elements2.size()) return -(elements1.size() - elements2.size());

        elements1.sort(Element.comparator);
        elements2.sort(Element.comparator);

        for(int i=0; i<elements1.size(); i++){
            Element el1 = elements1.get(i);
            Element el2 = elements2.get(i);
            res = el1.compareTo(el2);
            if (res == 0) {
                // dikasih negatif biar terurut dari level yang terbesar
                res = -(e1.getLevel() - e2.getLevel());
            }
        }

        return res;
    }
}

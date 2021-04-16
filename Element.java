import java.util.*;

public enum Element {
    FIRE(0), ICE(1), GROUND(2), ELECTRIC(3), WATER(4);

    public final int idx;
    public final static ElementComparator comparator = new ElementComparator();

    private Element(int i) {
        this.idx = i;
    }

    public static ArrayList<Element> constructElements(String elements) {
        ArrayList<Element> res = new ArrayList<Element>();
        try {
            for (String el : elements.split(",")) {
                res.add(Element.valueOf(el.toUpperCase().trim()));
            }
        } catch (Exception e) {
            System.out.println("Element not found");
        }
        res.sort(comparator);
        return res;
    }
}
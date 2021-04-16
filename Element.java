import java.util.ArrayList;

public enum Element {
    FIRE(0), ICE(1), GROUND(2), ELECTRIC(3), WATER(4);

    public final int idx;
    public static final ElementComparator comparator = new ElementComparator();

    private Element(int i) {
        this.idx = i;
    }

    public static final double[][] advantage = { { 1, 0, 1, 0.5, 2 }, { 2, 1, 0, 1, 1 }, { 1, 2, 1, 0, 1.5 },
            { 1.5, 1, 2, 1, 0 }, { 0, 1, 0.5, 2, 1 } };

    public static ArrayList<Element> constructElements(String elements) {
        ArrayList<Element> res = new ArrayList<Element>();
        try {
            for (String el : elements.split(",")) {
                res.add(Element.valueOf(el.toUpperCase().trim()));
            }
        } catch (Exception e) {
            Logger.print("Element not found");
        }
        res.sort(comparator);
        return res;
    }
}
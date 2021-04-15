import java.util.Comparator;

public class ElementComparator implements Comparator<Element> {
    public int compare(Element e1, Element e2) {
        return (e1.idx - e2.idx);
    }
}

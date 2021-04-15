public enum Element {
    FIRE(0), ICE(1), GROUND(2), ELECTRIC(3), WATER(4);

    public final int idx;

    private Element(int i) {
        this.idx = i;
    }

    public int compare(Element e1, Element e2) {
        return e1.idx - e2.idx;
    }
}
public enum Element {
    FIRE(0), ICE(1), GROUND(2), ELECTRIC(3), WATER(4);

    public final int idx;
    public final static ElementComparator comparator = new ElementComparator();

    private Element(int i) {
        this.idx = i;
    }
}
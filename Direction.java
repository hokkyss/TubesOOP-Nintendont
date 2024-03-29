import java.util.Random;

public enum Direction {
    UP(0), DOWN(1), LEFT(2), RIGHT(3);
    public final int idx;

    private Direction(int i) {
        this.idx = i;
    }

    public static Direction randomize() {
        Random rand = new Random();

        switch (rand.nextInt(4)) {
        case 0:
            return UP;
        case 1:
            return DOWN;
        case 2:
            return LEFT;
        default:
            return RIGHT;
        }
    }
}

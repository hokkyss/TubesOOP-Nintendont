import java.util.Comparator;

public class EngimonComparator implements Comparator<Engimon> {
    public int compare(Engimon e1, Engimon e2) {
        int res;
        Element firstE1 = e1.getElements().get(0);
        Element firstE2 = e2.getElements().get(0);

        res = firstE1.compareTo(firstE2);
        if (res == 0) {
            // dikasih negatif biar terurut dari level yang terbesar
            res = -(e1.getLevel() - e2.getLevel());
        }

        return res;
    }
}

package at.htlleonding.library;

import java.util.Comparator;

public class MagazineDateComparator implements Comparator<Magazine> {
    public int compare(Magazine m1, Magazine m2) {
        int result = Integer.compare(m2.getYear(), m1.getYear());

        if(result == 0) {
            result = Integer.compare(m2.getMonth(), m1.getMonth());
        }

        return result;
    }
}

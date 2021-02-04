package math;

import java.util.Comparator;

public class CriticalPComparator implements Comparator<CriticalP> {
    @Override
    public int compare(CriticalP o1, CriticalP o2) {
        return Float.compare(o1.x_intersection, o2.x_intersection);
    }
}

import java.util.Comparator;

public class CustomComparatorPriority implements Comparator<Publications> {
    public int compare(Publications obj1, Publications obj2) {
        if (obj1.getPriority() > obj2.getPriority()) return -1;
        if (obj1.getPriority() < obj2.getPriority()) return 1;
        return 0;
    }
}

import java.util.Comparator;

public class CustomComparatorDate implements Comparator<Publications> {
    @Override
    public int compare(Publications obj1, Publications obj2) {
        return obj1.getYear().compareTo(obj2.getYear());
    }
}

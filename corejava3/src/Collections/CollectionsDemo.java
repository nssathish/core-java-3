package Collections;

import java.util.*;

public class CollectionsDemo {
    public static void show() {
        Collection<String> collection = new ArrayList<>();
        Collections.addAll(collection, "a", "b", "c");

        Collection<String> other = new ArrayList<>();
        Collections.addAll(other, "a", "b", "c");

        System.out.println(collection == other);
        System.out.println(collection.equals(other));
    }
}

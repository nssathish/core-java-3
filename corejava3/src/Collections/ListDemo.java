package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo {
    public static void show() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        //list.set(0, "!");
        list.add("a");
        Collections.addAll(list, "c", "d");
        System.out.println(list);
        System.out.println(list.indexOf("a"));
        System.out.println(list.lastIndexOf("a"));
    }
}

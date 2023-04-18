package Collections;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void show() {
        var c1 = new Customer("a", "e1");
        var c2 = new Customer("b", "e2");
        Map<String, Customer> map = new HashMap<>();
        map.put(c1.getEmail(), c1);
        map.put(c2.getEmail(), c2);

        System.out.println(map.get("e1"));

        System.out.println(map.getOrDefault("e10", new Customer("Unknown", "")));

        for (var item :
                map.keySet()) {
            System.out.println(item);
        }
        for (var item :
                map.entrySet()) {
            System.out.println(item.getValue());
        }
    }
}

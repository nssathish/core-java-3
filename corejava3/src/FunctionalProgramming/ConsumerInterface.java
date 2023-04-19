package FunctionalProgramming;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerInterface {
    public static void show() {
        List<Integer> integers = List.of(1, 2, 3);
        //imperative programming
        for(var number: integers)
            System.out.println(number);

        //declarative programming
        Consumer<Integer> numbers = item -> System.out.println(item);
        integers.forEach(numbers);

        /*
            Chaining Consumer
         */
        List<String> list = List.of("a", "b", "c");
        Consumer<String> print = item -> System.out.println(item);
        Consumer<String> printUpperCase = item -> System.out.println(item.toUpperCase());

        list.forEach(print.andThen(printUpperCase).andThen(print));
    }
}

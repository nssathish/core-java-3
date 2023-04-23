package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public class CreateStreamsDemo {
    public static void show() {
        //creating stream by collections
        Collection<String> names = new ArrayList<>();
        Collections.addAll(names, "a", "b", "c", "d");
        names.stream()
                .forEach(name -> System.out.println(name));

        //creating stream by array
        int[] numbers = { 1, 2, 3, 4 };
        Arrays.stream(numbers)
                .forEach(number -> System.out.println(number));

        //creating stream by arbitrary objects
        Stream.of(1, 2, 3, 4)
                .forEach(obj -> System.out.println(obj));

        //infinite stream
        var randomNumbers = Stream.generate(() -> Math.random()); //infinite random values
        //randomNumbers.forEach(number -> System.out.println(number));
    }
}

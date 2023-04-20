package FunctionalProgramming;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class FunctionInterface {
    public static void show() {
        Function<String, Integer> map = str -> str.length();
        System.out.println(map.apply("a"));

        // functions for primitive data types
        IntFunction intFn = value -> ++value;
        System.out.println(intFn.apply(15));

        BiFunction<Integer, Integer, Integer> max = (int1, int2) -> Integer.max(int1, int2);
        System.out.println(max.apply(6, 7));

        //Chaining Function Interface
        Function<String, String> replaceColon = str -> str.replace(':', '=');
        Function<String, String> addBraces = str -> "{" + str + "}";
        var result = replaceColon
                .andThen(addBraces)
                .apply("key:value");
        System.out.println(result);

        //using 'compose' method
        result = replaceColon.compose(addBraces).apply("key=value");
        System.out.println(result);
    }
}

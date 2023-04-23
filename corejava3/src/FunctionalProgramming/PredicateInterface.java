package FunctionalProgramming;

import java.util.List;
import java.util.function.Predicate;

public class PredicateInterface {

    public static void show() {
        Predicate<String> hasLeftBrace = str -> str.startsWith("{");
        Predicate<String> hasRightBrace = str -> str.endsWith("}");
        var result = hasLeftBrace.and(hasRightBrace).test("{key:value}");
        System.out.println(result);

        List<String> list = List.of("a", "b", "{key:value}");
        System.out.println(list.stream().allMatch(hasLeftBrace.and(hasRightBrace)));
        System.out.println(list.stream().anyMatch(hasLeftBrace.and(hasRightBrace)));
    }
}

package Streams;

import java.util.List;
import java.util.stream.Stream;

public class MapStreamDemo {
    public static void show() {
        var movies = List.of(
                new Movie("a", 10),
                new Movie("b", 12),
                new Movie("c", 15)
        );
        movies.stream()
                .map(movie -> movie.getLikes())
                .forEach(likes -> System.out.println(likes));

        movies.stream()
                .map(movie -> movie.getTitle())
                .forEach(title -> System.out.println(title));

        showFlatMap();
    }

    public static void showFlatMap() {
        var items = Stream.of(List.of(1, 2, 3), List.of(3, 4, 5, 6));
        items
                .flatMap(list -> list.stream())
                .forEach(n -> System.out.println(n));
    }
}

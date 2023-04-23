package Streams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show() {
        createStreamsDemo();
        mapStreamDemo();
        filterStreamDemo();
    }

    private static void filterStreamDemo() {
        var movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        Predicate<Movie> popularMovies = movie -> movie.getLikes() > 10;
        movies
                .stream()
                //.filter(movie -> movie.getLikes() > 10)
                .filter(popularMovies)
                .forEach(popularMovie -> System.out.println(popularMovie.getTitle()));
    }

    private static void mapStreamDemo() {
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

        var items = Stream.of(List.of(1, 2, 3), List.of(3, 4, 5, 6));
        items
                .flatMap(list -> list.stream())
                .forEach(n -> System.out.println(n));
    }

    private static void createStreamsDemo() {
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

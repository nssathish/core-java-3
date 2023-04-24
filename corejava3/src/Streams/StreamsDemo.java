package Streams;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
    private static List<Movie> movies = List.of(
            new Movie("a", 10),
            new Movie("a", 15),
            new Movie("b", 20),
            new Movie("c", 30),
            new Movie("d", 40)
    );

    public static void show() {
        createStreamsDemo();
        mapStreamDemo();
        filterStreamDemo();
        sliceStreamDemo();
        sortStreamDemo();
        uniqueElementsFromStreamDemo();
        peekElementsInStreamDemo();
        reducersDemo();
        collectorsDemo();
    }

    private static void collectorsDemo() {
        System.out.println("Collectors Demonstration");

        var resultList = movies.stream()
                .filter(movie -> movie.getLikes() > 30).collect(Collectors.toList());
        System.out.println(resultList);

        var resultSet = movies.stream()
                .filter(movie -> movie.getLikes() > 30).collect(Collectors.toSet());
        System.out.println(resultSet);

        var resultMap = movies.stream()
                .filter(movie -> movie.getLikes() > 30).collect(Collectors.toMap(Movie::getTitle, Movie::getLikes));
        System.out.println(resultMap);
        var resultMovieMap = movies.stream()
                .filter(movie -> movie.getLikes() > 30).collect(Collectors.toMap(Movie::getTitle, Function.identity()));
        System.out.println(resultMovieMap);

        var sum = movies.stream()
                .filter(movie -> movie.getLikes() > 30).collect(Collectors.summingInt(Movie::getLikes));
        System.out.println(sum);
        var summarizing = movies.stream()
                .filter(movie -> movie.getLikes() > 10).collect(Collectors.summarizingInt(Movie::getLikes));
        System.out.println(summarizing);
    }

    private static void reducersDemo() {
        System.out.println("Stream Reducers");
        //count
        System.out.println(movies.stream()
                .count());

        //anyMatch, allMatch and noneMatch
        Predicate<Movie> predicate = movie -> movie.getLikes() > 30;
        System.out.println(movies.stream().anyMatch(predicate));
        System.out.println(movies.stream().allMatch(predicate));
        System.out.println(movies.stream().noneMatch(predicate));

        //findFirst, findAny
        System.out.println(movies.stream().findAny().get().getTitle());
        System.out.println(movies.stream().findFirst().get().getTitle());

        //max, min
        var max = movies.stream()
                .max(Comparator.comparing(movie -> movie.getLikes()))
                .get();
        System.out.println(max.getTitle());
        var min = movies.stream()
                .min(Comparator.comparing(Movie::getLikes))
                .get();
        System.out.println(min.getTitle());

        //reduce()
        Optional<Integer> sum = movies.stream()
                .map(Movie::getLikes)
                .reduce((a, b) -> a + b);
        System.out.println(sum.orElse(0));

        var intSum = movies.stream()
                .map(Movie::getLikes)
                .reduce(0, Integer::sum);
        System.out.println(intSum);
    }

    private static void peekElementsInStreamDemo() {
        movies.stream()
                .peek(movie -> System.out.println("filtered: " + movie.getTitle()))
                .map(Movie::getTitle)
                .peek(title -> System.out.println("mapped: " + title))
                .forEach(System.out::println);
    }

    private static void uniqueElementsFromStreamDemo() {
        System.out.println("Unique elements from Stream Demo");

        movies.stream()
                .map(movie -> movie.getTitle())
                .distinct()
                .forEach(System.out::println);
    }

    private static void sortStreamDemo() {
        System.out.println("Sort Stream Demo");
        movies.stream()
                .sorted((mov1, mov2) -> mov1.getTitle().compareTo(mov2.getTitle()))
                .forEach(movie -> System.out.println(movie.getTitle()));
        movies.stream()
                .sorted(Comparator.comparing(movie -> movie.getTitle())) //Comparator.Comparing requires Comparable Inteface implementaiton
                .forEach(movie -> System.out.println(movie.getTitle()));
        movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle).reversed())
                .forEach(movie -> System.out.println(movie.getTitle()));

        movies.stream()
                //.sorted(Comparator.comparing((movie) -> movie.getTitle()).reversed()) //FOR SOME REASON THIS THROWS EXCEPTION
                .forEach(movie -> System.out.println(movie.getTitle()));
    }

    private static void sliceStreamDemo() {
        //combination of the below can be used for pagination of results
        //returned from an API
        movies.stream()
                .limit(2)
                .forEach(movie -> System.out.println(movie.getTitle()));
        movies.stream()
                .skip(2)
                .forEach(movie -> System.out.println(movie.getTitle()));

        //1000 movies in the system
        //10 movies per page - 'pageSize'
        //go to page 3 - 'pageNumber'
        //this can be achieved by the following slicing
        //.skip((pageNumber - 1) * pageSize) - skips first 20 movies
        //.limit(pageSize) - 3 page to have next 10 movies
        movies.stream()
                .skip(20)
                .limit(10)
                .forEach(movie -> System.out.println(movie.getTitle()));

        //takeWhile vs filter()
        System.out.println("takeWhile() vs filter()");
        movies = List.of(
                new Movie("a", 10),
                new Movie("b", 30),
                new Movie("c", 5),
                new Movie("d", 20),
                new Movie("e", 40)
        );
        movies.stream()
                .takeWhile(movie -> movie.getLikes() < 30)
                .forEach(movie -> System.out
                .println(movie.getTitle())); // this prints only one movie as the stream breaks IMMEDIATELY on first failure

        //dropWhile vs filter()
        System.out.println("dropWhile() vs filter()");
        movies.stream()
                .dropWhile(movie -> movie.getLikes() < 30)
                .forEach(movie -> System.out
                .println(movie.getTitle())); // this prints 1 movie as the stream breaks IMMEDIATELY on first failure
    }

    private static void filterStreamDemo() {
        Predicate<Movie> popularMovies = movie -> movie.getLikes() > 10;
        movies
                .stream()
                //.filter(movie -> movie.getLikes() > 10)
                .filter(popularMovies)
                .forEach(popularMovie -> System.out.println(popularMovie.getTitle()));
    }

    private static void mapStreamDemo() {
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

package ExecutorServiceThreadPool;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExecutorServiceDemo {
    public static void show() {
/*
        fixedThreadPoolDemo();
        callablesAndFuturesDemo();
        completableFutureDemo();
*/
        var start = LocalTime.now();
        var service = new FlightService();
        var futures = service.getQuotes()
                .map(future -> future.thenAccept(System.out::println))
                .collect(Collectors.toList());

        CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                        .thenRunAsync(() -> {
                            var end = LocalTime.now();
                            var duration = Duration.between(start, end);
                            System.out.println("Retrieved quotes from all sites in " + duration.toMillis() + " msec.");
                        });

        LoadTask.simulate();
    }

    private static void completableFutureDemo() {
/*
        createCompletableFuture();
        asynchronousAPIUsingCompletableFuture();
        asynchronousAPIUsingCompletableFutureAsync();
        callBackOnCompletion();
        completableFutureExceptionHandling();
        transformCompletableFutureDemo();
        composeAndCombineCompletableFuturesDemo();
        waitingForTasksDemo();
        handleTimeoutDemo();
*/
        var service = new FlightService();
//        service.getQuote("site")
//                .thenAcceptAsync(System.out::println);

        var start = LocalTime.now();

        var futures = service.getQuotes()
                .map(future -> future.thenAcceptAsync(System.out::println))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRunAsync(() -> {
                    var end = LocalTime.now();
                    var duration = Duration.between(start, end);
                    System.out.println("Retrieved all quotes in " + duration.toMillis() + " msec.");
                });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleTimeoutDemo() {
        var first = CompletableFuture.supplyAsync(() -> {
            LoadTask.simulate();
            return 1;
        });

        first.orTimeout(1, TimeUnit.SECONDS);
//                .thenRunAsync(() -> System.out.println("Task took more than 1 second"));
    }

    private static void waitingForTasksDemo() {
        var first = CompletableFuture.supplyAsync(() -> 1);
        var second = CompletableFuture.supplyAsync(() -> 2);
        var third = CompletableFuture.supplyAsync(() -> 3);

        CompletableFuture.allOf(first, second, third)
                .thenRunAsync(() -> {
                    try {
                        var firstResult = first.get();
                        System.out.println(firstResult);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("All tasks completed");
                });

        var future1 = CompletableFuture.supplyAsync(() -> {
//            LoadTask.simulate();
            System.out.println("first completed");
            return 1;
        });
        CompletableFuture.anyOf(future1, second) // with the LoadTask.simulate 'future1' completes after 'second'
                // so we don't see the sout from future1
                // but if the LoadTask.simulate is removed
                // we can see that 'future1' completes first, and we see the sout msg
                .thenRunAsync(() -> System.out.println("Any one thread completed"));
    }

    private static void composeAndCombineCompletableFuturesDemo() {
        composeCompletableFutureDemo();
        combineCompletableFutureDemo();
    }

    private static void combineCompletableFutureDemo() {
//        var first = CompletableFuture.supplyAsync(() -> 20);
        var first = CompletableFuture.supplyAsync(() -> "20USD")
                .thenApplyAsync(price -> price.replaceAll("USD",""))
                .thenApplyAsync(Integer::parseInt);
        var second = CompletableFuture.supplyAsync(() -> 0.9);
        first
                .thenCombineAsync(second, (price, exchangeRate) -> price * exchangeRate)
                .thenAccept(System.out::println);
    }

    private static void composeCompletableFutureDemo() {
        getUserEmail()
                .thenComposeAsync(ExecutorServiceDemo::getPlaylistAsync)
                .thenAcceptAsync(System.out::println);
    }

    private static CompletableFuture<String> getUserEmail() {
        return CompletableFuture.supplyAsync(() -> "email");
    }
    private static CompletableFuture<String> getPlaylistAsync(String email) {
        return CompletableFuture.supplyAsync(() -> "playlist");
    }
    private static int toFahrenheit(int celsius) {
        return (int) (celsius * 1.8) + 32;
    }
    private static void transformCompletableFutureDemo() {
        var future = CompletableFuture.supplyAsync(() -> 20);
        future
                .thenApplyAsync(ExecutorServiceDemo::toFahrenheit)
                .thenAcceptAsync(System.out::println);
    }

    private static void completableFutureExceptionHandling() {
        var future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting current weather of the city");
            throw new IllegalStateException();
        });

        try {
            //System.out.println(future.get());     // The regular call throws the exception
            System.out.println(future.exceptionally(ex -> 1).get()); // 'exceptoinally' will consume the
            // exception and return the value passed
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void callBackOnCompletion() {
        var future = CompletableFuture.supplyAsync(() -> 101);
        future.thenRun(() -> {
            System.out.println(Thread.currentThread().getName()); //since thenRun() is a synchronous process
            // it's getting called from the 'main' thread
            System.out.println("Done");
        });
        future.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName()); // since thenRunAsync is an asynchronous process
            // it's getting called from the 'asynchronous thread' not the 'main' thread
            System.out.println("Done");
        });

        future.thenAccept((result) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        });
        future.thenAcceptAsync((result) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        });
    }

    private static void asynchronousAPIUsingCompletableFutureAsync() {
        var service = new MailService();
        service.sendAsync(); // CALL-1

        System.out.println("asynchronousAPIUsingCompletableFutureAsync is done."); // This call (CALL-2) does not
        // wait for CALL-1
        try {
            Thread.sleep(5000); // this sleep is to show that CALL-1 has executed successfully
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void asynchronousAPIUsingCompletableFuture() {
        var service = new MailService();
        // example of blocking call
        service.send(); //first this call runs - CALL-1
        System.out.println("asynchronousAPIUSingCompletableFuture done."); // this runs after CALL-1 is
        // complete
    }

    private static void createCompletableFuture() {
        Runnable task = () -> System.out.println("a");
        CompletableFuture.runAsync(task); // this returns void
        Supplier<Integer> supplierTask = () -> {
            System.out.println("supplierTask");
            return 100;
        };
        var result = CompletableFuture.supplyAsync(supplierTask);
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void callablesAndFuturesDemo() {
        var executor = Executors.newFixedThreadPool(2);
        try {
            var future = executor.submit(() -> {
                LoadTask.simulate();
                return 1;
            });
            System.out.println("Do something");
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        finally {
            executor.shutdown();
        }
    }

    private static void fixedThreadPoolDemo() {
        var threadPool = Executors.newFixedThreadPool(3);
        try {
            System.out.println(threadPool.getClass().getName());
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }
        finally {
            threadPool.shutdown(); // it's always a best practice to put termination
            // task in the finally block - move on and shutdown gracefully after an
            // exception
        }
    }
}

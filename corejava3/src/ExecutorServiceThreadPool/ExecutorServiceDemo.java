package ExecutorServiceThreadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class ExecutorServiceDemo {
    public static void show() {
/*
        fixedThreadPoolDemo();
        callablesAndFuturesDemo();
*/
        completableFutureDemo();
    }

    private static void completableFutureDemo() {
/*
        createCompletableFuture();
        asynchronousAPIUsingCompletableFuture();
        asynchronousAPIUsingCompletableFutureAsync();
        callBackOnCompletion();
*/
        completableFutureExceptionHandling();
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

package ExecutorServiceThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void show() {
//        fixedThreadPoolDemo();
        callableFutureDemo();
    }

    private static void callableFutureDemo() {
        var executor = Executors.newFixedThreadPool(2);
        try {
            var future = executor.submit(() -> {
                LoadTask.load();
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

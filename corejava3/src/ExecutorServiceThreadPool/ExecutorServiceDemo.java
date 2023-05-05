package ExecutorServiceThreadPool;

import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void show() {
        fixedThreadPoolDemo();
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

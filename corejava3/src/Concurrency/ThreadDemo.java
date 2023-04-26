package Concurrency;

import java.util.ArrayList;

public class ThreadDemo {
    public static void show() {
//        startPauseThreadDemo();
//        joinThreadDemo();
//        interruptThreadDemo();
        RaceConditionDemo();
    }

    private static void RaceConditionDemo() {
        var status = new DocumentStatus();
        var threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            var thread = new Thread(new DownloadFileTask(status));
            thread.start();
            threads.add(thread);
        }

        for (var thread :
                threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("total bytes " + status.getTotalBytes());
    }

    private static void interruptThreadDemo() {
        Thread thread = new Thread(new DownloadFileTask(new DocumentStatus()));
        thread.start();

        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.interrupt();
    }

    private static void joinThreadDemo() {
        Thread thread = new Thread(new DownloadFileTask(new DocumentStatus()));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("file downloaded for scanning");
    }

    private static void startPauseThreadDemo() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DownloadFileTask(new DocumentStatus()));
            thread.start();
        }
    }
}

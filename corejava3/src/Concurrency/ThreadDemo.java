package Concurrency;

public class ThreadDemo {
    public static void show() {
        startPauseThreadDemo();
        joinThreadDemo();
    }

    private static void joinThreadDemo() {
        Thread thread = new Thread(new DownloadFileTask());
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
            Thread thread = new Thread(new DownloadFileTask());
            thread.start();
        }
    }
}

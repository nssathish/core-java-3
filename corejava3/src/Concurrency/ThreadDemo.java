package Concurrency;

import javax.swing.text.Document;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadDemo {
    public static void show() {
//        startPauseThreadDemo();
//        joinThreadDemo();
//        interruptThreadDemo();
//        RaceConditionDemo();
//        confinementDemo();
//        locksDemo();
//        volatileDemo();
//        waitAndNotifyDemo();
//        atomicObjectsAddersDemo();
//        synchronizedCollectionDemo();
        concurrentCollectionDemo();
    }

    private static void concurrentCollectionDemo() {
        Map<String, Integer> map = new ConcurrentHashMap<>();

        Thread thread1 = new Thread(() -> map.putAll(Map.of("a", 1, "b", 2, "c", 3)));
        Thread thread2 = new Thread(() -> map.putAll(Map.of("d", 4, "e", 5, "f", 6)));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(map);
    }

    private static void synchronizedCollectionDemo() {
//        Collection<Integer> collection = new ArrayList<>(); // this prints just [4, 5, 6] because of race condition

        Collection<Integer> collection = Collections.synchronizedCollection(
                new ArrayList<>()
        );

        Thread thread1 = new Thread(() -> collection.addAll(Arrays.asList(1, 2, 3)));
        Thread thread2 = new Thread(() -> collection.addAll(Arrays.asList(4, 5, 6)));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(collection);
    }

    private static void atomicObjectsAddersDemo() {
        var status = new DocumentStatus();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DownloadFileTask(status));
            threadList.add(thread);
            thread.start();
        }

        for (var thread :
                threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(status.getTotalBytes());
    }

    private static void waitAndNotifyDemo() {
        var status = new DocumentStatus();
        Thread thread1 = new Thread(new DownloadFileTask(status));
        Thread thread2 = new Thread(() -> {
            while(!status.isDone()) {
                synchronized (status) {
                    try {
                        status.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            System.out.println(status.getTotalBytes());
        });

        thread1.start();
        thread2.start();
    }

    private static void volatileDemo() {
        var status = new DocumentStatus();
        Thread thread1 = new Thread(new DownloadFileTask(status));
        Thread thread2 = new Thread(() -> {
            while(!status.isDone()) {}
            System.out.println(status.getTotalBytes());
        });
        thread1.start();
        thread2.start();
    }

    private static void locksDemo() {
        List<Thread> threadList = new ArrayList<>();
        var status = new DocumentStatus();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DownloadFileTask(status));
            thread.start();
            threadList.add(thread);
        }

        for (var thread :
                threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(status.getTotalBytes());
    }

    private static void confinementDemo() {
        List<DownloadFileTask> taskList = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            var downloadFileTaskStatus = new DocumentStatus();
            var downloadFileTask = new DownloadFileTask(downloadFileTaskStatus);
            Thread thread = new Thread(downloadFileTask);
            thread.start();
            threadList.add(thread);
            taskList.add(downloadFileTask);
        }

        for (var thread :
                threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        var totalBytesDownloaded = taskList.stream()
                .map(t -> t.getStatus().getTotalBytes())
                .reduce(Integer::sum)
                .get();

        System.out.println(totalBytesDownloaded);
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

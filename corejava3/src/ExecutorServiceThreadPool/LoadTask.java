package ExecutorServiceThreadPool;

public class LoadTask {
    public static void load() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

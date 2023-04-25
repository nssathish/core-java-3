package Concurrency;

public class DownloadFileTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Downloading file: " + Thread.currentThread().getName());

        //pausing a thread
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Download complete: " + Thread.currentThread().getName());
    }
}

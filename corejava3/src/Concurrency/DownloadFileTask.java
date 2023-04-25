package Concurrency;

public class DownloadFileTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Downloading file: " + Thread.currentThread().getName());

        //pausing a thread
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (Thread.currentThread().isInterrupted()) break;
            System.out.println("Downloaded byte  " + i);
        }
        System.out.println("Download complete: " + Thread.currentThread().getName());
    }
}

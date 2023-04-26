package Concurrency;

public class DownloadFileTask implements Runnable {

    private DocumentStatus status;
    public DownloadFileTask(DocumentStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        System.out.println("Downloading file: " + Thread.currentThread().getName());

        //pausing a thread
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            if (Thread.currentThread().isInterrupted()) return;
//            System.out.println("Downloaded byte  " + i);
//        }

        for (int i = 0; i < 10_000; i++) {
            if(Thread.currentThread().isInterrupted()) return;
            status.incrementTotalBytes();
        }
        System.out.println("Download complete: " + Thread.currentThread().getName());
    }
}

package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class DocumentStatus {
    //private int totalBytes;
    private AtomicInteger totalBytes = new AtomicInteger(); // this atomic objects are amazing to
    // replace synchronization for counter variables
    // this makes clear that the threads go out of sync because
    // of the assembly level operations
    private volatile boolean isDone;
    private int totalFiles;
//    private Lock lock = new ReentrantLock();
    private Object totalBytesLock = new Object();
//    private Object totalFilesLock = new Object();

    public int getTotalBytes() {
        return totalBytes.get();
    }

    public void incrementTotalBytes() {
//        lock.lock();
//        synchronized (this) {  // comes with a default 'ONLY ONE monitor object'
//        synchronized (totalBytesLock) { //separate locks for separate monitoring
            totalBytes.incrementAndGet();
//        }
//        lock.unlock();
    }
    public void incrementTotalFiles() {
//        synchronized (totalFilesLock) { //separate locks for separate monitoring
            totalFiles++;
//        }
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
}

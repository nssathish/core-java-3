package Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DocumentStatus {
    private int totalBytes;
    private int totalFiles;
//    private Lock lock = new ReentrantLock();
    private Object totalByesLock = new Object();
    private Object totalFilesLock = new Object();

    public int getTotalBytes() {
        return totalBytes;
    }

    public void incrementTotalBytes() {
//        lock.lock();
//        synchronized (this) {  // comes with a default 'ONLY ONE monitor object'
        synchronized (totalByesLock) { //separate locks for separate monitoring
            totalBytes++;
        }
//        lock.unlock();
    }
    public void incrementTotalFiles() {
        synchronized (totalFilesLock) { //separate locks for separate monitoring
            totalFiles++;
        }
    }
}

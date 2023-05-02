package Concurrency;

public class DocumentStatus {
    private int totalBytes;
    private volatile boolean isDone;
    private int totalFiles;
//    private Lock lock = new ReentrantLock();
    private Object totalBytesLock = new Object();
//    private Object totalFilesLock = new Object();

    public int getTotalBytes() {
        return totalBytes;
    }

    public void incrementTotalBytes() {
//        lock.lock();
//        synchronized (this) {  // comes with a default 'ONLY ONE monitor object'
        synchronized (totalBytesLock) { //separate locks for separate monitoring
            totalBytes++;
        }
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

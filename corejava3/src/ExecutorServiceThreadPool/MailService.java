package ExecutorServiceThreadPool;

import java.util.concurrent.CompletableFuture;

public class MailService {
    public void send() {    // blocking synchronous call
        LoadTask.simulate();
        System.out.println("Mail is sent");
    }

    public CompletableFuture<Void> sendAsync() {
        LoadTask.simulate();
        return CompletableFuture.runAsync(() -> send());
    }
}

package Exceptions;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionsDemo {
    public static void show() throws IOException, AccountException {
//        FileReader reader = null;
//        try {
//            reader = new FileReader("file.txt");
//            var value = reader.read();
//            new SimpleDateFormat().parse("");
//        } catch (IOException | ParseException e) {
//            System.out.println("Could not read data");
//        } finally {
//            try {
//                if (reader != null)
//                    reader.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        var account = new Account();
        try {
            account.deposit(1);
            account.withdraw(10);
        } catch (IOException e) {
            System.out.println("Logging");
            throw new IOException();
        } catch (AccountException e) {
            throw e;
        }
    }

    public static void showTryWithResources() {
        try (
            var reader = new FileReader("file.txt");
            var writer = new FileWriter("output.txt");
        ) {
            reader.read();
        } catch (IOException e) {
            System.out.println("Could not read data");
        }
    }
}

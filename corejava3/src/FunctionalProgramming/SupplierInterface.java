package FunctionalProgramming;

import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class SupplierInterface {
    public static void show() {
        Supplier<Double> supplier = () -> Math.random();
        var random = supplier.get();
        System.out.println(random);

        //for primitive data types supplier has special interfaces
        // the getAs..() method does the 'AutoBoxing'
        DoubleSupplier doubleSupplier = () -> Math.random();
        System.out.println(doubleSupplier.getAsDouble());

        LongSupplier longSupplier = () -> { return Long.MAX_VALUE; };
        System.out.println(longSupplier.getAsLong());
    }
}

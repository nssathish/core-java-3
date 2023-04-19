package FunctionalProgramming;

public class LambdasDemo {
    private String suffix = "!";

    public void showMethod() {
        greet(message -> System.out.println(message + suffix));
        greet(message -> System.out.println(message + this.suffix));
    }
    public static void show() {
//        greet(new ConsolePrinter());
        //anonymous inner class
        greet(new Printer() {
            @Override
            public void print(String message) {
                System.out.println(message);
            }
        });

        String prefix = "-";
        //lambda expression
        greet((message) -> {
            System.out.println(prefix + message);
        });
        greet(message -> System.out.println(message));
        Printer printer = message -> System.out.println(message);
        greet(printer);
    }

    private static void greet(Printer printer) {
        printer.print("hello world");
    }

    //Method references
    private static void foo(String message) {
        System.out.println("foo: " + message);
    }
    private void bar(String message) {

    }
    public static void showMethodRef() {
        greet(System.out::println); // java.lang method
        greet(LambdasDemo::foo); // static method
        greet(new LambdasDemo("something")::bar); //object method
        var demo = new LambdasDemo("something");
        greet(demo::bar); //object method
        greet(LambdasDemo::new); //constructor method
    }

    public LambdasDemo(String suffix) {
        this.suffix = suffix;
    }
}

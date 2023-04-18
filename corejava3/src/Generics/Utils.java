package Generics;

public class Utils {
    public static <T extends Comparable<T>> T max(T first, T second) {
        return first.compareTo(second) < 0 ? second : first;
    }

    public static <K, V> void print(K key, V value) {
        System.out.println(key + " = " + value);
    }

    public static void printUser(User user) {
        System.out.println(user);
    }

    // class CAP#1 extends User {}
    // class Instructor extends User {}
    public  static void printUsers(GenericList<? extends User> users) {
        User user = users.get(0);
        //Instructor instructor = users.get(0); // IMPOSSIBLE get returns CAP#1 type object. Instructor is different. So this is not possible
    }

    // class CAP#1 extends User {}
    // class Instructor extends User {}
    public static void addUsers(
            GenericList<? super User> users) {
        users.add(new Instructor(10)); // this is possible because CAP#1 and Instructor are children of User
        // User x = users.get(0); // this is IMPOSSIBLE because at compile time compiler does not whether this is Instructor or any other type that extends User
    }
}

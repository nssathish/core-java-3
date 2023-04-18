package Collections;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class QueueDemo {
    public static void show() {
        Queue<String> queue = new ArrayDeque<>(4); // Deque - Double Ended Queue
        queue.add("c");
        queue.add("b");
        Collections.addAll(queue, "e", "f");
        System.out.println(queue);

        queue.offer("g"); // return null if the queue is full
        System.out.println(queue);
        queue.add("h");
        System.out.println(queue);

        queue.removeAll(queue);
//        queue.remove(); // throws exception
        queue.poll(); // returns null
        System.out.println(queue);

        System.out.println(queue.peek()); // peek return null if the queue is empty
//        System.out.println(queue.element()); // element() throws exception
    }
}

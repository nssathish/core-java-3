package Generics;

public class List {
    private Object[] items = new Object[10];
    private int count;

    public void add (Object item) {
        items[count++] = item;
    }

    public int get(int index) {
        return (int) items[index];
    }
}

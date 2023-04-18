package Generics;

public class User implements Comparable<User> {
    public String firstName;
    public String lastName;
    public String email;
    private int points;

    public User(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(User o) {
//        if (points < o.points) return -1;
//        if (points > o.points) return 1;
//        return 0;
        return points - o.points;
    }

    @Override
    public String toString() {
        return "Points=" + points;
    }
}

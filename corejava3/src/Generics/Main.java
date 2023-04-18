package Generics;

import java.awt.font.GlyphJustificationInfo;

public class Main {
    public static void main(String[] args) {
        var list = new List();
        //list.add(1);
//        list.add(new User());

//        System.out.println(list.get(1));

//        var genericList = new GenericList<Integer>();
//        genericList.add(1); //boxing
//        int number = genericList.get(0); //unboxing
//
//        var genericUserList = new GenericList<User>();
//        genericUserList.add(new User());
//        User user = genericUserList.get(0);
//
//        var primitiveTypeList = new GenericList<Integer>();

        var user1 = new User(10);
        var user2 = new User(20);
        if (user1.compareTo(user2) < 0)
            System.out.println("User1 is less than User2");
        else if (user1.compareTo(user2) > 0)
            System.out.println("User1 is greater than User2");
        else
            System.out.println("User1 is equal to User2");

        System.out.println(Utils.max(1,3));
        System.out.println(Utils.max(user1, user2));

        Utils.print(1, "one");

        User user = new Instructor(10);
        Utils.printUser(user);
        var users = new GenericList<User>();
        Utils.printUsers(users);

        var instructors = new GenericList<Instructor>();
        Utils.printUsers(instructors);
        // Utils.printUsers(new GenericList<Integer>()); // pass only User or Children of User

        var genericList = new GenericList<String>();
//        var iterator = list.iterator();
//        while(iterator.hasNext()) {
//            var current = iterator.next();
//            System.out.printf(current);
//        }
        genericList.add("a");
        genericList.add("b");
        for(var item : genericList)
            System.out.println(item);

    }
}

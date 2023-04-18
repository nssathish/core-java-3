package Inheritance;

public class InheritanceDemo {
    public static void main(String[] args) {
        Person person = new Employee(); // All employees are persons
        //System.out.println(person.email); 'person' is NOT an employee
        //Employee employee = new Person(); //All Persons are NOT employees

    }
}

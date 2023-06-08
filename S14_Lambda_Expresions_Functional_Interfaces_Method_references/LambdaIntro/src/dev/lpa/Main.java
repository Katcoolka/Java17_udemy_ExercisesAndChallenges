package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    record Person(String firstName, String lastName){

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Main.Person("Lucy", "Van Pelt"),
                new Person("Sally", "Brown"),
                new Person("Linus", "Van Pelt"),
                new Person("Peppermint", "Patty"),
                new Person("Charlie", "Brown")));

        //using anonymous class
        var comparatorLastName = new Comparator<Person>(){

            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        };

        people.sort(comparatorLastName);
        System.out.println(people);

        //using comparator as an argument
        people.sort(new Comparator<Person>(){

            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        });
        System.out.println(people);

        //using Lambda expression
        people.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        System.out.println(people);

        interface EnhancedComparator<T> extends Comparator<T>{
            int secondLevel(T o1, T o2);
        }
        //this anonymous class cannot be changed into Lambda Expression - it is not technically a functional interface
        //a functional interface can only have one abstract method, this means only one, and includes counting any inherited methods. Java can't determine which abstract method
        //to use for this interface, so it can't be a target type for lambda expressions.
        var comparatorMixed = new EnhancedComparator<Person>(){

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName().compareTo(o2.lastName());
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };
        people.sort(comparatorMixed);
        System.out.println(people);
    }
}
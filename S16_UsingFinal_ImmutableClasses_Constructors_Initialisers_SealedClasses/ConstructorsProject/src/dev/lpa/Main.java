package dev.lpa;

import external.Child;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------Class Constructors---------->>>");
        //you do not have to create a constructor to instantiate a class
        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);

        System.out.println("<<<-------Class Constructors----------\n");

        System.out.println("-------Record Constructors---------->>>");
        //Record
        Person joe = new Person("Joe", "01-01-1950");
        System.out.println(joe);

        Person joeCopy = new Person(joe);
        System.out.println(joeCopy);

        System.out.println("<<<-------Record Constructors----------\n");

        System.out.println("-------Enum Constructors---------->>>");
        Generation g = Generation.BABY_BOOMER;

        System.out.println("<<<-------Enum Constructors----------\n");
    }
}
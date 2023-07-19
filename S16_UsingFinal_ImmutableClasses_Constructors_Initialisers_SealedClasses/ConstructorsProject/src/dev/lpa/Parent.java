package dev.lpa;

public class Parent {

    //static initializer- is called the first time a class is referenced or constructed.
    //A class can have any number of static initialization blocks.
    //They can be declared anywhere in the class body.
    //They're called in the order they appear in the source code.
    //You might use this to set up some environment data or log information, that's related to the //class before it can be used.
    //this will get executed only during the class's construction and not each instance's construction.

    static {
        System.out.println("Parent static initializer: class being constructed");
    }

    private final String name;
    private final String dob;
    protected final int siblings;

    //when we use final fields, we have to initialize them

    //instance initializer block - block of code declared directly in a class body
    //this code gets executed when an instance of the class is created
    //they are executed before any code in class constructors is executed
    //you can have multiple initializer blocks
    {
//        name = "John Doe";
//        dob = "01/01/1900";
        System.out.println("in Parent Initializer");
    }

//    public Parent() {
//        System.out.println("in Prent's No Args constructor");
//    }

    public Parent(String name, String dob, int siblings) {
        this.name = name;
        this.dob = dob;
        this.siblings = siblings;
        System.out.println("in Parent Constructor");
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getDob() {
        return dob;
    }

//    public void setDob(String dob) {
//        this.dob = dob;
//    }

    @Override
    public String toString() {
        return "name = '" + name + '\'' + ", dob = '" + dob + '\'';
    }
}

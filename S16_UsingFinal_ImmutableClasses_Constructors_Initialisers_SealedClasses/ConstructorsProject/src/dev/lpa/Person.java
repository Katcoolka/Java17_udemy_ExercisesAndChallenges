package dev.lpa;

public record Person(String name, String dob) {

    //canonical constructor - you cannot create another constructor
//    public Person(String name, String dob) {
//        this.name = name;
//        this.dob = dob.replace('-', '/');
//    }

    public Person(Person p) {
        this(p.name, p.dob);
    }

    //compact constructor - declared without parenthesis =  no arguments
    //cannot have canonical constructor, when you use this one
    public Person {
        if (dob == null) throw new IllegalArgumentException("Bad data");
        dob = dob.replace('-', '/');
    }
}

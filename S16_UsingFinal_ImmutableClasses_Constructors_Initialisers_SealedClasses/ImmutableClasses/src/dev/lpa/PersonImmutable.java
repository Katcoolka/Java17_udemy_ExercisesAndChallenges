package dev.lpa;

import java.util.Arrays;

public class PersonImmutable {
    //Creating Immutable class
    //1.) make all fields final
    private final String name;
    private final String dob; //day of birth
    //6.) private changed to protected to allow subclasses access
    protected final PersonImmutable[] kids;

    //4.) create defensive copies where needed
    public PersonImmutable(String name, String dob, PersonImmutable[] kids) {
        this.name = name;
        this.dob = dob;
        this.kids = kids == null ? null : Arrays.copyOf(kids, kids.length);
    }

    public PersonImmutable(String name, String dob) {
        this(name, dob, null);
    }

    //5.) to allow subclasses to create new Person we need to create new constructor
    protected PersonImmutable(PersonImmutable person) {
        //7.) changing constructor to prevent mutability
        this(person.getName(), person.getDob(), person.getKids());
//        this.name = person.name;
//        this.dob = person.dob;
//        this.kids = person.kids;
    }

    //2.) remove setters

    //3.) Create defensive copies of all getters
    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }
    //8.) adding final
    public final PersonImmutable[] getKids() {
        return kids == null ? null : Arrays.copyOf(kids, kids.length);
    }


    @Override
    public String toString() {
        String kidString = "n/a";
        if (kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i -> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return name + ", dob = " + getDob() + ", kids = " + kidString;
    }
}

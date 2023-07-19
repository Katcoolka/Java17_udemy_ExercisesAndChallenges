package dev.lpa;

import java.util.Arrays;

record Person(String name, String dob, Person[] kids) {
    //3.) another way to create a deep copy is to add custom constructor
    public Person(Person p) {
        this(p.name, p.dob, p.kids == null ? null : Arrays.copyOf(p.kids, p.kids.length));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}


public class Main {
    public static void main(String[] args) {

        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};

        System.out.println("------------- 1.) Shallow copy-------------->>>");
//        Person[] personsCopyShallow = Arrays.copyOf(persons, persons.length);
//
//        //change the second child for Jill, from jim to jane.
//        var jillsKidsShallow = personsCopyShallow[4].kids();
//        jillsKidsShallow[1] = jane;
//
//        //to check if both arrays refer to the same records
//        for (int i = 0; i < 5; i++) {
//            if (persons[i] == personsCopyShallow[i]) {
//                System.out.println("Equal references " + persons[i]);
//            }
//        }

        //code prints the reference, from the original array, so these aren't the copies.
        //console output:
        // Equal references Person{name='Joe', kids=null}
        //Equal references Person{name='Jim', kids=null}
        //Equal references Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
        //Equal references Person{name='Jane', kids=null}
        //Equal references Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        System.out.println("<<<-------------Shallow copy--------------\n");

        System.out.println("------------- 2.) Deep copy-------------->>>");
//        Person[] personsCopyDeep = new Person[5];
//
//        for (int i = 0; i < 5; i++) {
//            Person current = persons[i];
//            //var kids = current.kids();
//            //to prevent shallow copy:
//            var kids = current.kids()== null ? null : Arrays.copyOf(current.kids(), current.kids().length);
//            personsCopyDeep[i] = new Person(current.name(), current.dob(), kids);
//        }
//        //change the second child for Jill, from jim to jane.
//        var jillsKidsDeep = personsCopyDeep[4].kids();
//        jillsKidsDeep[1] = jane;
//
//        //to check if both arrays refer to the same records
//        for (int i = 0; i < 5; i++) {
//            if (persons[i] == personsCopyDeep[i]) {
//                System.out.println("Equal references " + persons[i]);
//            }
//        }
//        //no reference, because they do not refer to the same objects
//        //new instances were created
//
//        //to check through comparing last array elements
//        System.out.println(persons[4]);
//        System.out.println(personsCopyDeep[4]);

        //console output:
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        //=> results due to deep copy of Person, but shallow copy of Jill

        //console output after update:
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        //no side effects for original array of persons
        System.out.println("<<<-------------Deep copy--------------\n");

        System.out.println("------------- 3.) Deep copy with custom constructor-------------->>>");
//        Person[] personsCopyDeep = new Person[5];
//
//        for (int i = 0; i < 5; i++) {
//            personsCopyDeep[i] = new Person(persons[i]);
//        }
//        //change the second child for Jill, from jim to jane.
//        var jillsKidsDeep = personsCopyDeep[4].kids();
//        jillsKidsDeep[1] = jane;
//
//        //to check if both arrays refer to the same records
//        for (int i = 0; i < 5; i++) {
//            if (persons[i] == personsCopyDeep[i]) {
//                System.out.println("Equal references " + persons[i]);
//            }
//        }
//        //no reference, because they do not refer to the same objects
//        //new instances were created
//        //to check through comparing last array elements
//        System.out.println(persons[4]);
//        System.out.println(personsCopyDeep[4]);
        //console output:
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        System.out.println("<<<-------------Deep copy with custom constructor --------------\n");

        System.out.println("------ 4.) Deep copy with custom constructor and setAll method----->>>");
//        Person[] personsCopyDeep = new Person[5];
//        Arrays.setAll(personsCopyDeep, i -> new Person(persons[i]));
//
//        //change the second child for Jill, from jim to jane.
//        var jillsKidsDeep = personsCopyDeep[4].kids();
//        jillsKidsDeep[1] = jane;
//
//        //to check if both arrays refer to the same records
//        for (int i = 0; i < 5; i++) {
//            if (persons[i] == personsCopyDeep[i]) {
//                System.out.println("Equal references " + persons[i]);
//            }
//        }
//        //no reference, because they do not refer to the same objects
//        //new instances were created
//        //to check through comparing last array elements
//        System.out.println(persons[4]);
//        System.out.println(personsCopyDeep[4]);
//        //console output:
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
        //Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        System.out.println("<<<-----Deep copy with custom constructor and setAll method -----\n");

        System.out.println("------ 5.) Shallow copy with custom constructor and clone method----->>>");
        Person[] personsCopyDeep = persons.clone();
        //change the second child for Jill, from jim to jane.
        var jillsKidsDeep = personsCopyDeep[4].kids();
        jillsKidsDeep[1] = jane;

        //to check if both arrays refer to the same records
        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopyDeep[i]) {
                System.out.println("Equal references " + persons[i]);
            }
        }
        System.out.println(persons[4]);
        System.out.println(personsCopyDeep[4]);
        //console output:
       /* Equal references Person{name='Joe', kids=null}
        Equal references Person{name='Jim', kids=null}
        Equal references Person{name='Jack', kids=[Person{name='Joe', kids=null}, Person{name='Jim', kids=null}]}
        Equal references Person{name='Jane', kids=null}
        Equal references Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}
        Person{name='Jill', kids=[Person{name='Joe', kids=null}, Person{name='Jane', kids=null}]}*/
        System.out.println("<<<-----Shallow copy with custom constructor and clone method -----\n");
    }
}
package dev.lpa;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);
        //1. creating a List of Students
        List<Student> students = new ArrayList<>(List.of(bob, bill));

        //2. shallow copy
        List<Student> studentsFirstCopy = new ArrayList<>(students);

        //5. creating second copy of students
        List<Student> studentsSecondCopy = List.copyOf(students);

        //10. creating unmodifiable list = view
        List<Student> studentsThirdCopy = Collections.unmodifiableList(students);

        //3. adding new student to first copy => original unchanged, first copy updated
        studentsFirstCopy.add(new Student("Bonnie", new StringBuilder()));

        //6. adding new student to second copy =>code compiles, but raise an UnsupportedOperationException
        //studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));

        //7. trying .set() => code compiles, but raise an UnsupportedOperationException
        //studentsSecondCopy.set(0, new Student("Bonnie", new StringBuilder()));

        //8. trying sort() => code compiles, but raise an UnsupportedOperationException
        //studentsSecondCopy.sort(Comparator.comparing(Student::getName));

        //9.trying sort() on studentsFirstCopy => it runs, and it is sorted alphabetically
        studentsFirstCopy.sort(Comparator.comparing(Student::getName));

        //11. trying .set() => code compiles, but raise an UnsupportedOperationException
        //studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder()));

        //12. adding student Bonnie to original list
        students.add(new Student("Bonnie", new StringBuilder()));
        bobsNotes.append("Bob was one of my first students.");

        //4.adding bonnies notes
        StringBuilder bonniesNotes = studentsFirstCopy.get(2).getStudentNotes();
        bonniesNotes.append("Bonnie is taking 3 of my courses");

        System.out.println("-----Original List--->>>");
        students.forEach(System.out::println);
        System.out.println("------------------------\n");

        System.out.println("-----FirstCopy List--->>>");
        studentsFirstCopy.forEach(System.out::println);
        System.out.println("------------------------\n");

        System.out.println("-----SecondCopy List--->>>");
        studentsSecondCopy.forEach(System.out::println);
        System.out.println("------------------------\n");

        System.out.println("-----ThirdCopy List--->>>");
        studentsThirdCopy.forEach(System.out::println);
        System.out.println("------------------------\n");
        //This method doesn't return a copy, it actually returns a view,
        //an unmodifiable view, of the backing list. This list, students Third Copy, is always
        //going to reflect what's in the Students list, but I could pass this view to consumers.
        //This feature creates a kind of window, to the most current state of a list,
        //without allowing a client to modify it. In other words, you can pass a reference
        //to a mutating list, but prevent changes through that particular reference.

    }
}
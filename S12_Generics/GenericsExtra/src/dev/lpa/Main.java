package dev.lpa;

import dev.lpa.model.LPAStudent;
import dev.lpa.model.Student;
import dev.lpa.util.QueryItem;
import dev.lpa.util.QueryList;

import java.util.ArrayList;
import java.util.List;

record Employee(String name) implements QueryItem {

    @Override
    public boolean matchfieldValue(String fieldName, String value) {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {

        int studentCount = 10;
        //Student
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        students.add(new LPAStudent());
        //printList(students);
        printMoreLists(students);

        //LPA Student
        List<LPAStudent> lpaStudents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        //printList(lpaStudents);
        printMoreLists(lpaStudents);
        testList(new ArrayList<String>(List.of("Able", "Barry", "Charlie")));
        testList(new ArrayList<Integer>(List.of(1, 2, 3)));

        var querylist = new QueryList<>(lpaStudents);
        var matches = querylist.getMatches("Course", "Python");
        printMoreLists(matches);

        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
        printMoreLists(students2021);

        //QueryList<Employee> employeeList = new QueryList<>();
    }

    //wildcard ? means the type is unknown
    public static void printMoreLists(List<? extends Student> students) {
        //this method, with wildcards, has no way of knowing the type of the list
        //elements, that actually get passed to it. They're unknown, except it could be one of
        //many types, sub classed from Student. In other words, the compiler doesn't have
        //enough information to enforce the type checking rules.
//        Student last = students.get(students.size()-1);
//        students.set(0, last);
        for (var student : students) {
            System.out.println(student);
            //System.out.println(student.getYearStarted() + ": " + student);
        }
        System.out.println();
    }

    public static void testList(List<?> list) {
        for (var element : list) {
            if (element instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }

    //=================================================================================
    //generic method example
    //The generic method type parameter is separate (different) from a generic class type parameter.
//    public static <T extends Student> void printList(List<T> students) {
//        for (var student : students) {
//            System.out.println(student.getYearStarted() + ": " + student);
//        }
//        System.out.println();
//    }
//=================================================================================

    //two methods clash, because they have the same type erasure.
//A List has no upper bound declared for it, so it always resolves, in byte code, to a List of Object.
//In both of these cases, the method parameters,after type erasure, would be a List of Objects. This means these methods won't overload each other in the byte code, they'd have exactly the same signature, the same name, and parameter type.

//    public static void testList(List<String> list) {
//        for (var element : list) {
//            System.out.println("String: " + element.toUpperCase());
//        }
//    }
//
//    public static void testList(List<Integer> list) {
//        for (var element : list) {
//            System.out.println("Integer: " + element.floatValue());
//        }
//    }
}
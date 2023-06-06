package dev.lpa;

import dev.lpa.domain.Employee;
import dev.lpa.domain.EmployeeComparator;
import dev.lpa.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RunMethods {

    public static void main(String[] args) {

        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macy's"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));

        //we have implemented 3 Comparators in this package
        var c0 = new EmployeeComparator<StoreEmployee>(); //c0 is an instance of the top levelEmployeeComparator class, typed with the StoreEmployee class
        var c1 = new Employee.EmployeeComparator<StoreEmployee>(); //c1  uses the static nested class on the Employee class.
        var c2 = new StoreEmployee().new StoreComparator<StoreEmployee>(); //c2  is using the inner class on StoreEmployee.

        //using Local class
        class NameSort<T> implements Comparator<StoreEmployee> {

            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }

        var c3 = new NameSort<StoreEmployee>();

        //================ Anonymous Class start =========================
        //The class body, which is represented by these opening and closing curly braces, is telling Java that this isn't an instance of an interface. But actually, it's special syntax, that means this is an anonymous class being created, that implements Comparator.
        //And because this unnamed class is implementing an interface, this means we still need to implement any abstract methods on that interface. We'll do that for the compare to method.
        var c4 = new Comparator<StoreEmployee>() {

            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        //================ Anonymous Class end =========================

        //any of these comparators work with storeEmployees when we pass it to the sortIt method
        sortIt(storeEmployees, c0);
        sortIt(storeEmployees, c1);
        sortIt(storeEmployees, c2);
        sortIt(storeEmployees, c3);
        sortIt(storeEmployees, c4);

        //creating an anonymous class directly as a method argument//used before lambda expressions were introduced in Java 8:
        sortIt(storeEmployees, new Comparator<StoreEmployee>() {

            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        //changing the code and using lambda expression => it will look like this:
        sortIt(storeEmployees, (o1, o2) -> o1.getName().compareTo(o2.getName()));
    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {
        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for (var employee : list) {
            System.out.println(employee);
        }
    }
}

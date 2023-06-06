package dev.lpa;

import dev.lpa.domain.Employee;
import dev.lpa.domain.EmployeeComparator;
import dev.lpa.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13151, "Laura", 2020),
                new Employee(10050, "Jim", 2018)));

        //using nested class test
        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());

//        var comparator = new EmployeeComparator<>();
//        employees.sort(comparator);

        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("Store members");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macy's"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")));
        //using Inner class test
//        var genericEmployee =  new StoreEmployee();
//        var comparator = genericEmployee.new StoreComparator<>();

        //To create an instance of an inner class, you first must have an instance of the Enclosing Class. From that instance you call .new, followed by the inner class name and the parentheses,
        var comparator = new StoreEmployee().new StoreComparator<>();
        storeEmployees.sort(comparator);

        for (StoreEmployee e : storeEmployees) {
            System.out.println(e);
        }

        //using Local class test
        System.out.println("With Pig Latin Names");
        addPigLatinName(storeEmployees);
    }

    //=================== LOCAL CLASS example of usage =================================
    public static void addPigLatinName(List<? extends StoreEmployee> list) {

        String lastName = "Piggy";

        //================ Local class start ======================
        //Local class - usually used for one specific purpose
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
            private String pigLatinName;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName + " " + lastName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString() + " " + pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return pigLatinName.compareTo(o.pigLatinName);
            }
        }
        //================ Local class end  ======================

        List<DecoratedEmployee> newList = new ArrayList<>(list.size());

        for (var employee : list) {
            String name = employee.getName();
            String piLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedEmployee(piLatin, employee));
        }

        newList.sort(null);
        for (var dEmployee : newList) {
            System.out.println(dEmployee.originalInstance.getName() + " " + dEmployee.pigLatinName);
        }
    }
}
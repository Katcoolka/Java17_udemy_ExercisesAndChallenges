package dev.lpa.domain;

import java.util.Comparator;

public class Employee {
    //=============== static nested class ========================>
    public static class EmployeeComparator<T extends Employee> implements Comparator<Employee> {

        private String sortType;

        public EmployeeComparator() {
            this("name");
        }

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee o1, Employee o2) {
            //return o1.getName().compareTo(o2.getName());
            if(sortType.equalsIgnoreCase("yearStarted")){
                return o1.yearStarted - o2.yearStarted;
            }
            //now we can access the fields directly:
            return o1.name.compareTo(o2.name);
        }
    }
    //<===================== static nested class===================

    private int employeeID;
    private String name;
    private int yearStarted;

    public Employee() {
    }

    public Employee(int employeeID, String name, int yearStarted) {
        this.employeeID = employeeID;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%d %-8s %d".formatted(employeeID, name, yearStarted);
    }
}

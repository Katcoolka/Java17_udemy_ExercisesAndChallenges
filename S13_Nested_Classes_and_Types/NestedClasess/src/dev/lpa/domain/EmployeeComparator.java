package dev.lpa.domain;

import java.util.Comparator;

//this code from class EmployeeComparator is later copied and used as nested class in the Employee class
public class EmployeeComparator<T extends Employee> implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

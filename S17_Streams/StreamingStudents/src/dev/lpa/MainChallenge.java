package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainChallenge {
    public static void main(String[] args) {
        System.out.println("\n--- Terminal Operations, Part 2 --->>>\n");

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java MasterClass", 100);

        //crating local variable
        Course jgames = new Course("JGAME", "Creating games in Java");
        //change in class Student the method getRandomStudent => Lecture from 1 to 30

        //generate 5000 Students

        //List<Student> students = Stream
        //.iterate(1, s -> s <= 5000, s -> s++)
        //.iterate(1, s -> s <= 5000, s -> s + 1) //to get 1 to 10 integers
//                .limit(10)
//                .peek(System.out::println)
//                .map(s -> Student.getRandomStudent(jmc, pymc))
//                .toList();
        // output is 10 ones printed out => because of post increment operator
        //the post increment operator,in this method, only does the incrementing after the value, is returned as a result.
        //In other words, this will always be 1. You can see this if you peek at this code.

        List<Student> students = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

        System.out.println("--- 1) get the average percent complete for the Java master class. --->>>");
        double totalPercent = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .reduce(0, Double::sum);
        //.sum(); //we can get the same result using .sum as with .reduce

        double avePercent = totalPercent / students.size();
        System.out.printf("Average Percentage complete = %.2f%% %n", avePercent);

        System.out.println("\n<<<----******----\n");

        System.out.println("--- 2) use this result multiplied by 1.25 to collect a group of students (List or Set)--->>>");
        //add another local variable, which will be the minimum percentage complete, that we want these students to have achieved.
        int topPercent = (int) (1.25 * avePercent);
        System.out.printf("Best Percentage Completed = %d%% %n", topPercent);

        //create a collection of students that exceed this threshold, and who are active.
        List<Student> hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") > topPercent)
                .toList();
        System.out.println("hardWorkers = " + hardWorkers.size());

        System.out.println("\n<<<----******----\n");
        System.out.println("--- 3)  sort these students, and pick 10, using those who've been the students the longest, as the criteria for getting selected for course--->>>");

        Comparator<Student> longTermStudent = Comparator.comparing(Student::getYearEnrolled);
        List<Student> hardWorkers1 = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") > topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .toList();

        hardWorkers1.forEach(s -> {
            s.addCourse(jgames);
            System.out.println(s);
        });

        System.out.println("\n<<<----******----\n");

        //same studentId result as above printed in a row
        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") > topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .toList()
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println("\n<<<----******----\n");

        //same studentId result as above printed and sorted in a row using .collect(Collectors.toList()
        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") > topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(Collectors.toList())//this collection is modifiable
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println("\n<<<----******----\n");

        //same studentId result as above printed in a row using .collect(Collectors.toSet()) - not sorted
        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") > topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(Collectors.toSet())//not ordered Set
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println("\n<<<----******----\n");

        //studentId result printed in a row using TreeSet
        //we need new Comparator - using longTermStudent returns only unique Student

        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") > topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(() -> new TreeSet<>(longTermStudent), TreeSet::add, TreeSet::addAll)
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println("\n<<<----******----\n");

        //same studentId result as above printed in a row using new Comparator
        Comparator<Student> uniqueSorted = longTermStudent.thenComparing(Student::getStudentId);

        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") > topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .collect(() -> new TreeSet<>(uniqueSorted), TreeSet::add, TreeSet::addAll)
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println("\n<<<----******----\n");
        System.out.println("<<<------------------------------------------->>>");
    }
}

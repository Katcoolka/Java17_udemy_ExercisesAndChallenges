package dev.lpa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*; //static import with *

public class MainMapping {
    public static void main(String[] args) {

        System.out.println("\n<<<---------- Streams to maps ----------->>>\n");

        //use this list of students to create a map, that'll be keyed by country code, and have a List of students
        // for each country.
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        List<Student> students = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("----------------------");
        System.out.println("\n--- counts by country, for students who are 25 years or younger --->>>");
        int minAge = 25;
        //In this case, we are using an overloaded version of the groupingBy method, on Collectors.
        //now we get the counts by country, for students who are 25 years or younger.
        var youngerSet = students.stream().collect(groupingBy(Student::getCountryCode,
                filtering(s -> s.getAge() <= minAge, toList())));

        youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("\n--- collector returning map of boolean values to split population into 2 groups --->>>");
        //partitioningBy is another static method on Collectors class
        var experienced = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Experienced Students = " + experienced.get(true).size());

        //counting method - same result as above
        var expCount = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
        System.out.println("Experienced Students = " + expCount.get(true));

        //Experienced and Active Students using lambda expression
        var experiencedAndActive = students.stream()
                .collect(partitioningBy(s -> s.hasProgrammingExperience() && s.getMonthsSinceActive() == 0,
                        counting()));
        System.out.println("Experienced and Active Students = " + experiencedAndActive.get(true));

        System.out.println("\n--- multi level map (nested maps) --->>>");
        var multiLevel = students.stream()
                .collect(groupingBy(Student::getCountryCode,
                        groupingBy(Student::getGender)));
        multiLevel.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((key1, value1) -> System.out.println("\t" + key1 + " " + value1.size()));
        });

        System.out.println("\n---------- Streams to maps -----------<<<\n");
        System.out.println("-".repeat(90));

        System.out.println("\n<<<---------- Maps to Streams ----------->>>");
        System.out.println("\n--- Flat Map --->>>");

        //long studentCount = students.stream().count();
        //count of students
        long studentBodyCount = 0;
        for (var list : experienced.values()) {
            studentBodyCount += list.size();
        }
        System.out.println("studentBodyCount = " + studentBodyCount);

        //count of students active in the last 3 months
        studentBodyCount = experienced.values().stream()
                .mapToInt(l -> l.size())
                .sum();
        System.out.println("studentBodyCount = " + studentBodyCount);

        //mapping to stream for students active in last 3 months
        studentBodyCount = experienced.values().stream()
                .map(l -> l.stream()
                        .filter(s -> s.getMonthsSinceActive() <= 3)
                        .count())
                .mapToLong(l -> l)
                .sum();
        System.out.println("studentBodyCount active in last 3 months = " + studentBodyCount);

        //flat map operation (to not have a stream of streams)
        long count = experienced.values().stream()
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students (last 3 months) = " + count);

        System.out.println("\n--- multi level map (nested maps) using flatMap --->>>");
        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream()
                        .flatMap(l -> l.stream())
                )
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students (last 3 months) = " + count);

        //same as above, but nicer
        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active students (last 3 months) = " + count);

        System.out.println("\n>>>---------- Maps to Streams -----------<<<\n");
    }
}

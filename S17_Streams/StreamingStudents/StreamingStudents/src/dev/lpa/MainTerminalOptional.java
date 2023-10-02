package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MainTerminalOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students =
                Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                        .limit(1000)
                        .toList();

        System.out.println("\n<<<---------- More terminal operations ----------->>>\n");

        int minAge = 18;
        //findAny method
        System.out.println("--- findAny method --->>>");
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findAny()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        //findFirst method to find first instance
        System.out.println("\n--- findFirst method to find first instance --->>>");
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findFirst()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        //findFirst and sorted - provides the youngest student
        System.out.println("\n--- findFirst and sorted - provides the youngest student --->>>");
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .sorted(Comparator.comparing(Student::getAge))
                .findFirst()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        //findFirst with applied suggested min replacement - provides the youngest student
        System.out.println("\n--- findFirst with applied suggested min replacement - provides the youngest student" +
                " --->>>");
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        //findFirst with applied suggested max replacement - provides the oldest student (around 21)
        System.out.println("\n--- findFirst with applied suggested max replacement - provides the oldest student " +
                "(around 21) --->>>");
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        //mapToInt method - provides list of country abreviations
        System.out.println("\n--- mapToInt method --->>>");
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse(a -> System.out.printf("Avg age under 21: %.2f%n", a),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        //map method
        System.out.println("\n--- map method --->>>");
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .map(Student::getCountryCode)
                .distinct()
                .reduce((a, b) -> String.join(",", a, b))
                .ifPresentOrElse(System.out::println, () -> System.out.println("None found"));

        students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .map(l -> String.join(",", l))
                .filter(l -> l.contains("AU"))
                .findAny()
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Missing AU"));

        System.out.println("\n---------- More terminal operations -----------<<<\n");
    }
}

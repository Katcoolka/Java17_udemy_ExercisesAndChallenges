package dev.lpa;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java MasterClass");

        //test code
//        Student tim = new Student("AU", 2019, 30, "M", true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10,5,2019);
//        tim.watchLecture("PYMC", 7,7,2020);
//        System.out.println(tim);

//        Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
//                .limit(10)
//                .forEach(System.out::println);

        System.out.println("<<<------ Challenge: Terminal Operations ----->>>");
        System.out.println("\n--- 1.) count of male students --->>>\n");
        //how many male students we have in all courses (from 1000 students)

        var maleStudents = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000);

        maleStudents = maleStudents.filter(s -> s.getGender().equals("M"));
        System.out.println("# of male students " + maleStudents.count());
        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 2.) count of students by gender using arrays--->>>\n");
        //how many female students we have in all courses
        //we do not want to create new stream of students each time => array of students
        Student[] students = new Student[1000];
        Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

        var maleStudents1 = Arrays.stream(students)
                .filter(s -> s.getGender().equals("M"));

        System.out.println("# of male students " + maleStudents1.count());

        for (String gender : List.of("M", "F", "U")) {
            var myStudents = Arrays.stream(students)
                    .filter(s -> s.getGender().equals(gender));
            System.out.println("# of " + gender + " students " + myStudents.count());
        }
        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 3.) students by age --->>>\n");

        List<Predicate<Student>> list = List.of(
                (s) -> s.getAge() < 30,
                (Student s) -> s.getAge() >= 30 && s.getAge() < 60
        );
        long total = 0;
        for (int i = 0; i < list.size(); i++) {
            var myStudents = Arrays.stream(students).filter(list.get(i));
            long cnt = myStudents.count();
            total += cnt;
            System.out.printf("# of students (%s) = %d%n",
                    i == 0 ? " < 30" : ">= 30 & < 60", cnt);
        }
        System.out.println("# of students >= 60 = " + (students.length - total));
        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 4.) students - age summaryStatistics --->>>\n");

        var ageStream = Arrays.stream(students)
                .mapToInt(Student::getAgeEnrolled);
        System.out.println("Stats for Enrollment age = " + ageStream.summaryStatistics());
        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 5.) students - current age summaryStatistics --->>>\n");

        var currentAgeStream = Arrays.stream(students)
                .mapToInt(Student::getAge);
        System.out.println("Stats for Current age = " + currentAgeStream.summaryStatistics());
        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 5.) What countries are they from?--->>>\n");
        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 6.) Long term students active in past year?--->>>\n");
        boolean longTerm = Arrays.stream(students)
                .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12));
        System.out.println("longTerm students? " + longTerm);

        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 7.) How many Long term students were active in the past year?--->>>\n");
        long longTermCount = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .count();
        System.out.println("longTerm students count = " + longTermCount);

        System.out.println("\n<<<----******----\n");

        System.out.println("\n--- 8.) List of 5 students with no Programming Experience--->>>\n");
        Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n<<<----******----\n");


        System.out.println("<<<------------------------------------------->>>");
        System.out.println("<<<------------------------------------------->>>");
        System.out.println("<<<------------------------------------------->>>");
        System.out.println("<<<------------------------------------------->>>");

        System.out.println("\n--- Terminal operations for processing and transforming stream elements--->>>\n");
        Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toList()//unmodifiable list
                .forEach(System.out::println);

        System.out.println("\n<<<----******----\n");
        //creating unmodifiable list
        List<Student> longTimeLearners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toList();


        System.out.println("\n<<<----******----\n");

        var longTimeLearners1 = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                //.toArray(size -> new Student[size]); //can be replaced with method reference
                .toArray(Student[]::new);

        System.out.println("\n<<<----******----\n");
        //using collector
        var learners = Arrays.stream(students)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) &&
                        (s.getMonthsSinceActive() < 12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList());

        Collections.shuffle(learners);

        System.out.println("\n<<<----******----\n");
        System.out.println("<<<------------------------------------------->>>");
    }
}
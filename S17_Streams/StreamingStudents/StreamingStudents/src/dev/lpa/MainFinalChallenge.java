package dev.lpa;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainFinalChallenge {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        //create list of students
        int currentYear = LocalDate.now().getYear();
        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc, jgames))
                .filter(s -> s.getYearEnrolled() >= (currentYear - 4))
                .limit(10000)
                .toList();

        //summary statistic of this list
        System.out.println(students
                .stream()
                .mapToInt(Student::getYearEnrolled)
                .summaryStatistics());

        //print a sublist of 10 students
        students.subList(0, 10).forEach(System.out::println);

        //summary statistic of this sublist
        System.out.println(students
                .stream()
                .mapToInt(s -> s.getEngagementMap().size())
                .summaryStatistics());

        //distribution of students taking each class
        var mappedActivity = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.counting()));

        mappedActivity.forEach((k, v) -> System.out.println(k + " " + v));

        //counts of students taking one, two or three classes
        var classCounts = students.stream()
                .collect(Collectors.groupingBy(s -> s.getEngagementMap().size(),
                        Collectors.counting()));

        classCounts.forEach((k, v) -> System.out.println(k + " " + v));

        //average percent complete for each class
        var percentages = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.averagingDouble(CourseEngagement::getPercentComplete)));

        percentages.forEach((k, v) -> System.out.println(k + " " + v));

        //map of statistics
        var percentagesStat = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.summarizingDouble(CourseEngagement::getPercentComplete)));

        percentagesStat.forEach((k, v) -> System.out.println(k + " " + v));

        //nested map using the course code as the key for the nested map
        var yearMap = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.groupingBy(CourseEngagement::getLastActivityYear,
                                Collectors.counting())));

        yearMap.forEach((k, v) -> System.out.println(k + " " + v));

        //how many students enrolled each year (counts by enrolment year)
        students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getEnrollmentYear,
                        Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + ": " + v));

        //enrollments by course in each year
        students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getEnrollmentYear,
                        Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.counting())))
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

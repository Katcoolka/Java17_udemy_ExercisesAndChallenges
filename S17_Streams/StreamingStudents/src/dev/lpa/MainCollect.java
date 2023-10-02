package dev.lpa;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {

    public static void main(String[] args) {

        System.out.println("\n--- Using Stream's collect & reduce terminal operations--->>>\n");

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java MasterClass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .toList();
        System.out.println("\n<<<---- Students from Australia ----\n");

        Set<Student> australianStudents = students.stream()
                .filter((s) -> s.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());
        System.out.println("# of Australian Students = " + australianStudents.size());

        System.out.println("\n<<<----******----\n");

        System.out.println("\n<<<---- Students under 30 ----\n");
        Set<Student> underThirty = students.stream()
                .filter((s) -> s.getAgeEnrolled() < 30)
                .collect(Collectors.toSet());
        System.out.println("# of Under Thirty Students = " + underThirty.size());

        System.out.println("\n<<<----******----\n");

        System.out.println("\n<<<---- Students under 30 from Australia using Id----\n");
        Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(
                Student::getStudentId));
        youngAussies1.addAll(australianStudents);
        youngAussies1.retainAll(underThirty);
        youngAussies1.forEach((s) -> System.out.print(s.getStudentId() + " "));

        System.out.println("\n<<<----******----\n");

        //creating new set, which is not ordered, but contains the same Ids as previous set
//        Set<Student> youngAussies2 = students.stream()
//                .filter((s) -> s.getAgeEnrolled() < 30)
//                .filter((s) -> s.getCountryCode().equals("AU"))
//                .sorted() // this method is redundant in this case
//                .collect(Collectors.toSet());
//        youngAussies2.forEach((s) -> System.out.print(s.getStudentId() + " "));

        System.out.println("\n<<<----******----\n");

        System.out.println("\n<<<----supplier, accumulator and combiner----\n");
        //creating new set, with sorted result through  TreeTet in the collect method
        //we've set up our first collect method with a supplier, accumulator and combiner.
        //.sorted method is no longer marked as redundant at this point
        Set<Student> youngAussies3 = students.stream()
                .filter((s) -> s.getAgeEnrolled() < 30)
                .filter((s) -> s.getCountryCode().equals("AU"))
                //.collect(TreeSet::new, TreeSet::add, TreeSet::addAll); //supplier, accumulator and combiner => exception in thread - so we have to change the first argument to use lambda expression =>
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);

        youngAussies3.forEach((s) -> System.out.print(s.getStudentId() + " "));

        System.out.println("\n<<<----******----\n");

        System.out.println("\n<<<----reduce terminal operation----\n");
        String countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce("", (r, v) -> r + " " + v);
        System.out.println("countryList = " + countryList);

        System.out.println("\n<<<----******----\n");
        System.out.println("<<<------------------------------------------->>>");
    }

}

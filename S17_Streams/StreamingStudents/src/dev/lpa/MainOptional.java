package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {
    public static void main(String[] args) {

        System.out.println("\n--- Optional class --->>>\n");

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java MasterClass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .collect(Collectors.toList());

        //Optional using null
        Optional<Student> o1 = getStudent(null, "first");
        System.out.println("Empty " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);

        System.out.println("\n<<<----******----\n");

        //Optional using new ArrayList<>()
        Optional<Student> o2 = getStudent(new ArrayList<>(), "first");
        System.out.println("Empty " + o2.isEmpty() + ", Present = " + o2.isPresent());
        System.out.println(o2);
        //System.out.println(o2.get());
        o2.ifPresent(System.out::println);//nothing is returned as it is empty
        o2.ifPresentOrElse(System.out::println, () -> System.out.println("---> Empty")); //to see result

        System.out.println("\n<<<----******----\n");

        //Optional using List students
        Optional<Student> o3 = getStudent(students, "first");
        System.out.println("Empty " + o3.isEmpty() + ", Present = " + o3.isPresent());
        System.out.println(o3);
        //System.out.println(o3.get()); //optional without isPresent check requested
        //using if to check the isPresent
//        if (o3.isPresent()){
//            System.out.println(o3.get());
//        }
        //same if condition using lambda expression
        o3.ifPresent(System.out::println);

        System.out.println("\n<<<----******----\n");

        //Optional using List students but adding null element at 0 index
        //we will get null pointer exception
        //we have to replace Optional.of with Optional.ofNullable in getStudent method

        //students.add(0, null);
        Optional<Student> o4 = getStudent(students, "first");
        System.out.println("Empty " + o4.isEmpty() + ", Present = " + o4.isPresent());
        System.out.println(o4);

        System.out.println("\n<<<----******----\n");

        //checking if the first Student is present
        Student firstStudent = (o4.isPresent() ? o4.get() : null);
        long id = (firstStudent == null) ? -1 : firstStudent.getStudentId();
        System.out.println("firstStudent's id is " + id);

        System.out.println("\n<<<----******----\n");

        //checking if the first Student is present using functional style expression
        Student firstStudent1 = (o4.orElse(null));
        long id1 = (firstStudent1 == null) ? -1 : firstStudent1.getStudentId();
        System.out.println("firstStudent's id is " + id);

        System.out.println("\n<<<----******----\n");

        //checking if the first Student is present using functional style expression and dummy instance
        Student firstStudent2 = (o4.orElse(getDummyStudent(jmc)));
        //long id2 = (firstStudent2 == null) ? -1 : firstStudent2.getStudentId();
        long id2 = firstStudent2.getStudentId();//simplified row above
        System.out.println("firstStudent's id is " + id);

        System.out.println("\n<<<----******----\n");

        //checking if the first Student is present using functional style expression adn dummy instance and orElseGet method with Supplier
        Student firstStudent3 = (o4.orElseGet(() -> getDummyStudent(jmc)));
        long id3 = firstStudent3.getStudentId();
        System.out.println("firstStudent's id is " + id);

        System.out.println("\n<<<----******----\n");

        System.out.println("<<<------------------------------------------->>>");

        System.out.println("\n--- Optional class and methods mirroring the stream pipeline operations--->>>\n");

        List<String> countries = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .toList();
        //these are chained methods!!! not stream operations
        Optional.of(countries)
                .map(l -> String.join(",", l))
                .filter(l -> l.contains("FR"))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Missing FR"));

        System.out.println("\n<<<----******----\n");


        System.out.println("<<<------------------------------------------->>>");
    }


    private static Optional<Student> getStudent(List<Student> list, String type) {

//        if (list == null || list.size() == 0) {
//            return Optional.empty();
//        } else if (type.equals("first")) {
//            return Optional.of(list.get(0));
//        } else if (type.equals("last")) {
//            return Optional.of(list.get(list.size() - 1));
//        }
//        return Optional.of(list.get(new Random().nextInt(list.size())));

        if (list == null || list.size() == 0) {
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.get(0));
        } else if (type.equals("last")) {
            return Optional.ofNullable(list.get(list.size() - 1));
        }
        return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
    }

    private static Student getDummyStudent(Course... courses) {
        System.out.println("Getting the dummy student");
        return new Student("NO", 1, 1, "U", false, courses);
    }
}

package dev.lpa;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

public class Main {
    //setting 2 maps
    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

        Course jmc = new Course("jmc101", "Java Master Class", "Java");
        Course python = new Course("pyt101", "Python Master Class", "Python");

        addPurchase("Mary Martin", jmc, 129.99);
        addPurchase("Andy Martin", jmc, 139.99);
        addPurchase("Mary Martin", python, 149.99);
        addPurchase("Joe Jones", jmc, 149.99);
        addPurchase("Bill Brown", python, 119.99);

        addPurchase("Chuck Cheese", python, 119.99);
        addPurchase("Davey Jones", jmc, 139.99);
        addPurchase("Eva East", python, 139.99);
        addPurchase("Fred Forker", jmc, 139.99);
        addPurchase("Greg Brady", python, 129.99);

        //printing values
        purchases.forEach((key, value) -> System.out.println(key + ": " + value));//ordered by insertion order
        System.out.println("--------------------------");
        students.forEach((key, value) -> System.out.println(key + ": " + value));//returns students in alphabetical order
        System.out.println("--------------------------");
        NavigableMap<LocalDate, List<Purchase>> datedPurchases = new TreeMap<>();

        for (Purchase p : purchases.values()) {
            datedPurchases.compute(p.purchaseDate(),
                    (pdate, plist) -> {
                        List<Purchase> list = (plist == null) ? new ArrayList<>() : plist;
                        list.add(p);
                        return list;
                    });
        }
        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("--------------------------");

        System.out.println("<<------- NavigableMap methods ----------->>");
        System.out.println("-------->> headMap and tailMap method <<------");

        int currentYear = LocalDate.now().getYear();

        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<Purchase>> week1Purchases = datedPurchases.headMap(week1);// headMap - exclude the value you pass, unless you use the overloaded method and specify the inclusive flag to be true
        Map<LocalDate, List<Purchase>> week2Purchases = datedPurchases.tailMap(week1);//tailMap-includes the value you pass if it is in the map
        System.out.println("--------------------------");
        week1Purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("--------------------------");
        week2Purchases.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("--------------------------");
        displayStats(1, week1Purchases);
        displayStats(2, week2Purchases);
        System.out.println("--------------------------");

        System.out.println("--------->> lastKey and lastEntry method <<---------");
        LocalDate lastDate = datedPurchases.lastKey();
        var previousEntry = datedPurchases.lastEntry();
//        List<Purchase> lastDaysData = previousEntry.getValue();
//        System.out.println(lastDate + " purchases : " + lastDaysData.size());

        System.out.println("--------->> lowerKey and lowerEntry method <<---------");
        while (previousEntry != null) {
            List<Purchase> lastDaysData = previousEntry.getValue();
            System.out.println(lastDate + " purchases : " + lastDaysData.size());

            LocalDate prevDate = datedPurchases.lowerKey(lastDate);
            previousEntry = datedPurchases.lowerEntry(lastDate);
            lastDate = prevDate;
        }
        System.out.println("--------------------------");
        System.out.println("--------->> firstKey and firstEntry method <<---------");
        System.out.println("--------->> higherKey and higherEntry method <<---------");
        System.out.println("--------->> pollFirstEntry method <<---------");
        var reversed = datedPurchases.descendingMap();

        LocalDate firstDate = reversed.firstKey();
        //var nextEntry = reversed.firstEntry();
        var nextEntry = reversed.pollFirstEntry();

        while (nextEntry != null) {
            List<Purchase> lastDaysData = nextEntry.getValue();
            System.out.println(firstDate + " purchases : " + lastDaysData.size());

            LocalDate nextDate = reversed.higherKey(firstDate);
            //nextEntry = reversed.higherEntry(firstDate);
            nextEntry = reversed.pollFirstEntry();
            firstDate = nextDate;
        }
        System.out.println("--------------------------");
        //the poll methods, pollFirstEntry and pollLastEntry, remove data from the map, on each subsequent call.
        //These aren't just an alternate way get the first and last entry, because they're also mutating the map.
        // And secondly, the reversed map is a view.The true source is my dated purchases map.
        System.out.println("------//----original map------//------");
        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));

    }

    //method to keep track about students and which courses they are taking
    private static void addPurchase(String name, Course course, double price) {
        Student existingStudent = students.get(name);
        if (existingStudent == null) {
            existingStudent = new Student(name, course);
            students.put(name, existingStudent);
        } else {
            existingStudent.addCourse(course);
        }

        //int day = purchases.size() + 1;
        int day = new Random().nextInt(1, 15);
        String key = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
        purchases.put(key, purchase);
    }

    private static void displayStats(int period, Map<LocalDate, List<Purchase>> periodData) {
        System.out.println("--------->> merge method <<---------");
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((key, value) -> {
            System.out.println(key + ": " + value);
            for (Purchase p : value) {
                weeklyCounts.merge(p.courseId(), 1, (prev, current) -> {
                    return prev + current;
                });
            }
        });
        System.out.printf("Week %d Purchases = %s%n", period, weeklyCounts);
    }

}
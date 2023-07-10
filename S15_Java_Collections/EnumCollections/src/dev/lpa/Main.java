package dev.lpa;

import java.util.*;

public class Main {

    enum WeekDay {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}
    public static void main(String[] args) {

        List<WeekDay> annsWorkDays = new ArrayList<>(List.of(WeekDay.MONDAY, WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));
        System.out.println("--------->> copyOf method <<----------");
        var annsDaysSet = EnumSet.copyOf(annsWorkDays);
        System.out.println(annsDaysSet.getClass().getSimpleName());
        annsDaysSet.forEach(System.out::println);

        System.out.println("--------->> allOf method <<----------");
        var allDaysSet = EnumSet.allOf(WeekDay.class);
        System.out.println("----------------------------");
        allDaysSet.forEach(System.out::println);

        System.out.println("--------->> complementOf method <<----------");
        Set<WeekDay> newPersonDays = EnumSet.complementOf(annsDaysSet);
        newPersonDays.forEach(System.out::println);

        System.out.println("--------->> removeAll method <<----------");
        Set<WeekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(annsDaysSet);
        anotherWay.forEach(System.out::println);

        System.out.println("--------->> range method <<----------");
        Set<WeekDay> businessDays = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
        businessDays.forEach(System.out::println);

        //empty enumMap
        Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);
        //adding employees
        System.out.println("--------->> put method for Map <<----------");
        employeeMap.put(WeekDay.FRIDAY, new String[]{"Ann", "Mary", "Bob"});
        employeeMap.put(WeekDay.MONDAY, new String[]{"Mary", "Bob"});
        employeeMap.forEach((k, v)-> System.out.println(k + " : " + Arrays.toString(v)));

    }
}
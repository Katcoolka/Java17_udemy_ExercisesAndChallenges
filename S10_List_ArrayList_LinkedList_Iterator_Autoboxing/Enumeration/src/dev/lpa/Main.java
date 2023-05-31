package dev.lpa;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //examples of using Enum
        DayOfTheWeek weekDay = DayOfTheWeek.TUES;
        System.out.println(weekDay);

        for (int i = 0; i < 10; i++) {
            weekDay = getRandomDay();

//            System.out.printf("Name is %s, Ordinal Value = %d%n", weekDay.name(), weekDay.ordinal());
//
//            if (weekDay == DayOfTheWeek.FRI) {
//                System.out.println("Found a Friday!!");
//            }
            switchDayOfTheWeek(weekDay);
        }
        System.out.println("-".repeat(30));

        for (Topping topping : Topping.values()) {
            System.out.println(topping.name() + " : " + topping.getPrice());
        }
    }


    public static DayOfTheWeek getRandomDay() {
        int randomInteger = new Random().nextInt(7);
        var allDays = DayOfTheWeek.values();
        return allDays[randomInteger];
    }

    //switch
    public static void switchDayOfTheWeek(DayOfTheWeek weekDay) {
        int weeDayInteger = weekDay.ordinal() + 1;
        switch (weekDay) {
            case WED -> System.out.println("Wednesday is Day " + weeDayInteger);
            case SAT -> System.out.println("Saturday is Day " + weeDayInteger);
            default ->
                    System.out.println(weekDay.name().charAt(0) + weekDay.name().substring(1).toLowerCase() + "day is Day " + weeDayInteger);
        }

    }
}
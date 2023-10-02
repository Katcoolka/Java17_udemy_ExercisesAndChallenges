package dev.lpa;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println("<<<-------------- Local date ------------->>>");

        LocalDate today = LocalDate.now();
        System.out.println(today);
        System.out.println("*".repeat(30));

        LocalDate Five5 = LocalDate.of(2022, 5, 5);
        System.out.println(Five5);
        System.out.println("*".repeat(30));

        LocalDate May5th = LocalDate.of(2022, Month.MAY, 5);
        System.out.println(May5th);
        System.out.println("*".repeat(30));

        LocalDate Day125 = LocalDate.ofYearDay(2022, 125);
        System.out.println(Day125);
        System.out.println("*".repeat(30));

        LocalDate May5 = LocalDate.parse("2022-05-05");
        System.out.println(May5);
        System.out.println("*".repeat(30));

        System.out.println(May5.getYear());
        System.out.println(May5.getMonth());
        System.out.println(May5.getMonthValue());
        System.out.println("*".repeat(30));

        System.out.println(May5.getDayOfMonth());
        System.out.println(May5.getDayOfWeek());
        System.out.println(May5.getDayOfYear());
        System.out.println("*".repeat(30));

        //TemporalField is an interface for a class, that implements a date time field.
        //ChronoField enum implements TemporalField, to pick the date time field you want to get

        System.out.println(May5.get(ChronoField.YEAR));
        System.out.println(May5.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(May5.get(ChronoField.DAY_OF_MONTH));
        System.out.println(May5.get(ChronoField.DAY_OF_YEAR));
        System.out.println("*".repeat(30));

        //to adjust my date
        /*LocalDate, like all the temporal implementations, is immutable.
        Java doesn't give us any setters for that reason, but it does have methods with similar behavior.
        These methods start with the prefix with, w i t h. These would be setters, if this class were
        mutable, but basically you'll get a copy, with one of the fields, year, month or day, changed.
        These methods return a new instance of LocalDate, with the new value in the specified field.*/

        System.out.println(May5.withYear(2000));
        System.out.println(May5.withMonth(3));
        System.out.println(May5.withDayOfMonth(4));
        System.out.println(May5.withDayOfYear(126));
        System.out.println("*".repeat(30));

        System.out.println(May5);
        System.out.println(May5.with(ChronoField.DAY_OF_YEAR, 126));
        System.out.println("*".repeat(30));

        //plus or minus methods
        System.out.println(May5.plusYears(1));
        System.out.println(May5.plusMonths(12));
        System.out.println(May5.plusDays(365));
        System.out.println(May5.plusWeeks(52));
        System.out.println("*".repeat(30));

        System.out.println(May5.plus(365, ChronoUnit.DAYS));
        System.out.println(May5.minusYears(1)); //the rest is the same as plus methods
        System.out.println("*".repeat(30));

        //isAfter and isBefore methods
        System.out.println("May5 > today? " + May5.isAfter(today));
        System.out.println("today > May5? " + May5.isBefore(today));
        System.out.println("*".repeat(30));

        //compareTo method
        System.out.println("May5 > today? " + May5.compareTo(today));
        System.out.println("today > May5? " + today.compareTo(May5));
        System.out.println("*".repeat(30));

        //comparing equal days
        //getting a zero back, which is Comparable's result when two instances are equal.
        System.out.println("today = now ? " + today.compareTo(LocalDate.now()));
        System.out.println("today = now ? " + today.equals(LocalDate.now()));

        System.out.println(today.isLeapYear());
        System.out.println(May5.minusYears(2).isLeapYear());

        System.out.println(">>>-------------- LocalDate -------------<<<\n");

        System.out.println("<<<-------------- LocalDateTime ------------->>>\n");
        May5.datesUntil(May5.plusDays(7))
                .forEach(System.out::println);
        System.out.println("*".repeat(30));

        May5.datesUntil(May5.plusYears(1), Period.ofDays(7))
                .forEach(System.out::println);
        System.out.println("*".repeat(30));

        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println("*".repeat(30));

        LocalTime sevenAM = LocalTime.of(7, 0);
        System.out.println(sevenAM);
        System.out.println("*".repeat(30));

        LocalTime sevenThirty = LocalTime.of(7, 30, 15);
        System.out.println(sevenThirty);
        System.out.println("*".repeat(30));

        LocalTime sevenPM = LocalTime.parse("19:00");
        LocalTime sevenThirtyPM = LocalTime.parse("19:30:15.1000");
        System.out.println(sevenPM);
        System.out.println(sevenThirtyPM);
        System.out.println("*".repeat(30));

        System.out.println(sevenPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenThirtyPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println("*".repeat(30));

        System.out.println(sevenThirtyPM.getHour());
        System.out.println(sevenThirtyPM.get(ChronoField.HOUR_OF_DAY));
        //we can't pass a ChronoUnit like Days, when we have a LocalTime instance
        //System.out.println(sevenThirtyPM.get(ChronoField.YEAR));
        System.out.println("*".repeat(30));

        System.out.println(sevenThirtyPM.plus(24, ChronoUnit.HOURS));
        System.out.println("*".repeat(30));

        System.out.println(sevenPM.range(ChronoField.HOUR_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.CLOCK_HOUR_OF_DAY));
        System.out.println("*".repeat(30));

        LocalDateTime todayAndNow = LocalDateTime.now();
        System.out.println(todayAndNow);

        LocalDateTime May5Noon = LocalDateTime.of(2022, 5, 5, 12, 0);
        System.out.printf("%tD %tr %n", May5Noon, May5Noon); //output: 05/05/22 12:00:00 PM
        System.out.printf("%1$tF %1$tT %n", May5Noon);//output: 2022-05-05 12:00:00

        System.out.println(">>>-------------- LocalDateTime -------------<<<\n");
        System.out.println("<<<-------------- DateTimeFormatter class ------------->>>\n");

        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println("*".repeat(30));

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(May5Noon.format(dtf)); //štvrtok 5. mája 2022
        System.out.println("*".repeat(30));

        System.out.println(May5Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));//5. 5. 2022, 12:00:00

        LocalDateTime May6Noon = May5Noon.plusHours(24);
        System.out.println(May6Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));//6. 5. 2022, 12:00:00

        System.out.println(">>>-------------- DateTimeFormatter -------------<<<\n");


    }
}

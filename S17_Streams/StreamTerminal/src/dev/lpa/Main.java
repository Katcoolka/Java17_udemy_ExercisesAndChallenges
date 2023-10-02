package dev.lpa;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        System.out.println("<<<---------- Terminal Operations -------------->>>");

        System.out.println("---- summaryStatistics operation ---->>>\n");
        //good for initial understanding of set of data
        var result = IntStream
                .iterate(0, i -> i <= 1000, i -> i = i + 3)
                .summaryStatistics();
        System.out.println("Result = " + result);

        System.out.println("\n<<<---------------------------");
        //peek is useful when you're doing reductions. It gives you a window into what is happening.
        //in this case it print out the 7 leap years into console output

        var leapYearData = IntStream
                .iterate(2000, i -> i <= 2025, i -> i = i + 1)
                .filter(i -> i % 4 == 0)
                .peek(System.out::println)
                .summaryStatistics();
        System.out.println("Leap Year Data = " + leapYearData);
        System.out.println("\n<<<---------------------------");

        System.out.println("---- count operation ---->>>\n");
        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, i -> new Seat((char) ('A' + i / 10), i % 10 + 1));
        //Arrays.asList(seats).forEach(System.out::println);

        long reservationCount = Arrays
                .stream(seats)
                .filter(Seat::isReserved)
                .count();
        System.out.println("reservationCount = " + reservationCount);
        System.out.println("\n<<<---------------------------");

        System.out.println("---- anyMatch operation ---->>>\n");
        //at least one is true
        boolean hasBookings = Arrays
                .stream(seats)
                .anyMatch(Seat::isReserved);
        System.out.println("hasBookings = " + hasBookings);
        System.out.println("\n<<<---------------------------");

        System.out.println("---- allMatch operation ---->>>\n");
        //all have to be true
        boolean fullyBooked = Arrays
                .stream(seats)
                .allMatch(Seat::isReserved);
        System.out.println("fullyBooked= " + fullyBooked);
        System.out.println("\n<<<---------------------------");

        System.out.println("---- noneMatch operation ---->>>\n");
        //none is true
        boolean eventWashedOut = Arrays
                .stream(seats)
                .noneMatch(Seat::isReserved);
        System.out.println("eventWashedOut= " + eventWashedOut);
        System.out.println("\n<<<---------------------------");

        System.out.println("<<<-------------------------------------------->>>");
    }
}
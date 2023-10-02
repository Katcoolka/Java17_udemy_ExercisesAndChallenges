package dev.lpa;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("<<<-------- Streams - Intermediate Operations pt.1 ------->>>");

        System.out.println("\n--- iterate method --------");

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");


        System.out.println("\n--- filter method --------");

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .filter(i -> Character.toUpperCase(i) > 'E')
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");

        System.out.println("\n--- skip method --------");

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .skip(5)
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");

        System.out.println("\n--- dropWhile method --------");

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> i <= 'E')
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");

        //same result with this code update
        //This happens until the predicate becomes false the first time, and then that condition is no longer checked.
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");

        System.out.println("\n--- takeWhile method --------");

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .takeWhile(i -> i < 'a')
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");

        System.out.println("\n--- distinct operation --------");

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");

        Random random = new Random();
        Stream.generate(() -> random.nextInt((int) 'A', (int) 'Z' + 1))
                .limit(50)
                //.distinct()
                .sorted()
                .forEach(d -> System.out.printf("%c ", d));
        System.out.println("\n------------------------");

        System.out.println("<<<--------------------------------------------------->>>\n");

        System.out.println("<<<-------- Streams - Intermediate Operations pt.2 ------->>>");

        int maxSeats = 100;
        int seatsInRow = 10;
        System.out.println("\n--- map operation, mapToDouble operation, boxed operation --------");

        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .mapToDouble(Seat::price)
                .boxed()
                .map("%.2f"::formatted);
        stream.forEach(System.out::println);
        System.out.println("\n------------------------");

        System.out.println("\n--- mapToObj operation --------");
        var stream1 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .mapToDouble(Seat::price)
                .mapToObj("%.2f"::formatted);
        stream1.forEach(System.out::println);
        System.out.println("\n------------------------");

        System.out.println("\n--- sorted operation, thenComparing operation --------");
        //we have to implement Comparator
        var stream2 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .sorted(Comparator.comparing(Seat::price)
                        .thenComparing(Seat::toString));
        stream2.forEach(System.out::println);
        System.out.println("\n------------------------");

        System.out.println("\n--- peak operation --------");

        var stream3 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .skip(5)
                .limit(10)
                .peek(s -> System.out.println("-->" + s))
                .sorted(Comparator.comparing(Seat::price)
                        .thenComparing(Seat::toString));
        stream3.forEach(System.out::println);
        System.out.println("\n------------------------");

        System.out.println("<<<--------------------------------------------------->>>\n");
    }
}
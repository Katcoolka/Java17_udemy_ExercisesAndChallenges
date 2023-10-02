package dev.lpa;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int maxMinusFive = Integer.MAX_VALUE - 5;
        //for (int j = 0, id = maxMinusFive; j < 10; id++, j++){
//        for (int j = 0, id = maxMinusFive; j < 10; id = Math.incrementExact(id), j++){
//                System.out.printf("Assigning id %,d%n", id);
//            }
//        }

        //getting absolute value
        System.out.println(Math.abs(-50));//result is 50
        System.out.println(Math.abs(Integer.MIN_VALUE)); //causing integer overflow
        //System.out.println(Math.absExact(Integer.MIN_VALUE)); //getting exception
        System.out.println(Math.abs((long) Integer.MIN_VALUE)); //absolute value of Integer's MIN_VALUE.
        System.out.println("-".repeat(90));
        //getting max
        System.out.println("Max = " + Math.max(10, -10)); //10
        //getting min
        System.out.println("Min = " + Math.min(10.0000002, 10.001f)); //the double version of min is used, if either
        // of the arguments is a double. A literal decimal number is a double by default in Java.
        System.out.println("-".repeat(90));
        //round method
        System.out.println("Round Down = " + Math.round(10.2)); //10
        System.out.println("Round Up = " + Math.round(10.8)); //11
        System.out.println("Round ? = " + Math.round(10.5)); //11
        System.out.println("-".repeat(90));
        //floor and ceil methods
        System.out.println("Floor = " + Math.floor(10.8)); //10 - it doesn't matter how close the decimal brings the
        // value to a whole number, it will always round down.
        System.out.println("Ceil = " + Math.ceil(10.2)); //11 - it doesn't matter how close the decimal brings the
        // value to a whole number, it will always round up.
        System.out.println("-".repeat(90));
        //sqrt and pow methods
        System.out.println("Square root of 100 = " + Math.sqrt(100)); //10
        System.out.println("2 to the third power (2*2*2) = " + Math.pow(2, 3)); //8.0
        System.out.println("10 to the fifth power (10*10*10*10*10) = " + Math.pow(10, 5));//100000.0
        System.out.println("-".repeat(90));
        //random method
        for (int i = 0; i < 10; i++) {
            System.out.println(Math.random()); //result: 10 random numbers, all between the numbers 0 and 1,
            // including 0 but not 1.
        }
        System.out.println("-".repeat(90));
        //random method * 10
        for (int i = 0; i < 10; i++) {
            System.out.println(Math.random() * 10); //result: 10 random numbers, all between the numbers 0 and
            // 10, including 0 but not 10 in double numbers.
        }
        System.out.println("-".repeat(90));
        //random method * 10 casted to int
        for (int i = 0; i < 10; i++) {
            System.out.println((int) (Math.random() * 10)); //result: 10 random numbers, all between the numbers 0 and
            // 10, including 0 but not 10 in integers.
        }
        System.out.println("-".repeat(90));
        //random method * 10 casted to int
        for (int i = 0; i < 10; i++) {
            System.out.println((int) (Math.random() * 26) + 65); //result: 10 random numbers, all between the numbers 65
            // and 91 in integers.
            //This is 65 plus 26 letters in the english alphabet.
        }
        System.out.println("-".repeat(90));
        //random method * 10 casted to int getting character specifier
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26) + 65); //result: 10 random numbers with
            // character representation
            //This is 65 plus 26 letters in the english alphabet.
        }
        System.out.println("-".repeat(90));
        System.out.println("*".repeat(90));
        System.out.println("--- Random integers in a specified range --->");
        //the overloaded version of nextInt, that allows us to specify a range - 2 options
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", r.nextInt(65, 91));
        }
        System.out.println("-".repeat(90));

        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", r.nextInt((int) 'A', (int) 'Z' + 1)); // add 1, so the upper bound is one
            // more than the Z character, again because this bound is exclusive.
        }
        System.out.println("-".repeat(90));
        //get up to the full range of integers randomized, including negative integers
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d%n", r.nextInt());
        }
        System.out.println("-".repeat(90));
        //get random numbers between -10 and 10
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d%n", r.nextInt(-10, 11)); //specify a negative lower bound
        }
        System.out.println("-".repeat(90));
        System.out.println("*".repeat(90));

        System.out.println("---- Random streams --->");
        //10 random integers, both negative and positive, bounded by the integer's MIN VALUE and MAX VALUE.
        //getting infinite stream limited by 10
        r.ints()
                .limit(10)
                .forEach(System.out::println);

        System.out.println("-".repeat(90));
        //specified lower and upper bound for this stream
        r.ints(0, 10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("-".repeat(90));
        //finite stream - has 3 parameters (size, origin, bound)
        r.ints(10, 0, 10)
                .forEach(System.out::println);

        System.out.println("-".repeat(90));
        //finite stream - with 1 parameter (size)
        r.ints(10)
                .forEach(System.out::println);

        System.out.println("-".repeat(90));
        System.out.println("*".repeat(90));

        //seed is an initial number in Random
        //nanoTime -This method returns the running JVM's high-resolution time source, in nanoseconds.
        //code, if you want to test code that uses a random number generator:
        //to get the same set of numbers printed, in both cases.
        long nanoTime = System.nanoTime();
        Random pseudoRandom = new Random(nanoTime);
        pseudoRandom.ints(10, 0, 10)
                .forEach(i -> System.out.print(i + " "));

        System.out.println("\n-------------------------");

        Random notReallyRandom = new Random(nanoTime);
        notReallyRandom.ints(10, 0, 10)
                .forEach(i -> System.out.print(i + " "));
    }
}

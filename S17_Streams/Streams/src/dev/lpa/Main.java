package dev.lpa;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        System.out.println("<<<------------ BINGO game without a Stream-------------->>>");

        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
                //System.out.println("" + c + i);
            }
            start += 15;
        }
        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("----------------------------------\n");

        System.out.println("---> Sorted List in natural order with '-'<----");
        //the subList method returns a view, and to use sublist only when you really do want to alter the original list.
        //List<String> firstOnes = bingoPool.subList(0, 15);

        //creating modifiable copy of a sublist => original list is not changed
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf('O') == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n--------------------------------");
        //test code to check, if the original list has not changed
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("<<<-------------------------------------->>>\n");

        System.out.println("<<<------------ BINGO game with a Stream -------------->>>");
        //Stream
        //each step in the stream is called operation
        //Stream pipeline is this entire chain of operations
        //the source of the stream is where the data elements are coming from (in this case bingoPool list)
        bingoPool.stream()
                .limit(15)//intermediate operation -> is not required
                .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0) //intermediate operation
                .map(s -> s.charAt(0) + "-" + s.substring(1))//intermediate operation
                .sorted()//intermediate operation
                .forEach(s -> System.out.print(s + " ")); //this part is called terminal operation //it is a requirement to have terminal operation

        System.out.println("\n--------------------------------");
        //test code to check, if the original list has not changed
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("<<<-------------------------------------->>>\n");

        //another way to create stream
        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
        tempStream.forEach(s -> System.out.print(s + " "));

        System.out.println("\n--------------------------------");
        //stream has already been operated upon or closed => you cannot reuse a stream
        //tempStream.forEach(s -> System.out.print(s.toLowerCase() + " "));
        System.out.println("<<<------------ Stream sources -------------->>>");
        //Array
        String[] strings = {"One", "Two", "Three"};
        Arrays.stream(strings)
                .sorted(Comparator.reverseOrder()) //reverse alphabetical order
                .forEach(System.out::println);
        System.out.println("\n--------------------------------");

        //static method .of() on the Stream interface
        Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase) //method reference
                .forEach(System.out::println); //terminal operation
        System.out.println("\n--------------------------------");

        //2 Streams and concat method
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase);
//        Stream.concat(secondStream, firstStream)
//                        .forEach(System.out::println);
        System.out.println("\n--------------------------------");

        //adding intermediate operation
        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);
        System.out.println("\n--------------------------------");

        //Map collection views
        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;

        }
        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " +
                        e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);
        System.out.println("\n--------------------------------");

        //limit method - used also for infinite streams
        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n--------------------------------");

        System.out.println("<<<------------------------------------------>>>\n");

        System.out.println("<<<------------ Infinite Stream -------------->>>");
        //First Pipeline
        //numbers can be fed indefinitely into the streaming process, until enough numbers match the condition first, and then match the limiting size.
        IntStream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n--------------------------------");

        //Second Pipeline
        //I want to check a specified number of elements, and will operate on that specific size.
        IntStream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n--------------------------------");

        //Third Pipeline
        //The overloaded iterate method, has three parameters, the first is still the seed, but
        //the second parameter, the additional parameter, is a predicate functional interface type.
        //The third parameter is the UnaryOperator.
        //same result as Second Pipeline
        IntStream.iterate(1, n -> n <= 100, n -> n + 1)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n--------------------------------");

        //Fourth Pipeline
        //using .range() => 0 - 99
        //same result as Second and Third Pipeline
        IntStream.range(1, 100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n--------------------------------");

        //Fifth Pipeline
        //The rangeClosed method in this example, produces numbers from 1, up to and including 100.
        IntStream.rangeClosed(1, 100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n--------------------------------");

        System.out.println("<<<------------------------------------------>>>\n");
    }

    public static boolean isPrime(int wholeNumber) {
        if (wholeNumber <= 2) {
            return wholeNumber == 2;
        }
        for (int divisor = 2; divisor < wholeNumber; divisor++) {
            if (wholeNumber % divisor == 0) {
                return false;
            }
        }
        return true;
    }
}
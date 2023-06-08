package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    private static Random random = new Random();

    public static void main(String[] args) {
        //using .setAll()
        String[] names = {"Anna", "Bob", "Carole", "David", "Ed", "Fred", "Gary"};
        Arrays.setAll(names, i -> names[i].toUpperCase());
        System.out.println("--> Transform to UpperCase");
        System.out.println(Arrays.toString(names));

        //using List -  to have the ability to treat array like a list
        List<String> backedByArray = Arrays.asList(names);
        backedByArray.replaceAll(s -> s += " " + getRandomChar('A', 'D') + ".");
        System.out.println("--> Add random middle Initial");
        System.out.println(Arrays.toString(names));

        backedByArray.replaceAll(s -> s += " " + getReversedName(s.split(" ")[0]));
        System.out.println("--> Add reversed name as last name");
        Arrays.asList(names).forEach(s -> System.out.println(s));

        //to remove names that have the first name equal to the last.
        //for this we need a list that is not backed by an array
        List<String> newList = new ArrayList<>(List.of(names));
//        newList.removeIf(s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") +1)));

        //multi-line lambda example of the same code
        newList.removeIf(s -> {
            String first = s.substring(0, s.indexOf(" "));
            String last = s.substring(s.lastIndexOf(" ") + 1);
            return first.equals(last);
        });
        System.out.println("--> Remove names where first = last");
        newList.forEach(s -> System.out.println(s));

    }

    //helper methods for the challenge
    public static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int) startChar, (int) endChar + 1);
    }

    public static String getReversedName(String firstName) {
        return new StringBuilder(firstName).reverse().toString();
    }
}
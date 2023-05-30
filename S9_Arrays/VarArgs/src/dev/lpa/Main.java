package dev.lpa;

public class Main {
    public static void main(String... args) {
        System.out.println("Hello world again!");

        String[] splitStrings = "Hello World again".split(" ");
        printText(splitStrings);
        System.out.println("_".repeat(20));
        printText("Hello");

        System.out.println("_".repeat(20));
        printText("Hello", "World", "again");

        System.out.println("_".repeat(20));
        printText();

        String[] sArray = {"first", "second", "third", "fourth", "fifth"};
        System.out.println(String.join(",",sArray));
    }
    //to have the ability to pass a variable list of elements,
    //
    //vs. just passing an array of elements.
    private static void printText(String... textList) {
        for (String t : textList) {
            System.out.println(t);
        }
    }
}
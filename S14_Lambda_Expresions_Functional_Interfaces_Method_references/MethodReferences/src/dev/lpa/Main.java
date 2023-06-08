package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.*;

class PlainOld {
    private static int last_id = 1;
    private int id;

    public PlainOld() {
        id = PlainOld.last_id++;
        System.out.println("Creating a Plain Old Object");
    }
}

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of("Anna", "Bob", "Chuck", "Dave"));
        list.forEach(s -> System.out.println(s));
        //example of method reference ::
        list.forEach(System.out::println);

        //using method reference with integers and doubles
        calculator((a, b) -> a + b, 10, 25);//lambda expression
        calculator(Integer::sum, 10, 25);//using method reference
        calculator((a, b) -> a + b, 2.5, 7.5);//lambda expression
        calculator(Double::sum, 2.5, 7.5);//using method reference

        Supplier<PlainOld> reference1 = () -> new PlainOld();//lambda expression
        Supplier<PlainOld> reference2 = PlainOld::new; //the code isn't invoked at this time/ sort of like a method declaration
        PlainOld newPojo = reference2.get(); //executing the method
        System.out.println("Getting array");
        PlainOld[] pojo1 = seedArray(PlainOld::new, 10);

        //unbound reference
        calculator((s1, s2) -> s1 + s2, "Hello ", "World");
        calculator((s1, s2) -> s1.concat(s2), "Hello ", "World");
        calculator(String::concat, "Hello ", "World");

        BinaryOperator<String> b1 = (s1, s2) -> s1.concat(s2); //lambda expression
        BinaryOperator<String> b2 = String::concat; //using method reference

        BiFunction<String, String, String> b3 = (s1, s2) -> s1.concat(s2);//lambda expression
        BiFunction<String, String, String> b4 = String::concat; //using method reference

        UnaryOperator<String> u1 = (s1) -> s1.toUpperCase();//lambda expression
        UnaryOperator<String> u2 = String::toUpperCase;//using method reference

        System.out.println(b2.apply("Hello ", "world"));
        System.out.println(b4.apply("Hello ", "world"));
        System.out.println(u2.apply("Hello"));

        //transform method on a String
        String result = "Hello".transform(u2);
        System.out.println("Result = " + result);

        result = result.transform(String::toLowerCase);
        System.out.println("Result = " + result);

        Function<String, Boolean> f0 = String::isEmpty;
        boolean resultBoolean = result.transform(f0);
        System.out.println("Result = " + resultBoolean);
    }

    private static <T> void calculator(BinaryOperator<T> function, T value1, T value2) {
        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }
}
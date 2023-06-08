import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {

        System.out.println("------------ mini challenge 1 -----------------");
        //write the following anonymous class as a lambda expression

        Consumer<String> printTheParts = new Consumer<String>() {
            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };
        //lambda expression
        Consumer<String> printThePartsLambda = sentence -> {
            String[] parts = sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        printTheParts.accept("Let's split this up into an array");
        printThePartsLambda.accept("Let's split this up into an array");

        //enhanced for each
        Consumer<String> printThePartsForEach = sentence -> {
            String[] parts = sentence.split(" ");
            Arrays.asList(parts).forEach(s -> System.out.println(s));
        };
        printThePartsForEach.accept("Let's split this up into an array");

        //more concise
        Consumer<String> printThePartsConcise = sentence -> {
            Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));
        };
        printThePartsForEach.accept("Let's split this up into an array");

        System.out.println("------------ mini challenge 2 -----------------");
        //lambda expression
        Function<String, String > everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i< source.length(); i++){
                if (i % 2 == 1){
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        //Unary Operator
        UnaryOperator<String> everySecondCharUnary = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i< source.length(); i++){
                if (i % 2 == 1){
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println("------------ mini challenge 3 -----------------");
        //write the code to execute lambda expression functional method, using 1234567890, as the argument to that method, and print the result out
        System.out.println(everySecondChar.apply("1234567890"));

        System.out.println("------------ mini challenge 4 -----------------");
        //pass the function to a method
        //refer to method everySecondCharacter below

        System.out.println("------------ mini challenge 5 -----------------");
        //call the method created in challenge 4, passing the lambda variable we created earlier, and the string 1234567890, then print the result returned from the method
        String result = everySecondCharacter(everySecondChar, "1234567890");
        System.out.println(result);

        System.out.println("------------ mini challenge 6 -----------------");
        //write lambda expression that is declared with the Supplier interface
        //this lambda should return the String "I love Java", and assign it to a variable called, iLoveJava
        Supplier<String> iLoveJava = () -> "I love Java";
        //using return statement
        Supplier<String> iLoveJava2 = () -> {return "I love Java!";};

        System.out.println("------------ mini challenge 7 -----------------");
        //use this Supplier to assign a String "I love Java" to a variable called supplierResult
        //print that variable to the console

        System.out.println(iLoveJava.get());
        System.out.println(iLoveJava2.get());

    }

        //write the following method as a lambda expression
        public static String everySecondChar(String source){
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i< source.length(); i++){
                if (i % 2 == 1){
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        }
        //method for challenge 4
        public static String everySecondCharacter(Function<String, String> func, String source){
            return func.apply(source);
        }


}
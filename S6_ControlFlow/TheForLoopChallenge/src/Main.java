public class Main {
    public static void main(String[] args) {
        //The prime number challenge
        //test code
        System.out.println("0 is " + (isPrime(0) ? "" : "NOT ") + " a prime number");
        System.out.println("1 is " + (isPrime(1) ? "" : "NOT ") + " a prime number");
        System.out.println("2 is " + (isPrime(2) ? "" : "NOT ") + " a prime number");
        System.out.println("3 is " + (isPrime(3) ? "" : "NOT ") + " a prime number");

        System.out.println("8 is " + (isPrime(8) ? "" : "NOT ") + " a prime number");
        System.out.println("17 is " + (isPrime(17) ? "" : "NOT ") + " a prime number");

        //For loop challenge
        int count = 0;
        for (int i = 10; i <= 50; i++) {
            if (isPrime(i)) {
                System.out.println(" number " + i + " is a prime number");
                count++;
                if (count == 3) {
                    System.out.println("Found 3 - exiting for loop");
                    break;
                }
            }
            //System.out.println("Total prime numbers between 10 and 50 is " + count);
        }
    }
    //method for checking if a Whole number is a Prime number:
    public static boolean isPrime(int wholeNumber) {
        //check if the number is <= 2 with an if then statement. If it's <= 2, we want to return false,
        //if the number is negative, 0, or 1, because these are not prime numbers.
        //If the number is 2, we want to return true, because 2 is a prime number.
        if (wholeNumber <= 2) {
            return (wholeNumber == 2);
        }
        //variable divisor - a number we'll be dividing the wholeNumber by
        for (int divisor = 2; divisor < wholeNumber; divisor++) {
            if (wholeNumber % divisor == 0) {
                return false;
            }
        }
        return true; //if it is a prime number
    }
}

public class Main {
    public static void main(String[] args) {
        //test code
        System.out.println("The sum of the digits in number 1234 is " + sumDigit(1234));
        System.out.println("The sum of the digits in number -125 is " + sumDigit(-125));
        System.out.println("The sum of the digits in number 4 is " + sumDigit(4));
        System.out.println("The sum of the digits in number 32123 is " + sumDigit(32123));
    }

    public static int sumDigit(int number) {

        if (number < 0) {
            return -1;
        }

        int sum = 0;
        while (number > 9) {
            sum += (number % 10);
            number = number / 10;
        }
        sum += number;
        return sum;

    }
}
//-----------------------------CHALLENGE----------------------
/*In this challenge, your task is to write a method, with the name sumDigits, that has a single parameter named number, of type int, and it should return an int.
The method should only take a number that is a positive number.
If a negative number is passed, it should return -1, meaning an invalid value was passed.
The method should parse out each digit from the number, and sum the digits up.
So if 125 is the value passed to the method, the code should sum each digit, in this case, 1 + 2 + 5, and return 8, as a value.
And another example, if the value is 1000, the code should sum each digit, 1 + 0 + 0 + 0, and return 1 as a value.
If the number is a single digit number, simply return the number itself as the result.
*/
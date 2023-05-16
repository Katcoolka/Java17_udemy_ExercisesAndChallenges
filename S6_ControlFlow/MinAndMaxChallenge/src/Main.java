import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double min = 0;
        double max = 0;
        int loopCount = 1;

        while (true) {
            System.out.println("Enter a number, or any character to exit: ");
            String nextEntry = scanner.nextLine();
            try {
                double validNumber = Double.parseDouble(nextEntry);
                if (loopCount == 0 || validNumber < min) {
                    min = validNumber;
                }
                if (loopCount == 0 || validNumber > max) {
                    max = validNumber;
                }
                loopCount++;
            } catch (NumberFormatException e) {
                break;
            }
        }
        if (loopCount > 0) {
            System.out.println("min = " + min + ", max = " + max);
        } else {
            System.out.println("No valid data entered");
        }
    }
}

/*You'll be using an endless loop which:
Prompts the user to enter a number, or any character to quit.
Validates if the user-entered data really is a number, you can choose either an integer, or double validation method.
If the user-entered data is not a number, quit the loop.
Keep track of the minimum number entered.
Keep track of the maximum number entered.
If the user has previously entered a set of numbers (or even just one), display the minimum, and maximum number, which the user entered.
*/
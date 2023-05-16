import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //--------Parsing Values-----------
        //String currentYear = "2022";
//        String usersDateOfBirth = "1999";
//        int currentYear = 2022;
//        int dateOfBirth = Integer.parseInt(usersDateOfBirth);
//
//        System.out.println("age = " + (currentYear - dateOfBirth));
//
//        String usersAgeWithPartialYear = "22.5";
//        double ageWithPartialYear = Double.parseDouble(usersAgeWithPartialYear);
//        System.out.println("The user says he is " + ageWithPartialYear);

        int currentYear = 2022;
        try {
            System.out.println(getInputFromConsole(currentYear));
        } catch (NullPointerException e) {
            System.out.println(getInputFromScanner(currentYear));
        }
    }

    //using Console input
    //use terminal and type in: java src/Main.java
    public static String getInputFromConsole(int currentYear) {
        String name = System.console().readLine("Hi, What is your name? ");
        System.out.println("Hi " + name + ", Thanks for taking the course");

        String dateOfBirth = System.console().readLine("What year were you born?");
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return "So you are " + age + " years old.";
    }

    //using Scanner
    public static String getInputFromScanner(int currentYear) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi, What is your name?");
        String name = scanner.nextLine();
        System.out.println("Hi " + name + ", Thanks for taking the course");

        System.out.println("What year were you born? ");

        boolean validDOB = false;
        int age = 0;
        do {
            System.out.println("Enter a year of birth >= " + (currentYear - 125) + " and <= " + (currentYear));
            try {
                age = checkData(currentYear, scanner.nextLine());
                validDOB = age < 0 ? false : true;
            } catch (NumberFormatException e) {
                System.out.println("Characters not allowed!!! try again");
            }
        } while (!validDOB);

        return "So you are " + age + " years old.";
    }

    //validation method
    public static int checkData(int currentYear, String dateOfBirth) {
        int dob = Integer.parseInt(dateOfBirth);
        int minimumYear = currentYear - 125;

        if ((dob < minimumYear) || (dob > currentYear)) {
            return -1;
        }
        return (currentYear - dob);
    }
}
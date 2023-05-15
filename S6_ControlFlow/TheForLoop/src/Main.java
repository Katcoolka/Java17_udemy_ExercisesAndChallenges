public class Main {
    public static void main(String[] args) {

        for (int counter = 1; counter <= 5; counter++) {
            System.out.println(counter);
        }
        //manual calculation
        System.out.println(" 10,000 at 2% interest = " + calculateInterest(10000.0, 2.00));
        System.out.println(" 10,000 at 3% interest = " + calculateInterest(10000.0, 3.00));
        System.out.println(" 10,000 at 4% interest = " + calculateInterest(10000.0, 4.00));
        System.out.println(" 10,000 at 5% interest = " + calculateInterest(10000.0, 5.00));
        System.out.println(" 10,000 at 6% interest = " + calculateInterest(10000.0, 6.00));

        //using for loop statement for calculation
        for (double rate = 2.0; rate <= 5.0; rate++) {
            double interestAmount = calculateInterest(10000.0, rate);
            System.out.println("10,000 at " + rate + " % interest = " + interestAmount);
        }

        //minichallenge
        for(double i = 7.5; i <=10; i+=0.25) {
            double interestAmount = calculateInterest(100.0, i);
            if(interestAmount > 8.5){
                break; //break statement - getting out of the loop
            }
            System.out.println("$100 at " + i + " % interest = " + interestAmount);
        }
    }

    public static double calculateInterest (double amount, double interestRate){
        return (amount * (interestRate / 100));
    }
}
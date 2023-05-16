public class Main {
    public static void main(String[] args) {
        //For loop
        //init/expression/increment
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
        //While loop
        //variable has to be initialized before while loop as while loop contains only the expression
        int j = 1;
//        while (j <= 5) {
////            System.out.println(j);
////            j++;
        //another way how to write the while loop

        while (true) {
            if (j > 5) {
                break;
            }
            System.out.println(j);
            j++;
        }
        //do while loop
        //executes at least once
        //example passcode
        int k = 1;
        boolean isReady = false;
        do {
            if (k > 5) {
                break;
            }
            System.out.println(k);
            k++;
            isReady = (k > 0);
        } while (isReady);

        //continue statement - stop executing the current iteration of a block of code in a loop and start new iteration
        int number = 0;
        while (number < 50) {
            number += 5;
            if(number % 25 == 0){
                continue;
            }
            System.out.print(number + "_");
        }
    }
}
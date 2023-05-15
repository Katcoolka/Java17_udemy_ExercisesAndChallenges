public class Main {
    public static void main(String[] args) {
        //statement - the complete line ended with semicolon
        int myVariable = 50;
        myVariable++;
        myVariable--;
        System.out.println("this is a test");

        System.out.println("This is " +
                " another " +
                " still more.");
        //valid byt not easy to read:
        int anotherVariable = 50; myVariable--; System.out.println("myVariable =" + myVariable);
        
        //white space - java ignores it  - still readable
        int               myVariableTwo 
                 =          60;
        //code-reformat code
        int anotherVariableTwo = 50;
        myVariable--;
        System.out.println("myVariable =" + myVariable);

        if (myVariable == 0) {
            System.out.println("It's now zero");
        }
    }
}
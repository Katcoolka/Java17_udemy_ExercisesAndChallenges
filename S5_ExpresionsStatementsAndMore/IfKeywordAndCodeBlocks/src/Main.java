public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        //if statement
        if (score == 5000) {
            System.out.println("your score is 5000");
        }

        //if...else statement
        if (score < 5000) {
            System.out.println("your score is less than 5000");
        } else {
            System.out.println("Got here");
        }

        //if...else statement
        if (score <= 5000) {
            System.out.println("your score is less or equal to 5000");
        } else {
            System.out.println("Got here");
        }

        //if...else if...else block
        if (score < 5000 && score > 1000) {
            System.out.println("your score is less than 5000 but greater than 1000");
        } else if (score < 1000) {
            System.out.println("your score was less than 1000");
        }else {
            System.out.println("Got here");
        }
    }
}
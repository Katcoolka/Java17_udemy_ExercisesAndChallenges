public class MainChallenge {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        //int finalScore = score;
        int highScore = CalculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("The high score is " + highScore);
        //we can add just 2 arguments, but we have to add the names of rest:
        calculateScore(true, 800,  5, 100);

//        //if (gameOver)
//        if (gameOver == true){
//            finalScore += (levelCompleted * bonus); //finalScore + (levelCompleted * bonus) //800 + 500
//            System.out.println("Your final score was " + finalScore);
//        }

        //challenge
//        boolean newGameOver = true;
//        int newScore = 10000;
//        int newLevelCompleted = 8;
//        int newBonus = 200;
//
//        int newFinalScore = score;
//
//        if (newGameOver == true){
//            finalScore += (levelCompleted * bonus); //finalScore + (levelCompleted * bonus) //800 + 500
//            System.out.println("Your final score was " + newFinalScore);
//        }
        //removing int from original variables
        //redefining variables - duplicating code // more changes needed because we might not update all places
//        score = 10000;
//        levelCompleted = 8;
//        bonus = 200;
//
//        finalScore = score;
//        if (gameOver == true){
//            finalScore += (levelCompleted * bonus); //finalScore + (levelCompleted * bonus) //800 + 500
//            System.out.println("Your final score was " + finalScore);
//        }
        //new updated values:
        calculateScore(true, 10000, 8, 200);
        // => we should use methods to avoid code duplication
    }

    //METHODS
    //passing parameters into method in ();
    public static void calculateScore(boolean gameOver, int score, int levelCompleted, int bonus){
        //no limit to number of parameters, separated by coma
        //parameter is the definition in the method declaration
        //argument is the value passed
        //no different order od parameters, no smaller amount of parameters!!!

        int finalScore = score;
        //if (gameOver)
        if (gameOver == true){
            finalScore += (levelCompleted * bonus); //finalScore + (levelCompleted * bonus) //800 + 500
            finalScore +=1000;
            System.out.println("Your final score was " + finalScore);
        }
    }

    public static int CalculateScore(boolean gameOver, int score, int levelCompleted, int bonus){

        int finalScore = score;

        if (gameOver == true){
            finalScore += (levelCompleted * bonus);
            finalScore +=1000;
        }
        return finalScore;
    }
}
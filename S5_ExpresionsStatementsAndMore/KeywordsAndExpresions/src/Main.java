public class Main {
    public static void main(String[] args) {
        //statement
         double kilometers = (100 * 1.609344);
         //expression is only the part highScore = 50
        int highScore = 50;
        if (highScore > 25) {
            highScore = 1000 + highScore; //add bonus points
        }
        //example of 5 expressions:
        int health = 100;

        if ((health < 25) && (highScore > 1000)){
            highScore = highScore - 1000;
        }
        
    }
}
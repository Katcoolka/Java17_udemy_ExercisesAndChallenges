public class Main {
    public static void main(String[] args) {

        //Code without encapsulation
        //fields are public
//        Player player = new Player();
//        player.name = "Tim";
//        player.health = 20;
//        player.weapon = "Sword";
//
//        int damage = 10;
//        player.looseHealth(damage);
//        System.out.println("Remaining health= " + player.healthRemaining());
//
//        player.health = 200;
//        player.looseHealth(11);
//        System.out.println("Remaining health= " + player.healthRemaining());

        //with encapsulation
        EnhancedPlayer tim = new EnhancedPlayer("Tim", 200, "Sword");
        System.out.println("initial health = " + tim.healthRemaining());
    }
}
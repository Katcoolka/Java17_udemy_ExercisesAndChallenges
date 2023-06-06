package dev.lpa.burger;

public class Store {

    public static void main(String[] args) {
        //in AUS Dollars
        Meal regularMeal = new Meal();
        regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");
        System.out.println(regularMeal);

        //in USD
        Meal USRegularMeal = new Meal(0.68);
        System.out.println(USRegularMeal);
    }
}

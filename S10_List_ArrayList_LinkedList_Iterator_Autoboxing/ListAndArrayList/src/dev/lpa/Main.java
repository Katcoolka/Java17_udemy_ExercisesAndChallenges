package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;

record GroceryItem(String name, String type, int count){
    public GroceryItem(String name){
        this(name, "DAIRY", 1);
    }
    @Override
    public String toString(){
        return String.format("%d %s in %s", count, name.toUpperCase(),type);
    }
}
public class Main {
    public static void main(String[] args) {
//        Object[] groceryArray = new Object[3];
//        groceryArray[0] = new GroceryItem("milk");
//        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
//        groceryArray[2] = "5 oranges";

        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
        System.out.println(Arrays.toString(groceryArray));

        //raw use of this type - any object could be put in this array list
        ArrayList objectList = new ArrayList();
        objectList.add(new GroceryItem("Butter"));
        objectList.add("Yogurt");

        //specifying the type of the ArrayList
        ArrayList<GroceryItem> groceryList = new ArrayList<GroceryItem>();
        //<> diamond operator
        groceryList.add(new GroceryItem("Butter"));
        //ArrayList is resizeable - it will grow as needed
        groceryList.add(new GroceryItem("milk"));
        groceryList.add(new GroceryItem("oranges", "PRODUCE", 5));
        groceryList.set(0, new GroceryItem("apples", "PRODUCE", 6));
        groceryList.remove(1);
        System.out.println(groceryList);

    }
}
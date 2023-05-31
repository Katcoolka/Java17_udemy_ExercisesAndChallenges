package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoreLists {
    public static void main(String[] args) {
        String[] items = {"apples", "bananas", "milk", "eggs"};
        List<String> list = List.of(items);
        //we get a list of array items
        System.out.println(list);
        //getName provides more information than getSimpleName
//        System.out.println(list.getClass().getName());
//        list.add("yogurt");

        ArrayList<String> groceries = new ArrayList<>(list);
        groceries.add("yogurt");
        System.out.println(groceries);

        //created new ArrayList
        ArrayList<String> nextList = new ArrayList<>(List.of("pickles", "mustard", "cheese"));
        System.out.println(nextList);
        //added nextList to groceries and print all items
        groceries.addAll(nextList);
        System.out.println(groceries);

        //ArrayLists start with a zero index position, so the third element is at index 2
        System.out.println("Third Item = " + groceries.get(2));

        //search for an item in ArrayList
        if(groceries.contains("mustard")){
            System.out.println("List contains mustard");
        }
        //the first and the last index of an item
        groceries.add("yogurt");
        System.out.println(groceries);
        System.out.println("first = " + groceries.indexOf("yogurt"));
        System.out.println("first = " + groceries.lastIndexOf("yogurt"));
        //remove method
        System.out.println(groceries);
        groceries.remove(1);
        System.out.println(groceries);

        //removes only the first element
        groceries.remove("yogurt");
        System.out.println(groceries);

        //remove all specified elements
        groceries.removeAll(List.of("apples", "eggs"));
        System.out.println(groceries);

        //retain method-> removes every item in the grocery list, which isn't one of the items passed in the argument.
        groceries.retainAll(List.of("apples", "milk", "mustard", "cheese"));
        System.out.println(groceries);

        //clear() - > removes all items
        groceries.clear();
        System.out.println(groceries);
        System.out.println("is empty = " + groceries.isEmpty());

        groceries.addAll(List.of("apples", "milk", "mustard", "cheese"));
        groceries.addAll(Arrays.asList("eggs", "pickles", "mustard", "ham"));
        System.out.println(groceries);
        //natural order for strings A->Z, for numbers smallest -> largest
        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);

        //items are printed in reverse order
        groceries.sort(Comparator.reverseOrder());
        System.out.println(groceries);

        var groceryArray = groceries.toArray(new String [groceries.size()]);
        System.out.println(Arrays.toString(groceryArray));
    }
}

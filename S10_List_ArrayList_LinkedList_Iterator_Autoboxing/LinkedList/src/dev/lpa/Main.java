package dev.lpa;

import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        //LinkedList<String> placesToVisit = new LinkedList<>();
        var placesToVisit = new LinkedList<String>();

        placesToVisit.add("Sydney");
        placesToVisit.add(0, "Canberra");
        System.out.println(placesToVisit);

        addMoreElements(placesToVisit);
        System.out.println(placesToVisit);

//        removeElements(placesToVisit);
//        System.out.println(placesToVisit);
        //gettingElements(placesToVisit);
        System.out.println("-".repeat(15));
        printItinerary(placesToVisit);
        System.out.println("-".repeat(15));
        printItinerary2(placesToVisit);
        System.out.println("-".repeat(15));
        printItinerary3(placesToVisit);
        System.out.println("-".repeat(15));
        //testIterator(placesToVisit);
        System.out.println("-".repeat(15));
        testIterator2(placesToVisit);

    }

    private static void addMoreElements(LinkedList<String> list) {
        list.addFirst("Darwin");
        list.addLast("Hobart");

        //Queue methods
        list.offer("Melbourne");
        list.offerFirst("Brisbane");
        list.offerLast("Toowoomba");

        //Stack Methods
        list.push("Alice Springs");
    }

    private static void removeElements(LinkedList<String> list) {
        list.remove(4);
        list.remove("Brisbane");

        System.out.println(list);
        String s1 = list.remove(); //removes first element
        System.out.println(s1 + " was removed");

        String s2 = list.removeFirst(); //removes first element
        System.out.println(s2 + " was removed");

        String s3 = list.removeLast(); //removes last element
        System.out.println(s3 + " was removed");

        //Queue/Dequeue poll methods
        String p1 = list.poll(); //removes first element
        System.out.println(p1 + " was removed");

        String p2 = list.pollFirst(); //removes first element
        System.out.println(p2 + " was removed");

        String p3 = list.pollLast(); //removes last element
        System.out.println(p3 + " was removed");
        //at this stage our list is empty, so we have to populate it again
        list.push("Sydney");
        list.push("Brisbane");
        list.push("Canberra");
        System.out.println(list);

        String p4 = list.pop(); //removes first element
        System.out.println(p4 + " was removed");
        ;
    }

    private static void gettingElements(LinkedList<String> list) {
        System.out.println("Retrieved element = " + list.get(4));

        System.out.println("First Element = "  + list.getFirst());
        System.out.println("Last Element = " + list.getLast());

        System.out.println("Darwin is at position: " + list.indexOf("Darwin"));
        System.out.println("Melbourne is at position: " + list.lastIndexOf("Melbourne"));

        //Queue retrieval method
        System.out.println("Element from element() = " + list.element());

        //Stack retrieval method
        System.out.println("Element from peek() = " + list.peek());
        System.out.println("Element from peekFirst() = " + list.peekFirst());
        System.out.println("Element from peekLast() = " + list.peekLast());
    }
    //Traversing/stepping trought the elements
    //standard for loop
    private static void printItinerary(LinkedList<String> list){
        System.out.println("Trip starts at " + list.getFirst());
        for (int i = 1; i< list.size(); i++){
            System.out.println("---> From: " + list.get(i - 1) + " to " + list.get(i));
        }
        System.out.println("Trip ends at " + list.getLast());
    }
    //enhanced for loop
    private static void printItinerary2(LinkedList<String> list){
        System.out.println("Trip starts at " + list.getFirst());
        String previousTown = list.getFirst();
        for(String town : list){
            System.out.println("---> From: " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at " + list.getLast());
    }
    //Iterator
    private static void printItinerary3(LinkedList<String> list){
        System.out.println("Trip starts at " + list.getFirst());
        String previousTown = list.getFirst();
        ListIterator<String> iterator = list.listIterator(1); //by adding index 1 we can avoid double listing of Alice Springs
        while(iterator.hasNext()){
            var town  = iterator.next();
            System.out.println("---> From: " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at " + list.getLast());
    }

    //Iterator -> in more details
    //An Iterator is forwards only, and only supports the remove method.
    private static void testIterator(LinkedList<String> list){
        var iterator = list.iterator();
        while (iterator.hasNext()){
            //System.out.println(iterator.next());
            if(iterator.next().equals("Brisbane")){
                iterator.remove(); //The iterator provides a safe way to remove elements, while still iterating through the list. It's important, to make sure you're calling remove on the iterator object, and not the list object.
            }
        }
        System.out.println(list);
    }
    //ListIterator
    //A ListIterator can be used to go both forwards and backwards, and in addition to the remove method,it also supports the add and set methods.
    //the iterator's cursor positions are between the elements.
    private static void testIterator2(LinkedList<String> list){
        var iterator = list.listIterator();
        while (iterator.hasNext()){
            if(iterator.next().equals("Brisbane")){
                iterator.add("Lake Wivenhoe");
            }
        }
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
        System.out.println(list);

        var iterator2 = list.listIterator(3);
        System.out.println(iterator2.previous());
    }
}
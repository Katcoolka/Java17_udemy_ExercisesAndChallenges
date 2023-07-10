package dev.lpa;

import javax.lang.model.element.Name;
import java.util.*;

public class TreeSetMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        //NavigableSet<Contact> sorted =  new TreeSet<>(phones);
        //we have to implement Comparator to avoid compile errors
        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);

        //getting names of Contacts, sorted
        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(c -> justNames.add(c.getName()));
        System.out.println(justNames);

        //full set of contacts in alphabetical order
        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        //full list of contacts in alphabetical order contains duplicates
        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator());
        System.out.println("---------------------------");
        fullList.forEach(System.out::println);

        //minimum and maximum / first and last
        System.out.println("-------- min and max ----------");
        Contact min = Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());

        Contact first = fullSet.first();
        Contact last = fullSet.last();

        System.out.printf("min = %s, first = %s %n", min.getName(), first.getName());
        System.out.printf("max = %s, last = %s %n", max.getName(), last.getName());
        System.out.println("---------------------------");

        // poll first and poll last
        System.out.println("-------- pollFirst and pollLast ----------");
        //remove those elements from the set (first and last element)
        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("First element = " + copiedSet.pollFirst());
        System.out.println("Last element = " + copiedSet.pollLast());
        copiedSet.forEach(System.out::println);
        System.out.println("---------------------------");

        //adding few new contacts
        Contact daffy = new Contact("Daffy Duck");// is in set
        Contact daisy = new Contact("Daisy Duck"); // is not in set
        Contact snoopy = new Contact("Snoopy"); //is not in the set
        Contact archie = new Contact("Archie");

        System.out.println("------- ceiling, higher method--------");
        //ceiling as returning the element, that is either greater than or equal to the element passed.
        //higher method never returns the value that's equal to it in a set,
        //it always returns the next greater element, so we get Linus there.
        for (Contact c : List.of(daffy, daisy, last, snoopy)) {
            System.out.printf("ceiling(%s)=%s%n", c.getName(), fullSet.ceiling(c));
            System.out.printf("higher(%s)=%s%n", c.getName(), fullSet.higher(c));
        }
        System.out.println("----------------------------");

        System.out.println("------- floor, lower method--------");
        //same case but with first
        //floor method returns the element that's equal to the argument passed, if that element's in the set.
        //lower method returns the element just lower than the element passed, so we get Charlie Brown.
        for (Contact c : List.of(daffy, daisy, last, snoopy)) {
            System.out.printf("floor(%s)=%s%n", c.getName(), fullSet.floor(c));
            System.out.printf("lower(%s)=%s%n", c.getName(), fullSet.lower(c));
        }
        System.out.println("----------------------------");

        System.out.println("------- descending set--------");
        NavigableSet<Contact> descendingSet = fullSet.descendingSet();
        descendingSet.forEach(System.out::println);
        System.out.println("----------------------------");

        //The set that's returned is backed by the original set, so any changes to the fullSet, will be reflected in this set, and vice versa.

        Contact lastContact = descendingSet.pollLast();
        System.out.println("Removed " + lastContact);
        descendingSet.forEach(System.out::println);
        System.out.println("----------------------------");
        fullSet.forEach(System.out::println);
        System.out.println("----------------------------");

        System.out.println("------- headSet, tailSet--------");
        //headSet- is exclusive by default, meaning it will exclude the element passed.
        //the headSet, when passed the Maid Marion contact, returned all the elements less than Maid Marion.
        Contact marion = new Contact("Maid Marion");
        var headSet = fullSet.headSet(marion);
        headSet.forEach(System.out::println);
        System.out.println("----------------------------");

        //tailSet- is inclusive by default, meaning it will include the element
        //the tailSet, when passed the same contact, returned all the elements after Maid Marion, but also included the maid Marion record.
        var tailSet = fullSet.tailSet(marion);
        tailSet.forEach(System.out::println);
        System.out.println("----------------------------");

        System.out.println("------- subSet --------");
        Contact linus = new Contact("Linus Van Pelt");
        var subset = fullSet.subSet(linus, false, marion, true);
        subset.forEach(System.out::println);
        System.out.println("----------------------------");
    }
}

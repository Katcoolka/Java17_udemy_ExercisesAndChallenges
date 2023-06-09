package dev.lpa;

import java.util.*;

public class MapViewsMain {
    public static void main(String[] args) {
        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        Set<String> keysView = contacts.keySet();//values in no particular order
        System.out.println(keysView);

        Set<String> copyOfKeys = new TreeSet<>(contacts.keySet()); //creates a copy and put values in alphabetical order
        System.out.println(copyOfKeys);

        System.out.println("-------------------\n");

        if (contacts.containsKey("Linus Van Pelt")) {
            System.out.println("Linus and I go way back, so of course I have info");
        }
        keysView.remove("Daffy Duck");
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));
        System.out.println("-------------------\n");

        copyOfKeys.remove("Linus Van Pelt");
        System.out.println(copyOfKeys);
        contacts.forEach((k, v) -> System.out.println(v));
        System.out.println("-------------------\n");

        keysView.retainAll(List.of("Linus Van Pelt", "Charlie Brown", "Robin Hood", "Mickey Mouse"));
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));
        System.out.println("-------------------\n");

        keysView.clear();//clearing contact map of elements - we get {} in the console
        System.out.println(contacts);

        //adding contacts back
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
        System.out.println(keysView);

        System.out.println("-------------------\n");

        var values = contacts.values();
        values.forEach(System.out::println);
        System.out.println("-------------------\n");

        values.retainAll(ContactData.getData("email"));
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

        System.out.println("-------------------\n");
        System.out.println("-----last name first-------");
        //alphabetical list by last name
        List<Contact> list = new ArrayList<>(values);
        list.sort(Comparator.comparing(Contact::getNameLastFirst));
        list.forEach(c -> System.out.println(c.getNameLastFirst() + ": " + c));

        System.out.println("-------------------\n");
        System.out.println("------adding duplicate contact---");
        //under different key name
        Contact first = list.get(0);
        contacts.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
        keysView.forEach(System.out::println);
        System.out.println("-------------------\n");

        System.out.println("-------collection to hashSet-------");
        HashSet<Contact> set = new HashSet<>(values);
        set.forEach(System.out::println);
        if (set.size() < contacts.keySet().size()) {
            System.out.println("Duplicate Values are in my Map");
        }
        System.out.println("-------------------\n");
        System.out.println("<----- View Collections - EntrySet ------>");

        var nodeSet = contacts.entrySet();
        for (var node : nodeSet) {
            System.out.println(nodeSet.getClass().getName());
            if (!node.getKey().equals(node.getValue().getName())) {
                System.out.println(node.getClass().getName());
                System.out.println("Key doesn't match name: " + node.getKey() + ": " + node.getValue());
            }
        }





    }
}

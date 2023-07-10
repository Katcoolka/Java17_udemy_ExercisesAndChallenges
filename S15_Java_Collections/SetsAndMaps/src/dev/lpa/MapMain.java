package dev.lpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("---------------------------------");

        System.out.println("---------hashMap and put method------->");
        //hashMap
        //keys will be the contact names, values will be contact records
        //These elements aren't in any particular order, there's no duplicates,of either key or value. Keys must be unique,
        /*The put method always puts the element in the map.
            If the key is not in the map, the key and value are added.
            If the key is in the map, the value is replaced, and the previous value
            is returned from the put method.
          For a map, this means the last element in your list, is the one that ends up in your map*/
        Map<String, Contact> contacts = new HashMap<>();

        for (Contact contact : fullList) {
            contacts.put(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
        System.out.println("---------------------------------");

        System.out.println("---------hashMap and get method------->");
        //the get method successfully retrieved my Charlie Brown contact record.
        //If Charlie Brown is not a key in the map, the get method would return a null.
        System.out.println(contacts.get("Charlie Brown"));

        System.out.println(contacts.get("Chuck Brown"));
        System.out.println("---------hashMap and getOrDefault method------->");
        //It gives us a Contact record back, for Chuck Brown with no other data.
        //It's important to remember, though that Chuck Brown never gets added to the map, that contact is just there, the defaultContact, as a convenience.
        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));

        System.out.println("---------------------------------");
        System.out.println("---------hashMap,  merge method------->");
        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.put(contact.getName(), contact);
            if (duplicate != null) {
//                System.out.println("duplicate = " + duplicate);
//                System.out.println("current= " + contact);
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("---------------------------------");

        System.out.println("---------hashMap,  putIfAbsent method------->");
        //The put if absent method won't put an updated value in the map, it just ignores the element, if it already finds something in the map for the key. Again, this method returns an element, if one is already in the map for the key, but the method doesn't replace it with the current element.
        //It returns null if this is the first time an entry is being added to the map for that key.
        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            if (duplicate != null) {
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
        System.out.println("---------------------------------");

        System.out.println("-----------progression of merging-------");
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                (previous, current) -> {
                    System.out.println("prev: " + previous + " : current " + current);
                    Contact merged = previous.mergeContactData(current);
                    System.out.println("merged: " + merged);
                    return merged;
                }));
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("--------using lambda---------");
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergeContactData
        ));
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("---------------------------------");

        System.out.println("--------compute method---------");
        //Compute is like the put method in this way, replacing what's in the map with the result
        //of the Bi Function, or lambda expression.
        for (String contactName : new String[]{"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.compute(contactName, (k, v) -> new Contact(k));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
        System.out.println("---------------------------------");

        System.out.println("--------computeIfAbsent method---------");
        for (String contactName : new String[]{"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfAbsent(contactName, k -> new Contact(k));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
        System.out.println("---------------------------------");
        System.out.println("--------computeIfPresent method---------");
        for (String contactName : new String[]{"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfPresent(contactName, (k, v) -> {
                v.addEmail("Fun Place");
                return v;
            });
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("---------------------------------");
        System.out.println("--------replaceAll method---------");
        contacts.replaceAll((k, v)->{
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("---------------------------------");
        System.out.println("--------replace method---------");
        Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");

        Contact replacedContact = contacts.replace("Daisy Duck", daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("replacedContact = " + replacedContact);
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("---------------------------------");
        System.out.println("--------mergeContactData method---------");
        Contact updateDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy = " + updateDaisy);
        boolean success = contacts.replace("Daisy Duck", daisy, updateDaisy);
        if (success){
            System.out.println("Successfully replaced element");
        }else {
            System.out.println("Did not match on both key: %s and value %s %n"
                    .formatted("Daisy Duck", replacedContact));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("---------------------------------");
        System.out.println("--------remove method---------");
        success = contacts.remove("Daisy Duck", daisy);
        if (success){
            System.out.println("Successfully removed element");
        }else {
            System.out.println("Did not match on both key: %s and value: %s %n"
                    .formatted("Daisy Duck", daisy));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
    }

}

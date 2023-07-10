package dev.lpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

        //Set & HashSet
        //the hash set implementation doesn't include a replace, or replaceAll method.
        //basic functions on set: add, remove, and contains.
        // there's no get method on a set. If you want to get an element from the set, you'll have to iterate through every element, and look for a match manually.
        //HashSet's not going to be ordered or sorted.
        //Sets are valuable for groups of elements, when you'll be adding elements, removing duplicates, checking if an element is in the list, or other set operations

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone Contacts", phoneContacts);
        printData("Email Contacts", emailContacts);

        //adding company email to a new contact
        int index =  emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinHood);
        System.out.println();

        System.out.println("<<<---------- Set Operations ----------->>>");
        //Set Operations
        //sets can be explained and displayed in Venn diagrams
        System.out.println("*--->> Symmetric Operations <<---*");
        //Symmetric operations: addAll, retainAll
        //addAll method -> works as union of 2 sets
        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(emailContacts);
        unionAB.addAll(phoneContacts);
        printData("(A ∪ B) Union of emails (A) with phones (B)", unionAB); //symbol ∪ => \u222A

        //retainAll method -> works as intersection of 2 sets
        Set<Contact> intersectAB = new HashSet<>(emailContacts);
        intersectAB.retainAll(phoneContacts);
        printData("(A ∩ B) Intersect of emails (A) with phones (B)", intersectAB); //symbol ∩ => \u2229

        Set<Contact> intersectBA = new HashSet<>(phoneContacts);
        intersectBA.retainAll(emailContacts);
        printData("(B ∩ A) Intersect of phones (B) and emails (A)", intersectBA);

        System.out.println();
        System.out.println("*--->> Asymmetric Differences <<---*");
        //a difference subtracts elements in common from one set and another, leaving only the distinct elements from the firs set as a result
        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        printData("(A - B) emails (A) minus phones (B)", AMinusB);

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        printData("(B - A) phones (B) minus emails (A)", BMinusA);

        System.out.println();
        System.out.println("*--->> Symmetric Differences <<---*");
        //the elements from all sets that don't intersect
        Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        printData("Symmetric difference: phones and emails", symmetricDiff);

        Set<Contact> symmetricDiff2 = new HashSet<>(unionAB);
        symmetricDiff2.removeAll(intersectAB);
        printData("Symmetric difference: phones and emails", symmetricDiff2);
    }

    public static void printData(String header, Collection<Contact> contacts){
        System.out.println("-----------------------------------");
        System.out.println(header);
        System.out.println("-----------------------------------");
        contacts.forEach(System.out::println);
    }
}
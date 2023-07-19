package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainMailer {
    public static void main(String[] args) {

        String[] names = {"Ann Jones", "Ann Jones Ph.D.", "Bob Jones M.D.", "Carol Jones", "Ed Green Ph.D.", "Ed Green M.D.", "Ed Black"};

        List<StringBuilder> population = getNames(names);
        //System.out.println(population);
        Map<StringBuilder, Integer> counts = new TreeMap<>();
        population.forEach(s -> {
            counts.merge(s, 1, Integer::sum);
        });
        System.out.println(counts);
        System.out.println("--------------------------------------");

        //count of Ann Jones Ph.D.
        StringBuilder annJonesPhd = new StringBuilder("Ann Jones Ph.D.");
        System.out.println("There are " + counts.get(annJonesPhd) + " records for " + annJonesPhd);
        System.out.println("--------------------------------------");

        //Clean names without titles
        List<StringBuilder> cleanedNames = standardizedNames(population);
        System.out.println(cleanedNames);
        System.out.println("There are " + counts.get(annJonesPhd) + " records for " + annJonesPhd); //returns null records
        System.out.println(counts);//returns duplicates
        System.out.println("--------------------------------------");

        //records just for Ann Jones
        StringBuilder annJones = new StringBuilder("Ann Jones");
        System.out.println("There are " + counts.get(annJones) + " records for " + annJones);
        System.out.println("--------------------------------------");

        //counting key, value
        counts.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("--------------------------------------");

        //using keySet
        counts.keySet().forEach(k -> System.out.println(k + ": " + counts.get(k)));

    }

    private static List<StringBuilder> getNames(String[] names) {

        List<StringBuilder> list = new ArrayList<>();
        int index = 5;
        for (String name : names) {
            for (int i = 0; i < index; i++) {
                list.add(new StringBuilder(name));
            }
            index++;
        }
        return list;
    }

    //The standardized Names method, which seemed harmless enough, has produced a very ugly side effect, on the map of StringBuilders.
    //It didn't matter what collection my StringBuilders were in, they were all referring to the same group of instances in memory.
    //A change to one variable in any collection, will change that instance in memory.
    //If that instance's a key to a mapped collection, you get into this ugly situation.
    //This is why you should use an immutable object, for keys in a map, so that this never happens.
    private static List<StringBuilder> standardizedNames (List<StringBuilder> list) {

        List<StringBuilder> newList = new ArrayList<>();
        for (var name : list) {
            for (String suffix : new String[] {"Ph.D.", "M.D."}) {
                int startIndex = -1;
                if ((startIndex = name.indexOf(suffix)) > -1) {
                    name.replace(startIndex -1, startIndex + suffix.length(), "");
                }
            }
            newList.add(name);
        }
        return newList;
    }
}

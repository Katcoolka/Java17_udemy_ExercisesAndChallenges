package dev.lpa;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All tasks", tasks);

        //adding comparator to sort tasks  by priority
        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's tasks", annsTasks, sortByPriority);

        //1. List of all assigned tasks
        //first add sets for remaining colleagues
        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        Set<Task> carolsTasks = TaskData.getTasks("Carol");
        //create list of all sets
        List<Set<Task>> sets = List.of(annsTasks, bobsTasks, carolsTasks);
        //create union of all sets
        Set<Task> assignedTasks = getUnion(sets);
        sortAndPrint("Assigned tasks", assignedTasks);

        //2. list of all tasks
        Set<Task> everyTask = getUnion(List.of(tasks, assignedTasks));
        sortAndPrint("The true All Tasks", everyTask);

        //3. what is missing from Boss's list
        Set<Task> missingTasks = getDifference(everyTask, tasks);
        sortAndPrint("Missing Tasks", missingTasks);

        //4. which tasks has from Boss's list were not assigned
        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        sortAndPrint("Unassigned Tasks", unassignedTasks, sortByPriority);

        //5. finding overlaps of tasks
        Set<Task> overlap = getUnion(List.of(getIntersect(annsTasks, bobsTasks),
                getIntersect(carolsTasks, bobsTasks),
                getIntersect(annsTasks, carolsTasks)));
        sortAndPrint("Assigned to Multiples", overlap, sortByPriority);

        //6. list of tasks assigned to Ann and others that needs to be reassigned
        List<Task> overlapping = new ArrayList<>();
        for (Set<Task> set : sets) {
            Set<Task> dupes = getIntersect(set, overlap);
            overlapping.addAll(dupes);
        }
        //sort by priority => project => description
        Comparator<Task> priorityNatural = sortByPriority.thenComparing(Comparator.naturalOrder());
        sortAndPrint("Overlapping", overlapping, priorityNatural);
    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {
        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    //3 challenge methods:
    private static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> union = new HashSet<>();
        for (var taskSet : sets) {
            union.addAll(taskSet);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> a, Set<Task> b) {
        Set<Task> intersect = new HashSet<>(a);
        intersect.retainAll(b);
        return intersect;
    }

    private static Set<Task> getDifference(Set<Task> a, Set<Task> b) {
        Set<Task> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }
}
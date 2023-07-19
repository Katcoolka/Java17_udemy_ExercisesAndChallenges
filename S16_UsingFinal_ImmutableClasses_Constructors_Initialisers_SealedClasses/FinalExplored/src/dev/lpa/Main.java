package dev.lpa;

import consumer.specific.ChildClass;
import dev.lpa.generic.BaseClass;
import external.util.Logger;

public class Main {
    public static void main(String[] args) {

        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        System.out.println("-------------");
        childReferredToAsBase.recommendedMethod();
        System.out.println("--------------");
        child.recommendedMethod();
        System.out.println("--------------");

        System.out.println(">>>----- Static methods ------>>>");
        //static methods
        parent.recommendedStatic();
        System.out.println("-------------");
        childReferredToAsBase.recommendedStatic();
        System.out.println("--------------");
        child.recommendedStatic();
        System.out.println("--------------");

        //using class types
        BaseClass.recommendedStatic();
        ChildClass.recommendedStatic();
        System.out.println("--------------");

        System.out.println(">>>----- Final variables------>>>");
        String xArgument = "This is all I've got to say about section ";
        StringBuilder zArgument = new StringBuilder("Only saying this: Section ");
        doXYZ(xArgument, 16, zArgument);
        System.out.println("After Method, xArgument: " + xArgument);
        System.out.println("After Method, zArgument: " + zArgument);
        System.out.println("--------------");

        System.out.println(">>>----- When the change is not good------>>>");
        //When Change isn't good
        StringBuilder tracker = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker);
        tracker.append(", step 2 is xyz.");
        Logger.logToConsole(tracker);
        System.out.println("After logging, tracker = " + tracker);
        System.out.println("--------------");
        //adding to string will correct the outcome
        StringBuilder tracker1 = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker1.toString());
        tracker1.append(", step 2 is xyz.");
        Logger.logToConsole(tracker1.toString());
        System.out.println("After logging, tracker = " + tracker1);
    }

    private static void doXYZ(String x, int y, final StringBuilder z){
        final String c = x + y;
        System.out.println("c = " + c);
        x = c;
        z.append(y);
        //z = new StringBuilder("jbk"); //compiler error
    }
}
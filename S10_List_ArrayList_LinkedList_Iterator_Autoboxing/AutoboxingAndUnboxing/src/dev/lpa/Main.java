package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        //manual boxing and unboxing
        Integer boxedInt = Integer.valueOf(15); //preferred but unnecessary
        Integer deprecatedBoxing = new Integer(15);//deprecated since JDK 9
        int unboxedInt = boxedInt.intValue(); //unnecessary

        //Automatic /Autoboxing and Unboxing
        Integer autoboxed = 15;
        int autoUnboxed = autoboxed;
        System.out.println(autoboxed.getClass().getName());
        //System.out.println(autoUnboxed.getClass().getName());

        Double resultBoxed = getLiteralDoublePrimitive();
        double resultUnboxed = getDoubleObject();
        //Integer array
        Integer[] wrapperArray = new Integer[5];
        wrapperArray[0] = 50;
        System.out.println(Arrays.toString(wrapperArray));
        System.out.println(wrapperArray[0].getClass().getName());

        //character array
        Character[] characterArray = {'a', 'b','c', 'd'};
        System.out.println(Arrays.toString(characterArray));

        //var ourList = getList(1,2,3,4,5);
        var ourList = List.of(1,2,3,4,5);
        System.out.println(ourList);
    }
    private static ArrayList<Integer> getList(int...varargs){
        ArrayList<Integer> aList = new ArrayList<>();
        for (int i: varargs){
            aList.add(i);
        }
        return aList;
    }
    private static int returnAnInt(Integer i){
        return i;
    }
    private static Integer returnAnInteger(int i){
        return i;
    }
    private static  Double getDoubleObject(){
        return Double.valueOf(100.00);
    }

    private static double getLiteralDoublePrimitive(){
        return 100.0;
    }
}
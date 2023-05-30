package dev.dlp;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //two-dimensional array
        int[][] array2 = new int[4][4];
        System.out.println(Arrays.toString(array2));
        System.out.println("array2.length = " + array2.length);

        for (int[] outer : array2) {
            System.out.println(Arrays.toString(outer));
        }
        //traditional for loops
        for (int i = 0; i < array2.length; i++) {
            var innerArray = array2[i];
            for (int j = 0; j < innerArray.length; j++) {
                //System.out.print(array2[i][j]);
                array2[i][j] = (i * 10) + (j + 1);
            }
            //System.out.println();
        }

        //enhanced for loops
//        for (var outer: array2) {
//            for (var element : outer) {
//                System.out.print(element + " ");
//            }
//            System.out.println();
//        }
        System.out.println(Arrays.deepToString(array2));


        array2[1] = new int[]{10, 20, 30};
        System.out.println(Arrays.deepToString(array2));

        //multi-dimensional arrays
        Object[] anyArray = new Object[3];
        System.out.println(Arrays.toString(anyArray));

        //the 1st element of the array
        anyArray[0] = new String[]{"a", "b", "c"};
        System.out.println(Arrays.deepToString(anyArray));
        //the 2nd element of the array
        anyArray[1] = new String[][]{
                {"1", "2"},
                {"3", "4", "5"},
                {"6", "7", "8", "9"}
        };
        System.out.println(Arrays.deepToString(anyArray));
        //the 3rd element of the array
        anyArray[2] = new int[2][2][2];
        System.out.println(Arrays.deepToString(anyArray));

        for (Object element : anyArray) {
            System.out.println("Element type = " + element.getClass().getSimpleName());
            System.out.println("Element toString() = " + element);
            System.out.println(Arrays.deepToString((Object[]) element));
        }

    }
}
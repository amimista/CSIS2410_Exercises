package junitReview;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    /**
     * Calculates the sum of all numbers in the 2d array
     * If the array is empty, an IllegalArgumentException
     * should be thrown.
     *
     * @param a integer 2d array
     * @return sum of all elements
     */
    public static int arraySum(int[][] a) {
        int sum = 0;
        if(a.length == 0) {
            throw new IllegalArgumentException("The array can't be empty when calculating the sum of the numbers.");
        }
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[i].length; j++) {
                sum += a[i][j];
            }
        }
        return sum;
    }

    /**
     * Returns all numbers by one row after the other.
     *
     * @param a target list
     * @return
     */
    public static Iterable<Integer> getAllNumbers(int[][] a) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[i].length; j++) {
                list.add(a[i][j]);
            }
        }
        return list;
    }
//
// Change
//    === TEST CLIENT
    public static void main(String[] args) {

    }
}

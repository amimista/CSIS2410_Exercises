package playground;

import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        int[][] list = new int[5][5];
        // define the range
        int max = 10;
        int min = 1;
        int range = max - min + 1;

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[0].length; j++) {
                list[i][j] = (int)(Math.random()*range) + min;
                System.out.printf("%-2d ", list[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        int[][] list2 = new int[4][5];
//        System.arraycopy(list, 1, list2, 0, list2[0].length);
        for (int i = 1; i < list.length; i++) {
            for (int j = 0; j < list[0].length; j++) {
                list2[i-1][j] = list[i][j];
                System.out.printf("%-2d ", list2[i-1][j]);
            }
            System.out.println();
        }
    }
}

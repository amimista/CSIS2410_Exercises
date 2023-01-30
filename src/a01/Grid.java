package a01;

import java.util.*;
import java.lang.StringBuilder;

/**
 * Immutable class that represents a square grid filled with numbers.
 *
 * @author CSIS-2410 + ......
 *
 */
public final class Grid {
    private final int[][] grid;
    private StringBuilder stringB;

    /**
     * Creates a grid of the specified size and fills it with random n-digit numbers.
     * @param size dimensions of the grid. ex) 5 would yield a 5x5 grid
     */
    public Grid (int size, int n) {
        Random rand = new Random();
        grid = new int[size][size];
//		Being able to print out the starting grid for debugging purposes
        stringB = new StringBuilder();
        stringB.append("%").append(Integer.valueOf(n)).append("d ");
//		setting the size of the random number (non-inclusive)
        StringBuilder n_digits = new StringBuilder("10");
        n_digits.append("0".repeat(Math.max(0, n - 1)));

        System.out.println("******************************");
        System.out.printf("STARTING GRID (%dx%d) %d-digit%n", size, size, n);
        System.out.println("******************************\n");

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = rand.nextInt(Integer.parseInt(n_digits.toString()));
                System.out.printf(stringB.toString(), grid[i][j]);
            }
            System.out.println(); // end of the row, make a new line
        }
    }

    /**
     * Creates a grid of the specified size and fills it with random 2-digit numbers.
     * @param size the dimensions of the grid
     */
    public Grid (int size) {
        this(size, 2);
    }

    /**
     * Creates a grid based on <code>array2d</code> provided as an argument.
     * @param array2d two-dimensional array of numbers
     */
    public Grid (int[][] array2d) {
        grid = array2d;
        System.out.println("************************************");
        System.out.print("STARTING GRID (with predefined grid)\n");
        System.out.println("************************************");

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * <p>Determines whether the number in row i and column j is the largest among its neighbors.
     * A number is considered the largest if none of the neighboring numbers is larger.<p />
     *
     * <p>A number can have a maximum of 8 neighbors (above, below, left, right, and in the diagonals).
     * However, if the number is situated at the boundary of the grid, it will have fewer neighbors.</p>
     *
     * Examples based on the following 3 x 3 grid:
     * <p style="font-family:monospace">
     *  1  2  3<br />
     *  4  5  6<br />
     *  7  8  9</p>
     *
     * <p>Assume i = 1 and j = 1.<br />
     * This references the number 5 and the neighbors would be 1, 2, 3, 4, 6, 7, 8, and 9.<br />
     * The method largestNeighbor returns false.<br />
     * <br />
     * Assume i = 2 and j = 2. <br />
     * This would references the number 9 and the neighbors would be 5, 6, and 8.<br />
     * The method largestNeighbor returns true.</p>
     *
     * Example based on the following 3 x 3 grid:
     * <p style="font-family:monospace">
     *  8  2  2<br />
     *  3  5  4<br />
     *  6  6  4</p>
     *
     * <p>Assume i = 2 and j = 1. <br />
     * This references the number 6 and the neighbors would be 3, 5, 4, 6, and 4.<br />
     * The method largestNeighbor returns true.</p>
     *
     * @param i index of the row
     * @param j index of the column
     * @return true if no neighboring number is larger than the number in the specified position<br />
     *         false otherwise
     */
    public boolean largestNeighbor(int i, int j) {
        var rowLimit = grid.length-1;
        var columnLimit = grid[0].length-1;
        ArrayList<Integer> neighbors = new ArrayList<>();

        for(var x = Math.max(0, i-1); x <= Math.min(i+1, rowLimit); x++) {
            for(var y = Math.max(0, j-1); y <= Math.min(j+1, columnLimit); y++) {
                if(x != i || y != j) {
                    neighbors.add(grid[x][y]);
//                    System.out.println("added " + grid[x][y]);
                }
            }
        }
        neighbors.sort(Comparator.reverseOrder());
        return (grid[i][j] > neighbors.get(0));
    }


    /**
     * <p>Determines whether the number in row i and column j is the smallest among its neighbors.
     * A number is considered the smallest if none of the neighboring numbers is smaller.<p />
     *
     * @param i index of the row
     * @param j index of the column
     * @return true if no neighboring number is smaller than the one in the specified cell <br />
     *         false otherwise
     */
    public boolean smallestNeighbor(int i, int j) {
        return false; // TODO
    }

    /**
     * <p>Returns all the numbers in the grid in the following order:<br />
     * It starts with the number on position i = 0, j = 0, returns all numbers from row 0
     * left to right, then the numbers from the next row right to left, then the numbers
     * from the next row left to right again, etc.</p>
     *
     * Example based on the following 5 x 5 grid:
     * <p style="font-family:monospace">
     *  10  11  12  13  14<br />
     *  15  16  17  18  19<br />
     *  20  21  22  23  24<br />
     *  25  26  27  28  29<br />
     *  30  31  32  33  34</p>
     *
     *  <p>The numbers are returned in the following sequence:<br />
     *  10, 11, 12, 13, 14, 19, 18, 17, 16, 15, 20, 21, 22, 23, 24,
     *  29, 28, 27, 26, 25, 30, 31, 32, 33, 34</p>
     *
     *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
     *  A 0 x 0 grid returns an empty Iterable.</p>
     *
     * @return the grid elements in the specified way
     */
    public Iterable<Integer> snake1() {
        List<Integer> list = new ArrayList<>();
        // Go through all rows
        for (int i = 0; i < grid.length; i++) {

            // If current row is even, print from
            // left to right
            if (i % 2 == 0) {
                for (int j = 0; j < grid[0].length; j++)
//                    System.out.print(grid[i][j] + " ");
                    list.add(grid[i][j]);

                // If current row is odd, print from
                // right to left
            }
            else {
                for (int j = grid[0].length - 1; j >= 0; j--)
//                    System.out.print(grid[i][j] + " ");
                    list.add(grid[i][j]);

            }
        }

        return (list);
    }

    /**
     * <p>Returns all the numbers in the grid in the following order:<br />
     * It starts with the number on position i = 0, j = 0, returns all numbers from column 0
     * top to bottom, then the numbers from the next column bottom to top, then the numbers
     * from the next column top to bottom again, etc.</p>
     *
     * Example based on the following 5 x 5 grid:
     * <p style="font-family:monospace">
     *  10  11  12  13  14<br />
     *  15  16  17  18  19<br />
     *  20  21  22  23  24<br />
     *  25  26  27  28  29<br />
     *  30  31  32  33  34</p>
     *
     *  <p>The numbers are returned in the following sequence:<br />
     *  10, 15, 20, 25, 30, 31, 26, 21, 16, 11, 12, 17, 22, 27, 32,
     *  33, 28, 23, 18, 13, 14, 19, 24, 29, 34</p>
     *
     *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
     *  A 0 x 0 grid returns an empty Iterable.</p>
     *
     * @return the grid elements in the specified way
     */
    public Iterable<Integer> snake2() {
        return null; // TODO
    }

    /**
     * <p>Returns the numbers in the grid as if they were accessed along a spiral path
     * starting with the first row.<br />
     * Each element in the grid is returned exactly once.
     * The Iterable starts with the number on position i = 0, j = 0,
     * returns all numbers from row 0 left to right,
     * then the numbers from the last column top to bottom,
     * then the numbers from the last row right to left, etc.</p>
     *
     * Example based on the following 5 x 5 grid:
     * <p style="font-family:monospace">
     *  10  11  12  13  14<br />
     *  15  16  17  18  19<br />
     *  20  21  22  23  24<br />
     *  25  26  27  28  29<br />
     *  30  31  32  33  34</p>
     *
     *  <p>The numbers are returned in the following sequence:<br />
     *  10, 11, 12, 13, 14, 19, 24, 29, 34, 33, 32, 31, 30, 25, 20,
     *  15, 16, 17, 18, 23, 28, 27, 26, 21, 22</p>
     *
     *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
     *  A 0 x 0 grid returns an empty Iterable.</p>
     *
     * @return the grid elements in the specified way
     */
    public Iterable<Integer> spiral1() {
        List<Integer> result = new ArrayList<Integer>();

        if (grid.length == 0)
            return result;

        int gridLength = grid.length;
        boolean[][] seen = new boolean[gridLength][gridLength];
        int[] rowModifier = { 0, 1, 0, -1 };
        int[] columnModifier = { 1, 0, -1, 0 };
        int x = 0, y = 0, direction = 0;

        // Iterate from 0 to R * C - 1
        for (int i = 0; i < gridLength * gridLength; i++) {
            result.add(grid[x][y]);
            seen[x][y] = true;
            int nextRowPos = x + rowModifier[direction];
            int nextColumnPos = y + columnModifier[direction];

            if (0 <= nextRowPos && nextRowPos < gridLength && 0 <= nextColumnPos && nextColumnPos < gridLength
                    && !seen[nextRowPos][nextColumnPos]) {
                x = nextRowPos;
                y = nextColumnPos;
            }
            else {
                direction = (direction + 1) % 4;
                x += rowModifier[direction];
                y += columnModifier[direction];
            }
        }
        return result;
    }

    /**
     * <p>Returns the numbers in the grid as if they were accessed along a spiral path
     * starting with the first column.<br />
     * Each element in the grid is returned exactly once.
     * The Iterable starts with the number on position i = 0, j = 0,
     * returns all numbers from column 0 top to bottom,
     * the numbers from the last row left to right,
     * then the numbers from the last column bottom to top, etc.</p>
     *
     * Example based on the following 5 x 5 grid:
     * <p style="font-family:monospace">
     *  10  11  12  13  14<br />
     *  15  16  17  18  19<br />
     *  20  21  22  23  24<br />
     *  25  26  27  28  29<br />
     *  30  31  32  33  34</p>
     *
     *  <p>The numbers are returned in the following sequence:<br />
     *  10, 15, 20, 25, 30, 31, 32, 33, 34, 29, 24, 19, 14, 13, 12,
     *  11, 16, 21, 26, 27, 28, 23, 18, 17, 22</p>
     *
     *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
     *  A 0 x 0 grid returns an empty Iterable.</p>
     *
     * @return the grid elements in the specified way
     */
    public Iterable<Integer> spiral2() {
        return null; // TODO
    }

    /**
     * <p>
     * Returns a string that displays the grid elements right-aligned in straight columns.<br />
     * The number of columns matches the number of columns in the grid. The same is true
     * for the order of the elements.<br />
     * The width of the columns depends on the largest number in the grid. It is one larger
     * than its number of digits, ensuring a space between the columns. If all grid elements have
     * the same number of digits, all numbers will be preceded by exactly one space.
     * </p>
     *
     * Example: based on the following 4 x 4 grid:
     * <p style="font-family:monospace">
     *   10  20  30  40<br />
     *    2  33 444  55<br />
     *  123  23 345   5<br />
     *    0   1   2   3</p>
     *
     * <p>The largest numbers in the grid is a 3-digit numbers, which results in a column-width 4.
     * There is no space after the last element of a row.<br />
     *
     *  <p>A 1 x 1 grid returns a string that includes the number preceded by one space.<br />
     *  A 0 x 0 grid returns an empty string.</p>
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return null; // TODO
    }

    // = = = test client for your own testing = = =

    /**
     * Test client
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] array2d = {
                {25, 30, 89},
                {50, 68, 19},
                {84, 99, 20},
        };

        Grid grid1 = new Grid(array2d);
        System.out.println(grid1.largestNeighbor(2, 1)); // input coordinates cannot be out of range
        System.out.println(grid1.snake1());
        System.out.println(grid1.spiral1());
    }
}

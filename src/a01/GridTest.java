package a01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void largestNeighbor_3x3() {
        int i = 0;
        int j = 2;
        int[][] array2d = {
                {25, 30, 89},
                {50, 68, 19},
                {84, 99, 20},
        };

        Grid grid = new Grid(array2d);

        assertEquals(true, grid.largestNeighbor(i, j));
    }

    @Test
    void snake1() {
    }

    @Test
    void spiral1() {
    }
}
package junitReview;

import org.junit.jupiter.api.Assertions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DemoTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }
// = = =
    @org.junit.jupiter.api.Test
    void arraySum_3x3() {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertEquals(45, Demo.arraySum(a));
    }

    @org.junit.jupiter.api.Test
    void arraySum_emptyArray() {
        int[][] a = {};
        assertThrows(IllegalArgumentException.class, () -> {
            Demo.arraySum(a);
        });
    }

    @org.junit.jupiter.api.Test
    void getAllNumbers_3x3() {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(result, Demo.getAllNumbers(a));
    }

    @org.junit.jupiter.api.Test
    void getAllNumbers_5x5() {
        int[][] a = {
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
        };
        List<Integer> result = List.of(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5);
        assertEquals(result, Demo.getAllNumbers(a));
    }
}
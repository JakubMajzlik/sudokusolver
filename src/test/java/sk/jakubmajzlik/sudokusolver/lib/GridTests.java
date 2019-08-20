package sk.jakubmajzlik.sudokusolver.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class GridTests {

    @Test
    void isUnique_fullOfZero_success() {
        List<Integer> testList = Arrays.asList(0,0,0,0,0,0,0,0,0);
        Assertions.assertTrue(GameGrid.isUnique(testList));
    }

    @Test
    void isUnique_112345678_fail() {
        List<Integer> testList = Arrays.asList(1,1,2,3,4,5,6,7,8);
        Assertions.assertFalse(GameGrid.isUnique(testList));
    }
}

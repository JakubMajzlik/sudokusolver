package sk.jakubmajzlik.sudokusolver.lib.technique;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

class ScanningTests {
    @Test
    void ScanningTest1() {
        int[][] testGameGrid = {
                {0,0,5,9,0,4,0,0,0},
                {9,0,0,2,0,0,0,3,4},
                {3,4,0,0,6,0,0,0,0},
                {0,9,0,0,8,0,2,0,0},
                {2,0,3,0,0,0,8,0,1},
                {0,0,8,0,2,0,0,9,0},
                {0,0,0,0,7,0,0,8,2},
                {8,1,0,0,0,2,0,0,9},
                {0,0,0,3,0,8,7,0,0}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        Scanning scanning = new Scanning();
        scanning.apply(gameGrid);
        String expectedResult =
                "0 0 5 9 3 4 0 0 8 \n" +
                "9 8 0 2 0 0 0 3 4 \n" +
                "3 4 0 8 6 0 9 0 0 \n" +
                "0 9 0 0 8 0 2 0 0 \n" +
                "2 0 3 0 0 0 8 0 1 \n" +
                "0 0 8 0 2 0 0 9 0 \n" +
                "0 3 0 0 7 0 0 8 2 \n" +
                "8 1 7 0 0 2 3 0 9 \n" +
                "0 0 0 3 0 8 7 0 0 \n";
        Assertions.assertEquals(expectedResult, gameGrid.toString());
    }

    @Test
    void ScanningTest2() {
        int[][] testGameGrid = {
                {0,0,3,0,6,9,0,0,0},
                {0,9,0,0,4,2,6,0,0},
                {6,2,0,0,0,0,0,0,0},
                {4,5,0,0,0,0,0,6,0},
                {0,0,2,6,0,1,4,0,0},
                {0,6,0,0,0,0,0,7,3},
                {0,0,0,0,0,0,0,3,8},
                {0,0,1,5,9,0,0,4,0},
                {0,0,0,1,3,0,9,0,0}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        Scanning scanning = new Scanning();
        scanning.apply(gameGrid);
        String expectedResult =
                "0 1 3 0 6 9 0 0 4 \n" +
                "0 9 0 3 4 2 6 1 0 \n" +
                "6 2 4 0 1 5 3 0 0 \n" +
                "4 5 0 0 0 3 0 6 1 \n" +
                "0 0 2 6 0 1 4 0 0 \n" +
                "1 6 0 0 0 0 0 7 3 \n" +
                "0 0 0 0 0 0 1 3 8 \n" +
                "0 0 1 5 9 0 0 4 0 \n" +
                "0 0 0 1 3 0 9 0 0 \n";
        Assertions.assertEquals(expectedResult, gameGrid.toString());
    }
}

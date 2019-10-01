package sk.jakubmajzlik.sudokusolver.lib.technique;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

class EliminationTests {

    @Test
    void EliminationTest1() {
        int[][] testGameGrid = {
                {0,0,9,1,4,0,2,0,0},
                {0,4,8,0,0,2,0,1,0},
                {0,0,1,8,3,9,0,0,5},
                {9,0,6,0,0,0,8,0,0},
                {4,0,5,0,0,0,0,0,6},
                {0,0,7,0,0,0,5,0,1},
                {6,0,3,9,5,4,1,0,0},
                {0,0,4,3,2,0,7,5,0},
                {0,0,2,0,0,1,0,0,0}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        Elimination elimination = new Elimination();
        elimination.apply(gameGrid);
        String expectedResult =
                "0 0 9 1 4 0 2 0 0 \n" +
                "0 4 8 0 0 2 0 1 0 \n" +
                "0 0 1 8 3 9 0 0 5 \n" +
                "9 0 6 0 0 0 8 0 0 \n" +
                "4 0 5 0 0 0 0 0 6 \n" +
                "0 0 7 0 0 0 5 0 1 \n" +
                "6 7 3 9 5 4 1 0 0 \n" +
                "1 0 4 3 2 6 7 5 0 \n" +
                "0 0 2 0 0 1 0 0 0 \n";
        Assertions.assertEquals(expectedResult, gameGrid.toString());
    }
}

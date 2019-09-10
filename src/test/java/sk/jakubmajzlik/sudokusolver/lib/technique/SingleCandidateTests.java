package sk.jakubmajzlik.sudokusolver.lib.technique;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

/**
 *  Tests for {@code SingleCandidate class
 *  @author Jakub Majzlík
 *  @see SingleCandidate
 */
class SingleCandidateTests {

    @Test
    void SingleCandidateTest() {
        int[][] testGameGrid = {
                {2,0,0,3,6,4,0,0,0},
                {0,7,0,0,5,0,0,0,6},
                {5,4,0,0,0,0,0,0,3},
                {0,0,7,8,0,0,3,0,0},
                {0,5,9,0,0,0,7,1,0},
                {0,0,1,0,0,5,9,0,0},
                {7,0,0,0,0,0,0,2,8},
                {6,0,0,0,7,0,0,5,0},
                {0,0,0,6,2,1,0,0,7}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        SingleCandidate singleCandidate = new SingleCandidate();
        singleCandidate.apply(gameGrid);
        String expectedResult =
                "2 9 8 3 6 4 5 7 1 \n" +
                "1 7 3 9 5 2 8 4 6 \n" +
                "5 4 6 1 8 7 2 9 3 \n" +
                "4 2 7 8 1 9 3 6 5 \n" +
                "8 5 9 2 3 6 7 1 4 \n" +
                "3 6 1 7 4 5 9 8 2 \n" +
                "7 1 4 5 9 3 6 2 8 \n" +
                "6 3 2 4 7 8 1 5 9 \n" +
                "9 8 5 6 2 1 4 3 7 \n";
        Assertions.assertEquals(expectedResult, gameGrid.toString());
    }
}

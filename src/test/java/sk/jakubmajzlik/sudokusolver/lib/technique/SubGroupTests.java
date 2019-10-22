package sk.jakubmajzlik.sudokusolver.lib.technique;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

class SubGroupTests {
    @Test
    void SubGroup_PairTest1() {
        int[][] testGameGrid = {
                {5,0,4,0,8,0,0,1,3},
                {8,6,1,0,5,3,0,7,4},
                {0,0,3,4,0,1,8,6,5},
                {6,5,0,0,0,0,0,3,1},
                {3,0,0,1,0,0,0,5,2},
                {1,4,0,0,3,0,0,8,9},
                {9,3,5,8,2,7,1,4,6},
                {4,8,6,0,1,0,3,2,7},
                {0,1,0,4,6,4,5,9,8}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        SubGroup subGroup = new SubGroup();
        subGroup.apply(gameGrid);
        Assertions.assertEquals(8, gameGrid.get(3,2).getCandidates().get(0));
        Assertions.assertEquals(9, gameGrid.get(3,2).getCandidates().get(1));
        Assertions.assertEquals(8, gameGrid.get(4,2).getCandidates().get(0));
        Assertions.assertEquals(9, gameGrid.get(4,2).getCandidates().get(1));
    }
}

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

    @Test
    void SubGroup_TrioTest1() {
        int[][] testGameGrid = {
                {4,6,5,0,8,0,3,2,0},
                {7,9,8,0,3,2,6,0,5},
                {1,2,3,5,6,0,0,9,8},
                {8,0,0,2,0,5,0,3,0},
                {0,0,2,0,0,0,5,0,0},
                {5,7,0,3,0,6,0,8,0},
                {0,8,4,0,5,3,1,7,0},
                {0,0,0,0,2,0,8,5,4},
                {0,5,7,0,1,0,0,6,3}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        SubGroup subGroup = new SubGroup();
        subGroup.apply(gameGrid);
        Assertions.assertEquals(1, gameGrid.get(4,3).getCandidates().get(0));
        Assertions.assertEquals(8, gameGrid.get(4,3).getCandidates().get(1));
        Assertions.assertEquals(1, gameGrid.get(4,5).getCandidates().get(0));
        Assertions.assertEquals(8, gameGrid.get(4,5).getCandidates().get(1));
    }

    @Test
    void SubGroup_quartetTest1() {
        int[][] testGameGrid = {
                {3,0,0,0,0,4,9,1,7},
                {5,7,4,0,9,1,3,0,2},
                {0,1,0,0,3,0,4,5,0},
                {0,3,1,4,0,0,7,0,0},
                {0,0,7,0,8,3,5,0,0},
                {0,0,0,0,0,2,6,3,0},
                {1,0,6,0,4,0,8,7,3},
                {7,9,3,0,1,0,2,4,5},
                {8,4,0,3,0,0,1,0,0}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        SubGroup subGroup = new SubGroup();
        subGroup.apply(gameGrid);
        Assertions.assertEquals(2, gameGrid.get(5,2).getCandidates().size());
        Assertions.assertEquals(5, gameGrid.get(5,2).getCandidates().get(0));
        Assertions.assertEquals(8, gameGrid.get(5,2).getCandidates().get(1));
    }

    @Test
    void SubGroup_quintetTest1() {
        int[][] testGameGrid = {
                {0,0,0,0,0,4,3,6,5},
                {0,9,6,0,7,5,8,0,0},
                {5,0,0,0,0,6,2,7,9},
                {1,0,0,6,0,0,0,0,3},
                {0,0,0,5,1,2,0,0,0},
                {6,0,0,0,0,3,0,0,8},
                {0,5,8,0,0,0,0,0,0},
                {0,1,2,0,6,0,5,3,0},
                {9,6,3,4,5,0,0,8,2},
        };
        GameGrid gameGrid = new GameGrid(testGameGrid);
        SubGroup subGroup = new SubGroup();
        subGroup.apply(gameGrid);
        subGroup.apply(gameGrid);
        subGroup.apply(gameGrid);
        subGroup.apply(gameGrid);
        subGroup.apply(gameGrid);

        Assertions.assertEquals(2, gameGrid.get(6,3).getCandidates().size());
        Assertions.assertEquals(2, gameGrid.get(6,3).getCandidates().get(0));
        Assertions.assertEquals(3, gameGrid.get(6,3).getCandidates().get(1));

        Assertions.assertEquals(2, gameGrid.get(6,4).getCandidates().size());
        Assertions.assertEquals(2, gameGrid.get(6,4).getCandidates().get(0));
        Assertions.assertEquals(3, gameGrid.get(6,4).getCandidates().get(1));
    }
}

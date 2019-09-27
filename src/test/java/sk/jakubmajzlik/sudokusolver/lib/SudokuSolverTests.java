package sk.jakubmajzlik.sudokusolver.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SudokuSolverTests {

    @Test
    void sudokuSolver_TestGrid1() {
        SudokuSolver sudokuSolver = new SudokuSolver();
        int[][] grid = {
                {0,0,0,0,5,0,1,2,0},
                {0,0,9,0,0,2,6,7,8},
                {3,0,2,0,1,0,0,0,0},
                {0,0,8,0,0,0,0,3,1},
                {0,1,0,0,0,0,0,5,0},
                {2,3,0,0,0,0,8,0,0},
                {0,0,0,0,6,0,4,0,9},
                {9,6,7,4,0,0,5,0,0},
                {0,2,1,0,8,0,0,0,0},
        };
        GameGrid gameGrid = new GameGrid(grid);
        sudokuSolver.solve(gameGrid);

        Assertions.assertTrue(gameGrid.check());
        Assertions.assertTrue(gameGrid.isSolved());

    }

}

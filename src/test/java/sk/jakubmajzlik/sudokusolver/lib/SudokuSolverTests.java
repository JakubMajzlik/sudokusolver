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

    @Test
    void sudokuSolver_TestGrid2() {
        SudokuSolver sudokuSolver = new SudokuSolver();
        int[][] grid = {
                {0,0,0,0,0,4,3,6,5},
                {0,9,6,0,7,0,8,0,0},
                {0,0,0,0,0,6,2,7,9},
                {1,0,0,6,0,0,0,0,3},
                {0,0,0,5,1,2,0,0,0},
                {6,0,0,0,0,3,0,0,8},
                {0,5,8,0,0,0,0,0,0},
                {0,0,2,0,6,0,5,3,0},
                {9,6,0,4,0,0,0,8,0},
        };
        GameGrid gameGrid = new GameGrid(grid);
        sudokuSolver.solve(gameGrid);

        Assertions.assertTrue(gameGrid.check());
        Assertions.assertTrue(gameGrid.isSolved());

    }

    @Test
    void sudokuSolver_TestGrid3() {
        SudokuSolver sudokuSolver = new SudokuSolver();
        int[][] grid = {
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
        GameGrid gameGrid = new GameGrid(grid);
        sudokuSolver.solve(gameGrid);

        Assertions.assertTrue(gameGrid.check());
        Assertions.assertTrue(gameGrid.isSolved());

    }

//    TODO: Gordonian rectangles implementation needed, for this test grid
//    @Test
//    void sudokuSolver_TestGrid4() {
//        SudokuSolver sudokuSolver = new SudokuSolver();
//        int[][] grid = {
//                {6,0,0,0,0,3,2,8,0},
//                {9,0,0,0,0,7,0,0,0},
//                {0,1,0,0,4,0,0,6,7},
//                {0,0,0,5,2,9,0,0,8},
//                {0,8,0,0,0,0,0,2,0},
//                {2,0,0,4,3,8,0,0,0},
//                {8,2,0,0,6,0,0,7,0},
//                {0,0,0,8,0,0,0,0,1},
//                {0,6,3,9,0,0,0,0,2},
//        };
//        GameGrid gameGrid = new GameGrid(grid);
//        sudokuSolver.solve(gameGrid);
//
//        Assertions.assertTrue(gameGrid.check());
//        Assertions.assertTrue(gameGrid.isSolved());
//
//    }

}

package sk.jakubmajzlik.sudokusolver.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for {@code GameGrid} class
 * @author Jakub Majzlík
 * @see GameGrid
 */
class GameGridTests {

    GameGrid initializeTestGrid() {
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
        return new GameGrid(testGameGrid);
    }

    @Test
    void GameGrid_2DArrayConstructor() {

        GameGrid gameGrid = initializeTestGrid();
        String expectedResult =
                "2 0 0 3 6 4 0 0 0 \n" +
                "0 7 0 0 5 0 0 0 6 \n" +
                "5 4 0 0 0 0 0 0 3 \n" +
                "0 0 7 8 0 0 3 0 0 \n" +
                "0 5 9 0 0 0 7 1 0 \n" +
                "0 0 1 0 0 5 9 0 0 \n" +
                "7 0 0 0 0 0 0 2 8 \n" +
                "6 0 0 0 7 0 0 5 0 \n" +
                "0 0 0 6 2 1 0 0 7 \n";
        Assertions.assertEquals(expectedResult, gameGrid.toString());
        Assertions.assertEquals(9, gameGrid.getSize());
    }

    @Test
    void GameGrid_2DArrayConstructor_exceptionsThrown() {
        int[][] testGameGridWithMissingCell = {
                {2,0,0,3,6,4,0,0,0},
                {0,7,0,0,5,0,0,0,6},
                {5,4,0,0,0,0,0,0,3},
                {0,0,7,8,0,0,3,0,0},
                {0,5,9,0,0,0,7,1,0},
                {0,0,1,0,0,5,9,0},
                {7,0,0,0,0,0,0,2,8},
                {6,0,0,0,7,0,0,5,0},
                {0,0,0,6,2,1,0,0,7}
        };
        Assertions.assertThrows(NullPointerException.class, () -> new GameGrid(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GameGrid(testGameGridWithMissingCell));
    }

    @Test
    void isNumberInRowTest() {
        GameGrid gameGrid = initializeTestGrid();
        Assertions.assertTrue(gameGrid.isNumberInRow(1,5));
        Assertions.assertFalse(gameGrid.isNumberInRow(2,1));
    }

    @Test
    void isNumberInColumnTest() {
        GameGrid gameGrid = initializeTestGrid();
        Assertions.assertTrue(gameGrid.isNumberInColumn(3,8));
        Assertions.assertFalse(gameGrid.isNumberInColumn(4,9));
    }

    @Test
    void isNumberInRectangleTest() {
        GameGrid gameGrid = initializeTestGrid();
        Assertions.assertTrue(gameGrid.isNumberInRectangle(0,7));
        Assertions.assertFalse(gameGrid.isNumberInRectangle(1,9));
    }

    @Test
    void getRectangleIndexTest() {
        GameGrid gameGrid = new GameGrid();
        Assertions.assertEquals(8, gameGrid.getRectangleIndex(6,8));
        Assertions.assertEquals(0, gameGrid.getRectangleIndex(0,0));
        Assertions.assertEquals(8, gameGrid.getRectangleIndex(8,8));
        Assertions.assertEquals(2, gameGrid.getRectangleIndex(0,8));
        Assertions.assertEquals(3, gameGrid.getRectangleIndex(3,0));
    }

    @Test
    void getRowTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<Cell> expectedList = Arrays.asList(
                new Cell(8, 0, 0, gameGrid),
                new Cell(8, 1, 0, gameGrid),
                new Cell(8, 2, 0, gameGrid),
                new Cell(8, 3, 6, gameGrid),
                new Cell(8, 4, 2, gameGrid),
                new Cell(8, 5, 1, gameGrid),
                new Cell(8, 6, 0, gameGrid),
                new Cell(8, 7, 0, gameGrid),
                new Cell(8, 8, 7, gameGrid));
        Assertions.assertEquals(expectedList, gameGrid.getRow(8));
    }

    @Test
    void getColumnTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<Cell> expectedList = Arrays.asList(
                new Cell(0, 0, 2, gameGrid),
                new Cell(1, 0, 0, gameGrid),
                new Cell(2, 0, 5, gameGrid),
                new Cell(3, 0, 0, gameGrid),
                new Cell(4, 0, 0, gameGrid),
                new Cell(5, 0, 0, gameGrid),
                new Cell(6, 0, 7, gameGrid),
                new Cell(7, 0, 6, gameGrid),
                new Cell(8, 0, 0, gameGrid));
        Assertions.assertEquals(expectedList, gameGrid.getColumn(0));
    }

    @Test
    void getRectangleTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<Cell> expectedList = Arrays.asList(
                new Cell(0, 0, 2, gameGrid),
                new Cell(0, 1, 0, gameGrid),
                new Cell(0, 2, 0, gameGrid),
                new Cell(1, 0, 0, gameGrid),
                new Cell(1, 1, 7, gameGrid),
                new Cell(1, 2, 0, gameGrid),
                new Cell(2, 0, 5, gameGrid),
                new Cell(2, 1, 4, gameGrid),
                new Cell(2, 2, 0, gameGrid));
        Assertions.assertEquals(expectedList, gameGrid.getRectangle(0));
    }

    @Test
    void setTest() {
        GameGrid gameGrid = initializeTestGrid();
        gameGrid.set(0,1,9);
        String expectedResult =
                "2 9 0 3 6 4 0 0 0 \n" +
                "0 7 0 0 5 0 0 0 6 \n" +
                "5 4 0 0 0 0 0 0 3 \n" +
                "0 0 7 8 0 0 3 0 0 \n" +
                "0 5 9 0 0 0 7 1 0 \n" +
                "0 0 1 0 0 5 9 0 0 \n" +
                "7 0 0 0 0 0 0 2 8 \n" +
                "6 0 0 0 7 0 0 5 0 \n" +
                "0 0 0 6 2 1 0 0 7 \n";
        Assertions.assertEquals(expectedResult, gameGrid.toString());
    }

    @Test
    void checkTest_success() {
        GameGrid gameGrid = initializeTestGrid();

        Assertions.assertTrue(gameGrid.check());

        int[][] testGameGrid = {
                {2,9,8,3,6,4,5,7,1},
                {1,7,3,9,5,2,8,4,6},
                {5,4,6,1,8,7,2,9,3},
                {4,2,7,8,1,9,3,6,5},
                {8,5,9,2,3,6,7,1,4},
                {3,6,1,7,4,5,9,8,2},
                {7,1,4,5,9,3,6,2,8},
                {6,3,2,4,7,8,1,5,9},
                {9,8,5,6,2,1,4,3,7}
        };
        gameGrid = new GameGrid(testGameGrid);

        Assertions.assertTrue(gameGrid.check());
    }

    @Test
    void checkTest_failed() {
        int[][] testGameGrid1 = {
                {2,9,8,3,6,4,5,7,1},
                {1,1,3,9,5,2,8,4,6},//two 1
                {5,4,6,1,8,7,2,9,3},
                {4,2,7,8,1,9,3,6,5},
                {8,5,9,2,3,6,7,1,4},
                {3,6,1,7,4,5,9,8,2},
                {7,1,4,5,9,3,6,2,8},
                {6,3,2,4,7,8,1,5,9},
                {9,8,5,6,2,1,4,3,7}
        };
        int[][] testGameGrid2 = {
                {2,9,8,3,6,4,5,7,1},
                {1,7,3,9,5,2,8,4,6},
                {5,4,6,1,8,7,2,9,3},//two 6 in column
                {4,2,6,8,1,9,3,6,5},
                {8,5,9,2,3,6,7,1,4},
                {3,6,1,7,4,5,9,8,2},
                {7,1,4,5,9,3,6,2,8},
                {6,3,2,4,7,8,1,5,9},
                {9,8,5,6,2,1,4,3,7}
        };
        int[][] testGameGrid3 = {
                {2,9,8,3,6,4,5,7,1},
                {1,1,3,9,5,2,8,4,6},
                {5,4,6,1,8,7,2,9,3},
                {4,2,7,8,1,9,3,6,5},
                {8,5,9,2,3,6,7,1,4},
                {3,6,1,7,4,5,9,8,2},
                {7,1,4,5,9,3,6,2,8},//Two 8 in rectangle
                {6,3,2,4,7,8,1,8,9},
                {9,8,5,6,2,1,4,3,7}
        };
        GameGrid gameGrid = new GameGrid(testGameGrid1);
        Assertions.assertFalse(gameGrid.check());

        gameGrid = new GameGrid(testGameGrid2);
        Assertions.assertFalse(gameGrid.check());

        gameGrid = new GameGrid(testGameGrid3);
        Assertions.assertFalse(gameGrid.check());
    }

    @Test
    void getSuperIndexTest() {
        GameGrid gameGrid = new GameGrid();
        Assertions.assertEquals(0, gameGrid.getSuperIndex(0));
        Assertions.assertEquals(1, gameGrid.getSuperIndex(4));
        Assertions.assertEquals(2, gameGrid.getSuperIndex(7));
    }

    @Test
    void getSuperRowTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<List<Cell>> superRowResult = gameGrid.getSuperRow(1);
        List<List<Cell>> expected =  new ArrayList<>();
        expected.add(Arrays.asList(
                new Cell(3, 0, 0, gameGrid),
                new Cell(3, 1, 0, gameGrid),
                new Cell(3, 2, 7, gameGrid),
                new Cell(3, 3, 8, gameGrid),
                new Cell(3, 4, 0, gameGrid),
                new Cell(3, 5, 0, gameGrid),
                new Cell(3, 6, 3, gameGrid),
                new Cell(3, 7, 0, gameGrid),
                new Cell(3, 8, 0, gameGrid)));
        expected.add(Arrays.asList(
                new Cell(4, 0, 0, gameGrid),
                new Cell(4, 1, 5, gameGrid),
                new Cell(4, 2, 9, gameGrid),
                new Cell(4, 3, 0, gameGrid),
                new Cell(4, 4, 0, gameGrid),
                new Cell(4, 5, 0, gameGrid),
                new Cell(4, 6, 7, gameGrid),
                new Cell(4, 7, 1, gameGrid),
                new Cell(4, 8, 0, gameGrid)));
        expected.add(Arrays.asList(
                new Cell(5, 0, 0, gameGrid),
                new Cell(5, 1, 0, gameGrid),
                new Cell(5, 2, 1, gameGrid),
                new Cell(5, 3, 0, gameGrid),
                new Cell(5, 4, 0, gameGrid),
                new Cell(5, 5, 5, gameGrid),
                new Cell(5, 6, 9, gameGrid),
                new Cell(5, 7, 0, gameGrid),
                new Cell(5, 8, 0, gameGrid)));
        Assertions.assertEquals(expected, superRowResult);
    }

    @Test
    void getSuperColumnTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<List<Cell>> superColumnResult = gameGrid.getSuperColumn(2);
        List<List<Cell>> expected =  new ArrayList<>();
        expected.add(Arrays.asList(
                new Cell(0, 6, 0, gameGrid),
                new Cell(1, 6, 0, gameGrid),
                new Cell(2, 6, 0, gameGrid),
                new Cell(3, 6, 3, gameGrid),
                new Cell(4, 6, 7, gameGrid),
                new Cell(5, 6, 9, gameGrid),
                new Cell(6, 6, 0, gameGrid),
                new Cell(7, 6, 0, gameGrid),
                new Cell(8, 6, 0, gameGrid)));
        expected.add(Arrays.asList(
                new Cell(0, 7, 0, gameGrid),
                new Cell(1, 7, 0, gameGrid),
                new Cell(2, 7, 0, gameGrid),
                new Cell(3, 7, 0, gameGrid),
                new Cell(4, 7, 1, gameGrid),
                new Cell(5, 7, 0, gameGrid),
                new Cell(6, 7, 2, gameGrid),
                new Cell(7, 7, 5, gameGrid),
                new Cell(8, 7, 0, gameGrid)));
        expected.add(Arrays.asList(
                new Cell(0, 8, 0, gameGrid),
                new Cell(1, 8, 6, gameGrid),
                new Cell(2, 8, 3, gameGrid),
                new Cell(3, 8, 0, gameGrid),
                new Cell(4, 8, 0, gameGrid),
                new Cell(5, 8, 0, gameGrid),
                new Cell(6, 8, 8, gameGrid),
                new Cell(7, 8, 0, gameGrid),
                new Cell(8, 8, 7, gameGrid)));

        Assertions.assertEquals(expected, superColumnResult);
    }

    @Test
    void getCandidatesForCellTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<Integer> result = gameGrid.getCandidatesForCell(gameGrid.get(2,2));
        List<Integer> expected = Arrays.asList(6, 8);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void isSolvedTest_Solved() {
        int[][] grid = {
            {8,4,6,7,5,9,1,2,3},
            {1,5,9,3,4,2,6,7,8},
            {3,7,2,6,1,8,9,4,5},
            {6,9,8,5,2,4,7,3,1},
            {7,1,4,8,9,3,2,5,6},
            {2,3,5,1,7,6,8,9,4},
            {5,8,3,2,6,7,4,1,9},
            {9,6,7,4,3,1,5,8,2},
            {4,2,1,9,8,5,3,6,7},
        };
        GameGrid gameGrid = new GameGrid(grid);
        Assertions.assertTrue(gameGrid.isSolved());
    }

    @Test
    void isSolvedTest_NotSolved() {
        GameGrid gameGrid = initializeTestGrid();
        Assertions.assertFalse(gameGrid.isSolved());
    }
}

package sk.jakubmajzlik.sudokusolver.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        List<Integer> expectedList = Arrays.asList(0,0,0,6,2,1,0,0,7);
        Assertions.assertEquals(expectedList, gameGrid.getRow(8));
    }

    @Test
    void getColumnTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<Integer> expectedList = Arrays.asList(2,0,5,0,0,0,7,6,0);
        Assertions.assertEquals(expectedList, gameGrid.getColumn(0));
    }

    @Test
    void getRectangleTest() {
        GameGrid gameGrid = initializeTestGrid();
        List<Integer> expectedList = Arrays.asList(2,0,0,0,7,0,5,4,0);
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
        //TODO: Implement test after implementation of the method is done
    }

    @Test
    void checkTest_failed() {
        //TODO: Implement test after implementation of the method is done
    }
}

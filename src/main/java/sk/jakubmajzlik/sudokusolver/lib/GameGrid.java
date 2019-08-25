package sk.jakubmajzlik.sudokusolver.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides basic structure for all sudoku solving algorithms of this library.
 * Game grid is implemented as {@code List<List<Integer>>}. Empty cells must be filled with zeros.
 *
 * @version 1.0
 * @author Jakub Majzlík
 */
public class GameGrid {
    /**
     * Game grid
     */
    private List<List<Integer>> ground = new ArrayList<>();

    /**
     * Size of game grid
     */
    private int size;

    /**
     * Creates empty game grid of the size size X size.
     * @param size Number of columns and rows
     * @since 1.0
     */
    private GameGrid(int size) {
        for (int i = 0; i < size; i++) {
            ground.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                ground.get(i).add(0);
            }
        }
        this.size = size;
    }

    /**
     * Creates empty game grid of the size 9 X 9.
     * @since 1.0
     */
    public GameGrid() {
        this(9);
    }

    /**
     * Creates the game grid of the size 9x9 with numbers which are stored in 2d array {@code gameGrid}.
     *  So far the only grid of size <string>9x9</string> is supported.
     * @param gameGrid 2d array with numbers
     * @throws NullPointerException if {@code gameGrid} is null
     * @throws IllegalArgumentException if {@code gameGrid} is not 9x9
     * @since 1.0
     */
    public GameGrid(int[][] gameGrid) {
        if(gameGrid == null) throw new NullPointerException();
        if(gameGrid.length != 9) throw  new IllegalArgumentException("Array must be 9x9");
        int fieldSum = 0;
        for (int[] ints : gameGrid) fieldSum += ints.length;
        if(fieldSum != 81) throw  new IllegalArgumentException("Array must be 9x9");

        size = 9;
        for(int i = 0; i < size; i++) {
            ground.add(new ArrayList<>());
            for(int cell : gameGrid[i]) {
                ground.get(i).add(cell);
            }
        }
    }

    /**
     * Checks if are numbers in grid correctly placed.
     *
     * @return true if are numbers in grid correctly placed
     */
    public boolean check() {
        //TODO: Implementation and documentation what it checks
        // Check columns

        // Check rows

        // Check squares

        return true;
    }

    /**
     * Gets number from specific position in grid
     * @param row Index of the row from 0 to {@code size}
     * @param col Index of the column from 0 to {@code size}
     * @return Number at position [row,col]
     * @since 1.0
     */
    public int get(int row, int col) {
        return ground.get(row).get(col);
    }

    /**
     * Puts number on specific location in game grid
     * @param row Index of the row from 0 to {@code size}
     * @param col Index of the column from 0 to {@code size}
     * @param number Number at position [row,col]
     */
    public void set(int row, int col, int number) {
        ground.get(row).remove(col);
        ground.get(row).add(col, number);
    }

    /**
     * Gets List of numbers in specific row
     * @param row Index of the row from 0 to {@code size}
     * @return List of numbers in specific row
     * @since 1.0
     */
    public List<Integer> getRow(int row) {
        return ground.get(row);
    }

    /**
     * Gets List of numbers in specific column
     * @param col Index of the column from 0 to {@code size}
     * @return List of numbers in specific column
     * @since 1.0
     */
    public List<Integer> getColumn(int col) {
        List<Integer> colList = new ArrayList<>();
        for(List<Integer> column : ground) {
            colList.add(column.get(col));
        }
        return colList;
    }

    /**
     * Gets list of numbers in specific rectangle
     * @param rectangleIndex Index of the rectangle from 0 to {@code size}
     * @return List of numbers in specific rectangle
     * @since 1.0
     * @see #getRectangleIndex(int, int)
     */
    public List<Integer> getRectangle(int rectangleIndex) {
        List<Integer> rectangleList = new ArrayList<>();
        int rowStartIndex = 0;
        if(rectangleIndex > 2 && rectangleIndex < 6) rowStartIndex = 3;
        if(rectangleIndex > 5) rowStartIndex = 6;

        int columnStartIndex = 0;
        if (Arrays.asList(1,4,7).contains(rectangleIndex)) columnStartIndex = 3;
        if (Arrays.asList(2,5,8).contains(rectangleIndex)) columnStartIndex = 6;

        for(int rowIndex = rowStartIndex; rowIndex < rowStartIndex + 3; rowIndex++) {
            for(int columnIndex = columnStartIndex; columnIndex < columnStartIndex + 3; columnIndex++) {
                rectangleList.add(this.get(rowIndex, columnIndex));
            }
        }
        return rectangleList;
    }

    /**
     * Gets size of game grid
     * @return Size of game grid
     * @since 1.0
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if specific row contains the number
     * @param rowIndex Index of the row from 0 to {@code size}
     * @param number The number
     * @return {@code true} if  row contains the number
     * @since 1.0
     */
    public boolean isNumberInRow(int rowIndex, int number) {
        return this.getRow(rowIndex).contains(number);
    }

    /**
     * Checks if specific column contains the number
     * @param columnIndex Index of the column from 0 to {@code size}
     * @param number The number
     * @return {@code true} if column contains the number
     * @since 1.0
     */
    public boolean isNumberInColumn(int columnIndex, int number) {
        return this.getColumn(columnIndex).contains(number);
    }

    /**
     * Checks if specific rectangle contains the number
     * @param rectangleIndex Index of the rectangle
     * @param number The number
     * @return {@code true} if  rectangle contains the number
     * @see #getRectangleIndex(int, int)
     * @since 1.0
     */
    public boolean isNumberInRectangle(int rectangleIndex, int number) {
        return this.getRectangle(rectangleIndex).contains(number);
    }

    /**
     * Calculates index of the rect in the grid. index = row / 3 * 3 + col / 3
     * @param row Index of the row from 0 to {@code size}
     * @param col Index of the column from 0 to {@code size}
     * @return Index of the rectangle
     * @since 1.0
     */
    public int getRectangleIndex(int row, int col) {
        return row / 3 * 3 + col / 3;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(List<Integer> row : ground) {
            for(int col : row) {
                stringBuilder.append(col +" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}

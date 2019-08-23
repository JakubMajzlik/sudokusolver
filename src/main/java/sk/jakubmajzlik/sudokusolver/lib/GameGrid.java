package sk.jakubmajzlik.sudokusolver.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides basic structure for all sudoku solving algorithms of this library.
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
     * Creates the game grid of the size size X size.
     * @param size Number of columns and rows
     * @since 1.0
     */
    private GameGrid(int size) {
        // Initialize grid
        for (int i = 0; i < size; i++) {
            ground.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                ground.get(i).add(0);
            }
        }
        this.size = size;
    }

    /**
     * Creates the game grid of the size 9 X 9.
     * @since 1.0
     */
    public GameGrid() {
        this(9);
    }

    /**
     * Checks if are numbers in grid correctly placed.
     *
     * @return Validity of the grid
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
        int rowIndex = 0;
        if(rectangleIndex > 2 && rectangleIndex < 6) rowIndex = 3;
        if(rectangleIndex > 5) rowIndex = 6;

        int columnIndex = 0;
        if (Arrays.asList(1,4,7).contains(rectangleIndex)) columnIndex = 3;
        if (Arrays.asList(2,5,8).contains(rectangleIndex)) columnIndex = 6;

        for(int i = rowIndex; i < rowIndex + 3; i++) {
            for(int j = columnIndex; j < columnIndex + 3; j++) {
                if(this.get(i, j) == number) return true;
            }
        }
        return false;
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

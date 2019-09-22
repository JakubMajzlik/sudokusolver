package sk.jakubmajzlik.sudokusolver.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class provides basic structure for all sudoku solving algorithms of this library.
 * Game grid is implemented as {@code List<List<Integer>>}. Empty cells must be filled with zeros.
 *
 * @version 1.2
 * @author Jakub Majzlík
 */
public class GameGrid {
    /**
     * Game grid
     */
    private List<List<Cell>> ground = new ArrayList<>();

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
        for (int row = 0; row < size; row++) {
            ground.add(new ArrayList<>());
            for (int column = 0; column < size; column++) {
                ground.get(row).add(new Cell(row, column, 0, this));
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
        for(int row = 0; row < size; row++) {
            ground.add(new ArrayList<>());
            for(int column = 0; column < size; column++) {
                ground.get(row).add(new Cell(row, column, gameGrid[row][column], this));
            }
        }
    }

    /**
     * Checks if are numbers in grid correctly placed. It checks if every number in a row,
     * in a column and in a rectangle is unique.
     * @return true if are numbers in grid correctly placed
     */
    public boolean check() {
        for(int i = 0; i < this.size; i++) {
            if(!isUnique(getRow(i))) return false;
            if(!isUnique(getColumn(i))) return false;
            if(!isUnique(getRectangle(i))) return false;
        }
        return true;
    }

    /**
     *  Check if numbers in the {@code list} are unique. List can contain multiple 0.
     * @param list List of numbers
     * @return true if all numbers in the {@code list} are unique
     */
    private boolean isUnique(List<Cell> list) {
        List<Cell> listOfNumbers = new ArrayList<>();
        for (Cell cell : list) {
            for(Cell cellInList : listOfNumbers) {
                if(cellInList.getValue() == cell.getValue() && cell.getValue() > 0) {
                    return false;
                }
            }
            listOfNumbers.add(cell);
        }
        return true;
    }

    /**
     * Gets number from specific position in grid
     * @param row Index of the row from 0 to {@code size}
     * @param col Index of the column from 0 to {@code size}
     * @return Number at position [row,col]
     * @since 1.0
     */
    public Cell get(int row, int col) {
        return ground.get(row).get(col);
    }

    /**
     * Puts number on specific location in game grid
     * @param row Index of the row from 0 to {@code size}
     * @param col Index of the column from 0 to {@code size}
     * @param number Number at position [row,col]
     */
    public void set(int row, int col, int number) {
        ground.get(row).get(col).setValue(number);
    }

    /**
     * Gets List of numbers in specific row
     * @param row Index of the row from 0 to {@code size}
     * @return List of numbers in specific row
     * @since 1.0
     */
    public List<Cell> getRow(int row) {
        return ground.get(row);
    }

    /**
     * Gets List of numbers in specific column
     * @param col Index of the column from 0 to {@code size}
     * @return List of numbers in specific column
     * @since 1.0
     */
    public List<Cell> getColumn(int col) {
        List<Cell> colList = new ArrayList<>();
        for(List<Cell> column : ground) {
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
    public List<Cell> getRectangle(int rectangleIndex) {
        List<Cell> rectangleList = new ArrayList<>();
        int rowStartIndex = 0;
        if(rectangleIndex > 2 && rectangleIndex < 6) rowStartIndex = 3;
        if(rectangleIndex > 5) rowStartIndex = 6;

        int columnStartIndex = 0;
        if (Arrays.asList(1,4,7).contains(rectangleIndex)) columnStartIndex = 3;
        if (Arrays.asList(2,5,8).contains(rectangleIndex)) columnStartIndex = 6;

        for(int rowIndex = rowStartIndex; rowIndex < rowStartIndex + 3; rowIndex++) {
            for(int columnIndex = columnStartIndex; columnIndex < columnStartIndex + 3; columnIndex++) {
                rectangleList.add(get(rowIndex, columnIndex));
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
        for(Cell cell : getRow(rowIndex)) {
            if(cell.getValue() == number) return true;
        }
        return false;
    }

    /**
     * Checks if specific column contains the number
     * @param columnIndex Index of the column from 0 to {@code size}
     * @param number The number
     * @return {@code true} if column contains the number
     * @since 1.0
     */
    public boolean isNumberInColumn(int columnIndex, int number) {
        for(Cell cell : getColumn(columnIndex)) {
            if(cell.getValue() == number) return true;
        }
        return false;
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
        for(Cell cell : getRectangle(rectangleIndex)) {
            if(cell.getValue() == number) return true;
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

    /**
     * Gets list of rows from the specific super row.
     * @param superRowIndex Index of the super row from 0 to 2
     * @return The list of rows
     * @since 1.1
     */
    public List<List<Cell>> getSuperRow(int superRowIndex) {
        List<List<Cell>> superRow = new ArrayList<>();
        for(int row = superRowIndex * 3; row < superRowIndex * 3 + 3; row++) {
            superRow.add(this.getRow(row));
        }
        return superRow;
    }

    /**
     * Gets list of columns from specific super column.
     * @param superColumnIndex Index of the super column from 0 to 2
     * @return List of columns
     * @since 1.1
     */
    public List<List<Cell>> getSuperColumn(int superColumnIndex)
    {
        List<List<Cell>> superColumn = new ArrayList<>();
        for(int column = superColumnIndex * 3; column < superColumnIndex * 3 + 3; column++) {
            superColumn.add(this.getColumn(column));
        }
        return superColumn;
    }

    /**
     * Calculates the index of the super row or the super column.
     * @param index Index of the row or the column from 0 to 8
     * @return Index of the super row or the super column
     * @since 1.1
     */
    public int getSuperIndex(int index) {
        return index / 3;
    }

    /**
     * Gets list of candidates for specific cell in game grid and set it to cell attribute.
     * @param cell {@code Cell} for which we want candidates
     * @return List of candidates for the cell.
     * @since 1.1
     */
    public List<Integer> getCandidatesForCell(Cell cell) {
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        List<Integer> listOfCandidates = new ArrayList<>();
        for(int possibleCandidate = 1; possibleCandidate <= getSize(); possibleCandidate++) {
            if(isNumberInRow(rowIndex, possibleCandidate)) continue;
            if(isNumberInColumn(columnIndex, possibleCandidate)) continue;
            if(isNumberInRectangle(getRectangleIndex(rowIndex, columnIndex), possibleCandidate)) continue;

            listOfCandidates.add(possibleCandidate);
        }
        return listOfCandidates;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(List<Cell> row : ground) {
            for(Cell cell : row) {
                stringBuilder.append(cell.getValue() +" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameGrid gameGrid = (GameGrid) o;
        return size == gameGrid.size &&
                Objects.equals(ground, gameGrid.ground);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ground, size);
    }
}

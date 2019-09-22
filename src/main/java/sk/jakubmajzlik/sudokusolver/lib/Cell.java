package sk.jakubmajzlik.sudokusolver.lib;

import java.util.List;
import java.util.Objects;

/**
 * Object that represents cell in {@code GameGrid}.
 *
 * @version 1.0
 * @author Jakub Majzlík
 */
public class Cell {

    /**
     * Index of the row where the cell is.
     */
    private int rowIndex;

    /**
     * Index of the column where the cell is.
     */
    private int columnIndex;

    /**
     * Value in the cell. If the cell is empty, value is 0.
     */
    private int value = 0;

    /**
     * {@code GameGrid} where the cell is.
     */
    private GameGrid gameGrid;

    /**
     * Constructor for the cell.
     * @param rowIndex Index of the row where the cell is.
     * @param columnIndex Index of the column where the cell is.
     * @param value Value in the cell.
     * @param gameGrid {@code GameGrid} where the cell is.
     */
    public Cell(int rowIndex, int columnIndex, int value ,GameGrid gameGrid) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.value = value;
        this.gameGrid = gameGrid;
    }

    /**
     * Gets {@code rowIndex}.
     * @return {@code rowIndex}
     * @since 1.0
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Sets {@code rowIndex}.
     * @param rowIndex Index of the row.
     * @since 1.0
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Gets {@code columnIndex}.
     * @return {@code columnIndex}
     * @since 1.0
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Sets {@code columnIndex}.
     * @param columnIndex {@code columnIndex}
     * @since 1.0
     */
    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * Gets {@code gameGrid}.
     * @return {@code gameGrid}
     * @since 1.0
     */
    public GameGrid getGameGrid() {
        return gameGrid;
    }

    /**
     * Sets {@code gameGrid}.
     * @param gameGrid {@code gameGrid}
     * @since 1.0
     */
    public void setGameGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    /**
     * Gets {@code value}.
     * @return {@code value}
     * @since 1.0
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets {@code value}.
     * @param value {@code value}
     * @since 1.0
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets list of candidates for the cell.
     * @return List of candidates.
     * @since 1.0
     */
    public List<Integer> getCandidates() {
        return gameGrid.getCandidatesForCell(this);
    }

    /**
     * Gets super row in which the cell is.
     * @return Super row
     * @since 1.0
     */
    public int getSuperRow() {
        return rowIndex / 3;
    }

    /**
     * Gets super column in which the cell is.
     * @return Super column
     * @since 1.0
     */
    public int getSuperColumn() {
        return columnIndex / 3;
    }

    /**
     * Gets rectangle index
     * @return Rectangle index
     * @since 1.0
     */
    public int getRectangle() {
        return gameGrid.getRectangleIndex(rowIndex, columnIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return rowIndex == cell.rowIndex &&
                columnIndex == cell.columnIndex &&
                value == cell.value &&
                gameGrid.equals(cell.gameGrid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex, value, gameGrid);
    }
}

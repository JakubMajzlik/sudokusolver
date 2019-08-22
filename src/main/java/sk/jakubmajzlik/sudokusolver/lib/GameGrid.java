package sk.jakubmajzlik.sudokusolver.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameGrid {
    private List<List<Integer>> ground = new ArrayList<>();

    private int size;

    public GameGrid(int size) {
        // Initialize grid
        for (int i = 0; i < size; i++) {
            ground.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                ground.get(i).add(0);
            }
        }
        this.size = size;
    }

    public GameGrid() {
        this(9);
    }

    public boolean check() {

        // Check columns

        // Check rows

        // Check squares

        return true;
    }

    public int get(int row, int col) {
        return ground.get(row).get(col);
    }

    public void set(int row, int col, int number) {
        ground.get(row).remove(col);
        ground.get(row).add(col, number);
    }

    public List<Integer> getRow(int row) {
        return ground.get(row);
    }

    public List<Integer> getCol(int col) {
        List<Integer> colList = new ArrayList<>();
        for(List<Integer> column : ground) {
            colList.add(column.get(col));
        }
        return colList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean findNumberInRow(int rowIndex, int number) {
        return this.getRow(rowIndex).contains(number);
    }

    public boolean findNumberInColumn(int columnIndex, int number) {
        for(int rowIndex = 0; rowIndex < this.getSize(); rowIndex++) {
            if(this.get(rowIndex, columnIndex) == number) return true;
        }
        return false;
    }
    public boolean findNumberInRectangle(int rectangleIndex, int number) {
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

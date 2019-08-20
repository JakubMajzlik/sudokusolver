package sk.jakubmajzlik.sudokusolver.lib;

import java.util.ArrayList;
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

    public static boolean isUnique(List<Integer> list) {
        List<Integer> listOfNumbers = new ArrayList<>();
        for(int member : list) {
            if(listOfNumbers.contains(member) && member > 0) {
                return false;
            }
            listOfNumbers.add(member);
        }
        return true;
    }
}

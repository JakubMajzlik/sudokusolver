package sk.jakubmajzlik.sudokusolver.lib.tests;

import java.util.ArrayList;
import java.util.List;

public class GameGrid {
    private List<List<Integer>> ground = new ArrayList<>();

    public GameGrid() {
        // Initialize 8x8 grid
        for (int i = 0; i < 8; i++) {
            ground.add(new ArrayList<>());
            for (int j = 0; j < 8; j++) {
                ground.get(i).add(0);
            }
        }
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

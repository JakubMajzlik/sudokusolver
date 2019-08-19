package sk.jakubmajzlik.sudokusolver.lib.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

        ground.forEach(e -> {
            colList.add(e.get(0));
        });

        return colList;
    }

    public static boolean isUnique(List<Integer> list) {
        AtomicBoolean result = new AtomicBoolean(true);
        List<Integer> listOfNumbers = new ArrayList<>();
        list.forEach(e -> {
            if(listOfNumbers.contains(e.intValue()) && e.intValue() > 0) {
                result.set(false);
            }
            listOfNumbers.add(e);
        });
        return result.get();
    }
}

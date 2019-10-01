package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.Cell;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of Scanning technique of solving sudoku.
 * Principle of this technique is to find super row or column where is some number in two rows or columns,
 * so  then we know in which column or row will the third number be. This principe can be applied to one
 * row or column scanning.
 * So far the only grid of size 9x9 is supported.
 *
 * @author Jakub Majzlík
 * @version 1.0
 * @see Technique
 */
public class Scanning implements Technique {

    boolean done = false;

    /**
     * Applies technique of Scanning
     * @param gameGrid Game grid
     */
    @Override
    public void apply(GameGrid gameGrid) {
        if(gameGrid.getSize() != 9) return;
        while (!done) {
            done = true;
            for(int scannedNumber = 1; scannedNumber <= 9; scannedNumber++) {
                scanSuperColumn(gameGrid, scannedNumber);
                scanSuperRow(gameGrid, scannedNumber);
            }
        }
    }

    /**
     * Scans all super columns.
     * @param gameGrid Game grid
     * @param scannedNumber Number which method scan
     */
    void scanSuperColumn(GameGrid gameGrid, int scannedNumber) {
        //SCANNING SUPER COLUMNS
        for(int superColumnIndex = 0; superColumnIndex < 3; superColumnIndex++) {
            List<List<Cell>> superColumn = gameGrid.getSuperColumn(superColumnIndex);
            List<Integer> listOfRectangleIndexesWhereNumberInSuperColumnWillBe
                    = new ArrayList<>(Arrays.asList(superColumnIndex ,superColumnIndex + 3,superColumnIndex + 6));

            List<Integer> listOfColumnIndexesWhereNumberInSuperColumnWillBe =
                    new ArrayList<>(Arrays.asList(superColumnIndex * 3,superColumnIndex * 3 + 1,superColumnIndex * 3 +2));

            for(List<Cell> column : superColumn) {
                for(Cell cell : column) {
                    if(cell.getValue() == scannedNumber) {
                        listOfRectangleIndexesWhereNumberInSuperColumnWillBe
                                .remove((Integer)cell.getRectangle());
                        listOfColumnIndexesWhereNumberInSuperColumnWillBe
                                .remove((Integer)cell.getColumnIndex());
                    }
                }
            }

            List<Cell> listOfCandidates = new ArrayList<>();

            for(Integer rectangleIndex : listOfRectangleIndexesWhereNumberInSuperColumnWillBe) {
                List<Cell> rectangle = gameGrid.getRectangle(rectangleIndex);
                for(Cell cell : rectangle) {
                    if (!listOfColumnIndexesWhereNumberInSuperColumnWillBe.contains(cell.getColumnIndex())) continue;
                    if(cell.getValue() != 0) continue;
                    listOfCandidates.add(cell);
                }

                placeNumber(gameGrid, listOfCandidates, scannedNumber);
            }
        }
    }

    /**
     * Scans all super rows.
     * @param gameGrid Game grid
     * @param scannedNumber Number which method scan
     */
    void scanSuperRow(GameGrid gameGrid, int scannedNumber) {
        //SCANNING SUPER ROWS
        for(int superRowIndex = 0; superRowIndex < 3; superRowIndex++) {
            List<List<Cell>> superRow = gameGrid.getSuperRow(superRowIndex);
            List<Integer> listOfRectangleIndexesWhereNumberInSuperRowWillBe
                    = new ArrayList<>(Arrays.asList(superRowIndex * 3,superRowIndex * 3 + 1,superRowIndex * 3 + 2));

            List<Integer> listOfRowIndexesWhereNumberInSuperRowWillBe =
                    new ArrayList<>(Arrays.asList(superRowIndex * 3,superRowIndex * 3 + 1,superRowIndex * 3 + 2));

            for(List<Cell> row : superRow) {
                for(Cell cell : row) {
                    if(cell.getValue() == scannedNumber) {
                        listOfRectangleIndexesWhereNumberInSuperRowWillBe
                                .remove((Integer)cell.getRectangle());
                        listOfRowIndexesWhereNumberInSuperRowWillBe
                                .remove((Integer)cell.getRowIndex());
                    }
                }
            }

            List<Cell> listOfCandidates = new ArrayList<>();

            for(Integer rectangleIndex : listOfRectangleIndexesWhereNumberInSuperRowWillBe) {
                List<Cell> rectangle = gameGrid.getRectangle(rectangleIndex);
                for(Cell cell : rectangle) {
                    if (!listOfRowIndexesWhereNumberInSuperRowWillBe.contains(cell.getRowIndex())) continue;
                    if(cell.getValue() != 0) continue;
                    listOfCandidates.add(cell);
                }

                placeNumber(gameGrid, listOfCandidates, scannedNumber);
            }
        }
    }

    /**
     * Find cell from {@code listOfCandidateCells} where {@code scannedNumber} can be placed. If there are two cells,
     * where {@code scannedNumber} can be placed, method ends.
     * @param gameGrid Game grid
     * @param listOfCandidateCells List of cells, where can be {@code scannedNumber} placed.
     * @param scannedNumber Number which method put to cell.
     */
    void placeNumber(GameGrid gameGrid, List<Cell> listOfCandidateCells, int scannedNumber) {
        Cell foundScannedNumberCell = null;
        for (Cell candidatesForTheCell : listOfCandidateCells) {
            //candidatesForTheCell.refreshCandidateList();
            if(candidatesForTheCell.getCandidates().size() == 1
                    && candidatesForTheCell.getCandidates().contains(scannedNumber)) {
                gameGrid.set(candidatesForTheCell.getRowIndex(), candidatesForTheCell.getColumnIndex(), scannedNumber);
                done = false;
                return;
            }
            if(candidatesForTheCell.getCandidates().contains(scannedNumber)) {
                if(foundScannedNumberCell == null) {
                    foundScannedNumberCell = candidatesForTheCell;
                } else {
                    foundScannedNumberCell = null;
                    break;
                }
            }
        }

        if(foundScannedNumberCell != null) {
            gameGrid.set(foundScannedNumberCell.getRowIndex(), foundScannedNumberCell.getColumnIndex(), scannedNumber);
            done = false;
        }
    }

}

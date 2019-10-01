package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.Cell;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

import java.util.List;

/**
 * Implementation of Elimination technique of solving sudoku.
 * Principle of this technique is to find a cell in a row or in a column which contains unique candidate.
 * @author Jakub Majzlík
 * @version 1.0
 * @see Technique
 */
public class Elimination implements Technique {

    /**
     * Applies Elimination technique
     * @param gameGrid Game grid
     */
    @Override
    public void apply(GameGrid gameGrid) {
        if(gameGrid.getSize() != 9) return;
        boolean done = false;
        while (!done) {
            done = true;

            for(int columnIndex = 0; columnIndex < gameGrid.getSize(); columnIndex++) {
                List<Cell> column = gameGrid.getColumn(columnIndex);
                CellWithUniqueCandidate cellWithUniqueCandidate;
                while((cellWithUniqueCandidate = getCellWithUniqueCandidate(column)) != null) {
                    gameGrid.set(cellWithUniqueCandidate.cell.getRowIndex(),
                            cellWithUniqueCandidate.cell.getColumnIndex(),
                            cellWithUniqueCandidate.uniqueCandidate);
                    done = false;
                }
            }

            for(int rowIndex = 0; rowIndex < gameGrid.getSize(); rowIndex++) {
                List<Cell> row = gameGrid.getRow(rowIndex);
                CellWithUniqueCandidate cellWithUniqueCandidate;
                while((cellWithUniqueCandidate = getCellWithUniqueCandidate(row)) != null) {
                    gameGrid.set(cellWithUniqueCandidate.cell.getRowIndex(),
                            cellWithUniqueCandidate.cell.getColumnIndex(),
                            cellWithUniqueCandidate.uniqueCandidate);
                    done = false;
                }
            }
        }
    }

    /**
     * Gets cell with unique candidate for the list of cells
     * @param listOfCells List of cells
     * @return Cell and unique candidate
     */
    private CellWithUniqueCandidate getCellWithUniqueCandidate(List<Cell> listOfCells) {
        for(Cell cell : listOfCells) {
            if(cell.getValue() != 0) continue;
            List<Integer> listOfCandidatesForTheCell = cell.getCandidates();
            for(int candidate : listOfCandidatesForTheCell ) {
                boolean foundCellWithCandidate = false;
                for(Cell checkedCell : listOfCells) {

                    if(checkedCell != cell && checkedCell.getValue() == 0
                            && checkedCell.getCandidates().contains(candidate)) {
                        foundCellWithCandidate = true;
                    }
                }
                if(!foundCellWithCandidate) {
                    return new CellWithUniqueCandidate(cell, candidate);
                }
            }
        }
        return null;
    }

    /**
     * Object which holds cell and unique candidate
     */
    static class CellWithUniqueCandidate {
        Cell cell;
        int uniqueCandidate;

        CellWithUniqueCandidate(Cell cell, int uniqueCandidate) {
            this.cell = cell;
            this.uniqueCandidate = uniqueCandidate;
        }
    }
}

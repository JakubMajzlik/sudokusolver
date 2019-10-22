package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.Cell;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Subgroup technique of solving sudoku.
 * Principle of this technique is: If there is of candidates in row, column or in rectangle in cells with the same amount,
 * we can eliminate every of these candidates from the other cells in row, column or rectangle.
 * @author Jakub Majzlík
 * @version 1.0
 * @see Technique
 * TODO: Find trio, quartet, quintet
 */
public class SubGroup implements Technique {

    private GameGrid gameGrid;

    /**
     * Applies Subgroup technique
     * @param gameGrid Game grid
     */
    @Override
    public void apply(GameGrid gameGrid) {
        if(gameGrid.getSize() != 9) return;
        this.gameGrid = gameGrid;
        boolean done = false;
        while (!done) {
            done = true;
            for(int i = 0; i < gameGrid.getSize(); i++) {
                findPairs(gameGrid.getColumn(i));
                findPairs(gameGrid.getRow(i));
                findPairs(gameGrid.getRectangle(i));
            }

        }
    }

    /**
     * Finds pairs in listOfCells.
     * @param listOfCells List of cells
     */
    private void findPairs(List<Cell> listOfCells) {

        for(Cell cell : listOfCells) {
            if(cell.getValue() != 0 || cell.getCandidates().size() != 2)  continue;

            List<Cell> pair = new ArrayList<>();

            pair.add(cell);

            for(Cell pairCell : listOfCells) {
                if (pairCell.getValue() != 0 || pairCell == cell || pairCell.getCandidates().size() != 2) continue;

                if(pairCell.getCandidates().containsAll(cell.getCandidates())) {
                    if(pair.size() == 1) {
                        pair.add(pairCell);
                    } else {
                        pair.remove(1);
                        break;
                    }
                }
            }


            if(pair.size() == 2) {
                if(pair.get(0).getRectangle() == pair.get(1).getRectangle()) {
                    for(Cell cellWithCandidatesToRemove : gameGrid.getRectangle(pair.get(0).getRectangle())) {
                        if(cellWithCandidatesToRemove.getValue() != 0 || pair.contains(cellWithCandidatesToRemove)) continue;

                        cellWithCandidatesToRemove.removeCandidate(cell.getCandidates().get(0));
                        cellWithCandidatesToRemove.removeCandidate(cell.getCandidates().get(1));
                    }
                }

                if(pair.get(0).getRowIndex() == pair.get(1).getRowIndex()) {
                    for(Cell cellWithCandidatesToRemove : gameGrid.getRow(pair.get(0).getRowIndex())) {
                        if(cellWithCandidatesToRemove.getValue() != 0 || pair.contains(cellWithCandidatesToRemove)) continue;

                        cellWithCandidatesToRemove.removeCandidate(cell.getCandidates().get(0));
                        cellWithCandidatesToRemove.removeCandidate(cell.getCandidates().get(1));
                    }
                } else if(pair.get(0).getColumnIndex() == pair.get(1).getColumnIndex()) {
                    for(Cell cellWithCandidatesToRemove : gameGrid.getColumn(pair.get(0).getColumnIndex())) {
                        if(cellWithCandidatesToRemove.getValue() != 0 || pair.contains(cellWithCandidatesToRemove)) continue;

                        cellWithCandidatesToRemove.removeCandidate(cell.getCandidates().get(0));
                        cellWithCandidatesToRemove.removeCandidate(cell.getCandidates().get(1));
                    }
                }
            }

        }
    }
}

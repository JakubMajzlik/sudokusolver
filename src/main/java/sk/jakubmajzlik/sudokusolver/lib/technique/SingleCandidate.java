package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.Cell;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

/**
 * Implementation of SingleCandidate technique of solving sudoku.
 * Principle of this technique is to find cell in grid where is only one candidate and put the candidate there.
 * So far the only grid of size 9x9 is supported.
 *
 * @author Jakub Majzlík
 * @version 2.0
 * @see Technique
 */
public class SingleCandidate implements Technique {
    /**
     * Applies technique of Single Candidate
     * @param gameGrid Game grid
     */
    @Override
    public void apply(GameGrid gameGrid) {
        if(gameGrid.getSize() != 9) return;
        boolean done = false;
        while (!done) {
            done = true;
            for(int rowIndex = 0; rowIndex < gameGrid.getSize(); rowIndex++) {
                for(int columnIndex = 0; columnIndex < gameGrid.getSize(); columnIndex++) {
                    Cell cell = gameGrid.get(rowIndex, columnIndex);
                    if(cell.getValue() == 0 && cell.getCandidates().size() == 1) {
                        gameGrid.set(rowIndex, columnIndex, cell.getCandidates().get(0));
                        done = false;
                    }
                }
            }
        }
    }
}

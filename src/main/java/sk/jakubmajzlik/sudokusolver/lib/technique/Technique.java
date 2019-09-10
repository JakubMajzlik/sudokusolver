package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

/**
 * Interface for solving techniques.
 * @version 1.0
 * @author Jakub Majzlík
 */
public interface Technique {
    /**
     * Applies technique to the {@code gameGrid} and returns game grid
     * @param gameGrid Game grid
     * @since 1.0
     * @see SingleCandidate
     */
    void apply(GameGrid gameGrid);
}

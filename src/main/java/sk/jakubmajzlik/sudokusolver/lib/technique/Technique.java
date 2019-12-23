package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

/**
 * Interface for solving techniques.
 * @version 1.0
 * @author Jakub Majzlík
 *
 * @see SingleCandidate
 * @see Scanning
 * @see Elimination
 * @see SubGroup
 */
public interface Technique {
    /**
     * Applies technique to the {@code gameGrid} and returns game grid
     * @param gameGrid Game grid
     * @since 1.0
     */
    void apply(GameGrid gameGrid);
}

package sk.jakubmajzlik.sudokusolver.lib;

import sk.jakubmajzlik.sudokusolver.lib.technique.Scanning;
import sk.jakubmajzlik.sudokusolver.lib.technique.SingleCandidate;
import sk.jakubmajzlik.sudokusolver.lib.technique.SolvingTechnique;
import sk.jakubmajzlik.sudokusolver.lib.technique.Technique;

/**
 * This class contains methods, which try to solve the game grid.
 * @version 1.0
 * @author Jakub Majzlík
 */
public class SudokuSolver {

    /**
     * Solves {@code gameGrid}. The method automatically choose algorithm to use for solving.
     * @param gameGrid Game grid to solve.
     * @since 1.0
     */
    public void solve(GameGrid gameGrid) {
        SingleCandidate singleCandidate = new SingleCandidate();
        Scanning scanning = new Scanning();
        //TODO: Make choosing of algorithm smarter
        do {
            gameGrid.didProgress = false;
            singleCandidate.apply(gameGrid);
            scanning.apply(gameGrid);
        } while (gameGrid.didProgress);
    }

    /**
     * Solves {@code gameGrid} with specific technique.
     * @param solvingTechnique Technique, which we want to use.
     * @param gameGrid Game grid to solve.
     * @since 1.0
     */
    public void solve(SolvingTechnique solvingTechnique, GameGrid gameGrid) {
        Technique technique;
        switch (solvingTechnique) {
            case SINGLE_CANDIDATE: technique = new SingleCandidate(); break;
            case SCANNING: technique = new Scanning(); break;
            default: return;
        }

        while (gameGrid.didProgress) {
            gameGrid.didProgress = false;
            technique.apply(gameGrid);
        }
    }
}

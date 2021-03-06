package sk.jakubmajzlik.sudokusolver.lib;

import sk.jakubmajzlik.sudokusolver.lib.selector.TechniqueSelector;
import sk.jakubmajzlik.sudokusolver.lib.technique.*;

/**
 * This class contains methods, which try to solve the game grid.
 * @version 1.1
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
        Elimination elimination = new Elimination();
        SubGroup subGroup = new SubGroup();

        do {
            gameGrid.didProgress = false;
            singleCandidate.apply(gameGrid);
            scanning.apply(gameGrid);
            elimination.apply(gameGrid);
            subGroup.apply(gameGrid);
        } while (gameGrid.didProgress);
    }

    /**
     * Solves {@code gameGrid}. {@code TechniqueSelector} choose algorithm to use for solving.
     * @param gameGrid Game grid to solve.
     * @param techniqueSelector Algorithm selector
     * @since 1.1
     */
    public void solve(GameGrid gameGrid, TechniqueSelector techniqueSelector) {
        int attempts = 0;
        do {
            attempts++;
            gameGrid.didProgress = false;
            techniqueSelector.getTechnique().apply(gameGrid);
            if(gameGrid.didProgress) {
                attempts = 0;
            }
            //TODO: attempt number
        } while (attempts < 50);
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
            case ELIMINATION: technique = new Elimination(); break;
            case SUBGROUP: technique = new SubGroup(); break;
            default: return;
        }

        while (gameGrid.didProgress) {
            gameGrid.didProgress = false;
            technique.apply(gameGrid);
        }
    }
}

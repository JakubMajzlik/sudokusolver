package sk.jakubmajzlik.sudokusolver.lib.selector;

import sk.jakubmajzlik.sudokusolver.lib.technique.*;

import java.util.Random;

/**
 * Implementation of Random selecting of sudoku solving technique.
 * @version 1.0
 * @author Jakub Majzlík
 */
public class RandomSelector implements TechniqueSelector {

    private Random random = new Random();

    /**
     * Randomly select technique for solving sudoku.
     * @return Technique for solving sudoku.
     */
    @Override
    public Technique getTechnique() {
        final int technique = random.nextInt(SolvingTechnique.values().length);
        switch (SolvingTechnique.values()[technique]) {
            case SINGLE_CANDIDATE: return new SingleCandidate();
            case SCANNING: return new Scanning();
            case ELIMINATION: return new Elimination();
            case SUBGROUP: return new SubGroup();
            default: return null;
        }
    }
}

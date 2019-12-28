package sk.jakubmajzlik.sudokusolver.lib.selector;

import sk.jakubmajzlik.sudokusolver.lib.technique.*;

/**
 * Implementation of Linear selector.
 * @version 1.0
 * @author Jakub Majzlík
 */
public class LinearSelector implements TechniqueSelector {

    private int order = 0;

    /**
     * Select technique for sudoku solving in pre-defined order.
     * @return Technique for sudoku solving
     * @since 1.0
     */
    @Override
    public Technique getTechnique() {
        Technique technique = null;
        order++;
        //Well, this is how I solve sudoku
        //TODO: Find better sequence
        switch (order) {
            case 1:
            case 2:
            case 5:
                technique = new Scanning();
                break;
            case 3:
            case 6:
            case 8:
            case 9:
                technique = new SingleCandidate();
                break;
            case 4:
                technique = new Elimination();
                break;
            case 7:
                technique = new SubGroup();
                break;
            case 10:
                technique = new SingleCandidate();
                order = 0;
                break;
        }

        return technique;
    }
}

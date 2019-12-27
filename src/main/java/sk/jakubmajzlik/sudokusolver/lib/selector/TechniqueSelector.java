package sk.jakubmajzlik.sudokusolver.lib.selector;

import sk.jakubmajzlik.sudokusolver.lib.technique.Technique;

/**
 * Interface for algorithm selection
 * @version 1.0
 * @author Jakub Majzlík
 * @see RandomSelector
 */
public interface TechniqueSelector {
    /**
     * Returns instance of {@code Technique} implementation.
     * @return Technique 
     */
    Technique getTechnique();
}

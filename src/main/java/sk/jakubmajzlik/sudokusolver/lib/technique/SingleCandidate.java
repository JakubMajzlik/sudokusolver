package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

/**
 * Implementation of SingleCandidate technique of solving sudoku.
 * Principle of this technique is to find cell in grid where is only one candidate and put the candidate there.
 * So far the only grid of size 9x9 is supported.
 *
 * @author Jakub Majzlík
 * @version 1.0
 * @see Technique
 */
public class SingleCandidate implements Technique {
    /**
     * Applies technique of Single Candidate
     * @param gameGrid Game grid
     * @return Game grid with applied solving technique
     */
    @Override
    public GameGrid apply(GameGrid gameGrid) {
        if(gameGrid.getSize() != 9) return gameGrid;
        boolean done = false;
        while (!done) {
            done = true;
            for(int rowIndex = 0; rowIndex < gameGrid.getSize(); rowIndex++) {
                for(int columnIndex = 0; columnIndex < gameGrid.getSize(); columnIndex++) {
                    //If cell already contains a number, algorithm continue with next cell
                    if(gameGrid.get(rowIndex, columnIndex) > 0) continue;
                    int numberThatCanBePlaced = 0;
                    for(int possibleNumber = 1; possibleNumber <= gameGrid.getSize(); possibleNumber++) {
                        if(gameGrid.isNumberInRow(rowIndex, possibleNumber)) continue;
                        if(gameGrid.isNumberInColumn(columnIndex, possibleNumber)) continue;
                        if(gameGrid.isNumberInRectangle(gameGrid.getRectangleIndex(rowIndex, columnIndex), possibleNumber)) continue;

                        if(numberThatCanBePlaced > 0) {
                            numberThatCanBePlaced = 0;
                            break;
                        }
                        numberThatCanBePlaced = possibleNumber;
                    }
                    if(numberThatCanBePlaced > 0) {
                        done = false;
                        gameGrid.set(rowIndex, columnIndex, numberThatCanBePlaced);
                    }
                }
            }
        }
        return gameGrid;
    }
}

package sk.jakubmajzlik.sudokusolver.lib.technique;

import sk.jakubmajzlik.sudokusolver.lib.Cell;
import sk.jakubmajzlik.sudokusolver.lib.GameGrid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of Subgroup technique of solving sudoku.
 * Principle of this technique is: If there is of candidates in row, column or in rectangle in cells with the same amount,
 * we can eliminate every of these candidates from the other cells in row, column or rectangle.
 * @author Jakub Majzlík
 * @version 1.0
 * @see Technique
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

        for(int i = 0; i < gameGrid.getSize(); i++) {
            for(int sizeOfGroup = 2; sizeOfGroup <= 5; sizeOfGroup++) {
                findGroup(sizeOfGroup, gameGrid.getColumn(i));
                findGroup(sizeOfGroup, gameGrid.getRow(i));
                findGroup(sizeOfGroup, gameGrid.getRectangle(i));
            }
        }
    }

    /**
     * Finds group of the size {@code sizeOfGroup} and eliminate candidates of the other cells.
     * @param sizeOfGroup Size of the group
     * @param listOfCells List of cells where the method will find group
     * @since 1.0
     */
    private void findGroup(int sizeOfGroup, List<Cell> listOfCells) {
        //Finding group
        for(Cell cell : listOfCells) {
            if(cell.getValue() != 0 )  continue;
            if(cell.getCandidates().size() <= 1 || cell.getCandidates().size() > sizeOfGroup) continue;

            List<Cell> group = new ArrayList<>();
            Set<Integer> groupCandidates = new HashSet<>(cell.getCandidates());

            group.add(cell);

            for(Cell otherCell : listOfCells) {
                if (otherCell.getValue() != 0 || group.contains(otherCell)) continue;
                if(otherCell.getCandidates().size() <= 1 || otherCell.getCandidates().size() > sizeOfGroup) continue;

                if(!groupCandidates.containsAll(otherCell.getCandidates())
                    && !otherCell.getCandidates().containsAll(groupCandidates)) continue;

                if(group.size() < sizeOfGroup) {
                    groupCandidates.addAll(otherCell.getCandidates());
                    group.add(otherCell);
                } else {
                    group.clear();
                    break;
                }
            }

            //Removing candidates
            if(group.size() == sizeOfGroup) {
                if(isEveryCellInTheSameRectangle(group)) {
                    for(Cell cellWithCandidatesToRemove : gameGrid.getRectangle(group.get(0).getRectangle())) {
                        removeCandidates(group, groupCandidates, cellWithCandidatesToRemove);
                    }
                }

                if(isEveryCellInTheSameRow(group)) {
                    for(Cell cellWithCandidatesToRemove : gameGrid.getRow(group.get(0).getRowIndex())) {
                        removeCandidates(group, groupCandidates, cellWithCandidatesToRemove);
                    }
                } else if(isEveryCellInTheSameColumn(group)) {
                    for(Cell cellWithCandidatesToRemove : gameGrid.getColumn(group.get(0).getColumnIndex())) {
                        removeCandidates(group, groupCandidates, cellWithCandidatesToRemove);
                    }
                }
            }
        }
    }

    /**
     * Removes candidates from {@code cellWithCandidatesToRemove}. If the cell
     * is in the {@code group}, nothing will be removed.
     * @param group Group of cells
     * @param groupCandidates Candidates to remove from {@code cellWithCandidatesToRemove}
     * @param cellWithCandidatesToRemove Cell where the candidates will be removed
     * @since 1.0
     */
    private void removeCandidates(List<Cell> group, Set<Integer> groupCandidates, Cell cellWithCandidatesToRemove) {
        if (cellWithCandidatesToRemove.getValue() != 0 || group.contains(cellWithCandidatesToRemove)) return;

        for (Integer candidateToRemove : groupCandidates) {
            cellWithCandidatesToRemove.removeCandidate(candidateToRemove);
        }
    }

    /**
     * Checks if are all cells in {@code listOfCells} in the same row.
     * @param listOfCells List of cells
     * @return True if the cells are in the same row.
     * @since 1.0
     */
    private boolean isEveryCellInTheSameRow(List<Cell> listOfCells) {
        int cellRow = listOfCells.get(0).getRowIndex();
        for (Cell cell : listOfCells) {
            if(cell.getRowIndex() != cellRow) return false;
        }
        return true;
    }

    /**
     * Checks if are all cells in {@code listOfCells} in the same column.
     * @param listOfCells List of cells
     * @return True if the cells are in the same column.
     * @since 1.0
     */
    private boolean isEveryCellInTheSameColumn(List<Cell> listOfCells) {
        int cellColumn = listOfCells.get(0).getColumnIndex();
        for (Cell cell : listOfCells) {
            if(cell.getColumnIndex() != cellColumn) return false;
        }
        return true;
    }

    /**
     * Checks if are all cells in {@code listOfCells} in the same rectangle.
     * @param listOfCells List of cells
     * @return True if the cells are in the same rectangle.
     * @since 1.0
     */
    private boolean isEveryCellInTheSameRectangle(List<Cell> listOfCells) {
        int cellRectangle = listOfCells.get(0).getRectangle();
        for (Cell cell : listOfCells) {
            if(cell.getRectangle() != cellRectangle) return false;
        }
        return true;
    }
}

package bpa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The constraint that no cell in the grid can have 2 or more direct 
 * neighbour-cells that have the same state as this cell. A cell x is guilty of
 * a violation of this constraint, if it has two (or more) neighbouring cells
 * with the same state as x.
 * 
 * @author iVerb
 * @since 3-4-13
 */
public class BinaryPuzzleTripletConstraint implements Constraint {
        
    @Override
    public List<Cell> check(final List<Cell> group) {
        if (group.get(1).getState() == group.get(0).getState() 
                && group.get(1).getState() == group.get(2).getState()
                && group.get(1).getState() != CellState.EMPTY) {
            return group;
        }
        return Arrays.asList();
    }

    @Override
    public List<List<Cell>> obtainGroups(final Grid grid) {
        List<List<Cell>> groups = new ArrayList();
        Cell c1, c2, c3;
        for (int i = 1; i != grid.getWidth() - 1; i ++) {
            for (int j = 0; j != grid.getHeight(); j ++) {
                c1 = grid.getCell(i - 1, j);
                c2 = grid.getCell(i, j);
                c3 = grid.getCell(i + 1, j);
                List<Cell> group = Arrays.asList(c1, c2, c3);
                groups.add(group);
            }
        }        
        for (int i = 0; i != grid.getWidth(); i ++) {
            for (int j = 1; j != grid.getHeight() - 1; j ++) {
                c1 = grid.getCell(i, j - 1);
                c2 = grid.getCell(i, j);
                c3 = grid.getCell(i, j + 1);
                List<Cell> group = Arrays.asList(c1, c2, c3);
                groups.add(group);
            }
        }
        return groups;
    }
    
    @Override
    public String toString() {
        return "Binary Puzzle Triplet";
    }
    
}

package bpa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The constraint that not more than half of the cells in a line in the grid 
 * can have equal states, except for if this state is empty. A cell x is guilty 
 * of a violation of this constraint, if x resides in one of these violating 
 * lines in the grid and the cell-state of x is overrepresented in that line.
 * 
 * @author iVerb
 * @since 3-4-13
 */
public class BinaryPuzzleLineConstraint implements Constraint {

    @Override
    public List<Cell> check(final List<Cell> group) {

        List<Cell> zeroes = new ArrayList();
        List<Cell> ones = new ArrayList();        

        for (Cell c : group) {
            if (c.getState() == CellState.ZERO) {
                zeroes.add(c);
            }
            if (c.getState() == CellState.ONE) {
                ones.add(c);
            }
        }

        if (zeroes.size() > (group.size() / 2)) {
            return zeroes;
        }
        if (ones.size() > (group.size() / 2)) {
            return ones;
        }
        return Arrays.asList();
    }

    @Override
    public List<List<Cell>> obtainGroups(final Grid grid) {        
        List<List<Cell>> groups = new ArrayList();
        for (List<Cell> l : grid.lines()) {
            groups.add(l);
        }
        return groups;
    }
    
    @Override
    public String toString() {
        return "Binary Puzzle Line";
    }
    
}

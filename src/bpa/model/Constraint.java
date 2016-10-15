package bpa.model;

import java.util.List;

/**
 * A constraint which can be used to represent an additional invariant/rule that 
 * has to hold on some grid. By implementing this interface, it is easy to 
 * implement new rules, such as rules for Sudoku puzzles or variants of the 
 * binary puzzle. This interfaces provides functionality to obtain all the 
 * groups of cells that are relevant for the rule, and functionality to check
 * the constraint on some group.
 * 
 * @author iVerb
 * @since 3-4-13
 */
public interface Constraint {
    
    /**
     * Checks this constraint on the given group of cells and returns all 
     * guilty cells in this group.
     * 
     * @param group  the group of cells under inspection
     * @pre the size of {@code group} should be such that it complies with the 
     * requirements of this constraint. In example: if the constraint acts on 
     * pairs of cells, the size of {@code group} should be exactly 2.
     * @return a subset of {@code group} containing all the guilty cells in
     * {@code group}.
     */
    public abstract List<Cell> check(final List<Cell> group);
    
    /**
     * Returns all groups of cells in the given grid that are relevant for this 
     * constraint. These groups can represent any collection of cells in the 
     * grid, such as lines, triplets, blocks, etc.
     * 
     * @param grid  the grid for which we want to extract all relevant groups
     * @pre {@code grid.isRepOk()}
     * @return a list of relevant groups of cells on which this constraint 
     * applies. 
     */
    public abstract List<List<Cell>> obtainGroups(final Grid grid);
    
    @Override
    public abstract String toString();
     
}

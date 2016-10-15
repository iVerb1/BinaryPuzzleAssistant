package bpa.model;

import java.util.List;

/**
 * A representation of a single violation on some constraint. This class can be
 * used to pass information about what kind of violations are made on some grid,
 * and which cells were involved in each of these violations.
 * 
 * @author iVerb
 * @since 3-4-13
 */
public class Violation {

    /** The group of cells that are involved in this violation. */
    private List<Cell> group;
    
    /** The constraint that is violated by this violation. */
    private Constraint constraint;        
    
    public Violation(final List<Cell> group, 
            final Constraint constraint) {
        this.group = group;
        this.constraint = constraint;
    }
    
    /**
     * Gets the group of cells that are involved in this violation.
     * 
     * @return a {@code List<Cell>} that represents the group of cells that are 
     * involved in this violation
     */
    public List<Cell> getGroup() {
        return group;
    }
    
    /**
     * Gets the constraint that is violated by this violation. 
     * 
     * @return the {@code Constraint} object that corresponds to the constraint 
     * that is violated by this violation. 
     */
    public Constraint getConstraint() {
        return constraint;
    }
    
}

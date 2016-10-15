package bpa.model;

/**
 * An enumeration of all the possible states of a cell in the grid of the Binary
 * Puzzle.
 * 
 * @author iVerb
 * @since 9-3-13
 */
public enum CellState {
    ZERO("0"),
    ONE("1"),
    EMPTY(".");
    
    /** String representation of the state chosen for this enum. */
    private String token;
    
    private CellState(final String token) {
        this.token = token;
    }    
    
    @Override
    public String toString() {
        return token;
    }
    
}

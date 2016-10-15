package bpa.model;

/**
 * A cell in the grid of the Binary Puzzle representing either a zero, one or an
 * empty spot in the grid. This state is represented by a CellState. A cell can 
 * also be locked, disabling the possibility of editing the state.
 * 
 * @inv
 * - EmptyCellsNotLocked: !(state == CellState.EMPTY && locked)
 * 
 * @author iVerb
 * @since 22-3-13
 */
public class Cell {
    
    /** Whether this cell is locked */
    private boolean locked;
    
    /** The current state of this cell */
    private CellState state;
    
    /**
     * Initialized this cell with the given state and whether it is locked.
     * 
     * @param state  the desired state of this cell
     * @param locked  whether this cell should be locked or not
     * @pre {@code !(state == CellState.EMPTY && locked == true)}
     * @throws IllegalArgumentException  if {@code state == CellState.EMPTY && 
     * locked == true}
     */
    public Cell(final CellState state, final boolean locked) 
        throws IllegalArgumentException {
        
        if (state == CellState.EMPTY && locked) {
            throw new IllegalArgumentException("Cell.Cell.pre violated: "
                    + "state == CellState.EMPTY && locked == true");
        }                
        this.locked = locked;
        this.state = state;
    }
    
    /**
     * Returns whether this cell is locked or not.
     * 
     * @return whether this cell is locked or not
     */
    public boolean isLocked() {
        return locked;
    }
    
    /**
     * Returns the the current state of this cell.
     * 
     * @return the {@code CellState} corresponding to the state of this cell
     */
    public CellState getState() {
        return state;
    }
    
    /**
     * Sets the state of this cell, given that it is not locked
     * 
     * @param state the cell-state this cell should be changed to
     * @pre {@code !isLocked()}
     * @post the state of this cell has changed to {@code state}
     * @throws IllegalArgumentException if {@code isLocked()}
     */
    public void setState(final CellState state)
        throws IllegalArgumentException {
        if (locked) {
            throw new IllegalArgumentException("Cell.changeState.pre violated: "
                    + " cell is locked.");
        }
        this.state = state;
    }

    /**
     * Sets the locked status of this cell.
     * 
     * @param locked  the locked-state this cell should be changed to
     * @pre {@code getState() != CellState.EMPTY}
     * @post {@code isLocked()}
     * @throws IllegalArgumentException  if {@code getState() == CellState.EMPTY}
     */
    public void setLocked(final boolean locked) 
        throws IllegalArgumentException {
        if (state == CellState.EMPTY && locked) {
            throw new IllegalArgumentException("Cell.setLocked.pre violated: "
                    + " cell is empty.");
        }
        this.locked = locked;
    }
    
   /**
     * Returns the token this cell corresponds to.
     * 
     * @pre {@code true}
     * @return a String whose first character  is an element of the set 
     * {@code {'0', '1', '.'}}, corresponding to state of this cell. If this 
     * cell is unlocked, a second character {@code '*'} is appended to the 
     * string, except for when the state denotes an empty cell.
     */
    @Override
    public String toString() {
        String token = state.toString();        
        if (! isLocked() && state != CellState.EMPTY) {
            token += "*";
        }
        return token;
    }
    
}

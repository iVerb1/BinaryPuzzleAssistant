package bpa.model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * A rectangular grid used for the Binary Puzzle containing a Cell on each valid 
 * {@code (x,y)}-coordinate. This grid is initialized using a file containing 
 * the specification of the grid. This class implements {@code Iterable<Cell>} 
 * making it possible to iterate over all cells in the grid easily. Also, this
 * class contains a list of {@code Constraint} objects that represent the 
 * constraints that have to hold on this grid.

 * @author iVerb
 * @since 3-4-13
 */
public abstract class Grid implements Iterable<Cell> {

    /**
     * Builds a new grid using the specification the given scanner provides and
     * sets the constraints that have to hold on this grid to the given list of 
     * constraints.
     * 
     * @param scanner  The scanner scanning through the grid-specification
     * @param constraints  The list of constraints that have to hold on this 
     * grid
     * @pre {@code scanner} reads from a source with the right syntax
     * @post {@code this} represents the grid specified by the specification 
     * provided by {@code scanner} and the constraints of this grid are set to 
     * {@code constraints}.
     * @throws IllegalArgumentException  if the syntax of the specification 
     * given by the source {@code scanner} is reading from is wrong or if the 
     * grid that is specified violates some invariant(s)
     */
    public Grid(final Scanner scanner, final List<Constraint> constraints) 
        throws IllegalArgumentException { } 
    
    /**
     * Checks whether the representation invariants hold.
     * 
     * @return whether the representation invariants hold
     * @throws IllegalStateException  if precondition violated
     * @pre representation invariants hold
     * @post {@code \result}
     */
    public abstract boolean isRepOk() throws IllegalStateException;
        
    /**
     * Gets the row in the grid with row number equal to {@code index} (the top 
     * row of the grid has row number 0)
     * 
     * @param index  the number of the row to be retrieved
     * @pre {@code index >= 0 && index <} length of the row to be retrieved
     * @return sequence of all Cell objects for which the y-coordinate equals 
     * {@code index}
     * @throws IllegalArgumentException  if {@code index > 0 
     * || index >= } length of the row to be retrieved
     */
    public abstract List<Cell> getRow(int index) 
        throws IllegalArgumentException;
    
    /**
     * Gets the column in the grid with column number equal to {@code index} 
     * (the leftmost column of the grid has column number 0)
     * 
     * @param index  the number of the column to be retrieved
     * @pre {@code index >= 0 && index <} length of the column to be retrieved
     * @return sequence of all Cell objects for which the x-coordinate equals 
     * {@code index}
     * @throws IllegalArgumentException  if {@code index < 0 
     * || index >= } length of the column to be retrieved
     */
    public abstract List<Cell> getColumn(int index) 
        throws IllegalArgumentException;
    
    /**
     * Gets the width of the grid
     * 
     * @pre {@code true}
     */
    public abstract int getWidth();    
    
    /**
     * Gets the height of the grid
     * 
     * @pre {@code true}
     */
    public abstract int getHeight();
    
    /**
     * Gets the constraints that have to hold on this grid.
     * 
     * @pre {@code true}
     * @return the current list of constraints that have to hold on this grid
     */
    public abstract List<Constraint> getConstraints();
    
    /**
     * Sets the constraints that have to hold on this grid to the given list of 
     * constraints.
     * 
     * @pre {@code true}
     * @param constraints  the desired constraints that should hold on this grid
     */
    public abstract void setConstraints(List<Constraint> constraints);
    
    /**
     * Checks whether the given x- and y-coordinate are within the range of the 
     * grid.
     * 
     * @param x  the x-coordinate of the cell under inspection
     * @param y  the y-coordinate of the cell under inspection
     * @throws IllegalArgumentException  if {@code !checkCoordinates(x, y)}
     */
    public abstract void checkCoordinates(final int x, final int y)
        throws IllegalArgumentException;
    
    /**
     * Determines whether a given cell at the given x- and y-coordinate is 
     * locked or not.
     * 
     * @param x  the x-coordinate of the cell under inspection
     * @param y  the y-coordinate of the clel under inspection
     * @pre {@code checkCoordinates(x, y)}
     * @return whether the cell at position {@code (x, y)} is locked or not.
     * @throws IllegalArgumentException  if {@code !checkCoordinates(x, y)}
     */
    public abstract boolean isCellLocked(final int x, final int y);
    
    /**
     * Gets the cell at the given x- and y-coordinate
     * 
     * @param x  the x-coordinate of the cell 
     * @param y  the y-coordinate of the cell
     * @pre {@code checkCoordinates(x, y)}
     * @return the cell at position {@code (x, y)} in the grid
     * @throws IllegalArgumentException  if {@code !checkCoordinates(x, y)}
     */
    public abstract Cell getCell(final int x, final int y); 
    
    /**
     * Changes the state of the cell at the given coordinates to the given 
     * state.
     * 
     * @param x  the x-coordinate of the cell of which the state is changed
     * @param y  the x-coordinate of the cell of which the state is changed
     * @param state  the desired state of the cell of which the state is changed
     * @pre {@code checkCoordinates(x, y)}
     * @post state of the cell at position {@code (x, y)} is equal to {@code 
     * state}
     * @throws IllegalArgumentException  if {@code !checkCoordinates(x, y) ||
     * isCellLocked(x, y)}
     */
    public abstract void setCellState(final int x, final int y,  
        final CellState state);
    
    /**
     * Checks the grid on all the constraints that have to hold on this grid
     * and returns all violations of these constraints.
     * 
     * @pre {@code true}
     * @return a list of violations, all of which representing one violation of
     * one constraint by one group of cells.
     */
    public abstract List<Violation> getConstraintViolations();
    
    /**
     * Checks the position in the grid at the given coordinates for any 
     * constraints that have to hold on this grid and returns the types of 
     * constraints that were violated.
     * 
     * @param x  the x-coordinate of the cell under inspection
     * @param y  the y-coordinate of the cell under inspection
     * @pre {@code checkCoordinates(x, y)}
     * @return a list containing {@code Constraint} objects representing all
     * constraints that the cell at position {@code (x, y)} has violated
     */
    public abstract List<Constraint> getCellViolations(
        final int x, final int y); 
    
    /**
     * Gets a list of cells that violates one or more constraints that have to
     * hold on the grid.
     * 
     * @pre {@code true}
     * @return a list of cells where each cell violates one or more constraints
     * in {@code getConstraints()}
     */
    public abstract List<Cell> getViolatingCells();
    
    /**
     * Determines whether the grid is full, that is, each cell should be 
     * non-empty.
     * 
     * @pre {@code true}
     * @return {@code \forall x, y; checkCoordinates(x, y); 
     *      getCell(x, y).getState() != CellState.EMPTY}
     */
    public abstract boolean isFull();
    
    /**
     * Determines whether this grid is solved according to the constraints that
     * have to hold on this grid. In order for a grid to be solved, each cell 
     * should be non-empty and the grid should not violate any of the 
     * constraints that have to hold on this grid.
     * 
     * @pre {@code true}
     * @return {@code isFull() && getConstraintViolations().isEmpty}
     */
    public abstract boolean isSolved();
    
    /**
     * Gives a string representation of the grid. Can be used to save a grid to 
     * a file.
     */
    @Override
    public abstract String toString();

    /**
     * The iterator used to iterate over all cells in the grid.
     */
    @Override
    public abstract Iterator<Cell> iterator();
    
    /**
     * By using this method, it is possible to iterate over all lines in the 
     * grid, that is, all rows and columns
     */
    public abstract Iterable<List<Cell>> lines(); 
    
}
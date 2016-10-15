package bpa.solvers;

import bpa.model.Cell;
import bpa.model.CellState;
import bpa.model.Grid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * A generic backtracker that applies backtracking to find (a) solution(s) on 
 * some puzzle grid. To be extended by a concrete backtracker that applies a 
 * certain backtracking method for solving. This class implements 
 * {@code Observable} such that it can possibly register listeners to which 
 * it can report any found solutions during backtracking. Use of a helper 
 * strategy is optional and can be set using the {@code setHelperStrategy()} 
 * method.
 *
 * @author iVerb
 * @since 3-4-13
 */
public abstract class Backtracker extends Observable {

    /**
     * The grid to be solved by this backtracker.
     */    
    protected Grid grid;
    
    /** 
     * The helper strategy that will be used to increase efficiency of this
     * backtracker.
     */    
    protected Strategy helperStrategy;
    
    /**
     * Whether or not this backtracker has been/should be aborted.
     */
    protected boolean aborted;
    
    /** Constant used in the {@code openCell()} method, used to increase 
     * efficiency of the backtracker. This constant represents the desired 
     * amount of filled neighbours an open cell should have. This constant is 
     * used because backtracking using speculation over open cells is more 
     * efficient if these open cells reside in areas in the grid where there are 
     * a lot of cells that are non-empty.
     */
    private final int FILLED_NEIGHBOUR_THRESHOLD = 2;

    /**
     * Initializes a new backtracker that solves the given grid.
     *
     * @param grid  the puzzle grid to be solved by this backtracker
     */
    public Backtracker(final Grid grid) {
        this.grid = grid;
        this.helperStrategy = new CompositeStrategy();
        this.aborted = false;
    }

    /**
     * Sets the grid to be solved by this backtracker to the given grid.
     *
     * @param grid  the puzzle grid to be solved by this backtracker
     * @modifies grid
     */
    public void setGrid(final Grid grid) {
        this.grid = grid;
    }

    /**
     * Sets the helper strategy to be used by this backtracker to the given
     * strategy.
     *
     * @param helperStrategy  the helper strategy to be used by this backtracker
     * @modifies helperStrategy
     */
    public void setHelperStrategy(final Strategy helperStrategy) {
        this.helperStrategy = helperStrategy;
    }
    
    /**
     * Requests this backtracker to be aborted (at a convenient moment).
     * 
     * @modifies aborted
     */
    public void abort() {
        aborted = true;
    }

    /**
     * Applies a backtracking algorithm to solve the grid. Can use 
     * {@code findOpenCell()} to find an open cell and speculate over its 
     * cell-state.
     * 
     * @throws InterruptedException  if this backtracker has been aborted: if 
     * {@code abort()} is called during execution of this method.
     */
    public abstract void solve() throws InterruptedException;

    /**
     * Intelligently finds an open cell in the grid (a cell which is empty and 
     * unlocked). Main goal of this method is to find the cell with the highest
     * amount of filled neighbouring cells, while trying to avoid iterating over
     * all cells in the grid. The {@code FILLED_NEIGHBOUR_THRESHOLD} is used to
     * determine if some cell is good enough such that it can directly be
     * returned.
     * 
     * @pre the grid used in this backtracker has an empty cell / is not full.
     * @return a cell in the grid that has {@code X} filled neighbours where 
     * {@code X} is at least as big as the {@code FILLED_NEIGHBOUR_THRESHOLD} 
     * constant. If no such cell is found, the cell with the highest number of 
     * filled neighbours is returned.
     * @throws IllegalStateException  if the grid used in this backtracker has 
     * no empty cells / is full.
     */
    protected Cell findOpenCell() throws IllegalStateException {
        
        /** 
         * A mapping from some integer representing some amount of filled 
         * neighbouring cells to some cell which has this amount of filled 
         * neighbour cells.
         */
        Map<Integer, Cell> neighbourCountMapping = new HashMap<Integer, Cell>();
        
        for (int i = 0; i != grid.getWidth(); i ++) {
            for (int j = 0; j != grid.getHeight(); j ++) {
                Cell cell = grid.getCell(i, j);
                if (! cell.isLocked() && cell.getState() == CellState.EMPTY) {
                    int filledNeighbourCount = getFilledNeighbourCount(i, j);
                    if (filledNeighbourCount >= FILLED_NEIGHBOUR_THRESHOLD) {
                        return cell;
                    } 
                    else {
                        neighbourCountMapping.put(filledNeighbourCount, cell);
                    }
                }
            }
        }
        if (neighbourCountMapping.isEmpty()) {
            throw new IllegalStateException("Backtracker.findOpenCell.pre "
                    + "violated: puzzle grid contains no open cells.");
        }
        int maxNeighbourCount = 0;
        for (int neighbourCount : neighbourCountMapping.keySet()) {
            maxNeighbourCount = Math.max(maxNeighbourCount, neighbourCount);
        }
        return neighbourCountMapping.get(maxNeighbourCount);
    }

    /**
     * Determines how many filled neighbours the cell at coordinates 
     * {@code (x, y)} in the puzzle grid has. We only investigate direct 
     * neighbours (no diagonal neighbours). A neighbouring cell is filled if its 
     * cell-state is non-empty.
     * 
     * @param x  the x-coordinate in the grid of the cell under inspection
     * @param x  the y-coordinate in the grid of the cell under inspection
     * @return the amount of filled neighbouring cells of the cell at 
     * coordinates {@code (x, y)} in the puzzle grid
     */
    private int getFilledNeighbourCount(final int x, final int y) {
        List<Cell> neighbours = new ArrayList<Cell>();

        if (x > 0) {
            neighbours.add(grid.getCell(x - 1, y));
        }
        if (x < grid.getWidth() - 1) {
            neighbours.add(grid.getCell(x + 1, y));
        }
        if (y > 0) {
            neighbours.add(grid.getCell(x, y - 1));
        }
        if (y < grid.getHeight() - 1) {
            neighbours.add(grid.getCell(x, y + 1));
        }

        int filledNeighbours = 0;
        for (Cell neighbour : neighbours) {
            if (neighbour.getState() != CellState.EMPTY) {
                filledNeighbours ++;
            }
        }
        return filledNeighbours;
    }
    
}
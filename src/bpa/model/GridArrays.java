package bpa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * An implementation of {@code Grid} using a {@code Cell[][]} to
 * represent the grid. Since the grid is usually not that big and after solving
 * it the two-dimensional array is utilized to a full extent, this seems like an
 * efficient implementation of the puzzle grid.
 *
 * <p>
 * @inv 
 * - NotNull: {@code grid != null} <br> 
 * - EvenColumns: {@code grid.length % 2 == null} <br> 
 * - EvenRows: {@code \forall i; grid.has(i); grid[i].length % 2 == null} <br> 
 * - ElementsNotNull: {@code \forall i; grid.has(i); grid[i] != null} <br> 
 * - CellsNotNull: {@code \forall i; grid.has(i);
 *       \forall j; grid[i].has(j); grid[i][j] != null} <br> 
 * - StateOfCellsNotNull: {@code \forall i; grid.has(i);
 *       \forall j; grid[i].has(j); grid[i][j].getState() != null} <br> 
 * -  ColumnSizeConsistent: {@code \exist C;
 *        (\forall i; grid.has(i); grid[i].length == C)} <br>
 *
 * @author iVerb
 * @since 3-4-13
 */
public class GridArrays extends Grid {

    /**
     * The grid containing all cells.
     */
    private Cell[][] grid;
    
    /**
     * The list of constraints that have to hold on this grid
     */
    private List<Constraint> constraints;    

    public GridArrays(final Scanner sc, final List<Constraint> constraints) 
        throws IllegalArgumentException {
        super(sc, constraints);
        this.grid = GridParser.parseGrid(sc);
        this.constraints = constraints;
    }

    @Override
    public boolean isRepOk() throws IllegalStateException {
        if (grid == null) {
            throw new IllegalStateException("grid == null");
        }
        if (grid.length % 2 != 0) {
            throw new IllegalStateException("grid.length uneven");
        }

        int constant = grid[0].length; //used as constant for the column length

        for (int i = 0; i != grid.length; ++ i) {
            if (grid[i] == null) {
                throw new IllegalStateException("grid[" + i + "] == null");
            }
            if (grid[i].length % 2 != 0) {
                throw new IllegalStateException(
                        "grid[" + i + "].length uneven");
            }
            if (grid[i].length != constant) {
                throw new IllegalStateException(
                        "grid[" + i + "].length inconsistent with the rest of "
                        + "the columns. The grid is not rectangular");
            }
            for (int j = 0; j != grid[i].length; ++ j) {
                if (grid[i][j] == null) {
                    throw new IllegalStateException(
                            "grid[" + i + "][" + j + "] == null");
                }
                if (grid[i][j].getState() == null) {
                    throw new IllegalStateException(
                            "grid[" + i + "][" + j + "].getState() == null");
                }
            }
        }
        return true;
    }

    @Override
    public List<Cell> getRow(int index) throws IllegalArgumentException {
        List<Cell> row = new ArrayList();
        for (int i = 0; i != getWidth(); i ++) {
            if (index >= getHeight() || index < 0) {
                throw new IllegalArgumentException(
                        "GridArrays.getRow.pre violated: index = " + index
                        + " is out of range");
            }
            Cell cell = grid[i][index];
            row.add(cell);
        }
        return row;
    }

    @Override
    public List<Cell> getColumn(int index) throws IllegalArgumentException {
        if (index >= getWidth() || index < 0) {
            throw new IllegalArgumentException(
                    "GridArrays.getColumn.pre violated: index = " + index
                    + " is out of range");
        }
        Cell[] column = grid[index];
        List<Cell> result = Arrays.asList(column);
        return result;
    }

    @Override
    public int getWidth() {
        return grid.length;
    }

    @Override
    public int getHeight() {
        return grid[0].length; //class invariant: consistent column size
    }

    @Override
    public List<Constraint> getConstraints() {
        return constraints;
    }
    
    @Override
    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public void checkCoordinates(final int x, final int y)
        throws IllegalArgumentException {
        
        if (x >= getWidth() || x < 0 || y >= getHeight() || y < 0) {
            String caller;
            caller = Thread.currentThread().getStackTrace()[2].getMethodName();
            throw new IllegalArgumentException(
                    "GridArrays." + caller + ".pre violated: coordinates ("
                    + x + ", " + y + ") is out of range");
        }
    }

    @Override
    public Cell getCell(final int x, final int y) {
        checkCoordinates(x, y);
        return grid[x][y];
    }

    @Override
    public boolean isCellLocked(final int x, final int y) {
        checkCoordinates(x, y);
        return getCell(x, y).isLocked();
    }

    @Override
    public void setCellState(final int x, final int y, final CellState state) {
        checkCoordinates(x, y);
        getCell(x, y).setState(state);
    }

    @Override
    public List<Violation> getConstraintViolations() {        
        List<Violation> violations = new ArrayList();
        for (Constraint c : constraints) {
            for (List<Cell> group : c.obtainGroups(this)) {
                List<Cell> guiltyCells = c.check(group);
                if (! guiltyCells.isEmpty()) {
                    Violation violation = new Violation(guiltyCells, c);
                    violations.add(violation);
                }
            }
        }
        return violations;
    }    
    
    @Override
    public List<Constraint> getCellViolations(final int x, final int y) {
        checkCoordinates(x, y);
        List<Constraint> constraintTypes = new ArrayList();  
        for (Violation v : getConstraintViolations()) {
            for (Cell cell : v.getGroup()) {
                if (cell == getCell(x, y)) {
                    constraintTypes.add(v.getConstraint());
                }
            }
        }
        return constraintTypes;  
    }  
    
    @Override
    public List<Cell> getViolatingCells() {        
        List<Cell> cells = new ArrayList();        
        for (Violation v : getConstraintViolations()) {
            for (Cell cell : v.getGroup()) {
                if (! cells.contains(cell)) {
                    cells.add(cell);
                }
            }
        }
        return cells;
    }
    
    @Override
    public boolean isFull() {
        for (Cell cell : this) {
            if (cell.getState() == CellState.EMPTY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSolved() {
        if (isFull() && getConstraintViolations().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < getHeight(); i ++) {
            for (int j = 0; j < getWidth(); j ++) {
                Cell cell = getCell(j, i);
                String token = cell.toString();
                result += token + " ";
                if (token.length() == 1) {
                    result += " ";  
                }
            }
            result += System.getProperty("line.separator");
        }
        return result;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellIterator();
    }

    @Override
    public Iterable<List<Cell>> lines() {
        return new LineIterable();
    }


    /**
     * An iterator that iterates over all cells in the grid.
     */
    public class CellIterator implements Iterator<Cell> {

        /**
         * State of iterator.
         */
        private int x; //The current x-coordinate of the iterator
        private int y; //The current y-coordinate of the iterator

        /**
         * Constructs iterator in initial state.
         */
        CellIterator() {
            x = 0;
            y = 0;
        }

        @Override
        public boolean hasNext() {
            if (y == getHeight()) {
                return false;
            }
            return true;
        }

        @Override
        public Cell next() {
            if (y == getHeight()) {
                throw new NoSuchElementException(
                        "GridArrays.CellIterator.next");
            }
            Cell result = getCell(x, y);
            if (x == getWidth() - 1) {
                y++;
                x = 0;
            } 
            else {
                x++;
            }
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * A facility used to iterate over all lines in the grid (rows & columns).
     */
    public class LineIterable implements Iterable<List<Cell>> {

        @Override
        public Iterator<List<Cell>> iterator() {
            return new LineIterator();
        }
    }

    /**
     * An Iterator that iterates over all lines in the grid, that is, all rows
     * and columns. This is used to check rule violations of lines in the grid
     * conveniently.
     */
    public class LineIterator implements Iterator<List<Cell>> {

        /**
         * State of iterator.
         */
        private int index; //at which index of a row/column the iterator is
        private boolean iteratingOverRows; //whether the iterator is iterating over rows

        /**
         * Constructs iterator in initial state.
         */
        LineIterator() {
            index = 0;
            iteratingOverRows = true;
        }

        @Override
        public boolean hasNext() {
            if (!iteratingOverRows && index == getWidth()) {
                return false;
            }
            return true;
        }

        @Override
        public List<Cell> next() {
            if (iteratingOverRows && index < getHeight() - 1) {
                return getRow(index ++);
            }
            if (iteratingOverRows && index == getHeight() - 1) {
                iteratingOverRows = false;
                index = 0;
                return getRow(getHeight() - 1);
            }
            if (!iteratingOverRows && index < getWidth()) {
                return getColumn(index ++);
            }
            throw new NoSuchElementException("GridArrays.GridIterator.next");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * A static inner class possible of parsing a grid specification from a
     * given {@code Scanner} object.
     */
    static class GridParser {

        /**
         * Builds a new grid using the specification given in the source the
         * given scanner reads from.
         *
         * @param scanner The scanner reading the grid-specification
         * @pre {@code scanner} reads from a source with the right syntax
         * @return the grid as specified in the source {@code scanner} is
         * reading from.
         * @throws IllegalArgumentException if the syntax of the specification
         * given by the source {@code scanner} is reading from is wrong or if
         * the grid that is specified violates some invariant(s)
         */
        private static Cell[][] parseGrid(final Scanner scanner) {
            ArrayList<Cell[]> rows = new ArrayList();
            for (int i = 0; scanner.hasNextLine(); i ++) {
                Cell[] row = parseLine(scanner.nextLine());
                rows.add(i, row);
            }
            Cell[][] result = transpose(rows);
            return result;
        }

        /**
         * Parses the given line and returns the {@code Cell[]} this line
         * represents.
         */
        private static Cell[] parseLine(final String line) {
            Scanner rowScanner = new Scanner(line);
            ArrayList<Cell> row = new ArrayList();

            for (int i = 0; rowScanner.hasNext(); i ++) {
                String token = rowScanner.next();
                Cell cell = parseToken(token);
                row.add(i, cell);
            }
            Cell[] result = new Cell[row.size()];
            return row.toArray(result);
        }

        /**
         * Determines what kind of cell instance belongs to the given token.
         */
        private static Cell parseToken(final String token)
            throws IllegalArgumentException {

            switch (token) {
                case "0":
                    return new Cell(CellState.ZERO, true);
                case "1":
                    return new Cell(CellState.ONE, true);
                case "0*":
                    return new Cell(CellState.ZERO, false);
                case "1*":
                    return new Cell(CellState.ONE, false);
                case ".":
                    return new Cell(CellState.EMPTY, false);
                default: 
                    throw new IllegalArgumentException(
                        "GridArrays.GridInitializer.parseToken.pre violated: "
                        + "illegal token: " + token);
            }
        }

        /**
         * Transposes the given grid such that it is specified as
         * {@code grid[columns][rows]} which is required to query a cell at a
         * certain {@code (x, y)}-coordinate in a natural way using
         * two-dimensional arrays.
         *
         * @pre the length of each row in {@code grid} is consistent, hence, the
         * grid should be rectangular
         * @param grid the grid to be transposed
         * @return transposed version of {@code grid}
         * @throws IllegalArgumentException if the length of the rows in the
         * grid is inconsistent
         */
        private static Cell[][] transpose(final ArrayList<Cell[]> grid)
            throws IllegalArgumentException {

            int columnLength = grid.size();
            int rowLength = grid.get(0).length; //assuming consistent row length
            Cell[][] result = new Cell[rowLength][columnLength];

            for (int i = 0; i != rowLength; i ++) {
                for (int j = 0; j != columnLength; j ++) {
                    if (grid.get(j).length != rowLength) {
                        throw new IllegalArgumentException(
                                "GridArrays.GridParser.transpose.pre violated:"
                                + " grid not rectangular.");
                    }
                    result[i][j] = grid.get(j)[i];
                }
            }
            return result;
        }
    }
    
}
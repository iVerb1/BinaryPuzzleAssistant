package bpa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases for {@code Grid}, to be extended to obtain  
 * concrete test cases for an extension of {@code Grid}.
 * 
 * @author iVerb
 * @since 3-4-13
 */
public abstract class GridTestCases {
    
    /** Test fixture. */
    protected Grid instance;
    
    /** Grid specifications */
    protected String correctSpec  = "correctSpec";
    protected String falseSpec = "falseSpec";
    protected String completeSpec  = "completeSpec";
    
    /** 
     * GRID SPECIFICATION OVERVIEW:
     * 
     * Below is a description of all the grid specifications used in this set of
     * test cases.
     * 
     * correctSpec:
     *  -------
     * | .  1* |
     * | 0  1  |
     * | 1  1* |
     * | .  0* |
     *  -------
     * A simple grid specification with the right syntax. This generic puzzle 
     * grid is used for most tests.
     * 
     * 
     * completeSpec:
     *  ------------
     * | 1* 1* 0  1 |
     * | 0  0  1  0*|
     * | 0  1* 0  1 |
     * | 1* 1  0* 0 |
     *  ------------
     * A grid that with no empty cells but contains some violations of the 
     * Binary Puzzle line constraint.
     * 
     * 
     * falseSpec:
     *  ----------------
     * | .  .  0  .     |
     * | .  1  .  0  1* |
     *  ----------------
     * A false specification used to test for exceptions.
     * 
     */

        
    /**
     * Sets instance to a newly constructed grid and initializes this grid using 
     * the given {@code gridSpecification} that points to some file.
     * 
     * @param gridSpecification  one of the grid specifications described above
     */
    protected abstract void setInstance(final String gridSpecification);
    
    /**
     * Creates a new {@code Grid} instance and initializes this new grid
     * using the given {@code Scanner}.
     * 
     * @param scanner  the scanner reading the grid specification
     * @return the newly created grid conforming to the specification given by
     * the source {@code scanner} is reading from
     */
    protected abstract Grid createInstance(final Scanner scanner);    
    
    /** 
     * Checks if two arbitrary lines in the grid hold the same sequence of 
     * {@code Cell} objects. Useful for comparing expected lines with actual 
     * lines.
     */
    private boolean checkSimilar(
            final List<Cell> line1, final List<Cell> line2) {
        
        for (int i = 0; i != line1.size(); i ++) {
            if (line1.get(i) != line2.get(i)) {
                return false;
            }            
        }
        return true;
    }

   /**
    * Tests the constructor with a generic grid specification.
    */
    @Test
    public void testConstructor() {
        System.out.println("Grid");
        setInstance(correctSpec);
        assertTrue("isRepOk()", instance.isRepOk());
    }
    
   /**
    * Tests if the constructor throws the correct exception when given a false 
    * specification through a Scanner.     
    */
    @Test
    public void testConstructorForException() {
        System.out.println("Grid robustness");
        Class expected = IllegalArgumentException.class;
        try {
            setInstance(falseSpec);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        }
    }
    
    /**
     * Test of getRow method, of class Grid.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        setInstance(correctSpec);
        
        int index = 0;
        Cell c1 = instance.getCell(0, 0);
        Cell c2 = instance.getCell(1, 0);
        List<Cell> expResult = Arrays.asList(c1, c2);
        List<Cell> result = instance.getRow(index);
        assertTrue("row " + index, checkSimilar(expResult, result));
    }
    
    /**
     * Tests if getRow throws the correct exception when given a index that is 
     * out of range.
     */
    private void checkGetRowForException(final int index) {
        Class expected = IllegalArgumentException.class;
        try {
            instance.getRow(index);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        } 
    }    
    
    /**
     * Test for the robustness of getRow method, of class Grid
     */
    @Test
    public void testGetRowForException() {
        System.out.println("getRow robustness");
        setInstance(correctSpec);
        
        checkGetRowForException(-1);
        checkGetRowForException(4);
    }
    
    /**
     * Test of getColumn method, of class Grid.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        setInstance(correctSpec);
                
        int index = 1;
        Cell c1 = instance.getCell(index, 0);
        Cell c2 = instance.getCell(index, 1);
        Cell c3 = instance.getCell(index, 2);
        Cell c4 = instance.getCell(index, 3);
        List<Cell> expResult = Arrays.asList(c1, c2, c3, c4);
        List<Cell> result = instance.getColumn(index);
        assertTrue("column " + index, checkSimilar(expResult, result));
    }
    
    /**
     * Tests if getColumn throws the correct exception when given a index that is 
     * out of range.
     */
    private void checkGetColumnForException(final int index) {
        Class expected = IllegalArgumentException.class;
        try {
            instance.getColumn(index);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        } 
    }
    
    /**
     * Test for the robustness of getColumn method, of class Grid
     */
    @Test public void testGetColumnForException() {
        System.out.println("getColumn robustness");
        setInstance(correctSpec);
        
        checkGetColumnForException(-1);
        checkGetColumnForException(2);
    }
    
    /**
     * Test of getWidth method, of class Grid.
     */
    @Test 
    public void testGetWidth() {
        System.out.println("getWidth");
        
        setInstance(correctSpec);
        assertEquals("correctSpec", 2, instance.getWidth());
        
        setInstance(completeSpec);
        assertEquals("completeSpec", 4, instance.getWidth());
    }
    
    /**
     * Test of getHeight method, of class Grid.
     */
    @Test 
    public void testGetHeight() {
        System.out.println("getHeight");
        
        setInstance(correctSpec);
        assertEquals("correctSpec", 4, instance.getHeight());
        
        setInstance(completeSpec);
        assertEquals("completeSpec", 4, instance.getHeight());
    }
    
    /**
     * Test of getConstraints and setConstraints methods, of class Grid.
     */
    @Test 
    public void testGetAndSetConstraints() {
        System.out.println("getConstraints and setConstraints");
        setInstance(correctSpec);
        
        Constraint tripletConstraint = new BinaryPuzzleTripletConstraint();
        Constraint lineConstraint = new BinaryPuzzleLineConstraint();
        List<Constraint> constraints;
        
        constraints = Arrays.asList(tripletConstraint, lineConstraint);
        instance.setConstraints(constraints);
        assertEquals(constraints, instance.getConstraints());
        
        constraints = Arrays.asList(tripletConstraint);
        instance.setConstraints(constraints);
        assertEquals(constraints, instance.getConstraints());
        
        constraints = Arrays.asList(lineConstraint);
        instance.setConstraints(constraints);
        assertEquals(constraints, instance.getConstraints());
    }
    
    
    /**
     * Tests if checkCoordinates throws the correct exception when given an 
     * (x,y)-coordinate that is  out of range.
     */
    private void checkCheckCoordinatesForException(final int x, final int y) {
        Class expected = IllegalArgumentException.class;
        try {
            instance.checkCoordinates(x, y);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        } 
    }
    
    /**
     * Test for the robustness of checkCoordinates method, of class Grid
     */
    @Test public void testCheckCoordinatesForException() {
        System.out.println("checkCoordinates robustness");
        setInstance(correctSpec);
        
        checkCheckCoordinatesForException(-1, 0);
        checkCheckCoordinatesForException(-1, 3);
        checkCheckCoordinatesForException(0, -1);
        checkCheckCoordinatesForException(1, -1);
        checkCheckCoordinatesForException(0, 4);
        checkCheckCoordinatesForException(1, 4);
        checkCheckCoordinatesForException(2, 0);
        checkCheckCoordinatesForException(2, 3);
    }    
        
    /**
     * Test of getCell method, of class Grid.
     */
    @Test
    public void testGetCell() {
        System.out.println("isCellLocked");
        setInstance(correctSpec);
        
        int x = 1;
        int y = 2;
        Cell cell = instance.getCell(x, y);
        assertEquals("state", CellState.ONE, cell.getState());
        assertFalse("locked", cell.isLocked());
    }
    
    /**
     * Test of isCellLocked method, of class Grid.
     */
    @Test
    public void testIsCellLocked() {
        System.out.println("isCellLocked");
        setInstance(correctSpec);
        
        assertTrue(instance.isCellLocked(0, 1));
        assertTrue(instance.isCellLocked(1, 1));
        assertFalse(instance.isCellLocked(1, 2));
        assertFalse(instance.isCellLocked(1, 3));
    }

    /**
     * Test of setCellState method, of class Grid.
     */
    @Test
    public void testSetCellState() {
        System.out.println("setCellState");
        setInstance(correctSpec);
        
        CellState s1 = CellState.ONE;
        CellState s2 = CellState.EMPTY;
        instance.setCellState(0, 0, s1);
        instance.setCellState(1, 0, s2);        
        assertEquals(s1, instance.getCell(0, 0).getState());
        assertEquals(s2, instance.getCell(1, 0).getState());
    }
    
    /**
     * Test of getConstraintViolations method, of class Grid.
     */
    @Test
    public void testGetConstraintViolation() {
        System.out.println("getConstraintViolations");
        setInstance(correctSpec);       

        List<Violation> violations;
        Constraint tripletConstraint = new BinaryPuzzleTripletConstraint();
        Constraint lineConstraint = new BinaryPuzzleLineConstraint();
        List<Constraint> constraints;
        
        constraints = Arrays.asList(lineConstraint, tripletConstraint);
        instance.setConstraints(constraints);
        violations = instance.getConstraintViolations();
        checkViolationCount(violations, tripletConstraint, 1); 
        checkViolationCount(violations, lineConstraint, 2);         
        
        constraints = Arrays.asList(lineConstraint); 
        instance.setConstraints(constraints);
        violations = instance.getConstraintViolations(); 
        checkViolationCount(violations, tripletConstraint, 0); 
        checkViolationCount(violations, lineConstraint, 2); 
       
        constraints = Arrays.asList(tripletConstraint);
        instance.setConstraints(constraints);
        violations = instance.getConstraintViolations(); 
        checkViolationCount(violations, tripletConstraint, 1); 
        checkViolationCount(violations, lineConstraint, 0); 
       
        constraints = Arrays.asList();
        instance.setConstraints(constraints);
        violations = instance.getConstraintViolations(); 
        checkViolationCount(violations, tripletConstraint, 0); 
        checkViolationCount(violations, lineConstraint, 0); 
    }
        
    /**
     * Counts the amount of violations of constraints of type 
     * {@code constraint} in the given list of violations, and checks if this
     * complies with the given expected amount of violations.
     */
    private void checkViolationCount(
            final List<Violation> violations, 
            final Constraint constraint, 
            final int expViolations) {
        
        int violationCount = 0;        
        for (Violation v : violations) {
            if (v.getConstraint() == constraint) {
                violationCount ++;
            }
        }
        assertEquals("cell violation count", expViolations, violationCount);
    }
    
    /**
     * Test of getCellViolations method, of class Grid.
     */
    @Test
    public void testGetCellViolations() {
        System.out.println("getCellViolations");
        setInstance(correctSpec);
        
        Constraint tripletConstraint = new BinaryPuzzleTripletConstraint();
        Constraint lineConstraint = new BinaryPuzzleLineConstraint();
        List<Constraint> constraints;
        List<Constraint> violations;
        
        constraints = Arrays.asList(lineConstraint, tripletConstraint);
        instance.setConstraints(constraints);
        violations = instance.getCellViolations(0, 0);
        assertEquals(0, violations.size());        
        violations = instance.getCellViolations(1, 0);
        assertEquals(2, violations.size());        
        violations = instance.getCellViolations(1, 2);
        assertEquals(3, violations.size());
                
        constraints = Arrays.asList(lineConstraint);
        instance.setConstraints(constraints);
        violations = instance.getCellViolations(0, 0);
        assertEquals(0, violations.size());
        violations = instance.getCellViolations(1, 1);
        assertEquals(1, violations.size()); 
        violations = instance.getCellViolations(1, 2);
        assertEquals(2, violations.size());        
        violations = instance.getCellViolations(1, 3);
        assertEquals(0, violations.size());
        
        constraints = Arrays.asList(tripletConstraint);
        instance.setConstraints(constraints);
        violations = instance.getCellViolations(0, 2);
        assertEquals(0, violations.size());        
        violations = instance.getCellViolations(1, 0);
        assertEquals(1, violations.size());        
        violations = instance.getCellViolations(1, 1);
        assertEquals(1, violations.size());
        
        constraints = Arrays.asList();  
        instance.setConstraints(constraints);
        violations = instance.getCellViolations(1, 0);
        assertEquals(0, violations.size());        
        violations = instance.getCellViolations(1, 1);
        assertEquals(0, violations.size());        
        violations = instance.getCellViolations(1, 2);
        assertEquals(0, violations.size());        
    }
    
    /**
     * Test of getViolatingCells method, of class Grid.
     */
    @Test
    public void testGetViolatingCells() {
        System.out.println("isFull");

        Constraint tripletConstraint = new BinaryPuzzleTripletConstraint();
        Constraint lineConstraint = new BinaryPuzzleLineConstraint();
        List<Constraint> constraints;
        
        setInstance(correctSpec);
        constraints = Arrays.asList(tripletConstraint, lineConstraint);
        instance.setConstraints(constraints);        
        Cell c1 = instance.getCell(1, 0);
        Cell c2 = instance.getCell(1, 1);
        Cell c3 = instance.getCell(1, 2);
        Cell c4 = instance.getCell(0, 2);
        List<Cell> expViolatingCells = Arrays.asList(c1, c2, c3, c4);
        assertTrue("correctSpec", checkViolatingCellsList(expViolatingCells));       
    }
    
    /**
     * Checks whether the given list of cells holds the exact same elements as
     * the list of violating cells produced by the getViolatingCells method, of
     * class Grid.
     */
    private boolean checkViolatingCellsList(List<Cell> expViolatingCells) {
        List<Cell> violatingCells = instance.getViolatingCells();
        for (Cell c: violatingCells) {
            if (! expViolatingCells.contains(c)) {
                return false;
            }
        }
        for (Cell c: expViolatingCells) {
            if (! violatingCells.contains(c)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Test of isFull method, of class Grid.
     */
    @Test
    public void testIsFull() {
        System.out.println("isFull");  
        
        setInstance(correctSpec);
        assertFalse("correctSpec", instance.isFull());
        
        setInstance(completeSpec);
        assertTrue("completeSpec", instance.isFull());
    }
    
    /**
     * Test of isSolved method, of class Grid.
     */
    @Test
    public void testIsSolved() {
        System.out.println("isSolved");
        
        Constraint tripletConstraint = new BinaryPuzzleTripletConstraint();
        Constraint lineConstraint = new BinaryPuzzleLineConstraint();
        List<Constraint> constraints;
        
        setInstance(correctSpec);        
        constraints = Arrays.asList(lineConstraint, tripletConstraint);  
        instance.setConstraints(constraints);
        assertFalse(instance.isSolved());
        
        constraints = Arrays.asList(lineConstraint);
        instance.setConstraints(constraints);
        assertFalse(instance.isSolved());
        
        constraints = Arrays.asList(tripletConstraint);
        instance.setConstraints(constraints);
        assertFalse(instance.isSolved());
        
        
        setInstance(completeSpec);        
        constraints = Arrays.asList(lineConstraint, tripletConstraint);
        instance.setConstraints(constraints);
        assertFalse(instance.isSolved());
        
        constraints = Arrays.asList(lineConstraint);
        instance.setConstraints(constraints);
        assertFalse(instance.isSolved());
        
        constraints = Arrays.asList(tripletConstraint);
        instance.setConstraints(constraints);
        assertTrue(instance.isSolved());
    }   

    /**
     * Test of toString method, of class Grid.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        setInstance(correctSpec);
        
        String string = instance.toString();
        Scanner scanner = new Scanner(string);
        Grid copy = createInstance(scanner);        
        Cell cell, cellCopy;        
        for (int i = 0; i != instance.getWidth(); i ++) {
            for (int j = 0; j != instance.getHeight(); j ++) {
                cell = instance.getCell(i, j);
                cellCopy = copy.getCell(i, j);
                assertEquals("getState", cell.getState(), cellCopy.getState());
                assertEquals("isLocked", cell.isLocked(), cellCopy.isLocked());    
            }
        }
    }

    /**
     * Test of line method, of class Grid. Tests whether the collection 
     * that is iterated over, contains the correct elements.
     */
    @Test
    public void testLineIteratorCollection() {
        System.out.println("lines collection");
        setInstance(correctSpec);
        
        for (List<Cell> line : instance.lines()) {          
            
            boolean correctElement = false;
            for (int i = 0; i != instance.getHeight(); i ++) {
                if (checkSimilar(instance.getRow(i), line)) {
                    correctElement = true;
                }
            }
            for (int j = 0; j != instance.getWidth(); j ++) {
                if (checkSimilar(instance.getColumn(j), line)) {
                    correctElement = true;
                }
            }            
            assertTrue("false collection", correctElement);
        }
    }
    
    /**
     * Test of line method, of class Grid. Tests whether the collection 
     * that is iterated over, contains no duplicates.
     */
    @Test
    public void TestLineIteratorDuplicates() {
        System.out.println("lines duplicates");
        setInstance(correctSpec);
        List<List<Cell>> visited = new ArrayList();
        
        for (List<Cell> line : instance.lines()) {          
            for (List<Cell> visitedLine : visited) {
                assertFalse("duplicates", visitedLine == line);
            }
            visited.add(line);
        }
    }
    
    /**
     * Test of line method, of class Grid. Tests whether the collection 
     * that is iterated over, is of proper size.
     */
    @Test
    public void TestLineIteratorCount() {
        System.out.println("lines count");
        setInstance(correctSpec);
        List<List<Cell>> visited = new ArrayList();
        
        for (List<Cell> line : instance.lines()) {    
            visited.add(line);
        }
        
        int expCollectionSize = instance.getWidth() + instance.getHeight();
        assertEquals("lines collection size", 
                expCollectionSize, visited.size());
    }
    
    /**
     * Test of iterator method, of class Grid. Tests whether the 
     * collection that is iterated over, contains the correct elements.
     */
    @Test
    public void testCellIteratorCollection() {
        System.out.println("iterator collection");
        setInstance(correctSpec);
        
        for (Cell cell : instance) {                      
            boolean correctElement = false;
            
            for (int i = 0; i != instance.getWidth(); i ++) {
                for (int j = 0; j != instance.getHeight(); j ++) {
                    if (instance.getCell(i, j) == cell) {
                        correctElement = true;
                    }   
                }
            }              
            assertTrue("false collection", correctElement);
        }
    }
    
    /**
     * Test of iterator method, of class Grid. Tests whether the 
     * collection that is iterated over, contains no duplicates.
     */
    @Test
    public void TestCellIteratorDuplicates() {
        System.out.println("iterator duplicates");
        setInstance(correctSpec);
        List<Cell> visited = new ArrayList();
        
        for (Cell cell : instance) {          
            for (Cell visitedCell : visited) {
                assertFalse("duplicates", visitedCell == cell);
            }
            visited.add(cell);
        }
    }
    
    /**
     * Test of iterator method, of class Grid. Tests whether the 
     * collection that is iterated over, is of proper size.
     */
    @Test
    public void TestCellIteratorCount() {
        System.out.println("iterator count");
        setInstance(correctSpec);
        List<Cell> visited = new ArrayList();
        
        for (Cell cell : instance) {    
            visited.add(cell);
        }
        
        int expCollectionSize = instance.getWidth() * instance.getHeight();
        assertEquals("cells collection size", 
                expCollectionSize, visited.size());
    }
    
    /**
     * Test for the robustness of all iterators, of class Grid.
     */
    @Test
    public void testIteratorsForException() {
        System.out.println("iterators robustness");
        setInstance(correctSpec);
        
        Iterator<List<Cell>> lineIterator;      
        Iterator<Cell> cellIterator;
        lineIterator = instance.lines().iterator(); 
        cellIterator = instance.iterator();
        testIteratorForException(cellIterator);
        testIteratorForException(lineIterator);
    }    
    
    /**
     * Tests the robustness of a given Iterator.
     */
    private <C> void testIteratorForException(Iterator<C> iterator) {
        
        for (Iterator<C> iter = iterator; iter.hasNext(); ) { 
            if (! iter.hasNext()) {
                checkIteratorForException(iter);
            }
            iter.next();
        }
    }
    
    /**
     * Tests if the given Iterator throws the correct exception when hasNext 
     * does not hold.
     */
    private <C> void checkIteratorForException(final Iterator<C> iter) {
        Class expected = NoSuchElementException.class;
        try {
            iter.next();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        } 
    }

}

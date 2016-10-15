package bpa.solvers.commands;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases for abstract class {@code CellCommand}, to be extended 
 * to obtain concrete test cases for an extension of {@code CellCommand}.
 * 
 * @author iVerb
 * @since 23-3-13
 */
public abstract class CellCommandTestCases extends CommandTestCases {
    
    /**
     * Attempts to initialize a concrete {@code CellCommand} using parameteres
     * that violate the precondition of the constructor of {@code CellCommand}: 
     * {@code receiver == null}.
     */
    protected abstract void setNullInstance();
    
   /**
     * Test of constructor method for exceptions, of abstract class CellCommand.
     */
    @Test
    public void testCellCommandForExceptions() {
        System.out.println("CellCommand robustness");
        Class expected = NullPointerException.class;
        try {
            setNullInstance();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        }
    }
    
}

package bpa.solvers.commands;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases for {@code Command}, to be extended to obtain concrete
 * test cases for an extension of {@code Command}.
 *
 * @author iVerb
 * @since 22-3-13
 */
public abstract class CommandTestCases {

    /**
     * Test fixture.
     */
    protected Command instance;

    /**
     * Sets the testing instance of this class to a generic testing instance 
     * of a new (concrete) Command.
     */
    protected abstract void setGenericInstance();

    /**
     * Test of execute method for exceptions, of class Command.
     */
    @Test
    public void testExecuteForExceptions() {
        System.out.println("execute robustness");
        setGenericInstance();
        instance.execute();

        Class expected = IllegalStateException.class;
        try {
            instance.execute();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        }
    }

    /**
     * Test of undo method for exceptions, of class Command.
     */
    @Test
    public void testUndoForExceptions1() {
        System.out.println("undo robustness");
        setGenericInstance();
        
        Class expected = IllegalStateException.class;
        try {
            instance.undo();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        }
    }

    /**
     * Test of undo method for exceptions, of class Command.
     */
    @Test
    public void testUndoForExceptions2() {
        System.out.println("undo robustness");
        setGenericInstance();
        instance.execute();
        instance.undo();
        
        Class expected = IllegalStateException.class;
        try {
            instance.undo();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        }
    }
    
}

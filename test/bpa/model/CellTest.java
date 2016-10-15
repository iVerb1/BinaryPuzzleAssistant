package bpa.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for {@code Cell}.
 * 
 * @author iVerb
 * @since 15-3-13
 */
public class CellTest {
 
    /** Test fixture. */
    private  Cell instance;
    
    /**
     * Sets instance to a newly constructed Cell with the given parameters
     * 
     * @param state  the desired state for {@code instance}
     * @param locked  whether this Cell should be locked or not
     */
    private void setInstance(final CellState state, final boolean locked) {
        instance = new Cell(state, locked);
    }
    
   /** 
    * Tests the constructor with some generic parameters.
    */
    @Test
    public void testConstructor() {
        System.out.println("Cell");
        setInstance(CellState.ONE, true);
        assertEquals(CellState.ONE, instance.getState());
        assertTrue(instance.isLocked());
    }

   /**
    * Tests if the constructor throws the correct exception when a empty cell is 
    * instantiated that is locked;
    */
    @Test
    public void testConstructorForException() {
        System.out.println("Cell robustness");
        Class expected = IllegalArgumentException.class;
        try {
            final CellState state = CellState.EMPTY;
            final boolean locked = true;
            setInstance(state, locked);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        }
    }
    
    /**
     * Test of isLocked method, of class Cell.
     */
    @Test
    public void testIsLocked() {
        System.out.println("isLocked");
        setInstance(CellState.ONE, true);
        assertTrue(instance.isLocked());
        setInstance(CellState.ONE, false);
        assertFalse(instance.isLocked());
    }

    /**
     * Test of getState method, of class Cell.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        setInstance(CellState.ONE, false);
        assertEquals(CellState.ONE, instance.getState());
        setInstance(CellState.ZERO, false);
        assertEquals(CellState.ZERO, instance.getState());
        setInstance(CellState.EMPTY, false);
        assertEquals(CellState.EMPTY, instance.getState());
    }

    /**
     * Test of setState method, of class Cell.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        setInstance(CellState.ONE, false);
        
        instance.setState(CellState.ZERO);
        assertEquals(CellState.ZERO, instance.getState());       
        instance.setState(CellState.EMPTY);
        assertEquals(CellState.EMPTY, instance.getState());    
        instance.setState(CellState.ONE);
        assertEquals(CellState.ONE, instance.getState());  
    }
    
    /**
     * Tests if the setState method throws the correct exception when it is 
     * called when the cell is locked
     */
    @Test
    public void testSetStateForException() {
        Class expected = IllegalArgumentException.class;
        try {
            setInstance(CellState.ONE, true);
            instance.setState(CellState.ZERO);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        } 
    }
    
    /**
     * Test of setLocked method, of class Cell.
     */
    @Test
    public void testSetLocked() {
        System.out.println("setLocked");
        setInstance(CellState.ONE, false);
        
        instance.setLocked(true);
        assertTrue(instance.isLocked());       
        instance.setLocked(false);
        assertFalse(instance.isLocked());   
    }
    
    /**
     * Tests if the setLocked method throws the correct exception when it is 
     * called when the cell is locked
     */
    @Test
    public void testSetLockedForException() {
        Class expected = IllegalArgumentException.class;
        try {
            setInstance(CellState.EMPTY, false);
            instance.setLocked(true);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type; " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be empty", e.getMessage());
        } 
    }

    /**
     * Test of toString method, of class Cell.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        setInstance(CellState.ZERO, true);
        assertEquals("0", instance.toString());
        setInstance(CellState.ONE, true);
        assertEquals("1", instance.toString());
        setInstance(CellState.ZERO, false);
        assertEquals("0*", instance.toString());
        setInstance(CellState.ONE, false);
        assertEquals("1*", instance.toString());
        setInstance(CellState.EMPTY, false);
        assertEquals(".", instance.toString());
    }
    
}
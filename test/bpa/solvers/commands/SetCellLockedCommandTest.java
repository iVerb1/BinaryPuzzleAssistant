package bpa.solvers.commands;

import bpa.model.Cell;
import bpa.model.CellState;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for {@code SetCellLockedCommand}.
 * 
 * @author iVerb
 * @since 23-3-13
 */
public class SetCellLockedCommandTest extends CellCommandTestCases {

    @Override
    public void setGenericInstance() {
        Cell cell = new Cell(CellState.ONE, false);
        instance = new SetCellLockedCommand(cell, true);
    }
    
    @Override
    public void setNullInstance() {
        instance = new SetCellLockedCommand(null, false);
    }  
        
    /**
     * Test of execute method, of class SetCellLockedCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        final Cell cell = new Cell(CellState.ONE, true);
        final boolean newLockedState = false;
        instance = new SetCellLockedCommand(cell, newLockedState);
        instance.execute();
        assertEquals("After execute()", newLockedState, cell.isLocked());
    }

    /**
     * Test of undo method, of class SetCellLockedCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        final Cell cell = new Cell(CellState.ZERO, true);      
        final boolean newLockedState = false;
        final boolean oldLockedState = cell.isLocked();
        
        instance = new SetCellLockedCommand(cell, newLockedState);
        instance.execute();
        instance.undo();
        
        assertEquals("After execute() and undo()", oldLockedState, cell.isLocked());                
    }
    
}

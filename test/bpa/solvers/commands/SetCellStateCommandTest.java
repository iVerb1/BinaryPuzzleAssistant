package bpa.solvers.commands;

import bpa.model.Cell;
import bpa.model.CellState;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for {@code SetCellStateCommand}.
 *
 * @author iVerb
 * @since 23-3-13
 */
public class SetCellStateCommandTest extends CellCommandTestCases {

    @Override
    public void setGenericInstance() {
        Cell cell = new Cell(CellState.ZERO, false);
        instance = new SetCellStateCommand(cell, CellState.EMPTY);
    }

    @Override
    public void setNullInstance() {
        instance = new SetCellStateCommand(null, CellState.ONE);
    }

    /**
     * Test of execute method, of class SetCellStateCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        final Cell cell = new Cell(CellState.ONE, false);
        final CellState newCellState = CellState.ZERO;
        
        instance = new SetCellStateCommand(cell, newCellState);
        instance.execute();
        
        assertEquals("After execute()", newCellState, cell.getState());
    }

    /**
     * Test of undo method, of class SetCellStateCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        final Cell cell = new Cell(CellState.EMPTY, false);       
        final CellState newCellState = CellState.ONE;
        final CellState oldCellState = cell.getState();
        
        instance = new SetCellStateCommand(cell, newCellState);
        instance.execute();
        instance.undo();
        
        assertEquals("After execute() and undo()", oldCellState, cell.getState());
    }
    
}

package bpa.solvers.commands;

import bpa.model.Cell;
import bpa.model.CellState;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for {@code CompoundCommand}.
 *
 * @author iVerb
 * @since 22-3-13
 */
public class CompoundCommandTest extends CommandTestCases {

    @Override
    protected void setGenericInstance() {
        CellCommand c1, c2, c3;
        Cell cell = new Cell(CellState.ONE, true);

        c1 = new SetCellLockedCommand(cell, false);
        c2 = new SetCellStateCommand(cell, CellState.ZERO);
        c3 = new SetCellLockedCommand(cell, true);

        instance = new CompoundCommand();
        ((CompoundCommand) instance).add(c1);
        ((CompoundCommand) instance).add(c2);
        ((CompoundCommand) instance).add(c3);
    }

    /**
     * Test of add method, of class CompoundCommand.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        CellCommand c1, c2;
        Cell cell = new Cell(CellState.ONE, false);

        CellState newCellState = CellState.ZERO;
        c1 = new SetCellStateCommand(cell, newCellState);
        instance = new CompoundCommand();
        ((CompoundCommand) instance).add(c1);
        boolean oldLockedState = cell.isLocked();
        instance.execute();
        assertEquals("after single addition", cell.getState(), newCellState);
        assertEquals("after single addition", cell.isLocked(), oldLockedState);

        instance.undo();

        boolean newLockedState = true;
        c2 = new SetCellLockedCommand(cell, newLockedState);
        ((CompoundCommand) instance).add(c2);
        instance.execute();
        assertEquals("after two additions", cell.getState(), newCellState);
        assertEquals("after two additions", cell.isLocked(), newLockedState);
    }

    /**
     * Test of execute method, of class CompoundCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        CellCommand c1, c2;
        Cell cell = new Cell(CellState.EMPTY, false);

        CellState newCellState = CellState.ONE;
        boolean newLockedState = true;
        c1 = new SetCellStateCommand(cell, newCellState);
        c2 = new SetCellLockedCommand(cell, newLockedState);

        instance = new CompoundCommand();
        ((CompoundCommand) instance).add(c1);
        ((CompoundCommand) instance).add(c2);
        instance.execute();
        assertEquals("After execute()", cell.getState(), newCellState);
        assertEquals("After execute()", cell.isLocked(), newLockedState);
    }
    
    /**
     * Test of undo method, of class CompoundCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        CellCommand c1, c2;
        Cell cell = new Cell(CellState.ONE, false);

        CellState newCellState = CellState.ZERO;
        boolean newLockedState = true;
        CellState oldCellState = cell.getState();
        boolean oldLockedState = cell.isLocked();
        c1 = new SetCellStateCommand(cell, newCellState);
        c2 = new SetCellLockedCommand(cell, newLockedState);

        instance = new CompoundCommand();
        ((CompoundCommand) instance).add(c1);
        ((CompoundCommand) instance).add(c2);
        instance.execute();
        instance.undo();

        assertEquals("After execute() and undo()", cell.getState(), oldCellState);
        assertEquals("After execute() and undo()", cell.isLocked(), oldLockedState);
    }
    
}

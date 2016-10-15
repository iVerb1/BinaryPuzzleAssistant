package bpa.solvers;

import bpa.model.Cell;
import bpa.model.CellState;
import bpa.model.Grid;
import bpa.solvers.commands.CellCommand;
import bpa.solvers.commands.Command;
import bpa.solvers.commands.SetCellStateCommand;

/**
 * A concretization of {@code Backtracker} that continues backtracking until a
 * first solution is found, on which it will leave the solution in the puzzle 
 * grid. The solution is not notified to any registred observers.
 * 
 * @author iVerb
 * @since 4-4-13
 */
public class SingleSolver extends Backtracker {
    
    /** Whether or not this backtracker has already found a solution. */
    private boolean solved;
    
    public SingleSolver(final Grid grid) {
        super(grid);
        solved = false;
    }
    
    @Override
    public void solve() throws InterruptedException {

        if (aborted) {
            throw new InterruptedException("SingleSolver.solve: execution "
                    + "aborted.");
        }

        Command strategyCommand = helperStrategy.apply(grid);

        if (! grid.getConstraintViolations().isEmpty()) {
            strategyCommand.undo();
            solved = false;
            return;
        } 
        else if (grid.isFull()) {
            solved = true;
            return;
        }

        Cell openCell = findOpenCell();
        CellCommand speculationCommand;

        speculationCommand = new SetCellStateCommand(openCell, CellState.ZERO);
        speculationCommand.execute();
        solve();
        if (solved) {
            return;
        }
        speculationCommand.undo();

        speculationCommand = new SetCellStateCommand(openCell, CellState.ONE);
        speculationCommand.execute();
        solve();
        if (solved) {
            return;
        }
        speculationCommand.undo();

        strategyCommand.undo();
        solved = false;
    }
    
}
package bpa.solvers;

import bpa.model.Cell;
import bpa.model.CellState;
import bpa.model.Grid;
import bpa.solvers.commands.CellCommand;
import bpa.solvers.commands.Command;
import bpa.solvers.commands.SetCellStateCommand;

/**
 * A concretization of {@code Backtracker} that continues backtracking until all
 * possibilities (speculative steps) have been exhausted. After exhausting all
 * possiblities, the grid returns to its state of before the top level call to 
 * {@code solve()} was made. All found solutions are notified to any registred 
 * observers.
 * 
 * @author iVerb
 * @since 4-4-13
 */
public class CompleteSolver extends Backtracker {

    public CompleteSolver(final Grid grid) {
        super(grid);
    }

    @Override
    public void solve() throws InterruptedException {

        if (aborted) {
            throw new InterruptedException("CompleteSolver.solve: execution "
                    + "aborted.");
        }

        Command strategyCommand = helperStrategy.apply(grid);

        if (! grid.getConstraintViolations().isEmpty()) {
            strategyCommand.undo();
            return;
        } 
        else if (grid.isFull()) {
            notifyObservers(grid.toString());
            setChanged();
            strategyCommand.undo();
            return;
        }

        Cell openCell = findOpenCell();
        CellCommand speculationCommand;

        speculationCommand = new SetCellStateCommand(openCell, CellState.ZERO);
        speculationCommand.execute();
        solve();
        speculationCommand.undo();

        speculationCommand = new SetCellStateCommand(openCell, CellState.ONE);
        speculationCommand.execute();
        solve();
        speculationCommand.undo();

        strategyCommand.undo();
    }
    
}

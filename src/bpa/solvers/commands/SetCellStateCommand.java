package bpa.solvers.commands;

import bpa.model.Cell;
import bpa.model.CellState;

/**
 * A command, that sets the state of the receiving {@code Cell} to a given
 * {@code CellState}.
 *
 * @author iVerb
 * @since 23-3-13
 */
public class SetCellStateCommand extends CellCommand {

    /**
     * The old CellState of the receiver before executon of this command.
     */
    private CellState oldCellState;
    
    /**
     * The desired new CellState of the receiver after execution of this
     * command.
     */
    private final CellState newCellState;

    /**
     * Constructs a {@code SetCellStateCommand} for a given receiving cell.
     *
     * @param receiver the receiver of this command
     * @param newCellState the desired new {@code CellState} of the receiver 
     * cell
     */
    public SetCellStateCommand(
            final Cell receiver, final CellState newCellState) {
        super(receiver);
        this.newCellState = newCellState;
    }

    @Override
    public void execute() {
        super.execute();
        oldCellState = receiver.getState();
        receiver.setState(newCellState);
    }

    @Override
    public void undo() {
        super.undo();
        receiver.setState(oldCellState);
    }
}

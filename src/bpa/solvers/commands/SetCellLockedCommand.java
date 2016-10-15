package bpa.solvers.commands;

import bpa.model.Cell;

/**
 * A command, that sets the locked-state of the receiving {@code Cell} to a 
 * given boolean value.
 * 
 * @author iVerb
 * @since 23-3-13
 */
public class SetCellLockedCommand extends CellCommand {

    /** 
     * The old locked-state of the receiver cell before executon of this 
     * command. 
     */
    private boolean oldLockedState;
    
    /** 
     * The desired new locked-state of the receiver cell after execution of this 
     * command. 
     */
    private final boolean newLockedState;   
    
    /**
     * Constructs a {@code SetCellLockedCommand} for a given receiving cell.
     * 
     * @param receiver  the receiver of this command
     * @param newLockedState the desired new locked-state of the receiver cell
     */
    public SetCellLockedCommand(
            final Cell receiver, final boolean newLockedState) {
        super(receiver);
        this.newLockedState = newLockedState;
    }
    
    @Override
    public void execute() {
        super.execute();
        oldLockedState = receiver.isLocked();
        receiver.setLocked(newLockedState);
    }

    @Override
    public void undo() {
        super.undo();
        receiver.setLocked(oldLockedState);
    }
    
}

package bpa.solvers.commands;

import bpa.model.Cell;

/**
 * A generic command that operates on a receiving cell. To be extended by a 
 * concrete command.
 *
 * @author iVerb
 * @since 23-3-13
 */
public abstract class CellCommand extends Command {

    /**
     * The receiving Cell.
     */
    protected final Cell receiver;

    /**
     * Constructs a {@code CellCommand} for a given receiving cell.
     *
     * @param receiver the receiver of this command
     * @pre {@code receiver != null}
     * @throws NullPointerException  if {@code receiver == null}
     */
    public CellCommand(final Cell receiver) throws NullPointerException {
        if (receiver == null) {
            throw new NullPointerException(
                    "CellCommand.CellCommand.pre violated: "
                    + "receiver == null");
        }
        this.receiver = receiver;
    }
    
}

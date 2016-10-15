package bpa.solvers.commands;

/**
 * Base class to represent an executable and undoable command.
 * Concrete command classes extend this base class,
 * by adding parameters (when needed), undo state (when needed), and
 * overriding execute() and undo().
 * 
 * @author iVerb
 * @since 22-3-13
 */
public abstract class Command {
        
    /** Execution state. */
    private boolean executed;
    
    /**
     * Executes the command.
     * A concrete command will override this method.
     * 
     * @throws IllegalStateException  if {@code executed}
     * @pre {@code ! executed && }
     *   precondition of the command holds in the receiver
     * @post {@code executed}
     */
    public void execute() throws IllegalStateException {
        if (executed) {
            throw new IllegalStateException("Command.execute().pre violated: "
                    + "command was already executed");
        }
        executed = true;
    }
    
    /**
     * Undoes the command.
     * A concrete command will override this method.
     *
     * @pre {@code executed && }
     *   precondition of the undo holds in the receiver
     * @post {@code ! executed}
     */
    public void undo() throws IllegalStateException {
        if (! executed) {
            throw new IllegalStateException("Command.undo().pre violated: "
                    + "command was not yet executed");
        }
        executed = false;
    }
    
}

package bpa.solvers.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * A compound command, capable of executing and undoing multiple {@code Command}
 * objects in a particular order.
 * 
 * @author iVerb
 * @since 24-3-13
 */
public class CompoundCommand extends Command {
    
    /**
     * The ordered list of commands to be executed in order upon execution of
     * this compound command.
     */
    private List<Command> commands;
    
    public CompoundCommand() {
        commands = new ArrayList<Command>();
    }

    /**
     * Adds the given command to the list of compounded commands.
     * 
     * @param command  The command to be added to the list of compounded 
     * commands.
     * @post the given command is appended at the end of the list of commands
     * to be executed by this compound command.
     */
    public void add(Command command) {
        commands.add(command);
    }
    
    @Override
    public void execute() {
        super.execute();
        for (int i = 0; i != commands.size(); i ++) {
            commands.get(i).execute();
        }
    }
    
    @Override
    public void undo() {
        super.undo();
        for (int i = commands.size() - 1; i >= 0; i --) {
            commands.get(i).undo();
        }
    }

}

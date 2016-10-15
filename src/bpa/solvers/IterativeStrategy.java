package bpa.solvers;

import bpa.model.Grid;
import bpa.solvers.commands.Command;
import bpa.solvers.commands.CompoundCommand;
import java.util.Stack;

/**
 * A concrete decorator of {@code Strategy} that exhausts the possibility of
 * applying the decorated strategy by iteratively applying the decorated
 * strategy until no more applications can be found. This is accomplised by
 * replacing the functionality provided by {@code apply(Grid)}.
 *
 * @author iVerb
 * @since 26-3-13
 */
public class IterativeStrategy extends StrategyDecorator {

    public IterativeStrategy(final Strategy decoratedStrategy) {
        super(decoratedStrategy);
    }

    /**
     * Applies the decorated strategy on the given {@code Grid} until no further
     * changes occur on that grid. This is accomplised by retrying the
     * application of the decorated strategy until {@code findApplication()}
     * returns {@code false}, while collecting all applications in a
     * {@code CompoundCommand} object. Afterwards, all applications are undone,
     * and the compounded command containing all collected applications is
     * executed and returned.
     *
     * @return the compounded command containing all accomplised applications of
     * the decorated strategy.
     */
    @Override
    public Command apply(final Grid grid) {
        setGrid(grid);
        Stack<Command> applicationStack = new Stack<Command>();        
        CompoundCommand compoundCommand = new CompoundCommand();
        boolean change = true;
        
        while (change) {
            change = false;
            if (super.findApplication()) {
                Command application = super.getApplication();
                application.execute();
                applicationStack.push(application);
                compoundCommand.add(application);
                change = true;
            }
        }
        for (Command application : applicationStack) {
            application.undo();
        }
        compoundCommand.execute();
        return compoundCommand;
    }
    
}

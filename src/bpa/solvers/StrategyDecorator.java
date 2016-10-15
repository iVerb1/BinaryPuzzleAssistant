package bpa.solvers;

import bpa.model.Grid;
import bpa.solvers.commands.Command;

/**
 * A standard decorator to be used to decorate {@code Strategy}.
 *
 * @author iVerb
 * @since 3-4-13
 */
public abstract class StrategyDecorator extends Strategy {

    /**
     * The strategy that is decorated.
     */
    protected final Strategy decoratedStrategy;

    /**
     * Sets up this decorator such that it decorates the given {@code Strategy}.
     *
     * @param decoratedStrategy The {@code Strategy} to be decorated by this
     * decorator
     * @modifies decoratedStrategy 
     */
    public StrategyDecorator(final Strategy decoratedStrategy) {
        this.decoratedStrategy = decoratedStrategy;
    }

    @Override
    public void setGrid(final Grid grid) {
        decoratedStrategy.setGrid(grid);
    }

    @Override
    public Grid getGrid() {
        return decoratedStrategy.getGrid();
    }

    @Override
    public void setApplication(final Command command) {
        decoratedStrategy.setApplication(command);
    }

    @Override
    public Command getApplication() {
        return decoratedStrategy.getApplication();
    }

    @Override
    public Command apply(final Grid grid) {
        return decoratedStrategy.apply(grid);
    }

    @Override
    public boolean findApplication() {
        return decoratedStrategy.findApplication();
    }
    
}

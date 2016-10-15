package bpa.solvers;

import bpa.model.Grid;
import java.util.ArrayList;
import java.util.List;

/**
 * A composite strategy to be used if the application of several strategies 
 * using a single call to {@code apply()} is desired.
 *
 * @author iVerb
 * @since 26-3-13
 */
public class CompositeStrategy extends Strategy {

    /**
     * The list of composed strategies to be applied by this composite strategy.
     */
    private List<Strategy> strategies;
    
    public CompositeStrategy() {
        this.strategies = new ArrayList<Strategy>();
    }

    /**
     * Adds the given strategy to the list of composed strategies.
     * 
     * @param strategy  The strategy to be added to the list of composed 
     * strategies.
     * @post the given strategy is appended at the end of the list of strategies
     * to be applied by this composite strategy.
     */
    public void add(Strategy strategy) {
        strategies.add(strategy);
    }
    
    /**
     * Sets the {@code Grid} object to be used by this strategy by setting the
     * grid of all composed strategies to this {@code Grid}.
     */
    @Override
    public void setGrid(final Grid grid) {
        super.setGrid(grid);
        for (Strategy strategy : strategies) {
            strategy.setGrid(grid);
        }
    }

    /**
     * Finds a single application among all composed strategies and if some 
     * strategy {@code X} in this list finds some application {@code Y}, then 
     * the application of {@code this} is set to application {@code Y}.
     */
    @Override
    public boolean findApplication() {
        for (Strategy strategy : strategies) {
            if (strategy.findApplication()) {
                setApplication(strategy.getApplication());
                return true;
            }
        }
        return false;
    }
   
}
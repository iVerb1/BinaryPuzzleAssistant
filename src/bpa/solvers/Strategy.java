package bpa.solvers;

import bpa.model.Grid;
import bpa.solvers.commands.Command;
import bpa.solvers.commands.CompoundCommand;

/**
 * A strategy used to intelligently find and apply forced cell changes on a 
 * certain {@code Grid}. Can be decorated using a {@code StrategyDecorator} to
 * decorate and change the functionality provided by this class.
 *
 * @author iVerb
 * @since 3-4-13
 */
public abstract class Strategy {

    /**
     * The grid that this strategy is applied to.
     */
    protected Grid grid;
    
    /**
     * The application that is (possibly) found using this strategy.
     */
    private Command application;

    /**
     * Sets the {@code Grid} object to be used by this strategy.
     * 
     * @param grid  the desired {@code Grid} to be used by this strategy   
     * @modifies grid 
     */
    public void setGrid(final Grid grid) {
        this.grid = grid;
    }

    /**
     * Gets the grid that is used by this strategy.
     * 
     * @return the {@code Grid} object that is used by this strategy
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Sets the found application of this strategy to the given {@code Command}.
     * This method is to be used if a call to {@code findApplication()} results
     * in finding an application of this strategy.
     * 
     * @pre {@code application != null}
     * @param application  the found application in the form of a 
     * {@code Command} object.
     * @throws NullPointerException  if {@code application == null}
     */
    protected void setApplication(final Command application) throws NullPointerException {
        if (application == null) {
            throw new NullPointerException("Strategy.setApplication.pre "
                    + "violated: application command == null");
        }
        this.application = application;
    }

    /**
     * Gets the found application of this strategy. Should only be used after
     * {@code findApplication()} has been called and returned {@code true}.
     * 
     * @pre This method is called after {@code findApplication()} and 
     * {@code findApplication() == true}
     * @return  the found application of this strategy in the form of a 
     * {@code Command} object.
     * @throws IllegalStateException  if this is called before 
     * {@code findApplication()} || {@code findApplication() == false}
     */
    public Command getApplication() throws IllegalStateException {
        if (application == null) {
            throw new IllegalStateException("Strategy.getApplication.pre "
                    + "violated: getApplication() called before find().");
        }
        return application;
    }

    /**
     * Applies this strategy and returns the application found. Without
     * decoration of this strategy, this method first sets this strategy's grid 
     * using {@code setGrid(Grid)} and then determines whether a single
     * application can be found using {@code findApplication()}, and if so, 
     * returns this application encapsulated in a {@code CompoundCommand}. If no
     * application can be found, an empty {@code CompoundCommand} is returned.
     * 
     * @param grid  the {@code Grid} in which this strategy should search for 
     * application and on which it should apply any found application.
     * @return  a {@code CompoundCommand} object which encapsulates any found 
     * application of this strategy (in the form of a {@code Command} object)
     */
    public Command apply(final Grid grid) {
        setGrid(grid);
        CompoundCommand compoundCommand = new CompoundCommand();
        if (findApplication()) {
            compoundCommand.add(getApplication());
        }
        compoundCommand.execute();
        return compoundCommand;
    }

    /**
     * Finds if this strategy can be applied on the grid and returns 
     * {@code true} if it is and sets the found application of this strategy 
     * (through {@code setApplication(Command)}) such that it can be used upon 
     * calling {@code apply(Grid)}. Note that this method is required to call 
     * {@code setApplication(Command)} upon finding an application and should 
     * only be called after {@code setGrid(Grid)}.
     * 
     * @return whether an application is found in the grid or not.
     */
    public abstract boolean findApplication();
    
}

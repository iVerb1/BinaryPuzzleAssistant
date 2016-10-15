package bpa.solvers;

import bpa.model.Cell;
import bpa.model.CellState;
import bpa.solvers.commands.Command;
import bpa.solvers.commands.SetCellStateCommand;
import java.util.List;

/**
 * The strategy that applies the following concept: if three adjacent cells 
 * somewhere in the grid contain two equal symbols (that are not empty), then 
 * the third cell, if empty, must be ﬁlled by the other symbol.
 *
 * @author iVerb
 * @since 1-4-13
 */
public class TripletStrategy extends Strategy {

    @Override
    public boolean findApplication() {
        Cell c1, c2, c3;
        for (List<Cell> line : grid.lines()) {
            for (int i = 0; i != line.size() - 2; i ++) {
                c1 = line.get(i);
                c2 = line.get(i + 1);
                c3 = line.get(i + 2);
                if (findApplicatoryCell(c1, c2, c3)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Inspects the given triplet to find a cell on which this strategy can be
     * applied and returns true if it is found, or false, if it is not.
     */
    private boolean findApplicatoryCell(
            final Cell c1, final Cell c2, final Cell c3) {
        if (isCellApplicatory(c1, c2, c3)) {
            return true;
        } 
        else if (isCellApplicatory(c1, c3, c2)) {
            return true;
        } 
        else if (isCellApplicatory(c2, c3, c1)) {
            return true;
        }
        return false;
    }

    /**
     * Determines whether the given target cell in the given triplet is forced 
     * to a given state by the other two cells, and if so, the corresponding 
     * boolean is returned and the application command is set to the resulting
     * cell change.
     */
    private boolean isCellApplicatory(
            final Cell c1, final Cell c2, final Cell targetCell) {
        CellState s1 = c1.getState();
        CellState s2 = c2.getState();
        CellState targetState = targetCell.getState();

        if (s1 == s2 && s1 != CellState.EMPTY && targetState == CellState.EMPTY) {            
            CellState newCellState = toggleState(s1);
            Command command = new SetCellStateCommand(targetCell, newCellState);
            setApplication(command);
            return true;
        }
        return false;
    }

    /**
     * Toggles the given state and returns the result, given that it is not 
     * empty. To be more precise:
     * - CellState.ZERO toggles to CellState.ONE
     * - CellState.ONE toggles to CellState.ZERO
     * 
     * @pre {@code state == CellState.ZERO || state == CellState.ONE}
     * @throws IllegalArgumentException  if {@code state != CellState.ZERO && 
     * state != CellState.ONE}
     */
    private CellState toggleState(final CellState state) throws IllegalArgumentException {
        switch (state) {
            case ZERO:
                return CellState.ONE;
            case ONE:
                return CellState.ZERO;
            default:
                throw new IllegalArgumentException(
                        "AdjacencyStrategy.toggleState.pre violated: "
                        + "illegal CellState: " + state);
        }
    }
    
}

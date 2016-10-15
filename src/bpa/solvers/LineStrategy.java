package bpa.solvers;

import bpa.model.Cell;
import bpa.model.CellState;
import bpa.solvers.commands.Command;
import bpa.solvers.commands.CompoundCommand;
import bpa.solvers.commands.SetCellStateCommand;
import java.util.ArrayList;
import java.util.List;

/**
 * The strategy that applies the following concept: If half of the cells in a
 * line in the grid contain equal symbols, then the remaining empty cells must
 * be ﬁlled by the other symbol.
 *
 * @author iVerb
 * @since 26-3-13
 */
public class LineStrategy extends Strategy {

    @Override
    public boolean findApplication() {
        for (List<Cell> line : grid.lines()) {
            List<Cell> emptyCells = new ArrayList<Cell>();
            int zeroes = 0;
            int ones = 0;
            for (Cell cell : line) {
                if (cell.getState() == CellState.EMPTY) {
                    emptyCells.add(cell);
                }
                if (cell.getState() == CellState.ZERO) {
                    zeroes ++;
                }
                if (cell.getState() == CellState.ONE) {
                    ones ++;
                }
            }
            if (zeroes == (line.size() / 2) && emptyCells.size() > 0) {
                CompoundCommand compoundCommand = buildCommand(emptyCells, CellState.ONE);
                setApplication(compoundCommand);
                return true;
            }
            if (ones == (line.size() / 2) && emptyCells.size() > 0) {
                CompoundCommand compoundCommand = buildCommand(emptyCells, CellState.ZERO);
                setApplication(compoundCommand);
                return true;
            }
        }
        return false;
    }

    /**
     * Builds the {@code CompoundCommand} that defines a cell-state-change of
     * all cells in cells to newCellState.
     */
    private CompoundCommand buildCommand(
        final List<Cell> cells, final CellState newCellState) {        
        CompoundCommand compoundCommand = new CompoundCommand();
        for (Cell emptyCell : cells) {
            Command command = new SetCellStateCommand(emptyCell, newCellState);
            compoundCommand.add(command);
        }
        return compoundCommand;
    }
    
}

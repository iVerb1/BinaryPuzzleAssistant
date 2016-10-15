/**
 * Package containing commands that can be executed and undone on the 
 * puzzle grid, used to facilitate the undo/redo facility. Package contains 
 * simple cell operations such as {@code SetCellLockedCommand}, and 
 * {@code SetCellStateCommand}. Also, it is possible to execute and undo a list
 * of compounded commands using the {@code CompoundCommand} class.
 * 
 * <p>
 * <b> Progress of the functionalities: </b>
 * <ul> 
 *  <li> Compound command: 100%
 *  <li> Command for changing the {@code CellState} of a cell: 100%
 *  <li> Command for changing the locked-state of a cell: 100%
 * </ul>
 * 
 * This package is complete and fully tested.
 * 
 * @author iVerb
 * @since 24-3-13
 */
package bpa.solvers.commands;

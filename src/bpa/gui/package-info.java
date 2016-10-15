/**
 * The GUI used for the binary puzzle assistant, including the view and the 
 * controller. The top level view is represented by a {@code MainFrame} object, 
 * which extends {@code JFrame}. This {@code MainFrame} is also responsible for 
 * handling all user generated events. Each {@code MainFrame} contains a 
 * {@code PuzzlePanel} (the view), which is an extension of {@code JPanel} and 
 * it visualizes the puzzle grid at runtime, and hence also holds a reference to 
 * the model ({@code Grid}) used.
 * 
 * <p>
 * <b> Development status: </b>
 * <ul> 
 *  <li> Opening and loading a puzzle state: 100%
 *  <li> Saving a puzzle state: 100%
 *  <li> Exiting the application: 100%
 *  <li> Cycling cell contents by point-and-click: 100%
 *  <li> Reverse cycling cell contents by point-and-click: 100%
 *  <li> Undoing a single cell change: 100%
 *  <li> Undoing all cell changes: 100%
 *  <li> Redoing a single undone cell change: 100%
 *  <li> Redoing all undone cell changes: 100%
 *  <li> Checking for rule violations using the `Check'-button: 100%
 *  <li> Enabling/disabling edit mode: 100%
 *  <li> Applying solving strategies: 100%
 *  <li> Possibility to run a backtracking algorithm in a background thread:
 *       100%
 *  <li> Applying a backtracking algorithm for finding a single solution: 100%
 *  <li> Applying a backtracking algorithm for finding all solutions: 100%
 *  <li> Functionality to abort execution of any backtracker at will: 100%
 *  <li> Resizing {@code MainFrame} to fit the model after loading a new puzzle 
 *       state: 100%
 * 
 *  <li> Visualizing the contents of cells in the puzzle grid: 100%
 *  <li> Visualizing the locked state of cells in the puzzle grid: 100%
 *  <li> Visualizing rule violations of cells in the puzzle grid: 100%
 *  <li> User feedback upon solving a puzzle: 100%
 *  <li> Visualizing the solutions found when searching for all solutions: 100%
 * </ul>
 * 
 * @author iVerb
 * @since 4-4-13
 */
package bpa.gui;

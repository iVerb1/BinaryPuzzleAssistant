package bpa.gui;

import org.junit.Test;

/**
 * A list of test scenarios for the controller of the Binary Puzzle Assistent
 * that can be executed manually by the user. These scenarios test the
 * functionality provided by user interactions with the GUI.
 * 
 * @author iVerb
 * @since 4-4-13
 */
public class GUITest {
    
    /*
     * --------------------------- TEST SCENARIOS: -----------------------------
     * 
     * - Open a simple puzzle state, and solve it manually.
     * - Open two puzzle states (with grids of different size)in sequence, and 
     *   exit the application afterwards.
     * - Open a puzzle state, make some cell changes, and save it under the same 
     *   name. Afterwards, re-open this puzzle state.
     * - Modify some cells in the grid such that there are some constraint 
     *   violations, and check for constraint violations.
     * - Enable edit mode, edit some cells, and disable edit mode.
     * - Modify some cells, undo all cell changes one by one, and redo all of 
     *   them at once.
     * - Apply some simple strategy a few times. Afterwards, select a different
     *   strategy, and apply it iteratively. Undo and redo all strategies 
     *   applied. Verify that it is possible to undo until before the initial 
     *   simple strategy was applied for the first time.
     * - Open a puzzle state which has only one solution, and request a single 
     *   solution. Re-open the puzzle state and request all solutions.
     * - Open a puzzle state which has several solutions, and request a single 
     *   solution. Re-open the puzzle state and request all solutions. After
     *   requesting all solutions, abort solving after a few solutions have been
     *   found.
     * 
     * -------------------------------------------------------------------------
     */
    
    /**
     * Exists only to avoid exceptions of occuring when running this test class.
     */
    @Test
    public void testDummy() {
    }
    
}

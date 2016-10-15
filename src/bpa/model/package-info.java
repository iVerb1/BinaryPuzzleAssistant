/**
 * The model used for the Binary Puzzle Assistant, containing functionality to 
 * maintain a grid and its cells and checking for rule violations. Each 
 * concretization of {@code Grid} represents a rectangular grid of 
 * {@code Cell} objects, which have a state represented by a {@code CellState} 
 * object. Each  {@code Violation} object represents a single violation of a 
 * single constraint on some {@code Grid} object by some list of 
 * {@code Cell} objects. This constraint is represented as a class that 
 * implements the {@code Constraint} interface.
 * 
 * <p>
 * <b> Development status: </b>
 * <ul>
 * <li> Loading a puzzle: 100%
 * <li> Converting puzzle to {@code String}: 100%
 * <li> Iterators: 100%
 * <li> Rule violation checking: 100%
 * <li> Basic queries: 100%
 * <li> Basic commands: 100%
 * </ul>
 * 
 * This package is complete and extensively tested
 * 
 * @author iVerb
 * @since 22-3-13
 */
package bpa.model;

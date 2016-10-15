/**
 * A collection of solver classes containing of strategy classes that are 
 * capable of finding and applying forced cell changes in a {@code Grid} by 
 * means of applying strategies, and more brute force classes that solve the 
 * grid by means of backtracking. The strategy classes can be combined flexibly 
 * by means of a {@code CompositeStrategy} and can be decorated using a 
 * {@code StrategyDecorator}.
 * 
 * 
 * <p>
 * <b> Development status: </b>
 * <ul> 
 *  <li> Applying the `triplet'-strategy: 100%
 *  <li> Applying the `line'-strategy: 100%
 *  <li> Combining strategies using a `composite'-strategy: 100%
 *  <li> Decorating a strategy: 100%
 *  <li> Decorating strategy that applies the decorated strategy until no 
 *       further changes occur: 100%
 * 
 *  <li> Applying backtracking to find a single solution: 100%
 *  <li> Applying backtracking to find all solutions: 100%
 *  <li> Possibility to abort backtracking: 100%
 *  <li> Adding listeners to a backtracker to which the backtracker can report
 *       solutions: 100%
 * </ul>
 * 
 * @author iVerb
 * @since 31-3-13
 */
package bpa.solvers;

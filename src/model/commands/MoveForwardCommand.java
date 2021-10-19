package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to move the turtle forward Dependencies: List
 * <p>
 * Example: When the user inputs "fd n", where n is an integer, make a move forward command to move
 * the turtle n pixels forwards.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class MoveForwardCommand extends MoveCommand {

  /**
   * Purpose: Create a new MoveForwardCommand. Assumptions: args parameter should have size one.
   *
   * @param args A list of integers of size one, containing the distance the turtle should move.
   * @throws IllegalArgumentException thrown if args is not of size one since the move forward
   * command only requires one argument.
   */
  public MoveForwardCommand(List<Integer> args) throws IllegalArgumentException {
    super(1, args);
  }
}

package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to move the turtle backward Dependencies: List
 * <p>
 * Example: When the user inputs "bk n", where n is an integer, make a move backward command to move
 * the turtle n pixels backwards.
 *
 * @author Luis Pereda
 * @author Evan Kenyon
 */
public class MoveBackwardCommand extends MoveCommand {

  /**
   * Purpose: Create a new MoveBackwardCommand. Assumptions: args parameter should have size one.
   *
   * @param args A list of integers of size one, containing the distance the turtle should move.
   * @throws IllegalArgumentException thrown if args is not of size one since the
   * move backward command only requires one argument.
   */
  public MoveBackwardCommand(List<Integer> args) throws IllegalArgumentException {
    super(-1, args);
  }
}

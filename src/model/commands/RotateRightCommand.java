package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to rotate the turtles right, or clockwise. Dependencies:
 * List
 * <p>
 * Example: When the user inputs "rt 90", create a RotateRightCommand that when ran rotates the
 * active turtles clockwise by 90 degrees.
 *
 * @author Luis Pereda
 * @author Evan Kenyon
 */
public class RotateRightCommand extends RotateCommand {

  /**
   * Purpose: Create a new RotateRightCommand Assumptions: args is of size 1.
   *
   * @param args List of integers of size one representing the degrees of rotation.
   * @throws IllegalArgumentException thrown if args is not of size one since the rotate right
   * command only requires one argument.
   */
  public RotateRightCommand(List<Integer> args)
      throws IllegalArgumentException {
    super(1, args);
  }
}

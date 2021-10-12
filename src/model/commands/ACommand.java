package model.commands;

/**
 * Purpose: This command inherits from MoveAndPenCommand to move the turtle backward with the pen
 * down.
 * Dependencies: None
 * Example: If the user inputs A, create an instance of ACommand and use the method runCommand to
 * move the turtle.
 *
 * @author Evan Kenyon
 */
public class ACommand extends MoveAndPenCommand {

  /**
   * Purpose: Create a new ACommand.
   * @param distance How far the turtle will be moved.
   */
  public ACommand(int distance) {
    super(true, -1, distance);
  }
}

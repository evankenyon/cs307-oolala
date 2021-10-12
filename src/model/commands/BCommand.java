package model.commands;

/**
 * Purpose: This command inherits from MoveAndPenCommand to move the turtle backward with the pen
 * up.
 * Dependencies: None
 * Example: If the user inputs B, create an instance of BCommand and use the method runCommand to
 * move the turtle.
 *
 * @author Evan Kenyon
 */
public class BCommand extends MoveAndPenCommand {

  /**
   * Purpose: Create a new BCommand.
   * @param distance How far the turtle will move.
   */
  public BCommand(int distance) {
    super(false, -1, distance);
  }
}

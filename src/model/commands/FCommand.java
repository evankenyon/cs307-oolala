package model.commands;

/**
 * Purpose: This command inherits from MoveAndPenCommand to move the turtle forward with the pen
 * down:
 * Dependencies: None
 * Example: If the user inputs F, create an instance of FCommand and use the method runCommand to
 * move the turtle.
 *
 * @author Evan Kenyon
 */
public class FCommand extends MoveAndPenCommand {

  /**
   * Create a new FCommand.
   * @param distance How far the turtle will move.
   */
  public FCommand(int distance) {
    super(true, 1, distance);
  }
}

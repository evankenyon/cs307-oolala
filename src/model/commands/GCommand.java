package model.commands;

/**
 * Purpose: This command inherits from MoveAndPenCommand to move the turtle forward with the pen
 * up:
 * Dependencies: None
 * Example: If the user inputs G, create an instance of GCommand and use the method runCommand to
 * move the turtle.
 *
 * @author Evan Kenyon
 */
public class GCommand extends MoveAndPenCommand {

  /**
   * Create a new GCommand.
   * @param distance How far the turtle will move.
   */
  public GCommand(int distance) {
    super(false, 1, distance);
  }
}

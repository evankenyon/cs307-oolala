package model.commands;

import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class is meant to be an abstraction for any command that needs to move a turtle
 * around with the pen either. Other commands are expected to extend this command to implement the
 * specific cases required by L systems.
 * Assumptions: This class will not be instantiated, only inherited from.
 * Dependencies: TurtleController, TurtleModel
 *
 * Example: Create a class that moves forward with the pen down that extends this class, so when
 * that action is required that class can handle that responsibility.
 *
 * @author Evan Kenyon
 */
public abstract class MoveAndPenCommand implements Command {
  private final boolean penPosition;
  private final int distance;

  /**
   * Purpose: Create a new MoveAndPenCommand
   * @param penPosition Whether the pen is up or down
   * @param direction 1 is forward, -1 is backward
   * @param distance How far the turtle will move
   */
  public MoveAndPenCommand(boolean penPosition, int direction, int distance) {
    this.penPosition = penPosition;
    this.distance = direction * distance;
  }

  /**
   * Purpose: This method sets the pen to the correct position and moves each active turtle.
   * @param turtleController The turtle controller parameter will have a list of active turtles.
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setPen(penPosition);
      turtleModel.move(distance);
    }
  }
}

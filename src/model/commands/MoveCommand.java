package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class represents an abstraction of implementing a command to move the turtle
 * forward or backward Assumptions: This class will only be inherited, never instantiated. Classes
 * that make a command to move forward or to move backward can inherit from this class.
 * Dependencies: List, TurtleController, TurtleModel
 * <p>
 * Example: Create a class that moves the turtle forward or backward that extends this class, use
 * the runCommand method to move the turtles.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public abstract class MoveCommand implements Command {

  private final int distance;

  /**
   * Purpose: Create a move command constructor with distance provided by args to be inherited by
   * subclasses. Assumptions: Args should have size one.
   *
   * @param direction 1 if forward, -1 if backward.
   * @param args      List of integers of length 1.
   * @throws IllegalArgumentException
   */
  public MoveCommand(int direction, List<Integer> args) throws IllegalArgumentException {
    if (args.size() != 1) {
      throw new IllegalArgumentException();
    }
    distance = direction * args.get(0);
  }

  /**
   * Purpose: This method moves all the active turtles in the turtle controller the distance
   * specified by the user.
   *
   * @param turtleController The turtle controller parameter will have a list of active turtles
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.move(distance);
    }
  }
}

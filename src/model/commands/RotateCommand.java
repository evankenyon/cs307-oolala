package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class represents an abstraction of a command to rotate the turtle, either to the
 * right or the left. Classes that will rotate left or right can extend this class. Assumptions:
 * This class should only be inherited from, never instantiated. Dependencies: List,
 * TurtleController, TurtleModel
 * <p>
 * Example: Create a class that rotates the turtles left, or counterclockwise, by n degrees that
 * extends this class.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public abstract class RotateCommand implements Command {

  private final int trajectory;

  /**
   * Create a RotateCommand constructor with degrees provided by args to be inherited by
   * subclasses.
   *
   * @param direction Right, or clockwise is 1. Left, or counterclockwise, is -1.
   * @param args      List of integers of length one, containing the number of degrees to rotate.
   * @throws IllegalArgumentException thrown if args is not of size one since the rotate command
   * only requires one argument.
   */
  public RotateCommand(int direction, List<Integer> args) throws IllegalArgumentException {
    if (args.size() != 1) {
      throw new IllegalArgumentException();
    }
    trajectory = direction * args.get(0);
  }

  /**
   * Purpose: This method rotates all the active turtles in the turtle controller by the specified
   * number of degrees.
   *
   * @param turtleController The turtle controller parameter will have a list of active turtles
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.rotate(trajectory);
    }
  }
}

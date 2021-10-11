package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class represents an abstraction of a command that will set the pen to either down
 * and drawing, or up and not drawing. Classes that will specifically set the pen up or set the pen
 * down can extend this class.
 * Assumptions: This class will never be instantiated, only inherited from.
 * Dependencies: List, TurtleController, TurtleModel
 *
 * Example: Create a class that sets the pen down that extends this class.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public abstract class SetPenCommand implements Command {

  private final boolean penPosition;

  /**
   * Purpose: Create a new SetPenCommand with a boolean determining whether the pen is up or down.
   *
   * @param penUpOrDown Boolean, if true pen is down and drawing, and if false pen is up and not
   *                    drawing.
   * @param args List of integers which should be empty.
   * @throws IllegalArgumentException
   */
  public SetPenCommand(boolean penUpOrDown, List<Integer> args) throws IllegalArgumentException {
    if (!args.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.penPosition = penUpOrDown;
  }

  /**
   * Purpose: Set the pen either down or up for all the active turtles
   * @param turtleController The turtle controller parameter will have a list of active turtles
   */
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setPen(penPosition);
    }
  }
}

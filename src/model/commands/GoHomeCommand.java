package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class implements a command for the turtle to be moved to home. Assumptions: Args
 * passed to the constructor should be empty. Dependencies: List, TurtleController, TurtleModel
 * <p>
 * Example: If the user types in 'home', create a GoHomeCommand and use the runCommand method to
 * make the turtle go home.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class GoHomeCommand implements Command {

  /**
   * Purpose: Create a GoHomeCommand.
   *
   * @param args should be empty
   * @throws IllegalArgumentException
   */
  public GoHomeCommand(List<Integer> args) throws IllegalArgumentException {
    if (!args.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Purpose: Move the turtles to their home locations
   *
   * @param turtleController The turtle controller parameter will have a list of active turtles
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.goHome();
    }
  }

}

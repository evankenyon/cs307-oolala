package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class represents a command to stamp the image of the turtle on the screen.
 * Assumptions: args passed to the constructor should be empty. Dependencies: List,
 * TurtleController, TurtleModel
 * <p>
 * Example: If the user inputs the command "STAMP", create an instance of this class and run the
 * method.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class StampCommand implements Command {

  /**
   * Purpose: Create a new StampCommand. Assumptions: args is of size 0.
   *
   * @param args List of integers of size 0.
   * @throws IllegalArgumentException
   */
  public StampCommand(List<Integer> args) throws IllegalArgumentException {
    if (!args.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Purpose: This method stamps the image of all the active turtles.
   *
   * @param turtleController The turtle controller parameter will have a list of active turtles.
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setShouldStampTrue();
    }
  }
}

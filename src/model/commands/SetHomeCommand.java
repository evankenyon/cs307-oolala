package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class represents a command to set the home location of the turtle to a x,y
 * coordinate.
 * Assumptions: 2 integers will be passed in
 * Dependencies: List, TurtleController, TurtleModel
 *
 * Example: If the user wants to set the home location to [100,200], create a SetHomeCommand that
 * when ran will set the home location of the turtle to these coordinates.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class SetHomeCommand implements Command {

  private final int[] home = new int[2];

  /**
   * Purpose: Create a new set home command.
   * Assumptions: args will be of length 2, the first element being the x coordinate, the second
   * being the y coordinate.
   *
   * @param args List of 2 integers representing the x,y coordinates that home will be set to.
   * @throws IllegalArgumentException
   */
  public SetHomeCommand(List<Integer> args) throws IllegalArgumentException {
    if (args.size() != 2) {
      throw new IllegalArgumentException();
    }
    home[0] = args.get(0);
    home[1] = args.get(1);
  }

  /**
   * Purpose: This method sets the home location of all the active turtles to the coordinates
   * provided as parameters in the constructor.
   *
   * @param turtleController The turtle controller parameter will have a list of active turtles
   */
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setHome(home);
    }
  }
}

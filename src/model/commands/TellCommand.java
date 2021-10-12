package model.commands;

import java.util.ArrayList;
import java.util.List;
import model.TurtleController;

/**
 * Purpose: This class represents a command to define which turtles will be executing the subsequent
 * commands.
 * Dependencies: ArrayList, List, TurtleController
 *
 * Example: If the user inputs the command "TELL" then create an instance of this class and run the
 * method.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class TellCommand implements Command {

  private final List<Integer> ids;

  /**
   * Purpose: Create a new TellCommand.
   * Assumptions: args should not be of size 0.
   * @param ids List of integers representing the ids of the turtles to execute the subsequent
   *            commands.
   * @throws IllegalArgumentException
   */
  public TellCommand(List<Integer> ids) throws IllegalArgumentException {
    if (ids.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.ids = new ArrayList<>(ids);
  }

  /**
   * Purpose: With the tell command, we are effectively saying which turtles we want to be doing "work" in
   * the future. At the start of the command we clear the active turtles then add the ones to the
   * active list we wish to work with.
   *
   * @param turtleController The turtle controller parameter will have a list of active turtles.
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    turtleController.resetActiveTurtles();
    for (Integer id : ids) {
      turtleController.addTurtleToActives(id);
    }
  }

}

package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

/**
 * Purpose: This class represents an abstraction of a command to either show or hide the turtle.
 * Assumptions: This class should not be instantiated, only inherited from.
 * Dependencies: List, TurtleController, TurtleModel
 *
 * Example: Create a class to show the turtle that extends this class.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public abstract class ShowOrHideCommand implements Command {

  boolean shouldShow;

  /**
   * Create a constructor for ShowOrHideCommand that sets shouldShow that can be inherited by
   * subclasses.
   * @param shouldShow Boolean that if true shows the turtle, if false hides the turtle.
   * @param args List of integers of size 0.
   * @throws IllegalArgumentException
   */
  public ShowOrHideCommand(boolean shouldShow, List<Integer> args) throws IllegalArgumentException {
    if (!args.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.shouldShow = shouldShow;
  }

  /**
   * Purpose: This method shows or hides all the active turtles
   * @param turtleController The turtle controller parameter will have a list of active turtles
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setShouldShow(shouldShow);
    }
  }
}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: This class is meant to organize all the TurtleModels in the program, in particular those
 * that are active, meaning that the subsequent commands are applicable to them. Assumptions: When a
 * command is run, even if tell has not been called there will be one active turtle to execute those
 * comnmands. If tell is called then this will add new active turtles. Dependencies: List,
 * ArrayList
 * <p>
 * Example: In the backend model for our IDE, there would one turtle controller handling all the
 * turtles in the program. If a user wanted to tell turtles 1, 2, 3, and 4 to move forward 50
 * pixels, the TurtleController would add these 4 turtles to activeTurtles, and create new turtles
 * for these IDs if these turtles do not yet exist. Then to move each turtle forward, the move
 * command would get all the active turtles from the turtle controller, iterate through them and
 * apply the move command on each TurtleModel.
 *
 * @author Luis Pereda
 */
public class TurtleController {

  private final List<TurtleModel> allTurtles;
  private final List<TurtleModel> activeTurtles;
  private final List<TurtleModel> newTurtles;
  private boolean hasNewTurtle;

  /**
   * Purpose: Constructor for a new TurtleController. When created, the controller should have only
   * 1 turtle with an ID of 1.
   */
  public TurtleController() {
    allTurtles = new ArrayList<>();
    activeTurtles = new ArrayList<>();
    newTurtles = new ArrayList<>();
    TurtleModel turtleModel = new TurtleModel(1);
    allTurtles.add(turtleModel);
    activeTurtles.add(turtleModel);
    hasNewTurtle = false;
  }

  /**
   * Purpose: This method is designed to change the list of active turtles, which are the ones which
   * will be executing commands input by the user. When TELL in the Logo IDE is called, for example,
   * this changes which turtles will follow the commands, which we handle here. Assumptions: This ID
   * can be of an already existing turtle, or a unique ID which would mean creating a new turtle.
   *
   * @param id Integer representing a unique ID of the turtle
   */
  public void addTurtleToActives(int id) {
    if (!checkIfTurtleIDExists(id)) {
      TurtleModel turtleModel = new TurtleModel(id);
      allTurtles.add(turtleModel);
      activeTurtles.add(turtleModel);
      newTurtles.add(turtleModel);
      hasNewTurtle = true;
    }
  }


  private boolean checkIfTurtleIDExists(int id) {
    for (TurtleModel turtleModel : allTurtles) {
      if (turtleModel.getID() == id) {
        checkNotInActiveTurtles(turtleModel);
        return true;
      }
    }
    return false;
  }

  private void checkNotInActiveTurtles(TurtleModel turtleModel) {
    if (!activeTurtles.contains(turtleModel)) {
      activeTurtles.add(turtleModel);
    }
  }

  /**
   * Purpose: Clears the active turtles list so that it is empty. Assumptions: When commands are
   * being passed to the turtles the active turtles will never be empty, this method simply
   * facilitates commands such as TELL in Logo IDE that reorganize the list of active turtles.
   */
  public void resetActiveTurtles() {
    activeTurtles.clear();
  }

  /**
   * Purpose: Getter for the active turtles in the controller.
   *
   * @return List of TurtleModels representing the turtles which will execute the commands input by
   * the user
   */
  public List<TurtleModel> getActiveTurtles() {
    return activeTurtles;
  }

  /**
   * Purpose: Checks to see if a new turtle has been added since the last time that all of the new
   * turtles were gotten.
   *
   * @return Boolean which is true if a new turtle has been added, and false if one has not.
   */
  public boolean hasNewTurtle() {
    return hasNewTurtle;
  }

  /**
   * Purpose: Getter for all the new turtles added to the controller. This is meant to help in
   * facilitating creating new turtles needed in the view.
   *
   * @return List of TurtleModels that are new in the program.
   */
  public List<TurtleModel> getNewTurtles() {
    hasNewTurtle = false;
    if (newTurtles.isEmpty()) {
      throw new NullPointerException();
    }
    List<TurtleModel> newTurtlesCopy = new ArrayList<>(newTurtles);
    newTurtles.clear();
    return newTurtlesCopy;
  }
}

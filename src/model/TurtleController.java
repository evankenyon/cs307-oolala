package model;

import java.util.ArrayList;
import java.util.List;

public class TurtleController {


  private List<TurtleModel> allTurtles;
  private List<TurtleModel> activeTurtles;
  private TurtleModel newTurtle;
  private boolean hasNewTurtle;


  public TurtleController() {
    allTurtles = new ArrayList<>();
    activeTurtles = new ArrayList<>();
    TurtleModel turtleModel = new TurtleModel(1);
    allTurtles.add(turtleModel);
    activeTurtles.add(turtleModel);
    hasNewTurtle = false;
  }

  /**
   * Add turtle is designed to work for the tell command. If tell is given with an id that already
   * exists
   *
   * @param id
   */
  public void addTurtleToActives(int id) {
    if (!checkIfTurtleIDExists(id)) {
      TurtleModel turtleModel = new TurtleModel(id);
      allTurtles.add(turtleModel);
      activeTurtles.add(turtleModel);
      newTurtle = turtleModel;
      hasNewTurtle = true;
    }
  }

  private boolean checkIfTurtleIDExists(int id) {
    for (TurtleModel turtleModel : allTurtles) {
      if (turtleModel.getID() == id) {
        if (!activeTurtles.contains(turtleModel)) {
          activeTurtles.add(turtleModel);
        }
        return true;
      }
    }
    return false;
  }

  public void resetActiveTurtles() {
    activeTurtles.clear();
  }

  public List<TurtleModel> getActiveTurtles() {
    return activeTurtles;
  }

  public boolean hasNewTurtle() {
    return hasNewTurtle;
  }

  public TurtleModel getNewTurtle() {
    hasNewTurtle = false;
    if (newTurtle == null) {
      throw new NullPointerException();
    }
    return newTurtle;
  }
}

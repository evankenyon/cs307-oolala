package model;

import java.util.ArrayList;
import java.util.List;
import view.TurtleDisplay;
import view.TurtleWindowDisplay;

public class TurtleController {


  private final List<TurtleModel> allTurtles;
  private final List<TurtleModel> activeTurtles;
  private final List<TurtleModel> newTurtles;
  private boolean hasNewTurtle;


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

  public void resetActiveTurtles() {
    activeTurtles.clear();
  }

  public List<TurtleModel> getActiveTurtles() {
    return activeTurtles;
  }

  public boolean hasNewTurtle() {
    return hasNewTurtle;
  }

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

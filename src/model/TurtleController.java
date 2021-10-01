package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TurtleController {

  private List<TurtleModel> allTurtles;
  private List<TurtleModel> activeTurtles;

  public TurtleController() {
    allTurtles = new ArrayList<>();
    activeTurtles = new ArrayList<>();
    TurtleModel turtleModel = new TurtleModel(1);
    allTurtles.add(turtleModel);
    activeTurtles.add(turtleModel);
  }

  /**
   * Add turtle is designed to work for the tell command. If tell is given with an id that already
   * exists
   *
   * @param id
   */
  public void addTurtleToActives(int id) {
    if(checkIfTurtleIDExists(id)){
      return;
    }
    else {
      TurtleModel turtleModel = new TurtleModel(id);
      allTurtles.add(turtleModel);
      activeTurtles.add(turtleModel);
    }
  }

  private boolean checkIfTurtleIDExists(int id) {
    for (TurtleModel turtleModel : allTurtles) {
      if (turtleModel.getID() == 1) {
        return true;
      }
      if (turtleModel.getID() == id) {
        activeTurtles.add(turtleModel);
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

}

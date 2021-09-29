package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurtleController {
  private List allTurtles;
  private List activeTurtles;

  public TurtleController() {
    allTurtles = new ArrayList<TurtleModel>();
    activeTurtles = new ArrayList();
  }

  public void addTurtle(int id){
    if(allTurtles.isEmpty()) {
      TurtleModel turtleModel = new TurtleModel(1);
      allTurtles.add(turtleModel);
      activeTurtles.add(turtleModel);
    }
    TurtleModel turtleModel = new TurtleModel(id);
    allTurtles.add(turtleModel);
    activeTurtles.add(turtleModel);
  }

  public List<TurtleModel> getActiveTurtles() {
    return activeTurtles;
  }

}

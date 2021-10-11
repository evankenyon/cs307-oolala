package model;

import java.util.List;

public abstract class AppModel {
  protected final TurtleController turtleController;

  public AppModel() {
    turtleController = new TurtleController();
  }

  public abstract void handleTextInput(String input);

  public abstract void runNextCommand();

  public List<TurtleModel> getActiveTurtles() {
    return turtleController.getActiveTurtles();
  }
}

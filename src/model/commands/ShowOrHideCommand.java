package model.commands;

import model.TurtleController;
import model.TurtleModel;

public class ShowOrHideCommand implements Command {

  boolean shouldShow;

  public ShowOrHideCommand(boolean shouldShow) {
    this.shouldShow = shouldShow;
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setShouldShow(shouldShow);
    }
  }
}

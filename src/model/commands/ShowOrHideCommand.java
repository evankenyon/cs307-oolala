package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

public abstract class ShowOrHideCommand implements Command {

  boolean shouldShow;

  public ShowOrHideCommand(boolean shouldShow, List<Integer> args) throws IllegalArgumentException {
    if (!args.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.shouldShow = shouldShow;
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setShouldShow(shouldShow);
    }
  }
}

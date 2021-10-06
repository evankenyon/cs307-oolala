package model.commands;

import model.TurtleController;
import model.TurtleModel;

public class StampCommand implements Command {

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setShouldStampTrue();
    }
  }
}

package model.commands;

import model.TurtleController;
import model.TurtleModel;

public class GoHomeCommand implements Command {

  public GoHomeCommand() {
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.goHome();
    }
  }

}

package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

public class GoHomeCommand implements Command {

  public GoHomeCommand(List<Integer> args) throws IllegalArgumentException {
    if (!args.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.goHome();
    }
  }

}

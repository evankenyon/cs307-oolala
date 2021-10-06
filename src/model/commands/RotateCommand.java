package model.commands;

import model.TurtleController;
import model.TurtleModel;

public class RotateCommand implements Command {

  private final int trajectory;

  public RotateCommand(int trajectory) {
    this.trajectory = trajectory;
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.rotate(trajectory);
    }
  }
}

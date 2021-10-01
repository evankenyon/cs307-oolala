package model.commands;

import model.TurtleController;
import model.TurtleModel;

public class MoveCommand extends Command {

  private int distance;

  public MoveCommand(int distance) {
    this.distance = distance;
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.move(distance);
    }
  }
}

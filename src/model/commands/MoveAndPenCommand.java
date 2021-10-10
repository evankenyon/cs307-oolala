package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

public abstract class MoveAndPenCommand implements Command {
  private final boolean penPosition;
  private final int distance;

  public MoveAndPenCommand(boolean penPosition, int direction, int distance) {
    this.penPosition = penPosition;
    this.distance = direction * distance;
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setPen(penPosition);
      turtleModel.move(distance);
    }
  }
}

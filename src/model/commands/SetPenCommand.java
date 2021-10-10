package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

public abstract class SetPenCommand implements Command {

  private final boolean penPosition;

  public SetPenCommand(boolean penUpOrDown, List<Integer> args) throws IllegalArgumentException {
    if (!args.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.penPosition = penUpOrDown;
  }

  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setPen(penPosition);
    }
  }
}

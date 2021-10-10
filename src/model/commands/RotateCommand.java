package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

public abstract class RotateCommand implements Command {

  private final int trajectory;

  public RotateCommand(int direction, List<Integer> args) throws IllegalArgumentException {
    if (args.size() != 1) {
      throw new IllegalArgumentException();
    }
    trajectory = direction * args.get(0);
  }

  @Override
  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.rotate(trajectory);
    }
  }
}

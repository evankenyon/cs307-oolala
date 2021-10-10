package model.commands;

import java.util.List;
import model.TurtleController;
import model.TurtleModel;

public class SetHomeCommand implements Command {

  private final int[] home = new int[2];

  public SetHomeCommand(List<Integer> args) throws IllegalArgumentException {
    if (args.size() != 2) {
      throw new IllegalArgumentException();
    }
    home[0] = args.get(0);
    home[1] = args.get(1);
  }

  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setHome(home);
    }
  }
}

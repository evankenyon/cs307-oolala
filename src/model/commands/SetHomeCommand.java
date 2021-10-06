package model.commands;

import model.TurtleController;
import model.TurtleModel;

public class SetHomeCommand implements Command {

  private final int[] home = new int[2];

  public SetHomeCommand(int[] home) {
    this.home[0] = home[0];
    this.home[1] = home[1];
  }

  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setHome(home);
    }
  }
}

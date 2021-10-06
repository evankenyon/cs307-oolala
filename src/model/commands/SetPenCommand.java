package model.commands;

import model.TurtleController;
import model.TurtleModel;

public class SetPenCommand implements Command {

  private boolean penPosition;

  public SetPenCommand(boolean penUpOrDown) {
    this.penPosition = penUpOrDown;
  }

  public void runCommand(TurtleController turtleController) {
    for (TurtleModel turtleModel : turtleController.getActiveTurtles()) {
      turtleModel.setPen(penPosition);
    }
  }
}

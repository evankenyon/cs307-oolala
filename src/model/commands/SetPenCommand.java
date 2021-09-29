package model.commands;

import model.TurtleModel;

public class SetPenCommand extends Command{
private boolean penPosition;

public SetPenCommand(boolean penUpOrDown) {
  this.penPosition = penUpOrDown;
}

@Override
  public void runCommand(TurtleModel turtleModel) {
    turtleModel.setPen(penPosition);
}
}

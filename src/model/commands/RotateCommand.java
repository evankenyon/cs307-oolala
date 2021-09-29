package model.commands;

import model.TurtleModel;

public class RotateCommand extends Command{
  private int trajectory;

  public RotateCommand(int trajectory) {
    this.trajectory = trajectory;
  }

  @Override
  public void runCommand(TurtleModel turtleModel) {
      turtleModel.rotate(trajectory);
  }
}

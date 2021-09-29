package model.commands;

import model.TurtleModel;

public class GoHomeCommand extends Command{

  public GoHomeCommand() {}

  @Override
  public void runCommand(TurtleModel turtleModel){
    turtleModel.goHome();
  }

}

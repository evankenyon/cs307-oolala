package model.commands;

import model.TurtleController;
import model.TurtleModel;

public abstract class Command {

  // Example implementation:
  // For a MoveCommand class, have int distance as a class
  // member variable, then call turtleModel.move(distance)
  // in runCommand
  public abstract void runCommand(TurtleController turtleController);
}

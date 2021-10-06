package model.commands;

import model.TurtleController;

public interface Command {

  // Example implementation:
  // For a MoveCommand class, have int distance as a class
  // member variable, then call turtleModel.move(distance)
  // in runCommand
  void runCommand(TurtleController turtleController);
}

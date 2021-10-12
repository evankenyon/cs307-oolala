package model.commands;

import model.TurtleController;

/**
 * Purpose: This interface is meant to be a parent class for all commands in the Logo Turtle
 * application Assumptions: This interface will not be instantiated, the other command classes will
 * implement it Dependencies: TurtleController Example implementation: For a MoveCommand class, have
 * int distance as a class member variable, then call turtleModel.move(distance) in runCommand
 *
 * @author Evan Kenyon
 */
public interface Command {

  /**
   * Purpose: This method will be used by all subclasses that implement this interface. This will
   * allow each specific command to do the work responsible for that command simply by calling
   * runCommand. This method is expected to be overridden by each sub-class. Assumptions: A working
   * turtle controller is expected to be passed in, if not this could introduce bugs.
   *
   * @param turtleController The turtle controller parameter will have a list of active turtles on
   *                         which the work from the method will be done.
   */
  void runCommand(TurtleController turtleController);
}

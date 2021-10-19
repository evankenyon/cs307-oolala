package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to set the pen down so that it draws. Assumption: Args
 * passed to the constructor should be empty Dependencies: List
 * <p>
 * Example: If a user inputs the command "pd", then make a SetPenDownCommand and run the command to
 * make it so all the active turtles are drawing.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class SetPenDownCommand extends SetPenCommand {

  /**
   * Purpose: Create a new SetPenDownCommand
   *
   * @param args List of integers of size 0.
   * @throws IllegalArgumentException thrown if args is not empty since the set pen down command
   * does not require any arguments.
   */
  public SetPenDownCommand(List<Integer> args) throws IllegalArgumentException {
    super(true, args);
  }

}

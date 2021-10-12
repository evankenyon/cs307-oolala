package model.commands;

import java.util.List;

/**
 * Purpose: This class implements a command to hide the turtle so that it cannot be seen.
 * Assumption: Args passed to the constructor should be empty
 * <p>
 * Dependencies: List
 * <p>
 * Example: If the user inputs the command "ht", then create an instance of this class and run the
 * command.
 *
 * @author Luis Pereda and Evan Kenyon
 */
public class HideCommand extends ShowOrHideCommand {

  /**
   * Purpose: Create a new hide command
   *
   * @param args Should be an empty list
   * @throws IllegalArgumentException
   */
  public HideCommand(List<Integer> args) throws IllegalArgumentException {
    super(false, args);
  }
}

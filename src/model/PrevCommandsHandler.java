package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Maintains the previously input user commands as a List<String> object, and saves it as
 * a file to the user's computer when saveCommandsFile is called.
 * Assumptions: All updates to this object are based on valid user input.
 * Dependencies: IOException, PrintWriter, StandardCharsets, ArrayList, List
 * Example: Instantiate this class in CommandModel, which calls the saveCommandsAsFile method when
 * LogoModel calls on CommandModel to do so. LogoModel is called to do so when the user presses the
 * save file button on the frontend.
 *
 * @author Evan Kenyon
 */
public class PrevCommandsHandler {

  private int numProgramsSaved;
  private String programName;
  protected List<String> prevCommands;

  /**
   * Construct a PrevCommandsHandler object.
   * @param programName the programName used for the filepath to save files to (i.e. command files
   *                    for the logo app should be saved in a logo subdirectory).
   */
  public PrevCommandsHandler(String programName) {
    numProgramsSaved = 0;
    prevCommands = new ArrayList<>();
    this.programName = programName;
  }

  /**
   * Purpose: Saves the prevCommands list into a .txt file
   *
   * @throws IOException thrown if unexpected error when creating program in preset path (i.e.
   * perhaps the path was deleted by the user).
   */
  public void saveCommandsAsFile() throws IOException {
    numProgramsSaved++;
    // Code for creating a file and writing to it borrowed from
    // https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
    PrintWriter currProgram = new PrintWriter(
        "./data/programs/" + programName + "/program" + numProgramsSaved
            + ".txt", StandardCharsets.UTF_8);
    currProgram.println("#Saved program number " + numProgramsSaved);
    for (String command : prevCommands) {
      currProgram.println(command);
    }
    currProgram.close();
  }

  /**
   * Gets the commandHistory by returning prevCommands.
   * @return the commandHistory (i.e. prevCommands).
   */
  public List<String> getCommandHistory() {
    return prevCommands;
  }

  /**
   * Purpose: Adds a String representation of a valid user command that was actually input to
   * prevCommands list.
   * Assumptions: function is a valid command, args are the valid String representations of the
   * arguments that the user input for that command
   *
   * @param function app command in String format (i.e. "fd").
   * @param args arguments that the user input for the app command (i.e. the "50" in "fd 50").
   */
  public void addPrevCommand(String function, List<String> args) {
    prevCommands.add(commandStringConstruction(function, args));
  }

  /**
   * Purpose: Adds input to prevCommands list.
   * Assumptions: input is the String representation of a valid user command that was actually
   * input.
   * @param input previous user command.
   */
  public void addPrevCommand(String input) {
    prevCommands.add(input);
  }

  private String commandStringConstruction(String function, List<String> args) {
    StringBuilder commandString = new StringBuilder();
    commandString.append(function);
    for (String arg : args) {
      commandString.append(" ");
      commandString.append(arg);
    }
    return commandString.toString();
  }
}

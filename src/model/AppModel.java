package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import model.commands.Command;

/**
 * Purpose: This class represents an abstraction at a high level for what each of the apps will look
 * like, and this could be used in the future as a form of boilerplate code for a new app we wish to
 * build. The class organizes the variables common to our current apps, including the
 * turtleController, commandsToRun, and the commandModel, as well as providing several abstract
 * methods which are expected to be implemented in classes that inherit from AppModel. Assumptions:
 * This class will never be instantiated, only inherited from. Dependencies: File,
 * FileNotFoundException, IOException, List, Queue, Command
 * <p>
 * Example: A class could be made for the model of Logo IDE that extends this class and implements
 * the abstract methods pr
 */
public abstract class AppModel {

  protected final TurtleController turtleController;

  protected Queue<Command> commandsToRun;
  protected CommandModel commandModel;

  /**
   * Purpose: Subclasses will be able to inherit this constructor
   */
  public AppModel() {
    turtleController = new TurtleController();
  }

  /**
   * Purpose: This class will be implemented so that text that is inputted by a user into a text box
   * can be parsed into commands, which can then be created and ran so that the expected commands
   * from the user can be executed. For example this could handle taking the string "fd 50" in Logo
   * IDE, and parsing this string into a command that will move a turtle forward 50 pixels.
   * Assumptions: The user will be inputting valid commands, and if not will be alerted of an
   * invalid input.
   *
   * @param input The user string input representing a command.
   */
  public abstract void handleTextInput(String input);

  /**
   * Purpose: If there are commands left to be run on the turtles, then this command runs the next
   * command so the turtle can do the work the user is expecting. Must be implemented in each class
   * that inherits from this one.
   */
  public abstract void runNextCommand();

  /**
   * Purpose: Getter for all the active turtles that will be executing the user's commands.
   *
   * @return List of active turtles
   */
  public List<TurtleModel> getActiveTurtles() {
    return turtleController.getActiveTurtles();
  }

  /**
   * Purpose: Getter for the list of commands that have already been executed. Can be useful for
   * showing the user the command history. Must be implemented in each subclass.
   *
   * @return List of commands that the user has run
   */
  public abstract List<String> getCommandHistory();

  /**
   * Purpose: The history of the user's commands can be saved as a .txt file to be used at a later
   * time. Must be implemented in each subclass.
   *
   * @throws IOException
   */
  public abstract void saveCommandsAsFile() throws IOException;

  /**
   * Purpose: This class will be implemented so that a user may input a .txt file that can be parsed
   * into commands, which can then be created and ran so that the expected commands from the user
   * can be executed. Assumptions: File is a .txt with valid commands, if not an exception will be
   * thrown.
   *
   * @param file A file containing the commands the user wishes to execute
   * @throws FileNotFoundException
   */
  public abstract void handleFileInput(File file) throws FileNotFoundException;

  /**
   * Purpose: Check to see if there are any new active turtles. Should be overridden to be
   * functional.
   *
   * @return If true there are new turtles, if false there are no new turtles.
   */
  public boolean hasNewTurtles() {
    return false;
  }

  /**
   * Purpose: Getter for the new turtles recently added to actives.
   *
   * @return List of new active turtle models
   */
  public List<TurtleModel> getNewTurtles() {
    return null;
  }

  public abstract void setHomeLocation(int x, int y);
}

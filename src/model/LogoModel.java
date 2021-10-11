package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.commands.Command;

public class LogoModel extends AppModel {

  private Queue<Command> commandsToRun;

  private final LogoCommandModel logoCommandModel;

  public LogoModel() {
    logoCommandModel = new LogoCommandModel();
    commandsToRun = new LinkedList<>();
  }

  /**
   * When the user inputs a file, use CommandModel's handleFile to return a list of commands
   *
   * @param file
   */
  public void handleFileInput(File file) {
    try {
      commandsToRun.addAll(logoCommandModel.handleFileSelected(file));
    } catch (IllegalArgumentException | FileNotFoundException e) {
      // TODO: fix
      e.printStackTrace();
    }
  }

  @Override
  public void runNextCommand() {
    if(!commandsToRun.isEmpty()) {
      commandsToRun.remove().runCommand(turtleController);
    }
  }

  @Override
  public List<String> getCommandHistory() {
    return logoCommandModel.getCommandHistory();
  }

  /**
   * When the user inputs a string of texts, have the command model parse the text and run the
   * appropriate command.
   *
   * @param input
   */
  @Override
  public void handleTextInput(String input) throws InputMismatchException, NumberFormatException {
    commandsToRun.addAll(logoCommandModel.getCommandsFromInput(input));
  }

  public boolean isTurtleActive(int turtleId) {
    try {
      getTurtleModel(turtleId);
    } catch (NullPointerException e) {
      return false;
    }
    return true;
  }

  public void saveCommandsAsFile() throws IOException {
    logoCommandModel.saveCommandsAsFile();
  }

  public List<TurtleModel> getActiveTurtles() {
    return turtleController.getActiveTurtles();
  }

  public List<TurtleModel> getNewTurtles() throws NullPointerException {
    return turtleController.getNewTurtles();
  }

  public boolean hasNewTurtles() {
    return turtleController.hasNewTurtle();
  }

  public TurtleModel getTurtleModel(int turtleId) throws NullPointerException {
    TurtleModel turtleModel = null;
    for (TurtleModel turtle : turtleController.getActiveTurtles()) {
      if (turtle.getID() == turtleId) {
        turtleModel = turtle;
      }
    }

    if (turtleModel == null) {
      throw new NullPointerException();
    }
    return turtleModel;
  }

  /**
   * Getter so the test classes can access the controller
   *
   * @return
   */
  TurtleController getTurtleController() {
    return turtleController;
  }

  LogoCommandModel getLogoCommandModel() {
    return logoCommandModel;
  }
}

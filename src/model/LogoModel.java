package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.commands.Command;

public class LogoModel {

  private Queue<Command> fileCommands;
  private final TurtleController turtleController;
  private final CommandModel commandModel;

  public LogoModel() {
    turtleController = new TurtleController();
    commandModel = new CommandModel();
    fileCommands = new LinkedList<>();
  }

  /**
   * When the user inputs a file, use CommandModel's handleFile to return a list of commands
   *
   * @param file
   * @return
   */
  public void handleFileInput(File file) {
    try {
      fileCommands.addAll(commandModel.handleFileSelected(file));
    } catch (IllegalArgumentException | FileNotFoundException e) {
      // TODO: fix
      e.printStackTrace();
    }
  }

  public void runFileCommand() {
    if(!fileCommands.isEmpty()) {
      fileCommands.remove().runCommand(turtleController);
    }
  }

  /**
   * When the user inputs a string of texts, have the command model parse the text and run the
   * appropriate command.
   *
   * @param input
   * @return
   */
  public void handleTextInput(String input) throws InputMismatchException, NumberFormatException {
    Command command = commandModel.parseInput(input);
    command.runCommand(turtleController);
  }

  public boolean isTurtleActive(int turtleId) {
    try {
      getTurtleModel(turtleId);
    } catch (NullPointerException e) {
      return false;
    }
    return true;
  }

  public void saveCommandsAsFile() throws FileNotFoundException, UnsupportedEncodingException {
    commandModel.saveCommandsAsFile();
  }

  /**
   * Returns the position of a turtle found by its ID This will be used in the step function to
   * determine if the turtle has moved and a new line needs to be made.
   *
   * @param turtleId
   * @return
   */
  public int[] getTurtlePosition(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getPosition();
  }

  public double getTurtleTrajectory(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getTrajectory();
  }

  public boolean getTurtlePenDown(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getPen();
  }

  public boolean getShouldTurtleStamp(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getShouldStamp();
  }

  public boolean getTurtleShouldShow(int turtleId) {
    TurtleModel turtleModel = getTurtleModel(turtleId);

    return turtleModel.getShouldShow();
  }

  public List<TurtleModel> getNewTurtles() throws NullPointerException {
    return turtleController.getNewTurtles();
  }

  public boolean hasNewTurtles() {
    return turtleController.hasNewTurtle();
  }

  private TurtleModel getTurtleModel(int turtleId) throws NullPointerException {
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

  CommandModel getCommandModel() {
    return commandModel;
  }
}

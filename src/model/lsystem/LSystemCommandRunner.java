package model.lsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import model.CommandModel;
import model.commands.*;
import model.logo.LogoCommandModel;
import view.TurtleWindowDisplay;

/**
 * Purpose: Represent an LSystemCommandModel, which takes in symbols and parses them into Command
 * objects Dependencies: ArrayList, HashMap, InputMismatchException, List, Map, commands,
 * TurtleWindowDisplay Example: Instantiate an LSystemCommandRunner in LSystemsModel in order to get
 * symbols from LSystemProgram's symbol generation functionality, and return commands based on those
 * symbols Other details: LSystemProgram handles the parsing of set commands, and LSystemsModel
 * updates the map in this class based on LSystemProgram's parsing
 *
 * @author Haseeb Chaudhry
 * @author Evan Kenyon
 */
public class LSystemCommandRunner extends CommandModel {

  private int movementLength;
  private int rotationAngle;
  private List<Integer> originalLocation;
  private List<Integer> currLocation;
  private int levelIncrement;
  private boolean isFirstCommand;
  Map<String, List<Command>> inputCharToCommand;
  private LogoCommandModel logoCommandModel;

  /**
   * Purpose: Construct an LSystemCommandRunner object Assumptions: distance and angle are
   * reasonable values (i.e. distance is not greater than the screen length)
   *
   * @param distance       initial movement command length
   * @param angle          initial rotate command angle
   * @param totalNumLevels initial number of levels that should be generated
   */
  public LSystemCommandRunner(int distance, int angle, int totalNumLevels) {
    movementLength = distance;
    rotationAngle = angle;
    originalLocation = new ArrayList<>();
    originalLocation.add(TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    originalLocation.add(TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    levelIncrement = (TurtleWindowDisplay.PREF_WINDOW_SIZE / 2) / totalNumLevels;
    isFirstCommand = true;
    inputCharToCommand = new HashMap<>();
    logoCommandModel = new LogoCommandModel();
    setupInputCharToCommand();
  }

  private void setupInputCharToCommand() {
    inputCharToCommand.put("f", logoCommandModel.getCommandsFromInput("pd fd " + movementLength));
    inputCharToCommand.put("g", logoCommandModel.getCommandsFromInput("pu fd " + movementLength));
    inputCharToCommand.put("a", logoCommandModel.getCommandsFromInput("pu bk " + movementLength));
    inputCharToCommand.put("b", logoCommandModel.getCommandsFromInput("pd bk " + movementLength));
    inputCharToCommand.put("+", logoCommandModel.getCommandsFromInput("rt " + rotationAngle));
    inputCharToCommand.put("-", logoCommandModel.getCommandsFromInput("lt " + rotationAngle));
    inputCharToCommand.put("x", logoCommandModel.getCommandsFromInput("stamp"));
  }

  /**
   * Purpose: Further parses the new symbol set command and adds the symbol to command mapping to
   * the inputCharToCommand map (or replaces it). Assumptions: newSymbolSet represents a partially
   * parsed set command from LSystemProgram, which originally took in a user inputted set command.
   *
   * @param newSymbolSet a partially parsed set command from LSystemProgram
   */
  public void putInInputCharToCommand(Map<String, List<String>> newSymbolSet) {
    for (String symbol : newSymbolSet.keySet()) {
      String parsedCommand = String.join(" ", newSymbolSet.get(symbol));
      parsedCommand = parsedCommand.replace("\"", "");
      parsedCommand = parsedCommand.toLowerCase();
      parsedCommand = parsedCommand.replace("length", "" + movementLength);
      parsedCommand = parsedCommand.replace("angle", "" + rotationAngle);
      List<Command> finalCommands = logoCommandModel.getCommandsFromInput(parsedCommand);
      inputCharToCommand.put(symbol, finalCommands);
    }
  }

  /**
   * @param input
   * @return
   * @throws InputMismatchException
   */
  @Override
  public List<Command> getCommandsFromInput(String input) throws InputMismatchException {
    List<Command> commands = new ArrayList<>();
    if (isFirstCommand) {
      currLocation = new ArrayList<>(originalLocation);
      commands.addAll(goToStartLocation());
      isFirstCommand = false;
    }
    for (String inputChar : input.split("(?!^)")) {
      commands.addAll(getCommand(inputChar));
    }
    currLocation.set(1, currLocation.get(1) + levelIncrement);
    commands.addAll(goToStartLocation());
    return commands;
  }

  private List<Command> getCommand(String inputChar) {
    List<Integer> rotateNum = new ArrayList<>();
    rotateNum.add(rotationAngle);
    return inputCharToCommand.get(inputChar.toLowerCase());
  }


  private List<Command> goToStartLocation() {
    List<Command> commands = new ArrayList<>();
    commands.add(new SetHomeCommand(currLocation));
    commands.add(new SetPenUpCommand(new ArrayList<>()));
    commands.add(new GoHomeCommand(new ArrayList<>()));
    return commands;
  }

  /**
   * Purpose: Set the start location of the turtle to inputStartLocation and reset levelIncrement
   * based on the new start location. Assumptions: totalNumLevels is a reasonable value (i.e. much
   * smaller than inputStartLocations y value).
   *
   * @param inputStartLocation the x and y values to set this class's start location to.
   * @param totalNumLevels     the total number of levels to draw.
   */
  public void setStartLocation(int[] inputStartLocation, int totalNumLevels) {
    originalLocation.clear();
    originalLocation.add(inputStartLocation[0]);
    originalLocation.add(inputStartLocation[1]);
    levelIncrement =
        (TurtleWindowDisplay.PREF_WINDOW_SIZE - originalLocation.get(1)) / totalNumLevels;
  }

  /**
   * Purpose: Sets the movement command length.
   *
   * @param distance the value to set the movement command length to.
   */
  public void setMovementLength(int distance) {
    this.movementLength = distance;
  }

  /**
   * Purpose: Sets the rotation angle value.
   *
   * @param rotationAngle the value to set the rotation angle to.
   */
  public void setRotationAngle(int rotationAngle) {
    this.rotationAngle = rotationAngle;
  }
}

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.commands.*;
import view.TurtleWindowDisplay;

public class LSystemCommandRunner extends CommandModel {
  private int movementLength;
  private int rotationAngle;
  private List<Integer> originalLocation;
  private List<Integer> currLocation;
  private int levelIncrement;
  private boolean isFirstCommand;
  Map<String, List<Command>> inputCharToCommand;
  private LogoCommandModel logoCommandModel;

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

  public void putInInputCharToCommand(Map<String, List<String>> newSymbolSet) {
    for(String symbol : newSymbolSet.keySet()) {
      String parsedCommand = String.join(" ", newSymbolSet.get(symbol));
      parsedCommand = parsedCommand.replace("\"", "");
      parsedCommand = parsedCommand.toLowerCase();
      parsedCommand = parsedCommand.replace("length", "" + movementLength);
      parsedCommand = parsedCommand.replace("angle", "" + rotationAngle);
      List<Command> finalCommands = logoCommandModel.getCommandsFromInput(parsedCommand);
      inputCharToCommand.put(symbol, finalCommands);
    }
  }

//  public putNewCommandMapping(String command, List<>) {
//    List<Command> commands = new ArrayList<>();
//    List<Integer> args = new ArrayList<>();
//    commands.add(new SetPenDownCommand(new ArrayList<>()));
//    args.add(movementLength);
//    commands.add(new MoveForwardCommand(args));
//    inputCharToCommand.put("f", commands);
//  }

  @Override
  public List<Command> getCommandsFromInput(String input) throws InputMismatchException {
    List<Command> commands = new ArrayList<>();
    if(isFirstCommand) {
      currLocation = new ArrayList<>(originalLocation);
      commands.addAll(goToStartLocation());
      isFirstCommand = false;
    }
    for(String inputChar : input.split("(?!^)")) {
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

  public void setStartLocation(int[] inputStartLocation, int totalNumLevels) {
    originalLocation.clear();
    originalLocation.add(inputStartLocation[0]);
    originalLocation.add(inputStartLocation[1]);
    levelIncrement = (TurtleWindowDisplay.PREF_WINDOW_SIZE - originalLocation.get(0)) / totalNumLevels;
  }

  public void setMovementLength(int distance) {
    this.movementLength = distance;
  }

  public void setRotationAngle(int rotationAngle) {
    this.rotationAngle = rotationAngle;
  }
}

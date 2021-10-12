package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import model.commands.*;
import view.TurtleWindowDisplay;

public class LSystemCommandRunner extends CommandModel {
  private int movementLength;
  private int rotationAngle;
  private List<Integer> originalLocation;
  private List<Integer> currLocation;
  private int levelIncrement;
  private boolean isFirstCommand;

  public LSystemCommandRunner(int distance, int angle, int totalNumLevels) {
    movementLength = distance;
    rotationAngle = angle;
    originalLocation = new ArrayList<>();
    originalLocation.add(TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    originalLocation.add(TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    levelIncrement = (TurtleWindowDisplay.PREF_WINDOW_SIZE / 2) / totalNumLevels;
    isFirstCommand = true;
  }

  @Override
  public List<Command> getCommandsFromInput(String input) throws InputMismatchException {
    List<Command> commands = new ArrayList<>();
    if(isFirstCommand) {
      currLocation = new ArrayList<>(originalLocation);
      commands.addAll(goToStartLocation());
      isFirstCommand = false;
    }
    for(String inputChar : input.split("(?!^)")) {
      commands.add(getCommand(inputChar));
    }
    currLocation.set(1, currLocation.get(1) + levelIncrement);
    commands.addAll(goToStartLocation());
    return commands;
  }

  private Command getCommand(String inputChar) {
    List<Integer> rotateNum = new ArrayList<>();
    rotateNum.add(rotationAngle);
    return switch (inputChar) {
      case "f" -> new FCommand(movementLength);
      case "g" -> new GCommand(movementLength);
      case "a" -> new ACommand(movementLength);
      case "b" -> new BCommand(movementLength);
      case "+" -> new RotateRightCommand(rotateNum);
      case "-" -> new RotateLeftCommand(rotateNum);
      case "x" -> new StampCommand(new ArrayList<>());
      default -> throw new InputMismatchException();
    };
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

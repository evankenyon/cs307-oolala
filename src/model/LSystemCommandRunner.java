package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import model.commands.*;
import view.TurtleWindowDisplay;

public class LSystemCommandRunner extends CommandModel {

  private int movementLength;
  private int rotationAngle;
  private List<Integer> location;
  private int levelIncrement;

  public LSystemCommandRunner(int distance, int angle, int totalNumLevels) {
    movementLength = distance;
    rotationAngle = angle;
    location = new ArrayList<>();
    location.add(TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    location.add(TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    levelIncrement = (TurtleWindowDisplay.PREF_WINDOW_SIZE / 2) / totalNumLevels;
  }

  @Override
  public List<Command> getCommandsFromInput(String input) throws InputMismatchException {
    List<Command> commands = new ArrayList<>();
    for(String inputChar : input.split("(?!^)")) {
      commands.add(getCommand(inputChar));
    }
    location.set(1, location.get(1) + levelIncrement);
    commands.add(new SetHomeCommand(location));
    commands.add(new SetPenUpCommand(new ArrayList<>()));
    commands.add(new GoHomeCommand(new ArrayList<>()));
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


  public void goToStartLocation() {
//    new SetHomeCommand(location).runCommand(turtleController);
//    new GoHomeCommand(new ArrayList<>()).runCommand(turtleController);
  }

  public void setStartLocation(int[] inputStartLocation) {
    location.clear();
    location.add(inputStartLocation[0]);
    location.add(inputStartLocation[1]);
  }

  public void setMovementLength(int distance) {
    this.movementLength = distance;
  }

  public void setRotationAngle(int rotationAngle) {
    this.rotationAngle = rotationAngle;
  }
}

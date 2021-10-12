package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

// fix this
public class LSystemsModel extends AppModel {
  public static final int DEFAULT_MOVEMENT_LENGTH = 10;
  public static final int DEFAULT_ROTATION_ANGLE = 30;
  public static final int DEFAULT_LEVEL_NUM_MAX = 3;

  private int levelNumMax;
  private int levelNumCurr;
  private LSystemProgram lSystemProgram;
  private boolean shouldRun;

  public LSystemsModel() {
    shouldRun = false;
    lSystemProgram = new LSystemProgram();
    commandsToRun = new LinkedList<>();
    levelNumCurr = 0;
    levelNumMax = DEFAULT_LEVEL_NUM_MAX;
    commandModel = new LSystemCommandRunner(DEFAULT_MOVEMENT_LENGTH, DEFAULT_ROTATION_ANGLE, levelNumMax);
  }

  public void setMovementLength(int movementLength) {
    ((LSystemCommandRunner) commandModel).setMovementLength(movementLength);
  }

  public void setRotationAngle(int rotationAngle) {
    ((LSystemCommandRunner) commandModel).setRotationAngle(rotationAngle);
  }

  public void setShouldRun(boolean shouldRun) {
    if(shouldRun) {
      this.shouldRun = true;
    }
  }

  public void setLevelNumMax(int number) {
    this.levelNumMax = number;
  }

  @Override
  public void setHomeLocation(int x, int y) {
    ((LSystemCommandRunner) commandModel).setStartLocation(new int[]{x, y}, levelNumMax);
  }

  @Override
  public void handleTextInput(String input) throws InputMismatchException, NumberFormatException {
      lSystemProgram.parseInput(input);
    ((LSystemCommandRunner) commandModel).putInInputCharToCommand(lSystemProgram.getNewSymbolSet());
  }

  public void runNextCommand() {
    if(levelNumCurr >= levelNumMax) {
      shouldRun = false;
    }
    if(shouldRun && lSystemProgram.getIsValidProgram() && commandsToRun.isEmpty() && levelNumCurr < levelNumMax) {
      commandsToRun.addAll(commandModel.getCommandsFromInput(lSystemProgram.getNextLevel(levelNumCurr)));
      levelNumCurr++;
    }
    if(!commandsToRun.isEmpty()) {
      commandsToRun.remove().runCommand(turtleController);
    }
  }

  @Override
  public List<String> getCommandHistory() {
    return lSystemProgram.getCommandHistory();
  }

  @Override
  public void saveCommandsAsFile() throws IOException {
    lSystemProgram.saveCommandsAsFile();
  }

  @Override
  public void handleFileInput(File file) throws FileNotFoundException {
    lSystemProgram.handleFileInput(file);
  }
}

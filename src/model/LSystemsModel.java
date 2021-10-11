package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.commands.Command;
import view.TurtleWindowDisplay;

// fix this
public class LSystemsModel extends AppModel {
  private final int DEFAULT_MOVEMENT_LENGTH = 10;
  private final int DEFAULT_ROTATION_ANGLE = 30;
  private final int DEFAULT_LEVEL_NUM_MAX = 3;

  private int levelNumMax;
  private int levelNumCurr;
  private int numLevels;
  private LSystemProgram lSystemProgram;


  public LSystemsModel() {
    this("");
  }

  public LSystemsModel(String beginningRule) {
    lSystemProgram = new LSystemProgram();
    commandsToRun = new LinkedList<>();
    levelNumCurr = 0;
    levelNumMax = DEFAULT_LEVEL_NUM_MAX;
    commandModel = new LSystemCommandRunner(DEFAULT_MOVEMENT_LENGTH, DEFAULT_ROTATION_ANGLE, levelNumMax);
  }

  public void setLevelNumMax(int number) {
    this.levelNumMax = number;
  }

  @Override
  public void handleTextInput(String input) throws InputMismatchException, NumberFormatException {
    try {
      lSystemProgram.parseInput(input);
    } catch (Exception e) {
      // TODO: fix
      e.printStackTrace();
    }

  }

  public void runNextCommand() {
    if(lSystemProgram.getIsValidProgram() && commandsToRun.isEmpty() && levelNumCurr < levelNumMax) {
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

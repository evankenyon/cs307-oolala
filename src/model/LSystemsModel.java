package model;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.commands.Command;

// fix this
public class LSystemsModel extends AppModel {
  private final int DEFAULT_MOVEMENT_LENGTH = 10;
  private final int DEFAULT_ROTATION_ANGLE = 30;
  private final int[] DEFAULT_HOME = new int[]{0, 0};
  private int levelNumMax;
  private int levelNumCurr;
  private int numLevels;
  private LSystemProgram lSystemProgram;


  public LSystemsModel() {
    this("");
  }

  public LSystemsModel(String beginningRule) {
    commandModel = new LSystemCommandRunner(DEFAULT_MOVEMENT_LENGTH, DEFAULT_ROTATION_ANGLE, DEFAULT_HOME);
    lSystemProgram = new LSystemProgram();
    commandsToRun = new LinkedList<>();
    levelNumCurr = 0;
    levelNumMax = 5;
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
}

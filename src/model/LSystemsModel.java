package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.commands.Command;

// fix this
public class LSystemsModel extends AppModel {

  private String startingRule;
  private List<LSystemRules> ruleBook;
  private int levelNumMax;
  private int levelNumCurr;
  private int numLevels;
  private int movementLength;
  private int rotationAngle;
  private boolean stampBranchImage;
  private int[] location;
  private LSystemCommandRunner lsystemCommandRunner;
  private LSystemProgram lSystemProgram;
  private Queue<Command> commandsToRun;

  public LSystemsModel() {
    this("");
  }

  public LSystemsModel(String beginningRule) {
    startingRule = beginningRule.toLowerCase();
    lsystemCommandRunner = new LSystemCommandRunner(20, 55, new int[]{0, 0});
    lSystemProgram = new LSystemProgram();
    ruleBook = new ArrayList<>();
    commandsToRun = new LinkedList<>();
    numLevels = 1;
    levelNumCurr = 0;
    levelNumMax = 3;
    movementLength = 2;
    rotationAngle = 30;
    stampBranchImage = false;
    location = new int[]{0, 0};
  }

  public void setStartLocation(int[] inputStartLocation) {
    location = inputStartLocation;
  }

  public void setNumLevels(int number) {
    this.numLevels = number;
  }

  public void setMovementLength(int distance) {
    this.movementLength = distance;
  }

  public void setRotationAngle(int rotationAngle) {
    this.rotationAngle = rotationAngle;
  }

  public void setStampBranchImage(boolean stampBranchImage) {
    this.stampBranchImage = stampBranchImage;
  }

  public void setStartingRule(String startingRule) {
    this.startingRule = startingRule;
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
      commandsToRun.addAll(lsystemCommandRunner.getCommandsFromInput(lSystemProgram.getNextLevel(levelNumCurr)));
      levelNumCurr++;
    }
    if(!commandsToRun.isEmpty()) {
      commandsToRun.remove().runCommand(turtleController);
    }
  }

  public void createRule(String rulePassedIn) {
    LSystemRules newRule = new LSystemRules(rulePassedIn);
    ruleBook.add(newRule);
  }

  public void run(TurtleController turtleController) {
    lsystemCommandRunner.goToStartLocation();
  }

  private String findRule(String ruleId) {
    try {
      for (LSystemRules rule : ruleBook) {
        if (rule.getId().equals(ruleId)) {
          return rule.getRule();
        }
      }
    } catch (IllegalCallerException e) {
      throw new IllegalArgumentException("Rule Doesn't Exist");
    }
    throw new NullPointerException();
  }
}

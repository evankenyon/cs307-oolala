package model;

import java.util.ArrayList;
import java.util.List;

// fix this
public class LSystemsModel {

  private String startingRule;
  private List<LSystemRules> ruleBook;
  private int numLevels;
  private int movementLength;
  private int rotationAngle;
  private boolean stampBranchImage;
  private int[] location;
  private LSystemCommandRunner lsystemCommandRunner;

  public LSystemsModel() {
    this("");
  }

  public LSystemsModel(String beginningRule) {
    startingRule = beginningRule.toLowerCase();
    lsystemCommandRunner = new LSystemCommandRunner(10, 30, new int[]{0, 0});
    ruleBook = new ArrayList<>();
    numLevels = 1;
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

  public void createRule(String rulePassedIn) {
    LSystemRules newRule = new LSystemRules(rulePassedIn);
    ruleBook.add(newRule);
  }

  public void run(TurtleController turtleController) {
    turtleController.resetActiveTurtles();
    turtleController.addTurtleToActives(1);
//    lsystemCommandRunner = new LSystemCommandRunner(turtleController, movementLength, rotationAngle,
//        stampBranchImage, location);
    lsystemCommandRunner.goToStartLocation();
    moveTurtleRecursively(numLevels, startingRule);
  }

  public void moveTurtleRecursively(int levelNum, String ruleId) {
    String currRule = ruleId;
    lsystemCommandRunner.runLsysCommand(ruleId);
    levelNum--;
    while(levelNum > 0) {
      currRule = currRule.replace(ruleId, findRule(ruleId));
      for (String ruleChar : currRule.split("(?!^)")) {
        lsystemCommandRunner.runLsysCommand(ruleChar);
      }
      System.out.println();
      levelNum--;
    }
  }

  public String findRule(String ruleId) {
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

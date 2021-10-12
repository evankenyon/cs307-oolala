package model.lsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import model.AppModel;

/**
 * Purpose: Handle the creation of a L-system backend by checking run conditios, commands taken in,
 * look for variables set, and update LSystemCommandRunner's maps for the turtle movement
 * Dependencies: File, LinkedList, FileNotFoundException, InputMismatchException, IOException, List,
 * AppModel Example: Create a LSystemsModel in LSystemInfoDisplay in order to set the appropriate
 * variables so that the turtle move's in an algorithmic way based on the rules Other details: Takes
 * in input from LSystemInfoDisplay and File entered buttons and updates LSystemProgram with said
 * files to be parsed
 *
 * @author Haseeb Chaudhry
 * @author Evan Kenyon
 */
public class LSystemsModel extends AppModel {

  public static final int DEFAULT_MOVEMENT_LENGTH = 10;
  public static final int DEFAULT_ROTATION_ANGLE = 30;
  public static final int DEFAULT_LEVEL_NUM_MAX = 3;

  private int levelNumMax;
  private int levelNumCurr;
  private LSystemProgram lSystemProgram;
  private boolean shouldRun;

  /**
   * Create a LSystemModel Object to handle inputted files and parameter for L-system
   */
  public LSystemsModel() {
    shouldRun = false;
    lSystemProgram = new LSystemProgram();
    commandsToRun = new LinkedList<>();
    levelNumCurr = 0;
    levelNumMax = DEFAULT_LEVEL_NUM_MAX;
    commandModel = new LSystemCommandRunner(DEFAULT_MOVEMENT_LENGTH, DEFAULT_ROTATION_ANGLE,
        levelNumMax);
  }

  /**
   * This method updates the movement variable in LSystemCommandRunner since LSystemCommandRunner
   * runs the actual parse in commands
   *
   * @param movementLength an int to pass in the distance the turtle needs to be moved when movment
   *                       command is called
   */
  public void setMovementLength(int movementLength) {
    ((LSystemCommandRunner) commandModel).setMovementLength(movementLength);
  }

  /**
   * This method updates the rotation variable in LSystemCommandRunner in order to keep the turtle
   * rotating at set angles for each + and - command called
   *
   * @param rotationAngle an int to pass in the set rotation the turtle needs to rotate when a
   *                      rotation command is called
   */
  public void setRotationAngle(int rotationAngle) {
    ((LSystemCommandRunner) commandModel).setRotationAngle(rotationAngle);
  }

  /**
   * The method checks for any button presses to run LSystem if true, then it updates the variable
   * shouldRun to kick off other methods and start running the rule's given
   *
   * @param shouldRun boolean check on whether the run button for LSystem was clicked
   */
  public void setShouldRun(boolean shouldRun) {
    if (shouldRun) {
      this.shouldRun = true;
    }
  }

  /**
   * The method set's the max number of recursion's the fractal algorithm should do in order to draw
   * the pattern described It updates the levelNumMax variable for use when the algorithm is in
   * effect
   *
   * @param number int number of the number of recursions needed
   */
  public void setLevelNumMax(int number) {
    this.levelNumMax = number;
  }

  /**
   * The updates the home location of the turtle when specifically the l-system algorithim and
   * programs are running This is serperate from the Logo home for turtle's
   *
   * @param x int for the x location of the turtle's home when the code is running
   * @param y int for the y location of the turtle's home when the code is running
   */
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
    if (levelNumCurr >= levelNumMax) {
      shouldRun = false;
    }
    if (shouldRun && lSystemProgram.getIsValidProgram() && commandsToRun.isEmpty()
        && levelNumCurr < levelNumMax) {
      commandsToRun.addAll(
          commandModel.getCommandsFromInput(lSystemProgram.getNextLevel(levelNumCurr)));
      levelNumCurr++;
    }
    if (!commandsToRun.isEmpty()) {
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
    ((LSystemCommandRunner) commandModel).putInInputCharToCommand(lSystemProgram.getNewSymbolSet());
  }
}

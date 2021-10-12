package model;

/**
 * Purpose: This class organizes the state and behovior of each of the turtles that will be used to
 * draw on the applications.
 * Assumptions:
 * Dependencies: None
 * Example: When a user starts the program, or calls tell on a non-existing turtle, instantiate a
 * new turtle model. This turtle can then be used to draw on the screen by moving it, rotating it,
 * and can implement the various other commands outlined in the instructions.
 *
 * @author Luis Pereda
 */
public class TurtleModel {
  public static final int DEFAULT_X_POSITION = 400;
  public static final int DEFAULT_Y_POSITION = 400;

  // Coordinate [x,y] denoting the position of the turtle
  private final int[] myPosition;
  // A number from 0 to 2pi representing the angle of travel of the turtle
  private double myTrajectory;
  // If true, pen is down and drawing; if false, pen is up and not drawing
  private boolean penSetting;
  private int[] myHome;

  private final int myID;
  private boolean shouldStamp;
  private boolean shouldShow;

  /**
   * Create a TurtleModel with the initial location where we want the turtle to be initialized on the screen.
   *
   * @param initialPosition Passed as a x,y coordinate in an int array
   */
  public TurtleModel(int turtleID, int[] initialPosition) {
    myPosition = initialPosition;
    myTrajectory = 0;
    penSetting = true;
    myHome = new int[]{initialPosition[0], initialPosition[1]};
    myID = turtleID;
    shouldStamp = false;
    shouldShow = true;
  }

  /**
   * Create a TurtleModel with a default initial location in the center of the screen
   * @param turtleID
   */
  public TurtleModel(int turtleID) {
      this(turtleID, new int[]{DEFAULT_X_POSITION, DEFAULT_Y_POSITION});
  }

  /**
   * Purpose: Getter for the ID of the turtle
   *
   * @return Int representing the unique ID of the turtle.
   */
  public int getID() {
    return myID;
  }

  /**
   * Purpose: Getter for the position of the turtle at that moment
   *
   * @return Int array representing the x,y position of the turtle
   */
  public int[] getPosition() {
    return myPosition;
  }

  /**
   * Purpose: Getter for the angle at which the turtle is moving
   *
   * @return Double representing the angle at which the turtle is traveling, in degrees.
   */
  public double getTrajectory() {
    return myTrajectory * (180 / Math.PI);
  }

  /**
   * Purpose: Moves the turtle based on the distance provided by the user
   *
   * @param distance How many pixels the turtle will move
   */
  public void move(int distance) {
    int dx = (int) (distance * Math.cos(myTrajectory));
    int dy = (int) (distance * Math.sin(myTrajectory));
    myPosition[0] = myPosition[0] + dx;
    myPosition[1] = myPosition[1] + dy;
    keepTurtleInBounds();
  }

  private void keepTurtleInBounds() {
    if (myPosition[0] < 0) myPosition[0] = 0;
    if (myPosition[0] > 800) myPosition[0] = 800;
    if (myPosition[1] < 0) myPosition[1] = 0;
    if (myPosition[1] > 800) myPosition[1] = 800;
  }

  /**
   * Purpose: Rotate the turtle based on the angle in degrees provided by the user
   *
   * @param angle How many degrees turtle will rotate
   */
  public void rotate(int angle) {
    myTrajectory = myTrajectory + (angle * Math.PI / 180) % (2 * Math.PI);
  }

  /**
   * Purpose: Set whether the pen is drawing or not drawing
   *
   * @param penUpOrDown
   */
  public void setPen(boolean penUpOrDown) {
    penSetting = penUpOrDown;
  }

  /**
   * Purpose: Get whether the pen is down (drawing) or up (not drawing)
   *
   * @return Boolean describing whether the pen is drawing or not drawing.
   */
  public boolean getPen() {
    return penSetting;
  }

  /**
   * Purpose: Get whether the turtle should be stamping its image on to the screen. If true, then
   * set shouldStamp to false so that it should no longer stamp.
   * Assumptions: We are only looking to stamp once with the stamp command, which is why we set
   * shouldStamp to false after we get the value.
   *
   * @return
   */
  public boolean getShouldStamp() {
    if (shouldStamp) {
      shouldStamp = false;
      return true;
    }
    return false;
  }

  /**
   * Purpose: Tell the model that the turtle should stamp its image.
   */
  public void setShouldStampTrue() {
    shouldStamp = true;
  }

  /**
   * Purpose: Get whether the turtle should be hidden or shown
   *
   * @return Boolean which is true if the turtle is shown, and false if hidden
   */
  public boolean getShouldShow() {
    return shouldShow;
  }

  /**
   * Purpose: Setter to show or hide the turtle
   *
   * @param shouldShow Boolean which is true if turtle is shown or false if hidden
   */
  public void setShouldShow(boolean shouldShow) {
    this.shouldShow = shouldShow;
  }

  /**
   * Purpose: Set home location for turtle
   * Assumptions: Only positive values for x and y between 0 and 400 should be input by the user.
   *
   * @param homeLocation Int array representing the x,y coordinates of the turtle home location
   *                     being assigned
   */
  public void setHome(int[] homeLocation) throws IllegalArgumentException{
    for (int i : homeLocation) {
      if(i < 0 || i > 400) {
        throw new IllegalArgumentException();
      }
    }
    myHome = homeLocation;
  }

  /**
   * Purpose: Moves turtle to home position.
   */
  public void goHome() {
    myPosition[0] = myHome[0];
    myPosition[1] = myHome[1];
  }
}

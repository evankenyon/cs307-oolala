package model;

import java.util.Arrays;

public class TurtleModel {

  // Coordinate [x,y] denoting the position of the turtle
  private int[] myPosition;
  // A number from 0 to 2pi representing the angle of travel of the turtle
  private double myTrajectory;
  // If true, pen is down and drawing; if false, pen is up and not drawing
  private boolean penSetting;
  private int[] myHome;
  private int myID;
  private boolean shouldStamp;

  /**
   * Create a TurtleModel with the initial where we want the turtle to be initialized on the screen.
   * If no initial position is passed then turtle will be placed at [0,0]
   *
   * @param initialPosition
   */
  public TurtleModel(int turtleID, int[] initialPosition) {
    myPosition = initialPosition;
    myTrajectory = 0;
    penSetting = true;
    myHome = new int[]{0, 0};
    myID = turtleID;
    shouldStamp = false;
  }

  public TurtleModel(int turtleID) {
    this(turtleID, new int[]{0,0});
  }

  /**
   * Getter for the ID of the turtle
   *
   * @return
   */
  public int getID() {
    return myID;
  }

  /**
   * Getter for the position of the turtle at that moment
   *
   * @return
   */
  public int[] getPosition() {
    return myPosition;
  }

  /**
   * Getter for the angle at which the turtle is moving
   *
   * @return
   */
  public double getTrajectory() {
    return myTrajectory * (180 / Math.PI);
  }

  /**
   * Moves the turtle based on the distance provided by the user
   *
   * @param distance
   */
  public void move(int distance) {
    int dx = (int) (distance * Math.cos(myTrajectory));
    int dy = (int) (distance * Math.sin(myTrajectory));
    myPosition[0] = myPosition[0] + dx;
    myPosition[1] = myPosition[1] + dy;
  }

  /**
   * Rotate the turtle based on the angle provided by the user
   *
   * @param angle
   */
  public void rotate(int angle) {
    myTrajectory = myTrajectory + (angle * Math.PI / 180) % (2 * Math.PI);
  }

  /**
   * Set whether the pen is drawing or not drawing
   *
   * @param penUpOrDown
   */
  public void setPen(boolean penUpOrDown) {
    penSetting = penUpOrDown;
  }

  public boolean getPen() {
    return penSetting;
  }

  public boolean getShouldStamp() {
    if(shouldStamp) {
      shouldStamp = false;
      return true;
    }
    return false;
  }

  public void setShouldStampTrue() {
    shouldStamp = true;
  }

  /**
   * Set home location for turtle
   *
   * @param homeLocation
   */
  public void setHome(int[] homeLocation) {
    myHome = homeLocation;
  }

  /**
   * Turtle is moved to home
   */
  public void goHome() {
    myPosition[0] = myHome[0];
    myPosition[1] = myHome[1];
  }
}

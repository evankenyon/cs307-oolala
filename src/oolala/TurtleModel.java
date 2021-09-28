package oolala;

public class TurtleModel {

  private double[] myPosition;
  // A number from 0 to 2pi representing the angle of travel of the turtle
  private double myTrajectory;

  /**
   * Create a TurtleModel with the initial where we want the turtle to be initialized on the screen.
   * If no initial position is passed then turtle will be placed at [0,0]
   *
   * @param initialPosition
   */
  public TurtleModel(double[] initialPosition) {
    myPosition = initialPosition;
  }

  public TurtleModel() {
    double[] turtlePosition = new double[2];
    myPosition = turtlePosition;
  }

  /**
   * Getter for the position of the turtle at that moment
   *
   * @return
   */
  public double[] getPosition() {
    return myPosition;
  }

  /**
   * Moves the turtle based on the distance and angle provided by the user
   *
   * @param distance
   * @param angle
   */
  public void move(double distance, double angle) {
    double angleInRadians = angle * Math.PI / 180;
    double dx = distance * Math.cos(angleInRadians);
    double dy = distance * Math.sin(angleInRadians);
    myPosition[0] = myPosition[0] + dx;
    myPosition[1] = myPosition[1] + dy;
  }

}

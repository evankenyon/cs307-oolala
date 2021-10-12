package view;

import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import util.PropertiesLoader;

/**
 * Purpose: This class provides a visual representation of each turtle for the GUI that handles 
 * the movement of the turtle and the line it draws. 
 * Assumptions: Each TurtleDisplay should be paired with a TurtleModel that is responsible for its 
 * state and behavior. 
 * Dependencies: Properties, Node, Image, ImageView, Line, PropertiesLoader
 * 
 * Example: If the user calls TELL to create a new turtle, create a new instance of TurtleDisplay
 * as well as TurtleModel to handle the view of this turtle as well as its state and behavior. 
 * 
 * @author Haseeb Chaudhry
 */
public class TurtleDisplay extends DisplayComponent {

  private int penThickness;
  private final ImageView turtleImgView;
  private Image turtleImg;
  private final int id;
  private double ratio;
  private Color penColor;
  private int[] home;
  private final int DEFAULT_ANGLE = 0;
  private final int IMAGE_SIZE_FIT = 20;
  private final int ROTATION_FACTOR = 90;

  /**
   * Purpose: Create a new Turtle Display. 
   * @param id Int representing a unique ID for a turtle. Helps TurtleModel be matched with a 
   *           TurtleDisplay.
   * @param home Int array representing a x,y coordinate for the home location of the turtle. 
   */
  public TurtleDisplay(int id, int[] home) {
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/image.properties");
    turtleImg = new Image(props.getProperty("turtleImagePath"));
    turtleImgView = new ImageView(turtleImg);
    turtleImgView.setX(home[0]);
    turtleImgView.setY(home[1]);
    this.home = home;
    setAngle(DEFAULT_ANGLE);
    updateImageSize();
    penThickness = 1;
    this.id = id;
  }

  private void updateImageSize() {
    ratio = turtleImg.getHeight() / turtleImg.getWidth();
    turtleImgView.setFitHeight(IMAGE_SIZE_FIT / ratio);
    turtleImgView.setFitWidth(IMAGE_SIZE_FIT);
  }

  /**
   * Purpose: Sets the pen color of the turtle
   * @param penColor Pen color of turtle.
   */
  public void setPenColor(Color penColor) {
    this.penColor = penColor;
  }

  /**
   * Purpose: Move the turtle to the position defined by the first argument and if turtlePenDown is 
   * true, draw a line connecting the old position to the new position.
   * @param position Int[] representing an x,y coordinate where the turtle will go
   * @param turtlePenDown
   * @return
   */
  public Line setPositionAndDraw(int[] position, boolean turtlePenDown) {
    double oldX = turtleImgView.getX();
    double oldY = turtleImgView.getY();
    turtleImgView.setX(position[0]);
    turtleImgView.setY(position[1]);
    Line drawnLine;
    if (turtlePenDown && (oldX != turtleImgView.getX() || oldY != turtleImgView.getY())) {
      drawnLine = new Line(oldX, oldY, turtleImgView.getX(), turtleImgView.getY());
      drawnLine.setStrokeWidth(penThickness);
    } else {
      drawnLine = new Line();
    }
    drawnLine.setStroke(penColor);
    return drawnLine;
  }

  /**
   * Purpose: Set the home location of the turtle(s).
   * @param home Int[] representing the x,y coordinates of the new home location for the turtle. 
   */
  public void setHome(int[] home) {
    this.home[0] = home[0];
    this.home[1] = home[1];
  }

  /**
   * Purpose: Set the angle of trajectory of the turtle(s).
   * @param angle in degrees
   */
  public void setAngle(double angle) {
    turtleImgView.setRotate(ROTATION_FACTOR + angle);
  }

  /**
   * Purpose: Get the unique ID of a specific turtle.
   * @return int representing the turtle ID.
   */
  public int getId() {
    return id;
  }

  /**
   * Purpose: Set the pen thickness to a desired amount.
   * @param penThickness
   */
  public void setPenThickness(int penThickness) {
    this.penThickness = penThickness;
  }

  /**
   * Purpose: Set the image for the turtle.
   * @param img
   */
  public void setImage(Image img) {
    turtleImgView.setImage(img);
    turtleImg = img;
    updateImageSize();
  }

  /**
   * Gets the ImageView of the turtle while also setting the position of the turtle to the desired
   * and rotating it if needed.
   * @return ImageView representing the turtle display.
   */
  public ImageView getStillTurtleImage() {
    ImageView turtleImgViewStill = new ImageView(turtleImg);
    turtleImgViewStill.setFitHeight(IMAGE_SIZE_FIT / ratio);
    turtleImgViewStill.setFitWidth(IMAGE_SIZE_FIT);
    turtleImgViewStill.setX(turtleImgView.getX());
    turtleImgViewStill.setY(turtleImgView.getY());
    turtleImgViewStill.setRotate(turtleImgView.getRotate());
    return turtleImgViewStill;
  }

  /**
   * Getter for the ImageView of the turtle.
   * @return ImageView
   */
  public ImageView getImageView() {
    return turtleImgView;
  }

  /**
   * Set whether the display of the turtle should be shown or hidden.
   * @param shouldShow
   */
  public void setShowOrHide(boolean shouldShow) {
    if (shouldShow) {
      turtleImgView.setImage(turtleImg);
    } else {
      turtleImgView.setImage(null);
    }
  }

  /**
   * Getter for the ImageView of the turtle that conforms to the standard we have defined for
   * DisplayComponent, so that it can be easily retrieved using this method
   * @return
   */
  @Override
  public Node getDisplayComponentNode() {
    // Used pane here because of advice from
    // https://stackoverflow.com/questions/42939530/setx-and-sety-not-working-when-trying-to-position-images/42939857
    return getImageView();
  }
}

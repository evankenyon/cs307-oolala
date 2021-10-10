package view;

import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import util.PropertiesLoader;


public class TurtleDisplay extends DisplayComponent {

  private int penThickness;
  private final ImageView turtleImgView;
  private Image turtleImg;
  private final int id;
  private double ratio;
  private int[] home;
  private final int DEFAULT_ANGLE = 0;
  private final int IMAGE_SIZE_FIT = 20;
  private final int ROTATION_FACTOR = 90;

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

  public Line setPosition(int[] position, boolean turtlePenUp) {
    double oldX = turtleImgView.getX();
    double oldY = turtleImgView.getY();
    turtleImgView.setX(position[0]);
    turtleImgView.setY(position[1]);
    Line drawnLine;
    if (turtlePenUp) {
      drawnLine = new Line(oldX, oldY, turtleImgView.getX(), turtleImgView.getY());
    } else {
      drawnLine = new Line();
    }
    drawnLine.setStrokeWidth(penThickness);
    return drawnLine;
  }

  public void setHome(int[] home) {
    this.home[0] = home[0];
    this.home[1] = home[1];
  }

  public void setAngle(double angle) {
    turtleImgView.setRotate(ROTATION_FACTOR + angle);
  }

  public int getId() {
    return id;
  }

  public void setPenThickness(int i) {
    penThickness = i;
  }

  public void setImage(Image img) {
    turtleImgView.setImage(img);
    turtleImg = img;
    updateImageSize();
  }


  public ImageView getStillTurtleImage() {
    ImageView turtleImgViewStill = new ImageView(turtleImg);
    turtleImgViewStill.setFitHeight(IMAGE_SIZE_FIT / ratio);
    turtleImgViewStill.setFitWidth(IMAGE_SIZE_FIT);
    turtleImgViewStill.setX(turtleImgView.getX());
    turtleImgViewStill.setY(turtleImgView.getY());
    turtleImgViewStill.setRotate(turtleImgView.getRotate());
    return turtleImgViewStill;
  }

  public ImageView getImageView() {
    return turtleImgView;
  }

  public void setShowOrHide(boolean shouldShow) {
    if (shouldShow) {
      turtleImgView.setImage(turtleImg);
    } else {
      turtleImgView.setImage(null);
    }
  }

  @Override
  public Node getDisplayComponentNode() {
    // Used pane here because of advice from
    // https://stackoverflow.com/questions/42939530/setx-and-sety-not-working-when-trying-to-position-images/42939857
    return getImageView();
  }
}

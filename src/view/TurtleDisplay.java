package view;

import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import util.PropertiesLoader;


public class TurtleDisplay extends DisplayComponent {

  private int penThickness;
  private ImageView turtleImgView;
  private Image turtleImg;
  private int id;
  private double ratio;

  public TurtleDisplay(int id) {
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/image.properties");

    turtleImg = new Image(props.getProperty("turtleImagePath"));
    turtleImgView = new ImageView(turtleImg);
    setAngle(0);
    updateImageSize();
    penThickness = 1;
    this.id = id;
  }

  private void updateImageSize() {
    ratio = turtleImg.getHeight() / turtleImg.getWidth();
    turtleImgView.setFitHeight(20 / ratio);
    turtleImgView.setFitWidth(20);
  }

  public Line setPosition(int[] position, boolean turtlePenUp) {
    double oldX = turtleImgView.getX();
    double oldY = turtleImgView.getY();
    turtleImgView.setX(position[0]);
    turtleImgView.setY(position[1]);
    Line drawnLine;
    if (turtlePenUp) {
      drawnLine = new Line(oldX, oldY, position[0], position[1]);
    } else {
      drawnLine = new Line();
    }
    drawnLine.setStrokeWidth(penThickness);
    return drawnLine;
  }

  public void setAngle(double angle) {
    turtleImgView.setRotate(90 + angle);
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
    turtleImgViewStill.setFitHeight(20 / ratio);
    turtleImgViewStill.setFitWidth(20);
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

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
  private int[] turtleHome;
  private int id;
  private double ratio;

  public TurtleDisplay(int id) {
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/image.properties");

    Image turtleImg = new Image(props.getProperty("turtleImagePath"));
    turtleImgView = new ImageView(turtleImg);
    setAngle(0);
    updateImageSize(turtleImg);
    penThickness = 1;
    turtleHome = new int[]{0,0};
    this.id = id;
  }

  private void updateImageSize(Image turtleImg) {
    ratio = turtleImg.getHeight() / turtleImg.getWidth();
    turtleImgView.setFitHeight(20 / ratio);
    turtleImgView.setFitWidth(20);
  }

  public Line setPosition(int[] position, boolean turtlePenUp) {
    double oldX = turtleImgView.getX();
    double oldY = turtleImgView.getY();
    turtleImgView.setX(position[0]);
    turtleImgView.setY(position[1]);
    Line ret;
    if(turtlePenUp){
      ret = new Line(oldX, oldY, position[0], position[1]);
    } else {
      ret = new Line();
    }
    ret.setStrokeWidth(penThickness);
    return ret;
  }

  public void setAngle(double angle) {
    turtleImgView.setRotate(90 + angle);
  }

  public int getId() {
    return id;
  }

  public void setHomeLocation(int[] coordinates) {
    turtleHome = coordinates;
  }

  public void setPenThickness(int i) {
    penThickness = i;
  }

  public void setImage(Image img) {
    turtleImgView = new ImageView(img);
    updateImageSize(img);
  }

  public ImageView getStillTurtleImage() {
    ImageView turtleImgViewStill = new ImageView(turtleImgView.getImage());
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

  @Override
  public Node getDisplayComponentNode() {
    // Used pane here because of advice from
    // https://stackoverflow.com/questions/42939530/setx-and-sety-not-working-when-trying-to-position-images/42939857
    return getImageView();
  }
}

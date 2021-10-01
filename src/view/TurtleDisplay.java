package view;

import java.sql.Array;
import java.util.Properties;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import util.PropertiesLoader;


public class TurtleDisplay extends DisplayComponent {

  private int penThickness;
  private ImageView turtleImgView;
  private boolean draw;
  private int[] turtleHome;

  public TurtleDisplay() {
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/image.properties");
    Image turtleImg = new Image(props.getProperty("turtleImagePath"));
    turtleImgView = new ImageView(turtleImg);
    updateImageSize(turtleImg);
    penThickness = 1;
    draw = false;
    turtleHome = new int[]{0,0};
  }

  private void updateImageSize(Image turtleImg) {
    double ratio = turtleImg.getHeight() / turtleImg.getWidth();
    turtleImgView.setFitHeight(20 / ratio);
    turtleImgView.setFitWidth(20);
  }

  public Line moveTurtle(int[] distance) {
    double oldX = turtleImgView.getX();
    double oldY = turtleImgView.getY();
    turtleImgView.setX(oldX + distance[0]);
    turtleImgView.setY(oldY + distance[1]);
    Line ret;
    if(draw){
      ret = new Line(oldX, oldY, oldX + distance[0], oldY + distance[1]);
    } else {
      ret = new Line();
    }
    ret.setStrokeWidth(penThickness);
    return ret;
  }

  public void rotateTurtle(int angle) {
    turtleImgView.setRotate(turtleImgView.getRotate() + angle);
  }

  public void turtleDrawing(boolean isItDrawing) {
    draw = isItDrawing;
  }

  public void setHomeLocation(int[] coordinates) {
    turtleHome = coordinates;
  }

  public void moveTurtleToHome(){
    turtleImgView.setX(turtleHome[0]);
    turtleImgView.setY(turtleHome[1]);
  }

  public void setPenThickness(int i) {
    penThickness = i;
  }

  public void setImage(Image img) {
    turtleImgView = new ImageView(img);
    updateImageSize(img);
  }

  public ImageView getImageView(){
    return turtleImgView;
  }

  @Override
  public Node getDisplayComponentNode() {
    return turtleImgView;
  }
}

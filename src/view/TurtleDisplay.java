package view;

import java.util.Properties;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.canvas.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.PropertiesLoader;


public class TurtleDisplay extends DisplayComponent {

  private Canvas board;
  private final double SIZE = 500;
  private int penThickness = 1;
  private ImageView turtleImgView;

  public TurtleDisplay() {
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/image.properties");
    Image turtleImg = new Image(props.getProperty("turtleImagePath"));
    turtleImgView = new ImageView(turtleImg);
    rotateTurtle(90);
    updateImageSize(turtleImg);
    board = new Canvas();
  }

  private void updateImageSize(Image turtleImg) {
    double ratio = turtleImg.getHeight() / turtleImg.getWidth();
    turtleImgView.setFitHeight(20 / ratio);
    turtleImgView.setFitWidth(20);
  }

  public void moveTurtle(int[] distance) {
    turtleImgView.setX(turtleImgView.getX() + distance[0]);
    turtleImgView.setY(turtleImgView.getY() + distance[1]);
  }

  public void rotateTurtle(int angle) {
    turtleImgView.setRotate(turtleImgView.getRotate() + angle);
  }

  public void turtleDrawing(boolean isItDrawing) {
//    turtle.setPen(isItDrawing);
  }

  public void setHomeLocation(int[] coordinates) {
//    turtle.setHome(coordinates);
  }

  public void setPenThickness(int i) {
    penThickness = i;
  }

  public void setImage(Image img) {

  }

  @Override
  public Node getDisplayComponentNode() {
    // Used pane here because of advice from
    // https://stackoverflow.com/questions/42939530/setx-and-sety-not-working-when-trying-to-position-images/42939857
    Pane pane = new Pane(turtleImgView);
    pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    return new Pane(pane);
  }

  public void updateTurtleScreen() {

  }
}

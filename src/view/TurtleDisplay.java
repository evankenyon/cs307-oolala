package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import util.PropertiesLoader;


public class TurtleDisplay extends DisplayComponent {

  private int penThickness;
  private ImageView turtleImgView;
  private boolean draw;
  private int[] turtleHome;
  private List<Line> lines;
  private int id;
  private Pane canvas;

  public TurtleDisplay(int id) {
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/image.properties");

    Image turtleImg = new Image(props.getProperty("turtleImagePath"));
    turtleImgView = new ImageView(turtleImg);
    setAngle(0);
    updateImageSize(turtleImg);
    penThickness = 1;
    draw = true;
    turtleHome = new int[]{0,0};
    lines = new ArrayList<>();
    this.id = id;
  }

  private void updateImageSize(Image turtleImg) {
    double ratio = turtleImg.getHeight() / turtleImg.getWidth();
    turtleImgView.setFitHeight(20 / ratio);
    turtleImgView.setFitWidth(20);
  }

  public Line setPosition(int[] position) {
    double oldX = turtleImgView.getX();
    double oldY = turtleImgView.getY();
    turtleImgView.setX(position[0]);
    turtleImgView.setY(position[1]);
    Line ret;
    if(draw){
      ret = new Line(oldX, oldY, position[0], position[1]);
    } else {
      ret = new Line();
    }
    lines.add(ret);
    canvas.getChildren().add(ret);
    ret.setStrokeWidth(penThickness);
    System.out.println(ret);
    return ret;
  }

  public void setAngle(double angle) {
    turtleImgView.setRotate(90 + angle);
  }

  public int getId() {
    return id;
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
    // Used pane here because of advice from
    // https://stackoverflow.com/questions/42939530/setx-and-sety-not-working-when-trying-to-position-images/42939857
    canvas = new Pane();
    canvas.setPrefSize(400,400);
    canvas.setStyle("-fx-background-color: floralwhite;\n"
        + "  -fx-border-style: solid;");
    canvas.getChildren().add(turtleImgView);
    return new Pane(canvas);
  }
}

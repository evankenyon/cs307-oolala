package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.CommandModel;
import model.TurtleModel;

import java.net.URL;

public class LogoDisplay {

  private DisplayComponent turtleDisplay;
  private DisplayComponent commandDisplay;
  private DisplayComponent instructionsDisplay;
  private DisplayComponent turtleInfoDisplay;

  public LogoDisplay() {
    turtleDisplay = new TurtleDisplay();
    commandDisplay = new CommandDisplay();
    instructionsDisplay = new InstructionsDisplay();
    turtleInfoDisplay = new TurtleInfoDisplay();
  }

  public Scene makeScene(int width, int height) {
    BorderPane root = new BorderPane();
    root.setBottom(commandDisplay.getDisplayComponentNode());
    root.setCenter(turtleDisplay.getDisplayComponentNode());
    root.setLeft(instructionsDisplay.getDisplayComponentNode());
    return new Scene(root, width, height);
  }

  public void setTurtleImage() {
//        turtleDisplay.setImage(url);
  }

//  public void move(int[] distance) {
//    turtleDisplay.moveTurtle(distance);
//  }
//
//  public void rotate(int rot) {
//    turtleDisplay.rotateTurtle(rot);
//  }
//
//  public void penState(boolean bol) {
//    turtleDisplay.turtleDrawing(bol);
//  }


}

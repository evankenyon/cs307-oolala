package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CommandModel;
import model.TurtleModel;

import java.net.URL;

public class LogoDisplay {
  // Magic values borrowed from example_animation course gitlab repo
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private TurtleDisplay turtleDisplay;
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

//    root.getChildren().add();
    root.setBottom(commandDisplay.getDisplayComponentNode());
    root.setCenter(turtleDisplay.getDisplayComponentNode());
    // Figured out how to set background for specific parts of BorderPane from
    // https://stackoverflow.com/questions/18164695/insert-image-into-borderpane-as-background
    root.getCenter().setStyle("-fx-background-color: white;");
    root.setLeft(instructionsDisplay.getDisplayComponentNode());

    // Timeline setup borrowed from example_animation course gitlab repo
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();

    return new Scene(root, width, height);
  }

  private void step(double elapsedTime) {
//    int[] test = {-1, 1};
//    turtleDisplay.moveTurtle();
  }

}

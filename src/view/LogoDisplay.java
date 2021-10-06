package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.LogoModel;

public class LogoDisplay {

  // Magic values borrowed from example_animation course gitlab repo
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private final List<TurtleDisplay> turtleDisplays;
  private final CommandDisplay commandDisplay;
  private final DisplayComponent instructionsDisplay;
  private final DisplayComponent turtleInfoDisplay;
  private Group root;
  private final LogoModel logoModel;
  private Group turtlesAndLines;

  public LogoDisplay() {
    turtleDisplays = new ArrayList<>();
    turtleDisplays.add(new TurtleDisplay(1));
    commandDisplay = new CommandDisplay();
    instructionsDisplay = new InstructionsDisplay();
    turtleInfoDisplay = new TurtleInfoDisplay();
    logoModel = new LogoModel();
  }

  public Scene makeScene(int width, int height) {
    root = new Group();
    turtlesAndLines = new Group();
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      turtlesAndLines.getChildren().add(turtleDisplay.getDisplayComponentNode());
    }
    VBox vBox = new VBox();
    vBox.getChildren().add(instructionsDisplay.getDisplayComponentNode());
    vBox.getChildren().add(commandDisplay.getDisplayComponentNode());
    root.getChildren().add(new HBox(vBox, turtlesAndLines));

    // Timeline setup borrowed from example_animation course gitlab repo
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();

    return new Scene(root, width, height);
  }

  private void step(double elapsedTime) {
    if (commandDisplay.getHasCommandUpdated()) {
      logoModel.handleTextInput(commandDisplay.getCommand());
      for (TurtleDisplay turtleDisplay : turtleDisplays) {
        turtlesAndLines.getChildren()
            .add(turtleDisplay.setPosition(logoModel.getTurtlePosition(turtleDisplay.getId())));
        turtleDisplay.setAngle(logoModel.getTurtleTrajectory(turtleDisplay.getId()));
      }
    }
  }

}

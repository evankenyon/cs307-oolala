package view;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.LogoModel;

public class LogoDisplay {
  // Magic values borrowed from example_animation course gitlab repo
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private List<TurtleDisplay> turtleDisplays;
  private CommandDisplay commandDisplay;
  private DisplayComponent instructionsDisplay;
  private DisplayComponent turtleInfoDisplay;
  private GridPane root;
  private LogoModel logoModel;
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
    root = new GridPane();
    turtlesAndLines = new Group();
    for(TurtleDisplay turtleDisplay : turtleDisplays) {
      turtlesAndLines.getChildren().add(turtleDisplay.getDisplayComponentNode());
    }

    root.add(instructionsDisplay.getDisplayComponentNode(), 0, 0, 7, 10);
    root.add(commandDisplay.getDisplayComponentNode(), 0, 11, 7, 10);
    root.add(turtlesAndLines, 9, 1, 20, 10);

    // Timeline setup borrowed from example_animation course gitlab repo
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();

    return new Scene(root, width, height);
  }

  private void step(double elapsedTime) {
    if(commandDisplay.getHasCommandUpdated()) {
      logoModel.handleTextInput(commandDisplay.getCommand());
      for(TurtleDisplay turtleDisplay : turtleDisplays) {
        turtleDisplay.setPosition(logoModel.getTurtlePosition(turtleDisplay.getId()), logoModel.getTurtlePenDown(turtleDisplay.getId()));
        turtleDisplay.setAngle(logoModel.getTurtleTrajectory(turtleDisplay.getId()));
      }
    }
  }

}

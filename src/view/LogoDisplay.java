package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.LogoModel;
import model.TurtleModel;
import util.PropertiesLoader;

public class LogoDisplay {

  // Magic values borrowed from example_animation course gitlab repo
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;


  private final List<TurtleDisplay> turtleDisplays;
  private final CommandDisplay commandDisplay;
  private final DisplayComponent instructionsDisplay;
  private final DisplayComponent turtleInfoDisplay;
  private GridPane root;
  private final LogoModel logoModel;
  private Pane turtleWindow;
  private final Properties props;
  // Could store this data in file
  // Or a String to int map
  private final int[] instructDispGridLayout = new int[]{0, 0, 7, 10};
  private final int[] commandDispGridLayout = new int[]{0, 11, 7, 10};
  private final int[] turtleWindowGridLayout = new int[]{9, 1, 20, 10};
  private final int PREF_WINDOW_SIZE = 400;

  public LogoDisplay() {
    props = PropertiesLoader.loadProperties("./src/view/resources/logo.properties");
    turtleDisplays = new ArrayList<>();
    // Have default constructor that just sets id to 1
    turtleDisplays.add(new TurtleDisplay(1));
    commandDisplay = new CommandDisplay();
    instructionsDisplay = new InstructionsDisplay();
    turtleInfoDisplay = new TurtleInfoDisplay();
    logoModel = new LogoModel();
  }

  public Scene makeScene(int width, int height) {
    turtleWindowSetup();
    rootSetup();
    setupAnimation();
    return new Scene(root, width, height);
  }

  private void rootSetup() {
    root = new GridPane();
    root.add(instructionsDisplay.getDisplayComponentNode(), instructDispGridLayout[0],
        instructDispGridLayout[1],
        instructDispGridLayout[2], instructDispGridLayout[3]);
    root.add(commandDisplay.getDisplayComponentNode(), commandDispGridLayout[0],
        commandDispGridLayout[1],
        commandDispGridLayout[2], commandDispGridLayout[3]);
    root.add(turtleWindow, turtleWindowGridLayout[0], turtleWindowGridLayout[1],
        turtleWindowGridLayout[2],
        turtleWindowGridLayout[3]);
  }

  private void turtleWindowSetup() {
    Group turtleDisplaysGroup = new Group();
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      turtleDisplaysGroup.getChildren().add(turtleDisplay.getDisplayComponentNode());
    }
    turtleWindow = new Pane();
    turtleWindow.setPrefSize(PREF_WINDOW_SIZE, PREF_WINDOW_SIZE);
    turtleWindow.setStyle("-fx-background-color: floralwhite;\n"
        + "  -fx-border-style: solid;");
    turtleWindow.getChildren().addAll(turtleDisplaysGroup);
  }

  private void setupAnimation() {
    // Timeline setup borrowed from example_animation course gitlab repo
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private void step(double elapsedTime) {
    handleCommandInputted();
    handleFileInputted();
    handleRunNextCommand();
    handleFileSave();
  }

  private void handleFileSave() {
    if (commandDisplay.shouldSaveAsFile()) {
      try {
        logoModel.saveCommandsAsFile();
      } catch (Exception e) {
        // TODO: fix
        showError();
      }
    }
  }

  private void handleCommandInputted() {
    if (commandDisplay.getHasCommandUpdated()) {
      try {
        logoModel.handleTextInput(commandDisplay.getCommand());
      } catch (Exception e) {
        showError();
      }
    }
  }

  private void handleFileInputted() {
    if (commandDisplay.getIsFileUploaded()) {
      try {
        logoModel.handleFileInput(commandDisplay.getUploadedCommandFile());
      } catch (Exception e) {
        // TODO: change
        showError();
      }
    }
  }

  private void handleRunNextCommand() {
    commandDisplay.updateCommandHistory(logoModel.getCommandHistory());
    logoModel.runNextCommand();
    addNewTurtles();
    updateTurtleWindowAndDisplays();
  }

  private void addNewTurtles() {
    if (logoModel.hasNewTurtles()) {
      for (TurtleModel turtleModel : logoModel.getNewTurtles()) {
        TurtleDisplay newTurtleDisplay = new TurtleDisplay(turtleModel.getID());
        turtleDisplays.add(newTurtleDisplay);
        turtleWindow.getChildren().add(newTurtleDisplay.getDisplayComponentNode());
      }
    }
  }

  private void updateTurtleWindowAndDisplays() {
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      if (isTurtleActive(turtleDisplay)) {
        updateTurtleWindow(turtleDisplay);
        updateTurtleDisplay(turtleDisplay);
      }
    }
  }

  private void updateTurtleDisplay(TurtleDisplay turtleDisplay) {
    turtleDisplay.setAngle(logoModel.getTurtleTrajectory(turtleDisplay.getId()));
    turtleDisplay.setShowOrHide(logoModel.getTurtleShouldShow(turtleDisplay.getId()));
  }

  private void updateTurtleWindow(TurtleDisplay turtleDisplay) {
    turtleWindow.getChildren().add(
        turtleDisplay.setPosition(logoModel.getTurtlePosition(turtleDisplay.getId()),
            logoModel.getTurtlePenDown(
                turtleDisplay.getId())));
    if (logoModel.getShouldTurtleStamp(turtleDisplay.getId())) {
      turtleWindow.getChildren().add(turtleDisplay.getStillTurtleImage());
    }
  }

  private boolean isTurtleActive(TurtleDisplay turtleDisplay) {
    return logoModel.isTurtleActive(turtleDisplay.getId());
  }

  //Borrowed from lab_browser course gitlab repo
  private void showError() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(props.getProperty("errorMessage"));
    alert.show();
  }
}

package view;

import java.lang.ProcessHandle.Info;
import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.AppModel;

public abstract class AppDisplay {

  public static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/";
  public static final String DEFAULT_RESOURCE_FOLDER = "/view/resources/";
  public static final String STYLESHEET = "Default.css";

  // FRAMES_PER_SECOND and SECOND_DELAY values borrowed from example_animation course gitlab repo
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private final int[] instructDispGridLayout = new int[]{0, 0, 7, 10};
  private final int[] commandDispGridLayout = new int[]{0, 11, 7, 10};
  private final int[] turtleWindowGridLayout = new int[]{9, 1, 20, 10};

  protected CommandDisplay commandDisplay;
  protected ClearDisplay clearDisplay;
  protected DisplayComponent instructionsDisplay;
  protected TurtleWindowDisplay turtleWindowDisplay;
  protected SetColorDisplay setPenColorDisplay;
  protected SetColorDisplay setBackgroundColorDisplay;
  protected GridPane root;
  protected AppModel model;
  protected Properties props;
  protected InfoDisplay infoDisplay;

  /**
   * Purpose: Create a new AppDisplay that will be organized by a GridPane root.
   */
  public AppDisplay() {
    root = new GridPane();
    setupDisplay();
  }

  /**
   * Purpose: This method will return a scene containing the LogoDisplay so that the application can
   * be run in a Main.
   *
   * @param width  Int representing the width of the scene.
   * @param height Int representing the height of the scene.
   * @return Scene containing the LogoDisplay.
   */
  public Scene makeScene(int width, int height) {
    setupAnimation();
    Scene scene = new Scene(root, width, height);
    scene.getStylesheets().add(DEFAULT_RESOURCE_FOLDER + STYLESHEET);
    return scene;
  }

  protected void setupDisplay() {
    commandDisplay = new CommandDisplay();
    instructionsDisplay = new InstructionsDisplay();
    turtleWindowDisplay = new TurtleWindowDisplay();
    clearDisplay = new ClearDisplay();
    setPenColorDisplay = new SetColorDisplay(props.getProperty("penColorLabel"));
    setBackgroundColorDisplay = new SetColorDisplay(props.getProperty("backgroundColorLabel"));
  }

  protected void setupAnimation() {
    // Timeline setup borrowed from example_animation course gitlab repo
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  protected void step(double elapsedTime) {
    handleReset();
    handleCommandInputted();
    handleFileInputted();
    handleUpdatePen();
    turtleWindowDisplay.updateBackgroundColor(setBackgroundColorDisplay.getColor());
    model.setHomeLocation(infoDisplay.getHomeX(), infoDisplay.getHomeY());
    handleRunNextCommand();
    handleFileSave();
    handleImageUploaded();
  }

  private void handleReset() {
    if (clearDisplay.getShouldReset()) {
      setupDisplay();
    }
  }

  private void handleCommandInputted() {
    if (commandDisplay.getHasCommandUpdated()) {
      try {
        model.handleTextInput(commandDisplay.getCommand());
      } catch (Exception e) {
        e.printStackTrace();
        showError();
      }
    }
  }

  private void handleFileInputted() {
    if (commandDisplay.getIsFileUploaded()) {
      try {
        model.handleFileInput(commandDisplay.getUploadedCommandFile());
      } catch (Exception e) {
        // TODO: change
        e.printStackTrace();
        showError();
      }
    }
  }

  private void handleRunNextCommand() {
    commandDisplay.updateCommandHistory(model.getCommandHistory());
    model.runNextCommand();
    if (model.hasNewTurtles()) {
      turtleWindowDisplay.addNewTurtles(model.getNewTurtles());
    }
    turtleWindowDisplay.updateTurtleWindowAndDisplays(model.getActiveTurtles());
  }

  private void handleFileSave() {
    if (commandDisplay.shouldSaveAsFile()) {
      try {
        model.saveCommandsAsFile();
      } catch (Exception e) {
        // TODO: fix
        e.printStackTrace();
        showError();
      }
    }
  }

  protected abstract void handleUpdatePen();

  protected void handleImageUploaded() {
    if (infoDisplay.getIsImageUploaded()) {
      try {
        turtleWindowDisplay.updateActiveTurtlesImage(model.getActiveTurtles(),
            infoDisplay.getUploadedImage());
      } catch (Exception e) {
        showError();
      }
    }
  }

  protected void rootSetup() {
    root.add(instructionsDisplay.getDisplayComponentNode(), instructDispGridLayout[0],
        instructDispGridLayout[1],
        instructDispGridLayout[2], instructDispGridLayout[3]);
    root.add(commandDisplay.getDisplayComponentNode(), commandDispGridLayout[0],
        commandDispGridLayout[1],
        commandDispGridLayout[2], commandDispGridLayout[3]);
    root.add(turtleWindowDisplay.getDisplayComponentNode(), turtleWindowGridLayout[0],
        turtleWindowGridLayout[1],
        turtleWindowGridLayout[2],
        turtleWindowGridLayout[3]);
    root.add(clearDisplay.getDisplayComponentNode(), 9, 12, 1, 1);
    root.add(setPenColorDisplay.getDisplayComponentNode(), 10, 12, 1, 1);
    root.add(setBackgroundColorDisplay.getDisplayComponentNode(), 11, 12, 1, 1);
    root.getStyleClass().add("grid-pane");
  }

  //Borrowed from lab_browser course gitlab repo
  void showError() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(props.getProperty("errorMessage"));
    alert.show();
  }

}

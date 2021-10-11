package view;

import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.AppModel;
import model.LSystemsModel;
import model.LogoModel;
import util.PropertiesLoader;

/**
 * Purpose: This class represents the high level UI of the Logo IDE. This program organizes the
 * scene, the many view elements that go into it, and when the program is run in Main this class is
 * what will be instantiated and used to make the scene for the whole program. This class also calls
 * on a backend LogoModel to do the work that is needed for the view elements to act as they should.
 * Assumptions: The LogoModel relies on many other classes to work properly, so the assumption is
 * that these other classes will be working properly and will be thoroughly tested to make it easy
 * to find any bug.
 * Dependencies: List, Properties, KeyFrame, Timeline, Group, Scene, Alert, AlertType, GridPane,
 * Pane, Duration, LogoModel, PropertiesLoader
 *
 * Example: In a Main, if you want to run a Logo IDE, then instantiate a LogoDisplay class and
 * pass the scene returned by makeScene() to the setScene() method of the stage. After this run
 * show() on stage to run the application.
 *
 * @author Evan Kenyon
 */
public class LogoDisplay {

  // Magic values borrowed from example_animation course gitlab repo
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/";
  public static final String LOGO_RESOURCES_PACKAGE = DEFAULT_RESOURCES_PACKAGE + "logo/";
  public static final String DEFAULT_RESOURCE_FOLDER = "/view/resources/";
  public static final String STYLESHEET = "Default.css";

  private CommandDisplay commandDisplay;
  private ClearDisplay clearDisplay;
  
  private DisplayComponent instructionsDisplay;
  private TurtleInfoDisplay turtleInfoDisplay;
  private TurtleWindowDisplay turtleWindowDisplay;
  private SetColorDisplay setPenColorDisplay;
  private SetColorDisplay setBackgroundColorDisplay;
  private GridPane root;
  private AppModel logoModel;
  private Properties props;
  // Could store this data in file
  // Or a String to int map
  private final int[] instructDispGridLayout = new int[]{0,0,7,10};
  private final int[] commandDispGridLayout = new int[]{0,11,7,10};
  private final int[] turtleWindowGridLayout = new int[]{9,1,20,10};
  private final int[] turtInfoDispGridLayout = new int[]{9,11,7,10};
  private final int PREF_WINDOW_SIZE = 400;

  /**
   * Purpose: Create a new LogoDisplay that will be organized by a GridPane root.
   */
  public LogoDisplay() {
    root = new GridPane();
    setupLogoDisplay();
  }

  private void setupLogoDisplay() {
    props = PropertiesLoader.loadProperties(LOGO_RESOURCES_PACKAGE + "English.properties");
    commandDisplay = new CommandDisplay();
    instructionsDisplay = new InstructionsDisplay();
    turtleInfoDisplay = new TurtleInfoDisplay();
    logoModel = new LSystemsModel();
    turtleWindowDisplay = new TurtleWindowDisplay();
    clearDisplay = new ClearDisplay();
    setPenColorDisplay = new SetColorDisplay(props.getProperty("penColorLabel"));
    setBackgroundColorDisplay = new SetColorDisplay(props.getProperty("backgroundColorLabel"));
    rootSetup();
  }

  /**
   * Purpose: This method will return a scene containing the LogoDisplay so that the application
   * can be run in a Main.
   * @param width Int representing the width of the scene.
   * @param height Int representing the height of the scene.
   * @return Scene containing the LogoDisplay.
   */
  public Scene makeScene(int width, int height) {
    setupAnimation();
    Scene scene = new Scene(root, width, height);
    scene.getStylesheets().add(DEFAULT_RESOURCE_FOLDER + STYLESHEET);
    return scene;
  }

  private void rootSetup() {
    root.add(instructionsDisplay.getDisplayComponentNode(), instructDispGridLayout[0],
        instructDispGridLayout[1],
        instructDispGridLayout[2], instructDispGridLayout[3]);
    root.add(commandDisplay.getDisplayComponentNode(), commandDispGridLayout[0],
        commandDispGridLayout[1],
        commandDispGridLayout[2], commandDispGridLayout[3]);
    root.add(turtleWindowDisplay.getDisplayComponentNode(), turtleWindowGridLayout[0], turtleWindowGridLayout[1],
        turtleWindowGridLayout[2],
        turtleWindowGridLayout[3]);
    root.add(turtleInfoDisplay.getDisplayComponentNode(), turtInfoDispGridLayout[0], turtInfoDispGridLayout[1],
            turtInfoDispGridLayout[2], turtInfoDispGridLayout[3]);

    root.add(clearDisplay.getDisplayComponentNode(), 9, 12, 1, 1);
    root.add(setPenColorDisplay.getDisplayComponentNode(), 10, 12, 1, 1);
    root.add(setBackgroundColorDisplay.getDisplayComponentNode(), 11, 12, 1, 1);
    root.getStyleClass().add("grid-pane");
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
    handleReset();
    handleCommandInputted();
//    handleFileInputted();
    handleUpdatePen();
    turtleWindowDisplay.updateBackgroundColor(setBackgroundColorDisplay.getColor());
    handleRunNextCommand();
//    handleFileSave();
  }

  private void handleReset() {
    if(clearDisplay.getShouldReset()) {
      setupLogoDisplay();
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
//        logoModel.handleFileInput(commandDisplay.getUploadedCommandFile());
      } catch (Exception e) {
        // TODO: change
        showError();
      }
    }
  }

  private void handleUpdatePen() {
    turtleWindowDisplay.updateActiveTurtlesPens(logoModel.getActiveTurtles(),
        setPenColorDisplay.getColor(), turtleInfoDisplay.getPenThicknesss());
  }

    private void handleRunNextCommand() {
    commandDisplay.updateCommandHistory(logoModel.getCommandHistory());
    logoModel.runNextCommand();
//    if (logoModel.hasNewTurtles()) {
//      turtleWindowDisplay.addNewTurtles(logoModel.getNewTurtles());
//    }
    turtleWindowDisplay.updateTurtleWindowAndDisplays(logoModel.getActiveTurtles());
  }

  private void handleFileSave() {
    if (commandDisplay.shouldSaveAsFile()) {
      try {
//        logoModel.saveCommandsAsFile();
      } catch (Exception e) {
        // TODO: fix
        showError();
      }
    }
  }

  //Borrowed from lab_browser course gitlab repo
  private void showError() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(props.getProperty("errorMessage"));
    alert.show();
  }
}

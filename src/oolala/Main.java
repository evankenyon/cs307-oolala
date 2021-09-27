package oolala;


import java.awt.Dimension;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.CommandDisplay;
import view.InstructionsDisplay;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  public static final String TITLE = "Logo Application";
  public static final Dimension DEFAULT_SIZE = new Dimension(1000, 800);

  /**
   * A method to test (and a joke :).
   */
  public double getVersion() {
    return 0.001;
  }

  /**
   * Start of the program.
   */
  public void start(Stage stage) {
    CommandDisplay commandDisplay = new CommandDisplay();
    InstructionsDisplay instructionsDisplay = new InstructionsDisplay();
    // give the window a title
    stage.setTitle(TITLE);
    // add our user interface components to Frame and show it
    BorderPane root = new BorderPane();
    root.setCenter(commandDisplay.getDisplayComponentNode());
    root.setLeft(instructionsDisplay.getDisplayComponentNode());
    stage.setScene(new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    stage.show();
  }
}

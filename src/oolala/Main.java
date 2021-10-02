package oolala;

import java.awt.Dimension;
import javafx.application.Application;
import javafx.stage.Stage;
import view.LogoDisplay;

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
    // Borrowed this setup code from lab_browser course gitlab repo
    LogoDisplay logoDisplay = new LogoDisplay();
    stage.setTitle(TITLE);
    stage.setScene(logoDisplay.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    stage.show();
  }
}

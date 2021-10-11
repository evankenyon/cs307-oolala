package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class LogoDisplayTest extends DukeApplicationTest {

  // keep only if needed to call application methods in tests
//  private CommandDisplay commandDisplay;
  // keep GUI components used in multiple tests
  private TextInputControl commandInput;
  private LogoDisplay logoDisplay;
  private ColorPicker choosePenColor;
  private ColorPicker chooseBackgroundColor;
  private Pane turtleWindow;

  // Below comment borrowed from Prof. Duvall
  // this method is run BEFORE EACH test to set up application in a fresh state
  @Override
  public void start(Stage stage) {
    logoDisplay = new LogoDisplay();

    stage.setScene(logoDisplay.makeScene(800, 800));
    stage.show();

    // components that will be reused in different test
    // Borrowed setup and comment above from example_testfx course gitlab repo
    commandInput = lookup("#Command-Input").query();
    choosePenColor = lookup("#Set-Color-0").query();
    chooseBackgroundColor = lookup("#Set-Color-1").query();
    turtleWindow = lookup("#Turtle-Window").query();
  }

  @Test
  public void testPenChangeRed () throws InterruptedException {
    Color expected = Color.RED;
    String command = "fd 50";
    List<String> commands = new ArrayList<>();
    commands.add(command);
    commandInput.clear();
    // Below line from example_testfx course gitlab repo
    setValue(choosePenColor, expected);
    clickOn(commandInput).write(command);
    clickOn(commandInput).write(KeyCode.ENTER.getChar());
    assertEquals(expected, ((Line) turtleWindow.getChildren().get(turtleWindow.getChildren().size() - 1)).getStroke());
  }

  @Test
  public void testBackgroundChangeRed () {
    Color expected = Color.RED;
    // Below line from example_testfx course gitlab repo
    setValue(chooseBackgroundColor, expected);
    assertEquals(expected, turtleWindow.getBackground().getFills().get(0).getFill());
  }
}
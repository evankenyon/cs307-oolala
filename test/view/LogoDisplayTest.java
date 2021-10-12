package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
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
  private Button resetButton;

  // Below comment borrowed from Prof. Duvall
  // this method is run BEFORE EACH test to set up application in a fresh state
  @Override
  public void start(Stage stage) {
    logoDisplay = new LogoDisplay();

    stage.setScene(logoDisplay.makeScene(1600, 1000));
    stage.show();

    // components that will be reused in different test
    // Borrowed setup and comment above from example_testfx course gitlab repo
    commandInput = lookup("#Command-Input").query();
    turtleWindow = lookup("#Turtle-Window").query();
    resetButton = lookup("#Reset-Button").queryButton();
  }

  @Test
  public void testSetHomeBasic() {
    int expectedX = 40;
    int expectedY = 50;
    TextInputControl setHomeX = lookup("#Arg-Value-Display-0").query();
    TextInputControl setHomeY = lookup("#Arg-Value-Display-1").query();
    setHomeX.clear();
    setHomeY.clear();
    clickOn(setHomeX).write("" + expectedX);
    clickOn(setHomeX).write(KeyCode.ENTER.getChar());
    clickOn(setHomeY).write("" + expectedY);
    clickOn(setHomeY).write(KeyCode.ENTER.getChar());
    String command = "home";
    List<String> commands = new ArrayList<>();
    commands.add(command);
    commandInput.clear();
    clickOn(commandInput).write(command);
    clickOn(commandInput).write(KeyCode.ENTER.getChar());
    ImageView turtleImgView = lookup("#Turtle-Img-View").query();
    assertEquals(expectedX, turtleImgView.getX());
    assertEquals(expectedY, turtleImgView.getY());
  }

  @Test
  public void testPenChangeRed () throws InterruptedException {
    choosePenColor = lookup("#Set-Color-2").query();
    chooseBackgroundColor = lookup("#Set-Color-3").query();
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
    choosePenColor = lookup("#Set-Color-4").query();
    chooseBackgroundColor = lookup("#Set-Color-5").query();
    Color expected = Color.RED;
    // Below line from example_testfx course gitlab repo
    setValue(chooseBackgroundColor, expected);
    assertEquals(expected, turtleWindow.getBackground().getFills().get(0).getFill());
  }

}
package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.logo.LogoDisplay;

public class ClearDisplayTest extends DukeApplicationTest {

  // keep only if needed to call application methods in tests
//  private CommandDisplay commandDisplay;
  // keep GUI components used in multiple tests
  private TextInputControl commandInput;
  private ListView<String> prevCommands;
  private LogoDisplay logoDisplay;
  private Button resetButton;


  // this method is run BEFORE EACH test to set up application in a fresh state
  @Override
  public void start(Stage stage) {
    // create application and add scene for testing to given stage
//    GridPane root = new GridPane();
//    commandDisplay = new CommandDisplay();
    logoDisplay = new LogoDisplay();

//    root.add(commandDisplay.getDisplayComponentNode(), 0 ,0);
    stage.setScene(logoDisplay.makeScene(800, 800));
    stage.show();

    // components that will be reused in different test
    // Borrowed setup and comment above from example_testfx course gitlab repo
    commandInput = lookup("#Command-Input").query();
    prevCommands = lookup("#Prev-Commands-0").queryListView();
    resetButton = lookup("#Reset-Button").queryButton();

  }

  @Test
  public void testClearSimpleCommand() throws InterruptedException {
    String expected = "fd 50";
    List<String> command = new ArrayList<>();
    command.add(expected);
    commandInput.clear();
    clickOn(commandInput).write(expected);
    clickOn(commandInput).write(KeyCode.ENTER.getChar());
    // Borrowed from example_testfx course gitlab repo
    assertEquals(expected, prevCommands.getItems().get(prevCommands.getItems().size() - 1));
    clickOn(resetButton);
    Thread.sleep(2000);
    prevCommands = lookup("#Prev-Commands-1").queryListView();
    assertTrue(prevCommands.getItems().isEmpty());
  }


}
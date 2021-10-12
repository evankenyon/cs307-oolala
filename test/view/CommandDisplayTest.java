package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

// Choice to extend borrowed from example_testfx course gitlab repo
public class CommandDisplayTest extends DukeApplicationTest {

  // keep only if needed to call application methods in tests
  private CommandDisplay commandDisplay;
  // keep GUI components used in multiple tests
  private TextInputControl commandInput;
  private Button saveCommandsFile;


  // this method is run BEFORE EACH test to set up application in a fresh state
  @Override
  public void start(Stage stage) {
    // create application and add scene for testing to given stage
    GridPane root = new GridPane();
    commandDisplay = new CommandDisplay();
    root.add(commandDisplay.getDisplayComponentNode(), 0 ,0);
    stage.setScene(new Scene(root, 800, 800));
    stage.show();

    // components that will be reused in different test
    // Borrowed setup and comment above from example_testfx course gitlab repo
    commandInput = lookup("#Command-Input").query();
    saveCommandsFile = lookup("#Save-Commands-File").query();
  }

  @Test
  public void testCommandInputDefault () {
    assertFalse(commandDisplay.getHasCommandUpdated());
    String expected = "Enter a command here!";
    assertLabelText(expected);
  }


  // For some reason this does not work with #Prev-Commands-1, so
  // just need to run this individually instead of with whole suite
  @Test
  public void testCommandInputForward () {
    ListView<String> prevCommands = lookup("#Prev-Commands-0").queryListView();
    assertFalse(commandDisplay.getHasCommandUpdated());
    String expected = "fd 50";
    commandInput.clear();
    clickOn(commandInput).write(expected);
    assertLabelText(expected);
    // Borrowed from example_testfx course gitlab repo
    clickOn(commandInput).write(KeyCode.ENTER.getChar());
    assertEquals(expected, prevCommands.getItems().get(prevCommands.getItems().size() - 1));
    assertTrue(commandDisplay.getHasCommandUpdated());
    assertEquals(expected, commandDisplay.getCommand());
    assertFalse(commandDisplay.getHasCommandUpdated());
  }

  @Test
  public void testPrevCommandsDefault () {
    ListView<String> prevCommands = lookup("#Prev-Commands-2").queryListView();
    assertTrue(prevCommands.getItems().isEmpty());
  }

  @Test
  public void testShouldSaveAsFile () {
    assertFalse(commandDisplay.shouldSaveAsFile());
    clickOn(saveCommandsFile);
    assertTrue(commandDisplay.shouldSaveAsFile());
  }

  // Borrowed from example_testfx course gitlab repo
  private void assertLabelText (String expected) {
    assertEquals(expected, commandInput.getText());
  }

}
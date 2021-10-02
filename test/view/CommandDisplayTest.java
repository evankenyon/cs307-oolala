package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class CommandDisplayTest extends DukeApplicationTest {

  // keep only if needed to call application methods in tests
  private CommandDisplay commandDisplay;
  // keep GUI components used in multiple tests
  private TextInputControl commandInput;
  private ListView<String> prevCommands;


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
    prevCommands = lookup("#Prev-Commands").queryListView();

  }

  @Test
  public void testCommandInputDefault () {
    String expected = "Enter a command here!";
    assertLabelText(expected);
  }

  // Borrowed from example_testfx course gitlab repo
  private void assertLabelText (String expected) {
    assertEquals(expected, commandInput.getText());
  }

}
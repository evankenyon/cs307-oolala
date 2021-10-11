package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import util.PropertiesLoader;

// Choice to extend borrowed from example_testfx course gitlab repo
public class InstructionsDisplayTest extends DukeApplicationTest {

  // keep only if needed to call application methods in tests
  private InstructionsDisplay instructionsDisplay;
  // keep GUI components used in multiple tests
  private List<Text> instructions;


  // this method is run BEFORE EACH test to set up application in a fresh state
  @Override
  public void start(Stage stage) {
    // create application and add scene for testing to given stage
    GridPane root = new GridPane();
    instructionsDisplay = new InstructionsDisplay();
    root.add(instructionsDisplay.getDisplayComponentNode(), 0 ,0);
    stage.setScene(new Scene(root, 800, 800));
    stage.show();

    instructions = new ArrayList<>();
    instructions.add(lookup("#Header-Instructions").query());
    instructions.add(lookup("#Fd-Instructions").query());
    instructions.add(lookup("#Bk-Instructions").query());
    instructions.add(lookup("#Lt-Instructions").query());
    instructions.add(lookup("#Rt-Instructions").query());
    instructions.add(lookup("#Pd-Instructions").query());
    instructions.add(lookup("#Pu-Instructions").query());
    instructions.add(lookup("#St-Instructions").query());
    instructions.add(lookup("#Ht-Instructions").query());
    instructions.add(lookup("#Home-Instructions").query());
    instructions.add(lookup("#Stamps-Instructions").query());
    instructions.add(lookup("#Tell-Instructions").query());
  }

  @Test
  public void testCommandInputDefault () {
//    String expected = PropertiesLoader.loadProperties();
//    assertLabelText(expected, instructions.get(0));
  }

  // Borrowed from example_testfx course gitlab repo
  private void assertLabelText (String expected, Text text) {
    assertEquals(expected, text.getText());
  }

}
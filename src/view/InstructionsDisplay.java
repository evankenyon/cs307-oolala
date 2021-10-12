package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.PropertiesLoader;

/**
 * Purpose: This class creates a VBox that holds the instructions describing to the user the
 * commands to use Logo IDE. Assumptions: The program will not need to be used outside of English,
 * Spanish, or French speakers for now, but if we hired a translator could easily get more
 * languages. Dependencies: ArrayList, List, Properties, Node, VBox, Font, Text, PropertiesLoader
 * <p>
 * Example: If somewhere in the GUI you want the instructions of the IDE to be present, then an
 * instance of this class can be made and used to display the instructions.
 *
 * @author Evan Kenyon and Luis Pereda
 */
public class InstructionsDisplay extends DisplayComponent {

  private List<Text> instructions;

  public InstructionsDisplay() {
    Properties props = PropertiesLoader.loadProperties(
        "./src/view/resources/instructions/English.properties");
    setupInstructions(props);
    setupInstructionsIds();
  }

  private void setupInstructions(Properties props) {
    instructions = new ArrayList<>();
    Text instructionsHeader = new Text(props.getProperty("instructionsHeader"));
    instructionsHeader.setFont(new Font(30));
    instructions.add(instructionsHeader);
    instructions.add(new Text(props.getProperty("fdInstructions")));
    instructions.add(new Text(props.getProperty("bkInstructions")));
    instructions.add(new Text(props.getProperty("ltInstructions")));
    instructions.add(new Text(props.getProperty("rtInstructions")));
    instructions.add(new Text(props.getProperty("pdInstructions")));
    instructions.add(new Text(props.getProperty("puInstructions")));
    instructions.add(new Text(props.getProperty("stInstructions")));
    instructions.add(new Text(props.getProperty("htInstructions")));
    instructions.add(new Text(props.getProperty("homeInstructions")));
    instructions.add(new Text(props.getProperty("stampsInstructions")));
    instructions.add(new Text(props.getProperty("tellInstructions")));
  }

  private void setupInstructionsIds() {
    instructions.get(0).setId("Header-Instructions");
    instructions.get(1).setId("Fd-Instructions");
    instructions.get(2).setId("Bk-Instructions");
    instructions.get(3).setId("Lt-Instructions");
    instructions.get(4).setId("Rt-Instructions");
    instructions.get(5).setId("Pd-Instructions");
    instructions.get(6).setId("Pu-Instructions");
    instructions.get(7).setId("St-Instructions");
    instructions.get(8).setId("Ht-Instructions");
    instructions.get(9).setId("Home-Instructions");
    instructions.get(10).setId("Stamps-Instructions");
    instructions.get(11).setId("Tell-Instructions");
  }

  @Override
  public Node getDisplayComponentNode() {
    VBox instructionsNode = new VBox();
    instructionsNode.getChildren().addAll(instructions);
    instructionsNode.getStyleClass().add("vbox");
    return instructionsNode;
  }
}

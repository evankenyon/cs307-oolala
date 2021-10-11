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
 * commands to use Logo IDE.
 * Assumptions: The program will not need to be used outside of English, Spanish, or French speakers
 * for now, but if we hired a translator could easily get more languages.
 * Dependencies: ArrayList, List, Properties, Node, VBox, Font, Text, PropertiesLoader
 *
 * Example: If somewhere in the GUI you want the instructions of the IDE to be present, then an
 * instance of this class can be made and used to display the instructions.
 *
 * @author Evan Kenyon and Luis Pereda
 */
public class InstructionsDisplay extends DisplayComponent {

  private final List<Text> instructions;

  public InstructionsDisplay() {
    Properties props = PropertiesLoader.loadProperties(
        "./src/view/resources/instructions/English.properties");
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

  @Override
  public Node getDisplayComponentNode() {
    VBox instructionsNode = new VBox();
    instructionsNode.getChildren().addAll(instructions);
    instructionsNode.getStyleClass().add("vbox");
    return instructionsNode;
  }
}

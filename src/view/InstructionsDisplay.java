package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import util.PropertiesLoader;

public class InstructionsDisplay extends DisplayComponent{
  private List<Text> instructions;

  public InstructionsDisplay() {
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/config.properties");
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
    return instructionsNode;

  }
}

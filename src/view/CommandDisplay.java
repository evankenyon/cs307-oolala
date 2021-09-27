package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CommandModel;
import util.PropertiesLoader;

public class CommandDisplay extends DisplayComponent{
  private TextField commandInput;
  private ListView<String> prevCommands;
  private Button runPrevCommand;
  private CommandModel commandModel;

  public CommandDisplay() {
    // Prop setup borrowed from https://mkyong.com/java/java-properties-file-examples/
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/config.properties");
    commandModel = new CommandModel();
    prevCommands = new ListView<>();
    commandInput = new TextField(props.getProperty("commandInputPrompt"));
    commandInput.setOnAction(event -> onCommandInput());
    runPrevCommand = new Button(props.getProperty("runPrevCommandText"));
    runPrevCommand.setOnAction(event -> onRunPrevCommand());
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(prevCommands, new HBox(commandInput, runPrevCommand));
  }

  private void onCommandInput() {
    prevCommands.getItems().add(commandInput.getText());
    commandInput.setText("");
  }

  private void onRunPrevCommand() {
    commandModel.parseInput(prevCommands.getSelectionModel().getSelectedItem());
  }
}

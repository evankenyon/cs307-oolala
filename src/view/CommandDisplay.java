package view;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.CommandModel;
import model.TurtleModel;
import util.PropertiesLoader;

public class CommandDisplay extends DisplayComponent {

  private TextField commandInput;
  private ListView<String> prevCommands;
  private Button runPrevCommand;
  private Button selectCommandsFile;
  private Button saveCommandsFile;
  private CommandModel commandModel;
  private FileChooser chooseCommandsFile;

  public CommandDisplay() {
    // Prop setup borrowed from https://mkyong.com/java/java-properties-file-examples/
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/command.properties");
    commandModel = new CommandModel();
    prevCommands = new ListView<>();
    chooseCommandsFile = new FileChooser();
    chooseCommandsFile.setTitle("Open Commands file");
    chooseCommandsFile.getExtensionFilters().addAll(
        new ExtensionFilter("Text Files", "*.txt"));
    commandInput = new TextField(props.getProperty("commandInputPrompt"));
    commandInput.setOnAction(event -> onCommandInput());
    runPrevCommand = new Button(props.getProperty("runPrevCommandText"));
    runPrevCommand.setOnAction(event -> onRunPrevCommand());
    selectCommandsFile = new Button("Select a commands file");
    selectCommandsFile.setOnAction(event -> {
      try {
        commandModel.handleFileSelected(chooseCommandsFile.showOpenDialog(null));
      } catch (FileNotFoundException e) {
        //TODO: make good
        e.printStackTrace();
      }
    });
    saveCommandsFile = new Button("Save current program as .txt file");
    saveCommandsFile.setOnAction(event -> {
      try {
        commandModel.saveCommandsAsFile();
      } catch (FileNotFoundException e) {
        //TODO: fix up
        e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
        //TODO: fix up
        e.printStackTrace();
      }
    });
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(prevCommands, new HBox(commandInput, runPrevCommand, saveCommandsFile, selectCommandsFile));
  }

  private void onCommandInput() {
    commandModel.parseInput(commandInput.getText());
    prevCommands.getItems().add(commandInput.getText());
    commandInput.setText("");
  }

  private void onRunPrevCommand() {
    commandModel.parseInput(prevCommands.getSelectionModel().getSelectedItem());
  }
}

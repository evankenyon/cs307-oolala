package view;

import java.io.File;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.PropertiesLoader;

public class CommandDisplay extends DisplayComponent {

  private TextField commandInput;
  private ListView<String> prevCommands;
  private Button runPrevCommand;
  private Button selectCommandsFile;
  private Button saveCommandsFile;
  private FileChooser chooseCommandsFile;
  private String command;
  private File commandFile;
  private boolean saveCommandsAsFile;
  private boolean hasCommandUpdated;

  public CommandDisplay() {
    // Prop setup borrowed from https://mkyong.com/java/java-properties-file-examples/
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/command.properties");
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
        commandFile = chooseCommandsFile.showOpenDialog(null);
    });
    saveCommandsFile = new Button("Save current program as .txt file");
    saveCommandsFile.setOnAction(event -> {
        saveCommandsAsFile = true;
    });
    saveCommandsAsFile = false;
    hasCommandUpdated = false;
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(prevCommands, new HBox(commandInput, runPrevCommand, saveCommandsFile, selectCommandsFile));
  }

  public String getCommand() {
    hasCommandUpdated = false;
    return command;
  }

  public boolean getHasCommandUpdated() {
    return hasCommandUpdated;
  }

  public File getCommandFile() {
    return commandFile;
  }

  public boolean shouldSaveAsFile() {
    if(saveCommandsAsFile) {
      saveCommandsAsFile = false;
      return true;
    }
    return false;
  }

  private void onCommandInput() {
    hasCommandUpdated = true;
    prevCommands.getItems().add(commandInput.getText());
    command = commandInput.getText();
    commandInput.setText("");
  }

  private void onRunPrevCommand() {
    hasCommandUpdated = true;
    command = prevCommands.getSelectionModel().getSelectedItem();
  }
}

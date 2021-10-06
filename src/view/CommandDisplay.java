package view;

import java.io.File;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
  private boolean isFileUploaded;

  public CommandDisplay() {
    // Prop setup borrowed from https://mkyong.com/java/java-properties-file-examples/
    Properties props = PropertiesLoader.loadProperties("./src/view/resources/command.properties");
    prevCommands = new ListView<>();
    prevCommands.setId("Prev-Commands");
    setupChooseCommandsFile(props);
    setupCommandInput(props);
    makeButtons(props);
    saveCommandsAsFile = false;
    isFileUploaded = false;
    saveCommandsAsFile = false;
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(prevCommands,
        new HBox(commandInput, runPrevCommand, saveCommandsFile, selectCommandsFile));
  }

  public String getCommand() {
    hasCommandUpdated = false;
    return command;
  }

  public void removeCommandFromHistory() {
    prevCommands.getItems().remove(prevCommands.getItems().size() - 1);
  }

  public boolean getHasCommandUpdated() {
    return hasCommandUpdated;
  }

  public File getCommandFile() {
    return commandFile;
  }

  public boolean getIsFileUploaded() {
    if (isFileUploaded) {
      isFileUploaded = false;
      return true;
    }
    return false;
  }

  public boolean shouldSaveAsFile() {
    if (saveCommandsAsFile) {
      saveCommandsAsFile = false;
      return true;
    }
    return false;
  }

  private Button makeButton(String label, EventHandler<ActionEvent> event) {
    Button button = new Button(label);
    button.setOnAction(event);
    return button;
  }

  private void makeButtons(Properties props) {
    runPrevCommand = makeButton(props.getProperty("runPrevCommandText"), event -> onRunPrevCommand());
    selectCommandsFile = makeButton(props.getProperty("selectFile"), event -> onSelectCommandsFile());
    saveCommandsFile = makeButton(props.getProperty("saveFile"), event -> onSaveCommandsFile());
  }

  private void setupCommandInput(Properties props) {
    commandInput = new TextField(props.getProperty("commandInputPrompt"));
    commandInput.setOnAction(event -> onCommandInput());
    commandInput.setId("Command-Input");
    hasCommandUpdated = false;
  }

  private void setupChooseCommandsFile(Properties props) {
    chooseCommandsFile = new FileChooser();
    chooseCommandsFile.setTitle(props.getProperty("openFile"));
    chooseCommandsFile.getExtensionFilters().addAll(
        new ExtensionFilter(props.getProperty("fileDescription"),
            props.getProperty("fileExtension")));
  }

  private void onCommandInput() {
    hasCommandUpdated = true;
    prevCommands.getItems().add(commandInput.getText());
    command = commandInput.getText();
    commandInput.setText("");
  }

  private void onSelectCommandsFile() {
    commandFile = chooseCommandsFile.showOpenDialog(null);
    isFileUploaded = true;
  }

  private void onRunPrevCommand() {
    hasCommandUpdated = true;
    command = prevCommands.getSelectionModel().getSelectedItem();
  }

  private void onSaveCommandsFile() {
    saveCommandsAsFile = true;
  }
}

package view;

import java.io.File;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.ButtonMaker;
import util.PropertiesLoader;

public class CommandDisplay extends DisplayComponent {

  // For TestFX purposes
  private static int numResets = 0;
  private static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/command/";

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
  private String language;

  public CommandDisplay() {
    // Prop setup borrowed from https://mkyong.com/java/java-properties-file-examples/
    setupCommandDisplay();
    language = "English";
  }

  private void setupCommandDisplay() {
    Properties props = PropertiesLoader.loadProperties(DEFAULT_RESOURCES_PACKAGE + language);
    prevCommands = new ListView<>();
    prevCommands.setId("Prev-Commands-" + numResets);
    setupChooseCommandsFile(props);
    setupCommandInput(props);
    makeButtons(props);
    hasCommandUpdated = false;
    isFileUploaded = false;
    saveCommandsAsFile = false;

    // For TestFX purposes
    numResets++;
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

  public void updateCommandHistory(List<String> commands) {
    // Borrowed usage of FXCollections from
    // https://stackoverflow.com/questions/41920217/what-is-the-difference-between-arraylist-and-observablelist
    prevCommands.setItems(FXCollections.observableList(commands));
  }

  public boolean getHasCommandUpdated() {
    return hasCommandUpdated;
  }

  public File getUploadedCommandFile() {
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

  private void makeButtons(Properties props) {
    runPrevCommand = ButtonMaker.makeButton(props.getProperty("runPrevCommandText"),
        event -> onRunPrevCommand());
    selectCommandsFile = ButtonMaker.makeButton(props.getProperty("selectFile"),
        event -> onSelectCommandsFile());
    saveCommandsFile = ButtonMaker.makeButton(props.getProperty("saveFile"), event -> onSaveCommandsFile());
    saveCommandsFile.setId("Save-Commands-File");
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

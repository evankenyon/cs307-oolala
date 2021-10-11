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

/**
 * Purpose: This class provides a display that handles all the UI related aspects of inputting
 * commands to the turtles.
 * Dependencies: File, List, Properties, FXCollections, Node, Button, ListView, TextField, HBox,
 * VBox, FileChooser, ExtensionFilter, ButtonMaker, PropertiesLoader
 *
 * Example: Create a CommandDisplay so that the user can input commands in text or file form which
 * can then be handled by a CommandModel to draw on a LogoDisplay
 *
 * @author Evan Kenyon and Luis Pereda
 */
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

  /**
   * Purpose: Create a new command display
   */
  public CommandDisplay() {
    // Prop setup borrowed from https://mkyong.com/java/java-properties-file-examples/
    setupCommandDisplay();
  }

  private void setupCommandDisplay() {
    Properties props = PropertiesLoader.loadProperties(DEFAULT_RESOURCES_PACKAGE + "English.properties");
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

  /**
   * Purpose: Provide a VBox containing the Command Display so that the higher level display can use
   * it.
   * @return Node
   */
  @Override
  public Node getDisplayComponentNode() {
    return new VBox(prevCommands,
        new HBox(commandInput, runPrevCommand, saveCommandsFile, selectCommandsFile));
  }

  /**
   * Purpose: Return a command input by the user
   * @return String representing a command
   */
  public String getCommand() {
    hasCommandUpdated = false;
    return command;
  }

  /**
   * Purpose: Update the visible command history so users can see what commands they have input.
   * @param commands List of previous commands.
   */
  public void updateCommandHistory(List<String> commands) {
    // Borrowed usage of FXCollections from
    // https://stackoverflow.com/questions/41920217/what-is-the-difference-between-arraylist-and-observablelist
    prevCommands.setItems(FXCollections.observableList(commands));
  }

  /**
   * Purpose: Getter to see if the user has entered a new command.
   * @return Boolean: true if the user has entered a new command.
   */
  public boolean getHasCommandUpdated() {
    return hasCommandUpdated;
  }

  /**
   * Purpose: Gets the file that the user has uploaded with commands.
   * @return File that contains commands.
   */
  public File getUploadedCommandFile() {
    return commandFile;
  }

  /**
   * Purpose: Gets whether a file has been updated or not.
   * @return Boolean: True if a file has been updated.
   */
  public boolean getIsFileUploaded() {
    if (isFileUploaded) {
      isFileUploaded = false;
      return true;
    }
    return false;
  }

  /**
   * Purpose: Helps to determine if the command history should be saved as a file.
   * @return Boolean: True if the command history should be saved as a file.
   */
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
    runPrevCommand.getStyleClass().add("button");
    selectCommandsFile = ButtonMaker.makeButton(props.getProperty("selectFile"),
        event -> onSelectCommandsFile());
    selectCommandsFile.getStyleClass().add("button");
    saveCommandsFile = ButtonMaker.makeButton(props.getProperty("saveFile"), event -> onSaveCommandsFile());
    saveCommandsFile.getStyleClass().add("button");
    saveCommandsFile.setId("Save-Commands-File");
  }

  private void setupCommandInput(Properties props) {
    commandInput = new TextField(props.getProperty("commandInputPrompt"));
    commandInput.setOnAction(event -> onCommandInput());
    commandInput.setId("Command-Input");
    commandInput.getStyleClass().add("text-field");
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

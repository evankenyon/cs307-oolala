package view;

import java.io.File;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.ButtonMaker;

public class ChooseFileDisplay extends DisplayComponent {

  private Button selectFile;
  private FileChooser chooseFile;
  private File commandFile;
  private boolean isFileUploaded;

  public ChooseFileDisplay(Properties props) {
    isFileUploaded = false;
    selectFile = ButtonMaker.makeButton(props.getProperty("openFile"),
        event -> onSelectFile());
    selectFile.getStyleClass().add("button");
    setupChooseFile(props);
  }

  /**
   * Purpose: Gets whether a file has been updated or not.
   *
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
   * Purpose: Gets the file that the user has uploaded with commands.
   *
   * @return File that contains commands.
   */
  public File getUploadedFile() {
    return commandFile;
  }

  @Override
  public Node getDisplayComponentNode() {
    return selectFile;
  }

  private void setupChooseFile(Properties props) {
    chooseFile = new FileChooser();
    chooseFile.setTitle(props.getProperty("openFile"));
    chooseFile.getExtensionFilters().addAll(
        new ExtensionFilter(props.getProperty("fileDescription"),
            props.getProperty("fileExtension")));
  }

  private void onSelectFile() {
    commandFile = chooseFile.showOpenDialog(null);
    isFileUploaded = true;
  }
}

package view;

import java.io.File;
import java.util.Properties;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.ButtonMaker;

public abstract class InfoDisplay extends DisplayComponent {
  protected SetArgumentValueDisplay setHomeX;
  protected SetArgumentValueDisplay setHomeY;
  private File imageFile;
  private FileChooser imageFileChooser;
  protected Button imageChooserButton;
  private boolean isImageUploaded;
  protected Properties props;

  public InfoDisplay() {
    setHomeX = new SetArgumentValueDisplay("Set home x coordinate: ");
    setHomeY = new SetArgumentValueDisplay("Set home y coordinate: ");
    setHomeX.getDisplayComponentNode().setId("Set-Home-X");
    setHomeY.getDisplayComponentNode().setId("Set-Home-Y");
  }

  public int getHomeX() {
    return setHomeX.getArgument();
  }

  public int getHomeY() {
    return setHomeY.getArgument();
  }

  public Image getUploadedImage(){
    Image image = new Image(imageFile.toURI().toString());
    return image;
  }

  public boolean getIsImageUploaded() {
    if (isImageUploaded) {
      isImageUploaded = false;
      return true;
    }
    return false;
  }

  protected void makeImageChooserButton() {
    imageChooserButton = ButtonMaker.makeButton(props.getProperty("chooseImage"), event -> onSelectImageFile());
  }

  protected void setupChooseImageFile() {
    imageFileChooser = new FileChooser();
    imageFileChooser.setTitle(props.getProperty("openFile"));
    imageFileChooser.getExtensionFilters().addAll(
        new ExtensionFilter(props.getProperty("fileDescription"),
            props.getProperty("fileExtension")));
  }

  private void onSelectImageFile() {
    imageFile = imageFileChooser.showOpenDialog(null);
    isImageUploaded = true;
  }
}

package view;

import java.io.File;
import java.util.Properties;
import javafx.scene.image.Image;

public abstract class InfoDisplay extends DisplayComponent {
  protected SetArgumentValueDisplay setHomeX;
  protected SetArgumentValueDisplay setHomeY;
  protected ChooseFileDisplay chooseImageFile;
  protected File imageFile;
  protected Properties props;

  public InfoDisplay() {
    setHomeX = new SetArgumentValueDisplay("Set home x coordinate: ", TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    setHomeY = new SetArgumentValueDisplay("Set home y coordinate: ", TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
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
    return new Image(chooseImageFile.getUploadedFile().toURI().toString());
  }

  public boolean getIsImageUploaded() {
    return chooseImageFile.getIsFileUploaded();
  }

//  protected void makeImageChooserButton() {
//    imageChooserButton = ButtonMaker.makeButton(props.getProperty("chooseImage"), event -> onSelectImageFile());
//  }

//  protected void setupChooseImageFile() {
//    imageFileChooser = new FileChooser();
//    imageFileChooser.setTitle(props.getProperty("openFile"));
//    imageFileChooser.getExtensionFilters().addAll(
//        new ExtensionFilter(props.getProperty("fileDescription"),
//            props.getProperty("fileExtension")));
//  }

//  private void onSelectImageFile() {
//    imageFile = imageFileChooser.showOpenDialog(null);
//    isImageUploaded = true;
//  }
}

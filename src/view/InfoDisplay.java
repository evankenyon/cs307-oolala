package view;

import java.io.File;
import java.util.Properties;
import javafx.scene.image.Image;

/**
 * Purpose: The purpose of this class is to setup up an abstract display class which the logo display and lsystems display
 * make use of to show the turtle screen and information such as current x and y coordinate of the turle's home
 * Dependencies: File, Properties, Image
 * <p>
 * Example: When this class is constructed in the initial application launch. It is then used above turteinfodisplay
 * if LOGO programming is selected to run the program.
 *
 * @author Evan Kenyon
 */
public abstract class InfoDisplay extends DisplayComponent {

  protected SetArgumentValueDisplay setHomeX;
  protected SetArgumentValueDisplay setHomeY;
  protected ChooseFileDisplay chooseImageFile;
  protected File imageFile;
  protected Properties props;

  /**
   * Here the arugments are intialized for the info display such as the home x and y for the turtle in the turtle screen.
   * where the user can type and change these values
   */
  public InfoDisplay() {
    setHomeX = new SetArgumentValueDisplay("Set home x coordinate: ",
        TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    setHomeY = new SetArgumentValueDisplay("Set home y coordinate: ",
        TurtleWindowDisplay.PREF_WINDOW_SIZE / 2);
    setHomeX.getDisplayComponentNode().setId("Set-Home-X");
    setHomeY.getDisplayComponentNode().setId("Set-Home-Y");
  }


  /**
   * The purpose of this method is to return the currently set home x value which is stored as an argument shown in info
   * display
   *
   * @return the as argument for the turtle's x coordinate of home
   */
  public int getHomeX() {
    return setHomeX.getArgument();
  }

  /**
   * The purpose of this method is to return the currently set home y value which is stored as an argument shown in info
   * display
   *
   * @return the as argument for the turtle's x coordinate of home
   */
  public int getHomeY() {
    return setHomeY.getArgument();
  }

  /**
   * The purpose of this method is to create a new image to pass into the turtledisplay object and display the correct
   * turtle to be shown.
   *
   * @return the new image variable for the user chosen image
   */
  public Image getUploadedImage() {
    return new Image(chooseImageFile.getUploadedFile().toURI().toString());
  }

  /**
   * The purpose of this method is to check and see if an image file is uploaded
   *
   * @return return boolean of the image file being uploaded as true if there is a new image file
   */
  public boolean getIsImageUploaded() {
    return chooseImageFile.getIsFileUploaded();
  }

}

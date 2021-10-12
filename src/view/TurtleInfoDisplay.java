package view;

import java.io.File;
import java.util.Properties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.ButtonMaker;
import util.PropertiesLoader;

public class TurtleInfoDisplay extends DisplayComponent {
  public static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/logo/";
  private Slider thicknessSlider;
  private Label thicknessLabel = new Label("Pen Thickness");
  private int penThicknesss = 1;
  private File imageFile;
  private FileChooser imageFileChooser;
  private Button imageChooserButton;
  private boolean isImageUploaded;

  public TurtleInfoDisplay() {
    Properties props = PropertiesLoader.loadProperties(DEFAULT_RESOURCES_PACKAGE + "English.properties");
    setupThicknessSlider();
    setupChooseImageFile(props);
  makeImageChooserButton(props);
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(thicknessLabel, thicknessSlider, imageChooserButton);
  }

  public void setupThicknessSlider() {
    thicknessSlider = new Slider();
    thicknessSlider.setMin(1);
    thicknessSlider.setMax(40);
    thicknessSlider.setValue(4);
    thicknessSlider.setShowTickLabels(true);
    thicknessSlider.setShowTickMarks(true);
    thicknessSlider.setMajorTickUnit(20);
    thicknessSlider.setMinorTickCount(2);
    thicknessSlider.setBlockIncrement(5);
    thicknessSlider.valueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov,
                          Number old_val, Number new_val) {
        penThicknesss = new_val.intValue();
      }
    });
  }

  private void setupTextField() {
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

  private void makeImageChooserButton(Properties props) {
    imageChooserButton = ButtonMaker.makeButton(props.getProperty("chooseImage"), event -> onSelectImageFile());
  }
  private void setupChooseImageFile(Properties props) {
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

  public int getPenThicknesss() {
    return penThicknesss;
  }
}
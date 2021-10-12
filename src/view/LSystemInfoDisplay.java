package view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import util.PropertiesLoader;

public class LSystemInfoDisplay extends InfoDisplay {
  private SetArgumentValueDisplay setMovementLength;
  private SetArgumentValueDisplay setAngleLength;
  public static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/logo/";

  public LSystemInfoDisplay() {
    props = PropertiesLoader.loadProperties(DEFAULT_RESOURCES_PACKAGE + "English.properties");
    setAngleLength = new SetArgumentValueDisplay("Set angle length: ");
    setMovementLength = new SetArgumentValueDisplay("Set movement length: ");
    chooseImageFile = new ChooseFileDisplay(props);
  }

  public int getAngleLength() {
    return setAngleLength.getArgument();
  }

  public int getMovementLength() {
    return setMovementLength.getArgument();
  }

  @Override
  public Node getDisplayComponentNode() {
    return new HBox(setMovementLength.getDisplayComponentNode(),
        setAngleLength.getDisplayComponentNode(), setHomeX.getDisplayComponentNode(),
        setHomeY.getDisplayComponentNode(), chooseImageFile.getDisplayComponentNode());
  }
}

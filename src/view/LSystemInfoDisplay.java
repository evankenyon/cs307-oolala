package view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import util.ButtonMaker;
import util.PropertiesLoader;

public class LSystemInfoDisplay extends InfoDisplay {
  private SetArgumentValueDisplay setMovementLength;
  private SetArgumentValueDisplay setAngleLength;
  private SetArgumentValueDisplay setMaxNumLevels;
  private Button runButton;
  private boolean shouldRun;

  public static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/logo/";

  public LSystemInfoDisplay() {
    shouldRun = false;
    props = PropertiesLoader.loadProperties(DEFAULT_RESOURCES_PACKAGE + "English.properties");
    setAngleLength = new SetArgumentValueDisplay("Set angle length: ");
    setMovementLength = new SetArgumentValueDisplay("Set movement length: ");
    setMaxNumLevels = new SetArgumentValueDisplay("Set max num levels: ");
    chooseImageFile = new ChooseFileDisplay(props);
    runButton = ButtonMaker.makeButton("Run program", event -> shouldRun = true);
  }

  public boolean getShouldRun() {
    if(shouldRun) {
      shouldRun = false;
      return true;
    }
    return false;
  }

  public int getAngleLength() {
    return setAngleLength.getArgument();
  }

  public int getMovementLength() {
    return setMovementLength.getArgument();
  }

  public int getMaxNumLevels() { return setMaxNumLevels.getArgument(); }

  @Override
  public Node getDisplayComponentNode() {
    return new HBox(runButton, setMaxNumLevels.getDisplayComponentNode(), setMovementLength.getDisplayComponentNode(),
        setAngleLength.getDisplayComponentNode(), setHomeX.getDisplayComponentNode(),
        setHomeY.getDisplayComponentNode(), chooseImageFile.getDisplayComponentNode());
  }
}

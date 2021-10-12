package view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class LSystemInfoDisplay extends InfoDisplay {
  private SetArgumentValueDisplay setMovementLength;
  private SetArgumentValueDisplay setAngleLength;

  public LSystemInfoDisplay() {
    setAngleLength = new SetArgumentValueDisplay("Set angle length: ");
    setMovementLength = new SetArgumentValueDisplay("Set movement length: ");
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
        setHomeY.getDisplayComponentNode());
  }
}

package view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import util.ButtonMaker;

public class ClearDisplay extends DisplayComponent {
  private Button resetButton;
  private boolean shouldReset;

  public ClearDisplay() {
    resetButton = ButtonMaker.makeButton("Reset everything", event -> shouldReset = true);
    resetButton.setId("Reset-Button");
    resetButton.getStyleClass().add("reset");
  }

  public boolean getShouldReset() {
    if(shouldReset) {
      shouldReset = false;
      return true;
    }
    return false;
  }

  @Override
  public Node getDisplayComponentNode() {
    return resetButton;
  }
}

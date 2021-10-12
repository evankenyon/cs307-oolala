package view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import util.ButtonMaker;

/**
 * Purpose: This class makes a button that when clicked changes shouldReset to true, so that a class
 * using this class knows the user wants to clear the display. Dependencies: Node, Button,
 * ButtonMaker
 * <p>
 * Example: In the step method of the program check the value of shouldReset. If it is true, handle
 * this by resetting the page.
 *
 * @author Evan Kenyon
 */
public class ClearDisplay extends DisplayComponent {

  private Button resetButton;
  private boolean shouldReset;

  /**
   * Purpose: Create a new ClearDisplay button.
   */
  public ClearDisplay() {
    resetButton = ButtonMaker.makeButton("Reset everything", event -> shouldReset = true);
    resetButton.setId("Reset-Button");
    resetButton.getStyleClass().add("reset");
  }

  /**
   * Purpose: Getter to see whether the display should be reset.
   *
   * @return Boolean: if true then reset the display.
   */
  public boolean getShouldReset() {
    if (shouldReset) {
      shouldReset = false;
      return true;
    }
    return false;
  }

  /**
   * Getter so that the high level display can use this button
   *
   * @return Node of resetButton
   */
  @Override
  public Node getDisplayComponentNode() {
    return resetButton;
  }
}

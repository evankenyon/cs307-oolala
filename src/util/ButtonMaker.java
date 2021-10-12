package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Purpose: This class abstracts the complexity of making a button into this neat organization.
 * Dependencies: ActionEvent, EventHandler, Button. Example: When creating a button, simply make an
 * instance of this class and call makeButton().
 *
 * @author Evan Kenyon
 */
public class ButtonMaker {

  private ButtonMaker() {
  }

  public static Button makeButton(String label, EventHandler<ActionEvent> event) {
    javafx.scene.control.Button button = new Button(label);
    button.setOnAction(event);
    return button;
  }
}

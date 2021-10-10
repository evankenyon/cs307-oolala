package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonMaker {
  private ButtonMaker() {
  }

  public static Button makeButton(String label, EventHandler<ActionEvent> event) {
    javafx.scene.control.Button button = new Button(label);
    button.setOnAction(event);
    return button;
  }
}

package view;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Purpose: This class hides away some complexity of creating a UI element to allow a user to choose
 * the color of a certain element in the UI. Dependencies: Node, ColorPicker, VBox, Color, Text
 * Example: If you want to change the pen color that a turtle draws with, create an instance of this
 * class to do the work for you.
 *
 * @author Evan Kenyon
 */
public class SetColorDisplay extends DisplayComponent {

  Text colorPickerLabel;
  ColorPicker chooseColor;
  private static int numResets = 0;

  /**
   * Purpose: Create a new SetColorDisplay.
   *
   * @param label Label for the color picker.
   */
  public SetColorDisplay(String label) {
    colorPickerLabel = new Text(label);
    chooseColor = new ColorPicker();
    chooseColor.setId("Set-Color-" + numResets);
    numResets++;
  }

  /**
   * Purpose: Get the color chosen by the user.
   *
   * @return Color chosen.
   */
  public Color getColor() {
    return chooseColor.getValue();
  }

  /**
   * Getter so high level UI can use this node.
   *
   * @return Color picker node in a VBox.
   */
  @Override
  public Node getDisplayComponentNode() {
    return new VBox(chooseColor, colorPickerLabel);
  }
}

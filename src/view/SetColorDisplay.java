package view;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SetColorDisplay extends DisplayComponent {
  Text colorPickerLabel;
  ColorPicker chooseColor;
  private static int numResets = 0;

  public SetColorDisplay(String label) {
    colorPickerLabel = new Text(label);
    chooseColor = new ColorPicker();
    chooseColor.setId("Set-Color-" + numResets);
    numResets++;
  }

  public Color getColor() {
    return chooseColor.getValue();
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(chooseColor, colorPickerLabel);
  }
}

package view;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SetPenColorDisplay extends DisplayComponent {
  Text colorPickerLabel;
  ColorPicker choosePenColor;

  public SetPenColorDisplay() {
    colorPickerLabel = new Text("Choose a color for the turtle's pen: ");
    choosePenColor = new ColorPicker();
    choosePenColor.setId("Pen-Color");
  }

  public Color getColor() {
    return choosePenColor.getValue();
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(colorPickerLabel, choosePenColor);
  }
}

package view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SetArgumentValueDisplay extends DisplayComponent {
  private Text label;
  private TextField argumentInput;
  private boolean hasArgumentUpdated;
  private int argument;

  public SetArgumentValueDisplay(String label) {
    this.label = new Text(label);
    hasArgumentUpdated = false;
    argumentInput = new TextField();
    argumentInput.setOnAction(event -> onArgumentInput());
    argumentInput.setText("10");
    argument = 10;
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(label, argumentInput);
  }

  public int getArgument() {
    return argument;
  }

  private void onArgumentInput() {
    hasArgumentUpdated = true;
    argument = Integer.parseInt(argumentInput.getText());
  }
}

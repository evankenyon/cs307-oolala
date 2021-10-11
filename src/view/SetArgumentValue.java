package view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SetArgumentValue extends DisplayComponent {
  private Text label;
  private TextField argumentInput;
  private boolean hasArgumentUpdated;
  private int argument;

  public SetArgumentValue(String label) {
    this.label = new Text(label);
    hasArgumentUpdated = false;
    argumentInput = new TextField();
    argumentInput.setOnAction(event -> onArgumentInput());
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(label, argumentInput);
  }

  private void onArgumentInput() {
    hasArgumentUpdated = true;
    argument = getArgument();
  }

  private int getArgument() {
    return Integer.parseInt(argumentInput.getText());
  }


}

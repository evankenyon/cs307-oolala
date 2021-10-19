package view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Purpose: The purpose of this class is to display the correctly stored values for arguments that are shown which
 * we need to take as input. Such as the home x and y coordinates. It shows these values in a
 * text field as well as using the textfield to input new values for the same variables being shown.
 * Assumptions: This class is specifically used only to show and take as input the arguments displayed by it which are
 * only int variables
 * Dependencies: Node, TextField, VBox, Text
 * <p>
 * Example: If the user wants to input x location for turtle's home, then the argument for x location as a string label and
 * what variable should be stored in it is passed to this class to create the textfield input showing the current value.
 *
 * @author Evan Kenyon
 */
public class SetArgumentValueDisplay extends DisplayComponent {

  private Text label;
  private TextField argumentInput;
  private boolean hasArgumentUpdated;
  private int argument;
  // For testFX purposes
  private static int numConstructed = 0;

  /**
   *
   * @param label take in the string label to state what value the textfield is showing and taking as input
   * @param originalValue take in the original value stored to initially show in the textfield
   */
  public SetArgumentValueDisplay(String label, int originalValue) {
    this.label = new Text(label);
    hasArgumentUpdated = false;
    argumentInput = new TextField();
    argumentInput.setOnAction(event -> onArgumentInput());
    argumentInput.setText("" + originalValue);
    argument = originalValue;

    // For testFX purposes
    argumentInput.setId("Arg-Value-Display-" + numConstructed);
    numConstructed++;
  }

  /**
   * The purpose of this method is to properly add the javaFx textfield and label to the screen to make the argument class
   * work.
   *
   * @return the javaFx object for the label and argument to show on screen
   */
  @Override
  public Node getDisplayComponentNode() {
    return new VBox(label, argumentInput);
  }

  /**
   * The purpose of this method is to return the current int value of the class object being stored. This is so the rest
   * of the system can be updated when the textfield is updated.
   *
   * @return the current argument being stored or int value by the class
   */
  public int getArgument() {
    return argument;
  }

  private void onArgumentInput() {
    hasArgumentUpdated = true;
    argument = Integer.parseInt(argumentInput.getText());
  }
}

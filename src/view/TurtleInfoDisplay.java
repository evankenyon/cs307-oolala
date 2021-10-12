package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Properties;

public class TurtleInfoDisplay extends DisplayComponent {
  private Slider thicknessSlider;
  private Label thicknessLabel = new Label("Pen Thickness");
  private int penThicknesss = 1;
  private Button showTurtleInfo;
  private TextField turtleIDInput;

  public TurtleInfoDisplay() {
    setupThicknessSlider();
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(new HBox(thicknessLabel, thicknessSlider), new HBox(turtleIDInput, showTurtleInfo));
  }

  private void turleIDInput() {
    turtleIDInput = new TextField("Input Values");
    turtleIDInput.setOnAction(event -> onCommandInput());
    turtleIDInput.setId("Command-Input");
  }

  private void setupTextField() {

  }



  public void setupThicknessSlider() {
    thicknessSlider = new Slider();
    thicknessSlider.setMin(1);
    thicknessSlider.setMax(40);
    thicknessSlider.setValue(4);
    thicknessSlider.setShowTickLabels(true);
    thicknessSlider.setShowTickMarks(true);
    thicknessSlider.setMajorTickUnit(20);
    thicknessSlider.setMinorTickCount(2);
    thicknessSlider.setBlockIncrement(5);
    thicknessSlider.valueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov,
                          Number old_val, Number new_val) {
        penThicknesss = new_val.intValue();
      }
    });
  }

  public int getPenThicknesss() {
    return penThicknesss;
  }
}
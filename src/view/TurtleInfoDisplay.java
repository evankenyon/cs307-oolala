package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TurtleInfoDisplay extends DisplayComponent {
  private Slider thicknessSlider;
  private Label thicknessLabel = new Label("Pen Thickness");
  private int penThicknesss = 1;

  public TurtleInfoDisplay() {
    setupThicknessSlider();
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(thicknessLabel, thicknessSlider);
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

  private void setupTextField() {
  }

  public int getPenThicknesss() {
    return penThicknesss;
  }
}
package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.PropertiesLoader;

/**
 * Purpose: This class represents a display component where the user can choose pen color,
 * background color, home location, and an image for the turtle.
 * Dependencies: ChangeListener, ObservableValue, Node, Label, Slider, HBox, VBox, PropertiesLoader
 *
 * Example: Create a TurtleInfoDisplay class inside the high level view, and place it on the screen
 * so the user has more freedom to manipulate the turtles.
 *
 * @author Luis Pereda and Haseeb Chaudhry
 */
public class TurtleInfoDisplay extends InfoDisplay {
  public static final String DEFAULT_RESOURCES_PACKAGE = "./src/view/resources/logo/";
  private Slider thicknessSlider;
  private Label thicknessLabel = new Label("Pen Thickness");
  private int penThicknesss = 1;

  public TurtleInfoDisplay() {
    props = PropertiesLoader.loadProperties(DEFAULT_RESOURCES_PACKAGE + "English.properties");
    chooseImageFile = new ChooseFileDisplay(props);
    setupThicknessSlider();
  }

  @Override
  public Node getDisplayComponentNode() {
    return new VBox(chooseImageFile.getDisplayComponentNode(), thicknessLabel, thicknessSlider,
        new HBox(setHomeX.getDisplayComponentNode(), setHomeY.getDisplayComponentNode()));
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
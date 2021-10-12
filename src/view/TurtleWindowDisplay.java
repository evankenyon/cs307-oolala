package view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.TurtleModel;

/**
 * Purpose: This class creates a display where the TurtleDisplays can move around and draw their
 * respective lines.
 * Dependencies: ArrayList, List, Group, Node, Pane, TurtleModel
 *
 * Example: In an app such as Logo IDE, to have an area where the turtles can move and be able to
 * draw, instantiate this class in the high level view.
 *
 * @author Evan Kenyon and Luis Pereda
 */
public class TurtleWindowDisplay extends DisplayComponent {
  public static final int PREF_WINDOW_SIZE = 800;

  private Pane turtleWindow;
  private final List<TurtleDisplay> turtleDisplays;
  private int[] defaultHome;

  /**
   * Purpose: Create a new TurtleWindowDisplay
   */
  public TurtleWindowDisplay() {
    turtleDisplays = new ArrayList<>();
    // Have default constructor that just sets id to 1
    turtleWindow = new Pane();
    turtleWindow.setId("Turtle-Window");
    turtleWindow.getStyleClass().add("pane");
    turtleWindow.setMaxSize(PREF_WINDOW_SIZE, PREF_WINDOW_SIZE);
    turtleWindow.setMinSize(PREF_WINDOW_SIZE, PREF_WINDOW_SIZE);
    defaultHome = new int[]{PREF_WINDOW_SIZE / 2, PREF_WINDOW_SIZE / 2};
    turtleDisplays.add(new TurtleDisplay(1, defaultHome));
    Group turtleDisplaysGroup = new Group();
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      turtleDisplaysGroup.getChildren().add(turtleDisplay.getDisplayComponentNode());
    }

    turtleWindow.getChildren().addAll(turtleDisplaysGroup);
  }

  /**
   * Purpose: Update the window so that the TurtleDisplays actively reflect the changes in their
   * state, and so that the window is reflecting these TurtleDisplays.
   *
   * @param activeTurtles The turtles which should be on the screen and being changed by the user's
   *                      commands.
   */
  public void updateTurtleWindowAndDisplays(List<TurtleModel> activeTurtles) {
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      TurtleModel activeTurtle = getActiveTurtle(turtleDisplay, activeTurtles);
      if (activeTurtle != null) {
        updateTurtleWindow(turtleDisplay, activeTurtle);
        updateTurtleDisplay(turtleDisplay, activeTurtle);
      }
    }
  }

  /**
   * Purpose: Create new turtle displays if there are new active turtles.
   * @param newTurtles
   */
  public void addNewTurtles(List<TurtleModel> newTurtles) {
    for (TurtleModel turtleModel : newTurtles) {
      TurtleDisplay newTurtleDisplay = new TurtleDisplay(turtleModel.getID(), defaultHome);
      turtleDisplays.add(newTurtleDisplay);
      turtleWindow.getChildren().add(newTurtleDisplay.getDisplayComponentNode());
    }
  }

  /**
   * 
   * @param activeTurtles
   * @param penColor
   * @param thickness
   */
  public void updateActiveTurtlesPens(List<TurtleModel> activeTurtles, Color penColor, int thickness) {
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      TurtleModel activeTurtle = getActiveTurtle(turtleDisplay, activeTurtles);
      if (activeTurtle != null) {
        updatePenColor(turtleDisplay, penColor);
        updatePenThickness(turtleDisplay, thickness);
      }
    }
  }

  public void updateActiveTurtlesImage(List<TurtleModel> activeTurtles, Image img) {
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      TurtleModel activeTurtle = getActiveTurtle(turtleDisplay, activeTurtles);
      if (activeTurtle != null) {
        updateTurtleImage(turtleDisplay, img);
      }
    }
  }

  public void updateBackgroundColor(Color backgroundColor) {
    turtleWindow.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
  }


  private void updatePenColor(TurtleDisplay turtleDisplay, Color penColor) {
    turtleDisplay.setPenColor(penColor);
  }

  private void updatePenThickness(TurtleDisplay turtleDisplay, int thickness) {
    turtleDisplay.setPenThickness(thickness);
  }

  private void updateTurtleImage(TurtleDisplay turtleDisplay, Image img) {
    turtleDisplay.setImage(img);
  }

  private TurtleModel getActiveTurtle(TurtleDisplay turtleDisplay, List<TurtleModel> activeTurtles) {
    for (TurtleModel turtleModel : activeTurtles) {
      if (turtleModel.getID() == turtleDisplay.getId()) {
        return turtleModel;
      }
    }
    return null;
  }

  private void updateTurtleDisplay(TurtleDisplay turtleDisplay, TurtleModel turtleModel) {
    turtleDisplay.setAngle(turtleModel.getTrajectory());
    turtleDisplay.setShowOrHide(turtleModel.getShouldShow());
  }

  private void updateTurtleWindow(TurtleDisplay turtleDisplay, TurtleModel turtleModel) {
    turtleWindow.getChildren().add(
        turtleDisplay.setPositionAndDraw(turtleModel.getPosition(),
            turtleModel.getPen()));
    if (turtleModel.getShouldStamp()) {
      turtleWindow.getChildren().add(turtleDisplay.getStillTurtleImage());
    }
  }

  /**
   * Purpose: Getter for the TurtleWindowDisplay that conforms to the DisplayComponent standards.
   *
   * @return Pane of the TurtleWindowDisplay
   */
  @Override
  public Node getDisplayComponentNode() {
    return turtleWindow;
  }
}

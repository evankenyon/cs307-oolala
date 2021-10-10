package view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.TurtleModel;

public class TurtleWindowDisplay extends DisplayComponent {
  public static final int PREF_WINDOW_SIZE = 400;

  private Pane turtleWindow;
  private final List<TurtleDisplay> turtleDisplays;
  private int[] defaultHome;

  public TurtleWindowDisplay() {
    turtleDisplays = new ArrayList<>();
    // Have default constructor that just sets id to 1
    turtleWindow = new Pane();
    turtleWindow.setPrefSize(PREF_WINDOW_SIZE, PREF_WINDOW_SIZE);
    defaultHome = new int[]{PREF_WINDOW_SIZE / 2, PREF_WINDOW_SIZE / 2};
    turtleDisplays.add(new TurtleDisplay(1, defaultHome));
    Group turtleDisplaysGroup = new Group();
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      turtleDisplaysGroup.getChildren().add(turtleDisplay.getDisplayComponentNode());
    }

    turtleWindow.setStyle("-fx-background-color: floralwhite;\n"
        + "  -fx-border-style: solid;");
    turtleWindow.getChildren().addAll(turtleDisplaysGroup);
  }

  public void updateTurtleWindowAndDisplays(List<TurtleModel> activeTurtles) {
    for (TurtleDisplay turtleDisplay : turtleDisplays) {
      TurtleModel activeTurtle = getActiveTurtle(turtleDisplay, activeTurtles);
      if (activeTurtle != null) {
        updateTurtleWindow(turtleDisplay, activeTurtle);
        updateTurtleDisplay(turtleDisplay, activeTurtle);
      }
    }
  }

  public void addNewTurtles(List<TurtleModel> newTurtles) {
    for (TurtleModel turtleModel : newTurtles) {
      TurtleDisplay newTurtleDisplay = new TurtleDisplay(turtleModel.getID(), defaultHome);
      turtleDisplays.add(newTurtleDisplay);
      turtleWindow.getChildren().add(newTurtleDisplay.getDisplayComponentNode());
    }
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
        turtleDisplay.setPosition(turtleModel.getPosition(),
            turtleModel.getPen()));
    if (turtleModel.getShouldStamp()) {
      turtleWindow.getChildren().add(turtleDisplay.getStillTurtleImage());
    }
  }

  @Override
  public Node getDisplayComponentNode() {
    return turtleWindow;
  }
}

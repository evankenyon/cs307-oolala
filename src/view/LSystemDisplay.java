package view;

import model.LSystemsModel;
import util.PropertiesLoader;

public class LSystemDisplay extends AppDisplay {
  public static final String LOGO_RESOURCES_PACKAGE = DEFAULT_RESOURCES_PACKAGE + "logo/";
  public static final int DEFAULT_PEN_THICKNESS = 1;

  private LSystemInfoDisplay lSystemInfoDisplay;

  @Override
  protected void setupDisplay() {
    props = PropertiesLoader.loadProperties(LOGO_RESOURCES_PACKAGE + "English.properties");
    model = new LSystemsModel();
    lSystemInfoDisplay = new LSystemInfoDisplay();
    super.setupDisplay();
    rootSetup();
  }

  protected void rootSetup() {
    super.rootSetup();
    final int[] turtInfoDispGridLayout = new int[]{9,11,7,10};
    root.add(lSystemInfoDisplay.getDisplayComponentNode(), turtInfoDispGridLayout[0], turtInfoDispGridLayout[1]);
  }

  @Override
  protected void step(double elapsedTime) {
    ((LSystemsModel) model).setRotationAngle(lSystemInfoDisplay.getAngleLength());
    ((LSystemsModel) model).setMovementLength(lSystemInfoDisplay.getMovementLength());
    super.step(elapsedTime);
  }

//  private void handleUpdateLengthArgs() {
//
//  }

  @Override
  protected void handleUpdatePen() {
    turtleWindowDisplay.updateActiveTurtlesPens(model.getActiveTurtles(),
        setPenColorDisplay.getColor(), DEFAULT_PEN_THICKNESS);
  }
}

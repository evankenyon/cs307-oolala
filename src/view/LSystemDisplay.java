package view;

import model.LSystemsModel;
import util.PropertiesLoader;

public class LSystemDisplay extends AppDisplay {
  public static final String LOGO_RESOURCES_PACKAGE = DEFAULT_RESOURCES_PACKAGE + "logo/";
  public static final int DEFAULT_PEN_THICKNESS = 1;

  public LSystemDisplay() {
    setupDisplay();
  }

  @Override
  protected void setupDisplay() {
    props = PropertiesLoader.loadProperties(LOGO_RESOURCES_PACKAGE + "English.properties");
    model = new LSystemsModel();
    super.setupDisplay();
    rootSetup();
  }

  @Override
  protected void handleUpdatePen() {
    turtleWindowDisplay.updateActiveTurtlesPens(model.getActiveTurtles(),
        setPenColorDisplay.getColor(), DEFAULT_PEN_THICKNESS);
  }
}

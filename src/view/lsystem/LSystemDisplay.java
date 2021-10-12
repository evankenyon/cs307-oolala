package view.lsystem;

import model.lsystem.LSystemsModel;
import util.PropertiesLoader;
import view.AppDisplay;

/**
 * Purpose: This class represents the high level UI of the LSystems. This program organizes the
 * scene, the many view elements that go into it, and when the program is run in Main this class is
 * what will be instantiated and used to make the scene for the whole program. This class also calls
 * on a backend LSystemsModel to do the work that is needed for the view elements to act as they
 * should. Assumptions: The LSystemDisplay relies on many other classes to work properly, so the
 * assumption is that these other classes will be working properly and will be thoroughly tested to
 * make it easy to find any bug. Dependencies: LSystemsModel, PropertiesLoader
 * <p>
 * Example: In a Main, if you want to run a Logo IDE, then instantiate a LogoDisplay class and pass
 * the scene returned by makeScene() to the setScene() method of the stage. After this run show() on
 * stage to run the application.
 *
 * @author Evan Kenyon
 */
public class LSystemDisplay extends AppDisplay {

  public static final String LOGO_RESOURCES_PACKAGE = DEFAULT_RESOURCES_PACKAGE + "logo/";
  public static final int DEFAULT_PEN_THICKNESS = 1;

  @Override
  protected void setupDisplay() {
    props = PropertiesLoader.loadProperties(LOGO_RESOURCES_PACKAGE + "English.properties");
    model = new LSystemsModel();
    infoDisplay = new LSystemInfoDisplay();
    super.setupDisplay();
    rootSetup();
  }

  protected void rootSetup() {
    super.rootSetup();
    final int[] turtInfoDispGridLayout = new int[]{24, 11, 7, 10};
    root.add(infoDisplay.getDisplayComponentNode(), turtInfoDispGridLayout[0],
        turtInfoDispGridLayout[1]);
  }

  @Override
  protected void step(double elapsedTime) {
    lSystemSpecificUpdates();
    super.step(elapsedTime);
  }

  private void lSystemSpecificUpdates() {
    ((LSystemsModel) model).setRotationAngle(((LSystemInfoDisplay) infoDisplay).getAngleLength());
    ((LSystemsModel) model).setMovementLength(
        ((LSystemInfoDisplay) infoDisplay).getMovementLength());
    ((LSystemsModel) model).setLevelNumMax(((LSystemInfoDisplay) infoDisplay).getMaxNumLevels());
    ((LSystemsModel) model).setShouldRun(((LSystemInfoDisplay) infoDisplay).getShouldRun());
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

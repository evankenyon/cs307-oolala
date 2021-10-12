package view;

import model.LogoModel;
import util.PropertiesLoader;

/**
 * Purpose: This class represents the high level UI of the Logo IDE. This program organizes the
 * scene, the many view elements that go into it, and when the program is run in Main this class is
 * what will be instantiated and used to make the scene for the whole program. This class also calls
 * on a backend LogoModel to do the work that is needed for the view elements to act as they should.
 * Assumptions: The LogoDisplay relies on many other classes to work properly, so the assumption is
 * that these other classes will be working properly and will be thoroughly tested to make it easy
 * to find any bug.
 * Dependencies: LogoModel, PropertiesLoader
 *
 * Example: In a Main, if you want to run a Logo IDE, then instantiate a LogoDisplay class and
 * pass the scene returned by makeScene() to the setScene() method of the stage. After this run
 * show() on stage to run the application.
 *
 * @author Evan Kenyon
 */
public class LogoDisplay extends AppDisplay {

  // Magic values borrowed from example_animation course gitlab repo
  public static final String LOGO_RESOURCES_PACKAGE = DEFAULT_RESOURCES_PACKAGE + "logo/";

  @Override
  protected void setupDisplay() {
    props = PropertiesLoader.loadProperties(LOGO_RESOURCES_PACKAGE + "English.properties");
    model = new LogoModel();
    infoDisplay = new TurtleInfoDisplay();
    super.setupDisplay();
    rootSetup();
  }


  @Override
  protected void rootSetup() {
    super.rootSetup();
    final int[] turtInfoDispGridLayout = new int[]{9,21,7,8};
    root.add(infoDisplay.getDisplayComponentNode(), turtInfoDispGridLayout[0], turtInfoDispGridLayout[1],
            turtInfoDispGridLayout[2], turtInfoDispGridLayout[3]);
  }

  @Override
  protected void handleUpdatePen() {
    turtleWindowDisplay.updateActiveTurtlesPens(model.getActiveTurtles(),
        setPenColorDisplay.getColor(), ((TurtleInfoDisplay) infoDisplay).getPenThicknesss());
  }

}

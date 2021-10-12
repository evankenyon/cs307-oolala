package model.lsystem;

import model.TurtleController;
import model.lsystem.LSystemCommandRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.TurtleWindowDisplay;

class LSystemCommandRunnerTest {

  private LSystemCommandRunner lSystemCommandRunner;
  private TurtleController turtleController;
  private int originalHome;

  @BeforeEach
  void setUp() {
    lSystemCommandRunner = new LSystemCommandRunner(10, 30, 3);
    turtleController = new TurtleController();
    originalHome = TurtleWindowDisplay.PREF_WINDOW_SIZE / 2;
  }

  @Test
  void testGetCommandsFromInput() {
    int expected = originalHome + 10;
    lSystemCommandRunner.getCommandsFromInput("f").get(0).runCommand(turtleController);
    Assertions.assertEquals(expected, turtleController.getActiveTurtles().get(0).getPosition()[0]);
  }

  @Test
  void goToStartLocation() {
  }

  @Test
  void setStartLocation() {
  }

  @Test
  void setMovementLength() {
  }

  @Test
  void setRotationAngle() {
  }
}
package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurtleControllerTest {

  private TurtleController turtleController;

  @BeforeEach
  public void setUp() {
    turtleController = new TurtleController();
  }

  @Test
  public void testAddTurtles() {
    turtleController.resetActiveTurtles();
    turtleController.addTurtleToActives(1);
    turtleController.addTurtleToActives(2);
    turtleController.addTurtleToActives(4);
    int expected = 3;

    assertEquals(expected, turtleController.getActiveTurtles().size());
  }


}
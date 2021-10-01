package model;

import static org.junit.jupiter.api.Assertions.*;

import model.TurtleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurtleModelTest {
  private TurtleModel turtleModel;
  private int[] expected;
  @BeforeEach
  public void setUp () {
    turtleModel = new TurtleModel(1);
    expected = new int[2];
  }

  @Test
  public void testGetPosition() {
    expected[0] = 10;
    expected[1] = 5;
    int[] test = new int[2];
    test[0] = 10;
    test[1] = 5;
    TurtleModel turtleModelTester = new TurtleModel(1, test);
    assertEquals(expected[0], turtleModelTester.getPosition()[0]);
    assertEquals(expected[1], turtleModelTester.getPosition()[1]);
  }

  @Test
  public void testSetHome() {
    expected[0] = 40;
    expected[1] = 30;
    turtleModel.setHome(expected);
    turtleModel.goHome();
    assertEquals(expected[0], turtleModel.getPosition()[0]);
    assertEquals(expected[1], turtleModel.getPosition()[1]);
  }

  @Test
  public void testMoveRight() {
    turtleModel.move(50);
    expected[0] = 50;
    assertEquals(expected[0], turtleModel.getPosition()[0]);
  }

  @Test
  public void testMoveUp() {
    turtleModel.rotate(90);
    turtleModel.move(50);
    expected[1] = 50;
    assertEquals(expected[1], turtleModel.getPosition()[1]);
  }

  @Test
  public void moveAtAngle() {
    turtleModel.rotate(60);
    turtleModel.move(50);
    expected[1] = (int)(25 * Math.sqrt(3));
    expected[0] = 25;
    assertEquals(expected[0], turtleModel.getPosition()[0]);
    assertEquals(expected[1], turtleModel.getPosition()[1]);
  }


}
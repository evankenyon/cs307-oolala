package oolala;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TurtleModelTest {
  @Test
  public void testGetPosition(){
    int[] expected = new int[2];
    expected[0] = 10;
    expected[1] = 5;
    int[] test = new int[2];
    test[0] = 10;
    test[1] = 5;
    TurtleModel turtleModel = new TurtleModel(test);
    assertEquals(expected[0], turtleModel.getPosition()[0]);
    assertEquals(expected[1], turtleModel.getPosition()[1]);
  }
}
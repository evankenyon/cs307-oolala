package model.commands;

import static org.junit.jupiter.api.Assertions.*;

import model.TurtleController;
import model.TurtleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveCommandTest {

  private TurtleController controller;
  private MoveCommand moveCommand;

  @BeforeEach
  public void setUp() {
    controller = new TurtleController();
    controller.addTurtleToActives(1);
  }

  @Test
  public void testMoveForward() {
    moveCommand = new MoveCommand(50);
    moveCommand.runCommand(controller);
    System.out.println(controller.getActiveTurtles().size());
    int expected = 50;
    int xpos = controller.getActiveTurtles().get(0).getPosition()[0];
    assertEquals(expected, xpos);
  }

}
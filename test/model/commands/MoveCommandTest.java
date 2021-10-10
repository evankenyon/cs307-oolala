package model.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import model.TurtleController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveForwardCommandTest {

  private TurtleController controller;
  private MoveCommand moveCommand;

  @BeforeEach
  public void setUp() {
    controller = new TurtleController();
    controller.addTurtleToActives(1);
  }

  @Test
  public void testMoveForward() {
    List<Integer> args = new ArrayList<>();
    args.add(50);
    moveCommand = new MoveForwardCommand(args);
    moveCommand.runCommand(controller);
    int expected = 50;
    int xpos = controller.getActiveTurtles().get(0).getPosition()[0];
    assertEquals(expected, xpos);
  }

}
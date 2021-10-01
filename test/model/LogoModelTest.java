package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogoModelTest {

  private LogoModel logoModel;
  private CommandModel commandModel;
  private TurtleController turtleController;

  @BeforeEach
  public void setUp() {
    logoModel = new LogoModel();
    commandModel = logoModel.getCommandModel();
    turtleController = logoModel.getTurtleController();
  }

  @Test
  public void testTextInput() {
    inputText("fd 50");
    String input;
    TurtleModel turtle = turtleController.getActiveTurtles().get(0);
    inputText("rt 90");
    inputText("fd 50");
    int id = turtle.getID();
    assertEquals(id, 1);
    int xposition = 50;
    int yposition = 50;

    assertEquals(xposition, turtle.getPosition()[0]);
    assertEquals(yposition, turtle.getPosition()[1]);
  }

  private void inputText(String s) {
    logoModel.handleTextInput(s);
  }
}
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

  @Test
  public void getTurtlePenDownCorrect() {
    assertTrue(logoModel.getTurtlePenDown(1));
  }

  @Test
  public void getTurtlePenDownIncorrect() {
    assertThrows(NullPointerException.class, () -> logoModel.getTurtlePenDown(2));
  }

  @Test
  public void getTurtleShouldShowTrue() {
    assertTrue(logoModel.getTurtleShouldShow(1));
  }

  @Test
  public void getTurtleShouldShowFalse() {
    logoModel.handleTextInput("ht");
    assertFalse(logoModel.getTurtleShouldShow(1));
  }

  @Test
  public void getShouldTurtleStampFalse() {
    assertFalse(logoModel.getShouldTurtleStamp(1));
  }

  @Test
  public void getShouldTurtleStampTrue() {
    logoModel.handleTextInput("stamp");
    assertTrue(logoModel.getShouldTurtleStamp(1));
  }

  @Test
  public void getTurtlePositionBasic() {
    assertEquals(0, logoModel.getTurtlePosition(1)[0]);
    assertEquals(0, logoModel.getTurtlePosition(1)[1]);
  }

  @Test
  public void getTurtleTrajectoryBasic() {
    assertEquals(0, logoModel.getTurtleTrajectory(1));
  }

  @Test
  public void isTurtleActiveTrue() {
    assertTrue(logoModel.isTurtleActive(1));
  }

  @Test
  public void isTurtleActiveFalse() {
    logoModel.handleTextInput("tell 2");
    assertFalse(logoModel.isTurtleActive(1));
  }

  @Test
  public void getNewTurtle() {
    logoModel.handleTextInput("tell 2");
    assertEquals(2, logoModel.getNewTurtle().getID());
  }

  @Test
  public void hasNewTurtleTrue() {
    logoModel.handleTextInput("tell 2");
    assertTrue(logoModel.hasNewTurtle());
  }

  @Test
  public void hasNewTurtleFalse() {
    assertFalse(logoModel.hasNewTurtle());
  }

  private void inputText(String s) {
    logoModel.handleTextInput(s);
  }
}
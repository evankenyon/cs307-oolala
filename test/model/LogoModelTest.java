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
    logoModel.runNextCommand();
    logoModel.runNextCommand();
    logoModel.runNextCommand();
    assertEquals(xposition, turtle.getPosition()[0]);
    assertEquals(yposition, turtle.getPosition()[1]);
  }

  @Test
  public void getTurtlePenDownCorrect() {
    assertTrue(logoModel.getTurtleModel(1).getPen());
  }

  @Test
  public void getTurtlePenDownIncorrect() {
    assertThrows(NullPointerException.class, () -> logoModel.getTurtleModel(2));
  }

  @Test
  public void getTurtleShouldShowTrue() {
    assertTrue(logoModel.getTurtleModel(1).getShouldShow());
  }

  @Test
  public void getTurtleShouldShowFalse() {
    logoModel.handleTextInput("ht");
    logoModel.runNextCommand();
    assertFalse(logoModel.getTurtleModel(1).getShouldShow());
  }

  @Test
  public void getShouldTurtleStampFalse() {
    assertFalse(logoModel.getTurtleModel(1).getShouldStamp());
  }

  @Test
  public void getShouldTurtleStampTrue() {
    logoModel.handleTextInput("stamp");
    logoModel.runNextCommand();
    assertTrue(logoModel.getTurtleModel(1).getShouldStamp());
  }

  @Test
  public void getTurtlePositionBasic() {
    assertEquals(0, logoModel.getTurtleModel(1).getPosition()[0]);
    assertEquals(0, logoModel.getTurtleModel(1).getPosition()[1]);
  }

  @Test
  public void getTurtleTrajectoryBasic() {
    assertEquals(0, logoModel.getTurtleModel(1).getTrajectory());
  }

  @Test
  public void isTurtleActiveTrue() {
    assertTrue(logoModel.isTurtleActive(1));
  }

  @Test
  public void isTurtleActiveFalse() {
    logoModel.handleTextInput("tell 2");
    logoModel.runNextCommand();
    assertFalse(logoModel.isTurtleActive(1));
  }

  @Test
  public void getNewTurtle() {
    logoModel.handleTextInput("tell 2");
    logoModel.runNextCommand();
    assertEquals(2, logoModel.getNewTurtles().get(0).getID());
  }

  @Test
  public void hasNewTurtleTrue() {
    logoModel.handleTextInput("tell 2");
    logoModel.runNextCommand();
    assertTrue(logoModel.hasNewTurtles());
  }

  @Test
  public void hasNewTurtleFalse() {
    assertFalse(logoModel.hasNewTurtles());
  }

  private void inputText(String s) {
    logoModel.handleTextInput(s);
  }
}
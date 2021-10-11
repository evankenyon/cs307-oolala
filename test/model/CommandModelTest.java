package model;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.TurtleWindowDisplay;

class CommandModelTest {
  private LogoCommandModel commandModel;
  private TurtleController turtleController;
  private int originalHome;

  @BeforeEach
  void setUp() {
    commandModel = new LogoCommandModel();
    originalHome = TurtleWindowDisplay.PREF_WINDOW_SIZE/2;
    turtleController = new TurtleController();
  }

  @Test
  public void parseInputForwardCorrect() {
    commandModel.getCommandsFromInput("fd 50").get(0).runCommand(turtleController);
    Assertions.assertEquals(originalHome + 50, turtleController.getActiveTurtles().get(0).getPosition()[0]);
  }

  @Test
  public void parseInputForwardIncorrect() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.getCommandsFromInput("fd bad"));
  }

  @Test
  public void parseInputBackwardCorrect() {
    commandModel.getCommandsFromInput("bk 50").get(0).runCommand(turtleController);
    Assertions.assertEquals(originalHome - 50, turtleController.getActiveTurtles().get(0).getPosition()[0]);
  }

  @Test
  public void parseInputBackwardIncorrect() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.getCommandsFromInput("bk bad"));
  }

  @Test
  public void parseInputLeftCorrect() {
    commandModel.getCommandsFromInput("lt 50").get(0).runCommand(turtleController);
    Assertions.assertEquals(-50, turtleController.getActiveTurtles().get(0).getTrajectory());
  }

  @Test
  public void parseInputRightCorrect() {
    commandModel.getCommandsFromInput("rt 50").get(0).runCommand(turtleController);
    Assertions.assertEquals(50, turtleController.getActiveTurtles().get(0).getTrajectory());
  }

  @Test
  public void parseInputPenUpCommand() {
    commandModel.getCommandsFromInput("pd").get(0).runCommand(turtleController);
    Assertions.assertTrue(turtleController.getActiveTurtles().get(0).getPen());
  }

  @Test
  public void parseInputPenDownCommand() {
    commandModel.getCommandsFromInput("pu").get(0).runCommand(turtleController);
    Assertions.assertFalse(turtleController.getActiveTurtles().get(0).getPen());
  }

  @Test
  public void parseInputGoHomeCommand() {
    commandModel.getCommandsFromInput("fd 50").get(0).runCommand(turtleController);
    Assertions.assertEquals(originalHome + 50, turtleController.getActiveTurtles().get(0).getPosition()[0]);
    commandModel.getCommandsFromInput("home").get(0).runCommand(turtleController);
    Assertions.assertEquals(originalHome, turtleController.getActiveTurtles().get(0).getPosition()[0]);
  }

  @Test
  public void parseInputNonExistentCommand() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.getCommandsFromInput("bad"));
  }

  @Test
  public void parseInputStampCommand() {
    commandModel.getCommandsFromInput("stamp").get(0).runCommand(turtleController);
    Assertions.assertTrue(turtleController.getActiveTurtles().get(0).getShouldStamp());
  }

  @Test
  public void parseInputHideCommand() {
    commandModel.getCommandsFromInput("ht").get(0).runCommand(turtleController);
    Assertions.assertFalse(turtleController.getActiveTurtles().get(0).getShouldShow());
  }

  @Test
  public void parseInputShowCommand() {
    parseInputHideCommand();
    commandModel.getCommandsFromInput("st").get(0).runCommand(turtleController);
    Assertions.assertTrue(turtleController.getActiveTurtles().get(0).getShouldShow());
  }

  @Test
  public void handleFileSelectedNotTxt() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.handleFileSelected(new File("./data/FOLDER_PURPOSE.md")));
  }

  @Test
  public void saveCommandsAsFileCorrect() throws IOException {
    commandModel.getCommandsFromInput("fd 50 rt 50");
    commandModel.saveCommandsAsFile();
    Scanner scanner = new Scanner(new File("./data/programs/logo/program1.txt"));
    scanner.useDelimiter("\n");
    Assertions.assertEquals("#Saved program number 1", scanner.next());
    Assertions.assertEquals("fd 50", scanner.next());
    Assertions.assertEquals( "rt 50", scanner.next());
  }

  @Test
  public void saveCommandsAsFileIncorrect() throws IOException {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.getCommandsFromInput("fd"));
    commandModel.getCommandsFromInput("fd 50");
    commandModel.getCommandsFromInput("rt 50");
    commandModel.saveCommandsAsFile();
    Scanner scanner = new Scanner(new File("./data/programs/logo/program1.txt"));
    scanner.useDelimiter("\n");
    Assertions.assertEquals("#Saved program number 1", scanner.next());
    Assertions.assertEquals("fd 50", scanner.next());
    Assertions.assertEquals( "rt 50", scanner.next());
  }

  @Test
  public void handleFileSelectedBadCommands() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.handleFileSelected(new File("./data/examples/logo/bad.txt")));
  }
}
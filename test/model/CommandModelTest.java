package model;

import java.io.File;
import java.util.InputMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandModelTest {
  private CommandModel commandModel;

  @BeforeEach
  void setUp() {
    commandModel = new CommandModel();
  }

  @Test
  public void parseInputForwardIncorrect() {
    Assertions.assertThrows(NumberFormatException.class, () -> commandModel.parseInput("fd bad"));
  }

  @Test
  public void parseInputBackwardIncorrect() {
    Assertions.assertThrows(NumberFormatException.class, () -> commandModel.parseInput("bk bad"));
  }

  @Test
  public void parseInputNonExistentCommand() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.parseInput("bad"));
  }

  @Test
  public void parseInputPenUpNumArg() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.parseInput("pu 50"));
  }

  @Test
  public void handleFileSelectedNotTxt() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> commandModel.handleFileSelected(new File("./data/FOLDER_PURPOSE.md")));
  }

  @Test
  public void handleFileSelectedBadCommands() {
    Assertions.assertThrows(InputMismatchException.class, () -> commandModel.handleFileSelected(new File("./data/examples/logo/bad.txt")));
  }

}
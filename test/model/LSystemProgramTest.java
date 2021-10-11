package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LSystemProgramTest {
  private LSystemProgram lSystemProgram;

  @BeforeEach
  void setUp() {
    lSystemProgram = new LSystemProgram();
  }

  @Test
  void parseInput() {
  }

  @Test
  void getNextLevel() {
  }

  @Test
  void getIsValidProgram() {
  }

  @Test
  void testSaveCommandsAsFileCorrect() throws IOException {
    lSystemProgram.parseInput("start f");
    lSystemProgram.parseInput("rule f f-f");
    lSystemProgram.saveCommandsAsFile();
    Scanner scanner = new Scanner(new File("./data/programs/lsystem/program1.txt"));
    scanner.useDelimiter("\n");
    Assertions.assertEquals("#Saved program number 1", scanner.next());
    Assertions.assertEquals("start f", scanner.next());
    Assertions.assertEquals( "rule f f-f", scanner.next());
  }

  @Test
  void getCommandHistory() {
  }

  @Test
  void handleFileInput() {
  }
}
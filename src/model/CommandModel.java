package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import model.commands.Command;
import model.commands.GoHomeCommand;
import model.commands.MoveCommand;
import model.commands.RotateCommand;
import model.commands.SetPenCommand;
import model.commands.ShowOrHideCommand;
import model.commands.StampCommand;
import model.commands.TellCommand;
import util.PropertiesLoader;

public class CommandModel {

  private Scanner scanner;
  private int numProgramsSaved;
  private final List<String> prevCommands;
  private Properties props;

  public CommandModel() {
    numProgramsSaved = 0;
    prevCommands = new ArrayList<>();
    prevCommands.add("fd 50");
    prevCommands.add("rt 50");
    props = PropertiesLoader.loadProperties("./src/model/resources/English.properties");
  }

  public Command parseInput(String input) throws InputMismatchException, NumberFormatException {
    if (input.startsWith("#") || input.equals("")) {
      return null;
    }
    scanner = new Scanner(input);
    while (scanner.hasNext()) {
      return getCommandFromInput();
    }
    return null;
  }

  private Command getCommandFromInput() {
    return switch (scanner.next().toLowerCase()) {
      case "fd" -> handleMovementCommand(1);
      case "bk" -> handleMovementCommand(-1);
      case "lt" -> handleAngleCommand(-1);
      case "rt" -> handleAngleCommand(1);
      case "pd" -> handlePenCommand(true);
      case "pu" -> handlePenCommand(false);
      case "st" -> handleShowOrHideCommand(true);
      case "ht" -> handleShowOrHideCommand(false);
      case "home" -> handleGoHomeCommand();
      case "stamp" -> handleStampCommand();
      case "tell" -> handleTellCommand();
      default -> throw new InputMismatchException();
    };
  }

  public List<Command> handleFileSelected(File commandFile)
      throws FileNotFoundException, IllegalArgumentException {
    ArrayList<Command> commands = new ArrayList<>();
    if (!commandFile.getName().endsWith(".txt")) {
      throw new IllegalArgumentException();
    }
    Scanner fileScanner = new Scanner(commandFile);
    fileScanner.useDelimiter("\n");
    while (fileScanner.hasNext()) {
      Command newCommand = parseInput(fileScanner.next());
      if (newCommand != null) {
        commands.add(newCommand);
      }
    }
    return commands;
  }

  public void saveCommandsAsFile() throws IOException {
    numProgramsSaved++;
    // Code for creating a file and writing to it borrowed from
    // https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
    PrintWriter currProgram = new PrintWriter("./data/programs/program" + numProgramsSaved
        + ".txt", StandardCharsets.UTF_8);
    currProgram.println("#Saved program number " + numProgramsSaved);
    for (String command : prevCommands) {
      currProgram.println(command);
    }
    currProgram.close();
  }

  private Command handleMovementCommand(int direction) throws InputMismatchException {
    return new MoveCommand(direction * parseFirstNumArg());
  }

  private Command handleAngleCommand(int direction) throws InputMismatchException {
    return new RotateCommand(direction * parseFirstNumArg());
  }

  private Command handlePenCommand(boolean isPenUp) {
    return new SetPenCommand(isPenUp);
  }

  private Command handleGoHomeCommand() {
    return new GoHomeCommand();
  }

  private Command handleStampCommand() {
    return new StampCommand();
  }

  private Command handleTellCommand() {
    List<Integer> currTurtleIds = new ArrayList<>();
    currTurtleIds.add(parseFirstNumArg());
    while (scanner.hasNext()) {
      currTurtleIds.add(parseNumInput());
    }
    return new TellCommand(currTurtleIds);
  }

  private Command handleShowOrHideCommand(boolean shouldShow) {
    return new ShowOrHideCommand(shouldShow);
  }

  private int parseFirstNumArg() {
    if (!scanner.hasNext()) {
      throw new InputMismatchException();
    }
    return parseNumInput();
  }

  private int parseNumInput() throws IllegalArgumentException {
    int numInput;
    try {
      numInput = Integer.parseInt(scanner.next());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
    return numInput;
  }

}

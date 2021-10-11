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
import model.commands.HideCommand;
import model.commands.MoveBackwardCommand;
import model.commands.MoveForwardCommand;
import model.commands.RotateLeftCommand;
import model.commands.RotateRightCommand;
import model.commands.SetPenDownCommand;
import model.commands.SetPenUpCommand;
import model.commands.ShowCommand;
import model.commands.StampCommand;
import model.commands.TellCommand;
import util.PropertiesLoader;

/**
 * Purpose: This class is meant to do the work of parsing for the Logo IDE. When the user inputs
 * commands, either in the text box or in file format, this class does the work of parsing the text
 * into commands that the turtles will follow to be able to draw on the screen.
 * Assumptions: The user can input commands either into the text box or in .txt file format. This
 * class is expected to be able to handle incorrect commands, or improper file formats.
 * Dependencies: File, FileNotFoundException, IOException, PrintWriter, StandardCharsets, ArrayList,
 * InputMismatchException, List, Properties, Scanner, Command, GoHomeCommand, HideCommand,
 * MoveBackwardCommand, MoveForwardCommand, RotateLeftCommand, RotateRightCommand,
 * SetPenDownCommand, SetPenUpCommand, ShowCommand, StampCommand, TellCommand, PropertiesLoader
 *
 * Example: Create an instance of this class in the high level model for the program. When the user
 * inputs text as commands and hits run or enter, call on this class to create the commands that
 * must be run, then have these run in the model.
 *
 * @Author Evan Kenyon
 */
public class CommandModel {

  private static final String DEFAULT_RESOURCE_PACKAGE = "./src/model/resources/";
  private Scanner scanner;
  private int numProgramsSaved;
  private final List<String> prevCommands;
  private Properties props;
  private String language;

  /**
   * Purpose: Create a new command model.
   * Assumptions: Will be called in the high level backend model to take responsibility for parsing.
   */
  public CommandModel() {
    numProgramsSaved = 0;
    prevCommands = new ArrayList<>();
    language = "English.properties";

    props = PropertiesLoader.loadProperties(DEFAULT_RESOURCE_PACKAGE + language);
  }

  /**
   * Purpose: Get a list of commands for the turtle(s) to execute based on the commands given by the
   * user as strings of text.
   * Assumptions: Any incorrect commands will throw exceptions.
   * @param input
   * @return List of Commands to be executed by the turtles
   * @throws InputMismatchException
   * @throws IllegalArgumentException
   */
  public List<Command> getCommandsFromInput(String input)
      throws InputMismatchException, IllegalArgumentException {
    scanner = new Scanner(input);
    List<List<Integer>> args = new ArrayList<>();
    List<String> functions = new ArrayList<>();
    getCommandsFromInput(args, functions);
    return getCommands(args, functions);
  }

  private void getCommandsFromInput(List<List<Integer>> args, List<String> functions) {
    args.add(new ArrayList<>());
    int count = 0;
    while (scanner.hasNext()) {
      String next = scanner.next().toLowerCase();
      try {
        args.get(count - 1).add(Integer.parseInt(next));
      } catch (NumberFormatException | IndexOutOfBoundsException e) {
        functions.add(next);
        args.add(new ArrayList<>());
        count++;
      }
    }
  }

  private List<Command> getCommands(List<List<Integer>> args, List<String> functions)
      throws IllegalArgumentException {
    List<Command> commands = new ArrayList<>();
    for (int index = 0; index < functions.size(); index++) {
      try {
        commands.add(getCommandFromInput(functions.get(index), args.get(index)));
        prevCommands.add(commandStringConstruction(functions.get(index), args.get(index)));
      } catch (IndexOutOfBoundsException e) {
        throw new IllegalArgumentException();
      }
    }
    return commands;
  }

  private String commandStringConstruction(String function, List<Integer> args) {
    StringBuilder commandString = new StringBuilder();
    commandString.append(function);
    for (Integer arg : args) {
      commandString.append(" ");
      commandString.append(arg);
    }
    return commandString.toString();
  }

  private Command getCommandFromInput(String function, List<Integer> args)
      throws IndexOutOfBoundsException {
    return switch (function) {
      case "fd" -> new MoveForwardCommand(args);
      case "bk" -> new MoveBackwardCommand(args);
      case "lt" -> new RotateLeftCommand(args);
      case "rt" -> new RotateRightCommand(args);
      case "pd" -> new SetPenDownCommand(args);
      case "pu" -> new SetPenUpCommand(args);
      case "st" -> new ShowCommand(args);
      case "ht" -> new HideCommand(args);
      case "home" -> new GoHomeCommand(args);
      case "stamp" -> new StampCommand(args);
      case "tell" -> new TellCommand(args);
      default -> throw new InputMismatchException();
    };
  }

  /**
   * Purpose: Get a list of commands for the turtle(s) to execute based on the commands in the file
   * provided by the user.
   * Assumptions: The file is in .txt format and commands in the file should be valid
   *
   * @param commandFile A .txt file containing commands for the program
   * @return List of Commands for the turtles to execute.
   * @throws FileNotFoundException
   * @throws IllegalArgumentException
   */
  public List<Command> handleFileSelected(File commandFile)
      throws FileNotFoundException, IllegalArgumentException {
    ArrayList<Command> commands = new ArrayList<>();
    if (!commandFile.getName().endsWith(".txt")) {
      throw new IllegalArgumentException();
    }
    Scanner fileScanner = new Scanner(commandFile);
    fileScanner.useDelimiter("\n");
    while (fileScanner.hasNext()) {
      String next = fileScanner.next();
      if (next.startsWith("#") || next.equals(" ")) {
        continue;
      }
      commands.addAll(getCommandsFromInput(next));
    }
    return commands;
  }

  /**
   * Purpose: Save the command history of the program as a .txt file that the user can download.
   *
   * @throws IOException
   */
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

  /**
   * Purpose: Get a list of the previous commands executed in the program.
   *
   * @return List of strings representing the command history
   */
  public List<String> getCommandHistory() {
    return prevCommands;
  }
}

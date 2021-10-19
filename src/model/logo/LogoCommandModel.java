package model.logo;

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
import model.CommandModel;
import model.PrevCommandsHandler;
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
 * into commands that the turtles will follow to be able to draw on the screen. Assumptions: The
 * user can input commands either into the text box or in .txt file format. This class is expected
 * to be able to handle incorrect commands, or improper file formats. Dependencies: File,
 * FileNotFoundException, IOException, PrintWriter, StandardCharsets, ArrayList,
 * InputMismatchException, List, Properties, Scanner, Command, GoHomeCommand, HideCommand,
 * MoveBackwardCommand, MoveForwardCommand, RotateLeftCommand, RotateRightCommand,
 * SetPenDownCommand, SetPenUpCommand, ShowCommand, StampCommand, TellCommand, PropertiesLoader
 * <p>
 * Example: Create an instance of this class in the high level model for the program. When the user
 * inputs text as commands and hits run or enter, call on this class to create the commands that
 * must be run, then have these run in the model.
 *
 * @Author Evan Kenyon
 */
public class LogoCommandModel extends CommandModel {

  private Scanner scanner;
  private int numProgramsSaved;
  private Properties props;
  private PrevCommandsHandler prevCommandsHandler;

  /**
   * Purpose: Create a new Logo command model. Assumptions: Will be called in the high level backend
   * model to take responsibility for parsing.
   */
  public LogoCommandModel() {
    numProgramsSaved = 0;
    props = PropertiesLoader.loadProperties("./src/model/resources/English.properties");
    prevCommandsHandler = new PrevCommandsHandler("logo");
  }

  /**
   * Purpose: Get a list of commands for the turtle(s) to execute based on the commands given by the
   * user as strings of text. Assumptions: Any incorrect commands will throw exceptions.
   *
   * @param input user String input or String input from a line of a user input file (ex. "fd 50"
   *              or a chain of commands such as "fd 50 rt 90 bk 50").
   * @return List of Commands to be executed by the turtles
   * @throws InputMismatchException thrown if unrecognized command is used (ex. user inputs "forward
   * 50" instead of "fd 50").
   * @throws IllegalArgumentException thrown if the user passes in an invalid argument to a command
   * (ex. just "fd" instead of "fd *distance*")
   */
  @Override
  public List<Command> getCommandsFromInput(String input)
      throws InputMismatchException, IllegalArgumentException {
    scanner = new Scanner(input);
    List<List<Integer>> args = new ArrayList<>();
    List<String> functions = new ArrayList<>();
    getCommandsFromInput(args, functions);
    return getCommands(args, functions);
  }

  /**
   * Purpose: Save the command history of the program as a .txt file that the user can download.
   *
   * @throws IOException thrown if saveCommandsFile throws this exception (see more in
   * PrevCommandsHandler class).
   */
  public void saveCommandsAsFile() throws IOException, IllegalArgumentException {
    prevCommandsHandler.saveCommandsAsFile();
  }

  /**
   * Purpose: Get a list of commands for the turtle(s) to execute based on the commands in the file
   * provided by the user. Assumptions: The file is in .txt format and commands in the file should
   * be valid
   *
   * @param commandFile A .txt file containing commands for the program
   * @return List of Commands for the turtles to execute.
   * @throws FileNotFoundException thrown if file selected is not found.
   * @throws IllegalArgumentException thrown if file is not of type .txt.
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
   * Purpose: Get a list of the previous commands executed in the program.
   *
   * @return List of strings representing the command history
   */
  public List<String> getCommandHistory() {
    return prevCommandsHandler.getCommandHistory();
  }

  private void getCommandsFromInput(List<List<Integer>> args, List<String> functions) {
    args.add(new ArrayList<>());
    int count = 0;
    while (scanner.hasNext()) {
      String next = scanner.next();
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
        List<String> stringArgs = new ArrayList<>();
        for (Integer arg : args.get(index)) {
          stringArgs.add(arg.toString());
        }
        prevCommandsHandler.addPrevCommand(functions.get(index), stringArgs);
      } catch (IndexOutOfBoundsException e) {
        throw new IllegalArgumentException();
      }
    }
    return commands;
  }

  private Command getCommandFromInput(String function, List<Integer> args)
      throws IndexOutOfBoundsException {
    return switch (function.toLowerCase()) {
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

}

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
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

public class CommandModel {

  private static final String DEFAULT_RESOURCE_PACKAGE = "./src/model/resources/";
  private Scanner scanner;
  private int numProgramsSaved;
  private final List<String> prevCommands;
  private Properties props;
  private String language;

  public CommandModel() {
    numProgramsSaved = 0;
    prevCommands = new ArrayList<>();
    language = "English";

    props = PropertiesLoader.loadProperties(DEFAULT_RESOURCE_PACKAGE + language);
  }

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

  public List<String> getCommandHistory() {
    return prevCommands;
  }
}

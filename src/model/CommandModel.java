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
    props = PropertiesLoader.loadProperties("./src/model/resources/command.properties");
  }

  public List<Command> getCommandsFromInput(String input)
      throws InputMismatchException, NumberFormatException {
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
      } catch (IndexOutOfBoundsException e) {
        throw new IllegalArgumentException();
      }
    }
    return commands;
  }

  private Command getCommandFromInput(String function, List<Integer> args)
      throws IndexOutOfBoundsException {
    return switch (function) {
      case "fd" -> new MoveCommand(args.get(0));
      case "bk" -> new MoveCommand(-args.get(0));
      case "lt" -> new RotateCommand(-args.get(0));
      case "rt" -> new RotateCommand(args.get(0));
      case "pd" -> new SetPenCommand(true);
      case "pu" -> new SetPenCommand(false);
      case "st" -> new ShowOrHideCommand(true);
      case "ht" -> new ShowOrHideCommand(false);
      case "home" -> new GoHomeCommand();
      case "stamp" -> new StampCommand();
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
      commands.addAll(getCommandsFromInput(fileScanner.next()));
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
}

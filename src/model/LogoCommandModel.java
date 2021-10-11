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

public class LogoCommandModel extends CommandModel {

  private Scanner scanner;
  private int numProgramsSaved;
  private Properties props;
  private PrevCommandsHandler prevCommandsHandler;

  public LogoCommandModel() {
    numProgramsSaved = 0;
    props = PropertiesLoader.loadProperties("./src/model/resources/English.properties");
  }

  @Override
  public List<Command> getCommandsFromInput(String input)
      throws InputMismatchException, IllegalArgumentException {
    scanner = new Scanner(input);
    List<List<Integer>> args = new ArrayList<>();
    List<String> functions = new ArrayList<>();
    prevCommandsHandler = new PrevCommandsHandler();
    getCommandsFromInput(args, functions);
    return getCommands(args, functions);
  }

  public void saveCommandsAsFile() throws IOException {
    prevCommandsHandler.saveCommandsAsFile();
  }

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

}

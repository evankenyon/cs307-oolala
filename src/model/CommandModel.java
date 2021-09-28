package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommandModel {

  private Scanner scanner;
  private int numProgramsSaved;
  private List<String> prevCommands;
  private List<TurtleModel> currTurtleModels;
  private List<TurtleModel> allTurtleModels;

  public CommandModel(TurtleModel firstTurtle) {
    numProgramsSaved = 0;
    prevCommands = new ArrayList<>();
    prevCommands.add("fd 50");
    prevCommands.add("rt 50");
    currTurtleModels = new ArrayList<>();
    currTurtleModels.add(firstTurtle);
    allTurtleModels = new ArrayList<>();
    allTurtleModels.add(firstTurtle);
  }

  public List<TurtleModel> parseInput(String input) throws InputMismatchException, NumberFormatException {
    if (input.startsWith("#") || input.equals("")) {
      return null;
    }
    scanner = new Scanner(input);
    while (scanner.hasNext()) {
      switch (scanner.next().toLowerCase()) {
        case "fd" -> handleMovementCommand(1);
        case "bk" -> handleMovementCommand(-1);
        case "lt" -> handleAngleCommand(-1);
        case "rt" -> handleAngleCommand(1);
        case "pd" -> handlePenCommand(true);
        case "pu" -> handlePenCommand(false);
        case "st" -> System.out.println("Show turtle");
        case "ht" -> System.out.println("Hide turtle");
        case "home" -> handleGoHomeCommand();
        case "stamp" -> System.out.println("Stamp turtle");
        case "tell" -> handleTellCommand();
        default -> throw new InputMismatchException();
      }
    }
    return null;
  }

  public void handleFileSelected(File commandFile)
      throws FileNotFoundException, IllegalArgumentException {
    if (!commandFile.getName().endsWith(".txt")) {
      throw new IllegalArgumentException();
    }
    Scanner fileScanner = new Scanner(commandFile);
    fileScanner.useDelimiter("\n");
    while (fileScanner.hasNext()) {
      parseInput(fileScanner.next());
    }
  }

  public void saveCommandsAsFile() throws FileNotFoundException, UnsupportedEncodingException {
    numProgramsSaved++;
    // Code for creating a file and writing to it borrowed from
    // https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
    PrintWriter currProgram = new PrintWriter("./data/programs/program" + numProgramsSaved
        + ".txt", "UTF-8");
    currProgram.println("#Saved program number " + numProgramsSaved);
    for (String command : prevCommands) {
      System.out.println(command);
      currProgram.println(command);
    }
    currProgram.close();
  }

  private void handleMovementCommand(int direction) throws InputMismatchException {
    int distance = parseFirstNumArg();
    for(TurtleModel turtleModel : currTurtleModels) {
      turtleModel.move(direction * distance);
    }
  }

  private void handleAngleCommand(int direction) throws InputMismatchException {
    int angle = parseFirstNumArg();
    for(TurtleModel turtleModel : currTurtleModels) {
      turtleModel.rotate(direction * angle);
    }
  }

  private void handlePenCommand(boolean isPenUp) {
    for(TurtleModel turtleModel : currTurtleModels) {
      turtleModel.setPen(isPenUp);
    }
  }

  private void handleGoHomeCommand() {
    for(TurtleModel turtleModel : currTurtleModels) {
      turtleModel.goHome();
    }
  }

  // Fix in case when try to tell a turtle that's more than one greater than allTurtleModels.size()
  private List<TurtleModel> handleTellCommand() {
    List<TurtleModel> newTurtleModels = new ArrayList<>();
    currTurtleModels.clear();
    int turtleNum = parseFirstNumArg();
    addCurrTurtleModel(turtleNum, newTurtleModels);
    while (scanner.hasNext()) {
      turtleNum = parseNumInput();
      addCurrTurtleModel(turtleNum, newTurtleModels);
    }
    return newTurtleModels;
  }

  private void addCurrTurtleModel(int turtleNum, List<TurtleModel> newTurtleModels) {
    TurtleModel newTurtleModel = new TurtleModel();
    try {
      currTurtleModels.add(allTurtleModels.get(turtleNum - 1));
    } catch (IndexOutOfBoundsException e) {
      currTurtleModels.add(newTurtleModel);
      allTurtleModels.add(newTurtleModel);
      newTurtleModels.add(newTurtleModel);
    }
  }

  private int parseFirstNumArg() {
    if (!scanner.hasNext()) {
      throw new InputMismatchException();
    }
    return parseNumInput();
  }

  private int parseNumInput() throws IllegalArgumentException{
    int numInput;
    try {
      numInput = Integer.parseInt(scanner.next());
    } catch (NumberFormatException e) {
      // TODO: fix this up??
      throw new IllegalArgumentException();
    }
    return numInput;
  }

}

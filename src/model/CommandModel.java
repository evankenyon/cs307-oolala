package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommandModel {

  private Scanner scanner;
  private int numProgramsSaved;
  private List<String> prevCommands;

  public CommandModel() {
    numProgramsSaved = 0;
    prevCommands = new ArrayList<>();
    prevCommands.add("fd 50");
    prevCommands.add("rt 50");
  }

  public void parseInput(String input) throws InputMismatchException, NumberFormatException {
    if (input.startsWith("#") || input.equals("")) {
      return;
    }
    scanner = new Scanner(input);
    while (scanner.hasNext()) {
      switch (scanner.next().toLowerCase()) {
        case "fd" -> handleMovementCommand("forward");
        case "bk" -> handleMovementCommand("backward");
        case "lt" -> handleAngleCommand("left");
        case "rt" -> handleAngleCommand("right");
        case "pd" -> System.out.println("Pen down");
        case "pu" -> System.out.println("Pen up");
        case "st" -> System.out.println("Show turtle");
        case "ht" -> System.out.println("Hide turtle");
        case "home" -> System.out.println("Go home");
        case "stamp" -> System.out.println("Stamp turtle");
        case "tell" -> handleTellCommand();
        default -> throw new InputMismatchException();
      }
    }
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

  private void handleAngleCommand(String direction) throws InputMismatchException {
    System.out.print("Turn " + direction);
    System.out.print(parseFirstNumArg());
    System.out.println(" degrees");
  }

  private void handleMovementCommand(String direction) throws InputMismatchException {
    System.out.print("Go " + direction);
    System.out.print(parseFirstNumArg());
    System.out.println(" pixels");
  }

  private void handleTellCommand() {
    System.out.print("Tell turtles ");
    System.out.print(parseFirstNumArg());
    System.out.print(" ");
    while (scanner.hasNext()) {
      System.out.print(parseNumInput());
      System.out.print(" ");
    }
    System.out.println();
  }

  private int parseFirstNumArg() {
    if (!scanner.hasNext()) {
      throw new InputMismatchException();
    }
    return parseNumInput();
  }

  private int parseNumInput() throws IllegalArgumentException{
    int numInput = -1;
    try {
      numInput = Integer.parseInt(scanner.next());
    } catch (NumberFormatException e) {
      // TODO: fix this up??
      throw new IllegalArgumentException();
    }
    return numInput;
  }

}

package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandModel {
  private Scanner scanner;

  public CommandModel() {

  }

  public void parseInput(String input) throws InputMismatchException {
    scanner = new Scanner(input);
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

  private void handleAngleCommand(String direction) throws InputMismatchException{
    System.out.print("Turn " + direction);
    System.out.print(parseFirstNumArg());
    System.out.println(" degrees");
  }

  private void handleMovementCommand(String direction) throws InputMismatchException{
    System.out.print("Go " + direction);
    System.out.print(parseFirstNumArg());
    System.out.println(" pixels");
  }

  private void handleTellCommand() {
    System.out.print("Tell turtles ");
    System.out.print(parseFirstNumArg());
    System.out.print(" ");
    while(scanner.hasNext()) {
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

  private int parseNumInput() {
    int numInput = - 1;
    try {
      numInput = Integer.parseInt(scanner.next());
    } catch (NumberFormatException e) {
      // TODO: fix this up
      e.printStackTrace();
    }
    return numInput;
  }

}

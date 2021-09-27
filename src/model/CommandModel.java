package model;

import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class CommandModel {

  public CommandModel() {

  }

  public void parseInput(String input) throws PatternSyntaxException {
    Scanner scanner = new Scanner(input);
    switch (scanner.next().toLowerCase()) {
      case "fd" -> System.out.println("Go forward");
      case "bk" -> System.out.println("Go backward");
      case "lt" -> System.out.println("Turn left");
      case "rt" -> System.out.println("Turn right");
      case "pd" -> System.out.println("Pen down");
      case "pu" -> System.out.println("Pen up");
      case "st" -> System.out.println("Show turtle");
      case "ht" -> System.out.println("Hide turtle");
      case "home" -> System.out.println("Go home");
      case "stamp" -> System.out.println("Stamp turtle");
      case "tell" -> System.out.println("Tell turtles");
      default -> throw new PatternSyntaxException("test", "test", 2);
    }
  }

}

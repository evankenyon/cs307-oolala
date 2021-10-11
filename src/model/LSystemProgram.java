package model;

import java.awt.SystemTray;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LSystemProgram {
  private String start;
  private List<LSystemRules> rules;
  private String currLevel;
  private boolean isValidProgram;
  private int levelNum;

  public LSystemProgram() {
    isValidProgram = false;
    levelNum = 0;
    rules = new ArrayList<>();
  }

  public void parseInput(String input) throws InputMismatchException {
    String[] inputAsArray = input.toLowerCase().split(" ");
    String command = inputAsArray[0];
    List<String> arguments = new ArrayList<>();

    for(int index = 1; index < inputAsArray.length; index++) {
      arguments.add(inputAsArray[index]);
    }

    switch (command) {
      case "start" -> start = arguments.get(0);
      case "rule" -> rules.add(new LSystemRules(arguments.get(0) + " " + arguments.get(1)));
      default -> throw new InputMismatchException();
    }
    if(!start.isEmpty() && !rules.isEmpty()) {
      isValidProgram = true;
    }
  }

  public String getNextLevel(int levelNum) {
    if(levelNum == 0) {
      currLevel = start;
    } else {
      for(LSystemRules rule : rules) {
        System.out.println(rule.getId());
        System.out.println(rule.getRule());
        currLevel = currLevel.replace(rule.getId(), rule.getRule());
      }
    }
    return currLevel;
  }

  public boolean getIsValidProgram() {
    return isValidProgram;
  }
}

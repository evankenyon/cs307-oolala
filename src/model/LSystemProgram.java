package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.commands.Command;

public class LSystemProgram {

  private String start;
  private List<LSystemRules> rules;
  private String currLevel;
  private boolean isValidProgram;
  private int levelNum;
  private PrevCommandsHandler prevCommandsHandler;
  private Map<String, List<String>> newSymbolSet;

  public LSystemProgram() {
    isValidProgram = false;
    levelNum = 0;
    rules = new ArrayList<>();
    prevCommandsHandler = new PrevCommandsHandler("lsystem");
    start= "";
    newSymbolSet = new HashMap<>();
  }

  public void parseInput(String input) throws InputMismatchException {
    String[] inputAsArray = input.toLowerCase().split("\\s+");
    String command = inputAsArray[0];
    List<String> arguments = new ArrayList<>();

    for (int index = 1; index < inputAsArray.length; index++) {
      arguments.add(inputAsArray[index]);
    }

    if(arguments.isEmpty()) {
      throw new InputMismatchException();
    }
    switch (command) {
      case "start" -> start = arguments.get(0);
      case "rule" -> rules.add(new LSystemRules(arguments.get(0) + " " + arguments.get(1)));
      case "set" -> newSymbolSet.put(arguments.get(0), arguments.subList(1, arguments.size()));
      default -> throw new InputMismatchException();
    }
    prevCommandsHandler.addPrevCommand(input);
    if (!start.isEmpty() && !rules.isEmpty()) {
      isValidProgram = true;
    }
  }

  public String getNextLevel(int levelNum) {
    if (levelNum == 0) {
      currLevel = start;
    } else {
      for (LSystemRules rule : rules) {
        currLevel = currLevel.replace(rule.getId(), rule.getRule());
      }
    }
    return currLevel;
  }

  public boolean getIsValidProgram() {
    return isValidProgram;
  }

  public void saveCommandsAsFile() throws IOException {
    prevCommandsHandler.saveCommandsAsFile();
  }

  public List<String> getCommandHistory() {
    return prevCommandsHandler.getCommandHistory();
  }

  public void handleFileInput(File commandFile)
      throws FileNotFoundException, IllegalArgumentException {
    if (!commandFile.getName().endsWith(".txt")) {
      throw new IllegalArgumentException();
    }
    Scanner fileScanner = new Scanner(commandFile);
    fileScanner.useDelimiter("\n");
    while (fileScanner.hasNext()) {
      String next = fileScanner.next();
      if (next.startsWith("#") || next.isBlank()) {
        continue;
      }
      parseInput(next);
    }
  }

  public Map<String, List<String>> getNewSymbolSet() {
    Map<String, List<String>> newSymbolSetCopy = new HashMap<>(newSymbolSet);
    newSymbolSet.clear();
    return newSymbolSetCopy;
  }
}

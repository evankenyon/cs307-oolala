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

/**
 * Purpose: Represent an LSystemProgram, which contains a start symbol, a list of rules for
 * transforming that symbol recursively, and a list of new symbols that is used to update
 * CommandRunner since that is the class responsible for parsing symbols into commands.
 * Dependencies: File, FileNotFoundException, IOException, ArrayList, HashMap,
 * InputMismatchException, List, Map, Scanner Example: Instantiate an LSystemProgram object in
 * LSystemsModel in order to parse user inputted commands into a start symbol and rules that can be
 * used to generate fractal levels. Other details: Generates the Strings that represent different
 * levels of fractals based on the start symbol and rules. Also, has a PrevCommandsHandler object
 * that deals with the backend aspect of storing a command history.
 *
 * @author Evan Kenyon
 */
public class LSystemProgram {

  private String start;
  private List<LSystemRules> rules;
  private String currLevel;
  private boolean isValidProgram;
  private PrevCommandsHandler prevCommandsHandler;
  private Map<String, List<String>> newSymbolSet;

  /**
   * Purpose: Construct an LSystemProgram object
   */
  public LSystemProgram() {
    isValidProgram = false;
    rules = new ArrayList<>();
    prevCommandsHandler = new PrevCommandsHandler("lsystem");
    start = "";
    newSymbolSet = new HashMap<>();
  }

  /**
   * Purpose: parses input into start, rules, or newSymbolSet, particularly does so as described by
   * the project requirements. Assumptions: input is user input from the frontend
   *
   * @param input string to parse into start, rules, or newSymbolSet
   * @throws InputMismatchException thrown if input does not follow syntax as described by the
   *                                project requirements.
   */
  public void parseInput(String input) throws InputMismatchException {
    String[] inputAsArray = input.toLowerCase().split("\\s+");
    String command = inputAsArray[0];
    List<String> arguments = new ArrayList<>();

    for (int index = 1; index < inputAsArray.length; index++) {
      arguments.add(inputAsArray[index]);
    }

    if (arguments.isEmpty()) {
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

  /**
   * Purpose: Gets the symbols for the next level (i.e. if we have start f and rule ff, first level
   * will be f, second will be ff, third will be ffff and so on). Assumptions: numLevel argument
   * monotonically increases starting at 0.
   *
   * @param levelNum the current level of the fractal.
   * @return the symbols for the current level as a String.
   */
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

  /**
   * Purpose: Gets whether this program is valid (i.e. has a start symbol and at least one rule).
   *
   * @return whether this program is valid as described above.
   */
  public boolean getIsValidProgram() {
    return isValidProgram;
  }

  /**
   * Purpose: Saves the previously input commands (i.e. previous rules, start symbols, and set
   * commands) as a .txt file.
   *
   * @throws IOException thrown if path that prevCommandsHandler tries to save the file to does not
   *                     exist.
   */
  public void saveCommandsAsFile() throws IOException {
    prevCommandsHandler.saveCommandsAsFile();
  }

  /**
   * Purpose: Gets the command history (i.e. previous rules, start symbols, and set commands).
   *
   * @return the command history as described above.
   */
  public List<String> getCommandHistory() {
    return prevCommandsHandler.getCommandHistory();
  }

  /**
   * Purpose: Takes in a commandFile in order to set the start symbol, rules, and create the
   * newSymbolSet of this class. Assumptions: commandFile follows the assumptions given in the
   * project overview on the course website.
   *
   * @param commandFile the file to parse in order to fulfill the purpose stated above.
   * @throws FileNotFoundException    thrown if commandFile does not exist.
   * @throws IllegalArgumentException thrown if file uploaded is not of type .txt.
   */
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

  /**
   * Purpose: returns this class's newSymbolSet object
   *
   * @return this class's newSymbolSet object
   */
  public Map<String, List<String>> getNewSymbolSet() {
    return newSymbolSet;
  }
}

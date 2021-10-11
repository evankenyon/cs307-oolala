package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PrevCommandsHandler {
  private int numProgramsSaved;
  private String programName;
  protected List<String> prevCommands;

  public PrevCommandsHandler(String programName) {
    numProgramsSaved = 0;
    prevCommands = new ArrayList<>();
    this.programName = programName;
  }

  public void saveCommandsAsFile() throws IOException {
    numProgramsSaved++;
    // Code for creating a file and writing to it borrowed from
    // https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
    PrintWriter currProgram = new PrintWriter("./data/programs/" + programName + "/program" + numProgramsSaved
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

  public void addPrevCommand(String function, List<String> args) {
    prevCommands.add(commandStringConstruction(function, args));
  }

  public void addPrevCommand(String input) {
    prevCommands.add(input);
  }

  private String commandStringConstruction(String function, List<String> args) {
    StringBuilder commandString = new StringBuilder();
    commandString.append(function);
    for (String arg : args) {
      commandString.append(" ");
      commandString.append(arg);
    }
    return commandString.toString();
  }
}

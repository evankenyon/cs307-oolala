package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import model.commands.Command;

public abstract class AppModel {
  protected final TurtleController turtleController;

  protected Queue<Command> commandsToRun;
  protected CommandModel commandModel;

  public AppModel() {
    turtleController = new TurtleController();
  }

  public abstract void handleTextInput(String input);

  public abstract void runNextCommand();

  public List<TurtleModel> getActiveTurtles() {
    return turtleController.getActiveTurtles();
  }

  public abstract List<String> getCommandHistory();

  public abstract void saveCommandsAsFile() throws IOException;

  public void handleFileInput(File file) {
    try {
      commandsToRun.addAll(commandModel.handleFileSelected(file));
    } catch (IllegalArgumentException | FileNotFoundException e) {
      // TODO: fix
      e.printStackTrace();
    }
  }
}

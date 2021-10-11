package model;

import java.util.List;
import model.commands.Command;

public abstract class CommandModel {

  public abstract List<Command> getCommandsFromInput(String input);
}

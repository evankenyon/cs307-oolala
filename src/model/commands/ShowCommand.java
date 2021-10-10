package model.commands;

import java.util.List;

public class ShowCommand extends ShowOrHideCommand {

  public ShowCommand(List<Integer> args) throws IllegalArgumentException {
    super(true, args);
  }
}

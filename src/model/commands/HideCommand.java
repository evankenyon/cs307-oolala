package model.commands;

import java.util.List;

public class HideCommand extends ShowOrHideCommand {

  public HideCommand(List<Integer> args) throws IllegalArgumentException {
    super(false, args);
  }
}

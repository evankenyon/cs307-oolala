package model.commands;

import java.util.List;

public class RotateLeftCommand extends RotateCommand {

  public RotateLeftCommand(List<Integer> args)
      throws IllegalArgumentException {
    super(-1, args);
  }
}

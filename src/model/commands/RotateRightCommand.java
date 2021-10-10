package model.commands;

import java.util.List;

public class RotateRightCommand extends RotateCommand {

  public RotateRightCommand(List<Integer> args)
      throws IllegalArgumentException {
    super(1, args);
  }
}

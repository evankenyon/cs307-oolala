package model.commands;

import java.util.List;

public class MoveForwardCommand extends MoveCommand {

  public MoveForwardCommand(List<Integer> args) throws IllegalArgumentException {
    super(1, args);
  }
}

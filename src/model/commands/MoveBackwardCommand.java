package model.commands;

import java.util.List;

public class MoveBackwardCommand extends MoveCommand {

  public MoveBackwardCommand(List<Integer> args) throws IllegalArgumentException {
    super(-1, args);
  }
}

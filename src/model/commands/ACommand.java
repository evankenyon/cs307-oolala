package model.commands;

import java.util.List;

public class ACommand extends MoveAndPenCommand {

  public ACommand(int distance) {
    super(true, -1, distance);
  }
}

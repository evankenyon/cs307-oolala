package model.commands;

import java.util.List;

public class FCommand extends MoveAndPenCommand {

  public FCommand(int distance) {
    super(true, 1, distance);
  }
}

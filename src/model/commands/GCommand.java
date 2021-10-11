package model.commands;

import java.util.List;

public class GCommand extends MoveAndPenCommand {

  public GCommand(int distance) {
    super(false, 1, distance);
  }
}

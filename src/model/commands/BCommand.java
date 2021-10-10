package model.commands;

import java.util.List;

public class BCommand extends MoveAndPenCommand {

  public BCommand(int distance) {
    super(false, -1, distance);
  }
}

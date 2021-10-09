package model.commands;

import java.util.List;

public class SetPenUpCommand extends SetPenCommand {

  public SetPenUpCommand(List<Integer> args) throws IllegalArgumentException {
    super(false, args);
  }

}

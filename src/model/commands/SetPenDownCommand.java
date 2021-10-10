package model.commands;

import java.util.List;

public class SetPenDownCommand extends SetPenCommand {

  public SetPenDownCommand(List<Integer> args) throws IllegalArgumentException {
    super(true, args);
  }

}

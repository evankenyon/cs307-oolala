package model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.TurtleController;
import model.TurtleModel;

public class TellCommand extends Command {

  private List<Integer> ids;

  public TellCommand(List<Integer> ids) {
    this.ids = new ArrayList<>(ids);
  }

  @Override
  public void runCommand(TurtleController turtleController) {

  }

  public void addNewTurtle() {

  }

}

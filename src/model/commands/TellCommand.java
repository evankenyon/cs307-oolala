package model.commands;

import java.util.ArrayList;
import java.util.List;
import model.TurtleController;

public class TellCommand extends Command {

  private List<Integer> ids;

  public TellCommand(List<Integer> ids) {
    this.ids = new ArrayList<>(ids);
  }

  /**
   * With the tell command, we are effectively saying which turtles we want to be doing "work" in
   * the future. At the start of the command we clear the active turtles then add the ones to the
   * active list we wish to work with
   *
   * @param turtleController
   */
  @Override
  public void runCommand(TurtleController turtleController) {
    turtleController.resetActiveTurtles();
    for (Integer id : ids) {
      turtleController.addTurtleToActives(id);
    }
  }

}

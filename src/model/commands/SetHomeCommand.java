package model.commands;

import javafx.scene.control.TextInputDialog;
import model.TurtleModel;

public class SetHomeCommand extends Command{
  private int[] home = new int[2];

  public SetHomeCommand(int[] home){
    this.home[0] = home[0];
    this.home[1] = home[1];
  }

  @Override
  public void runCommand (TurtleModel turtleModel) {
    turtleModel.setHome(home);
  }
}

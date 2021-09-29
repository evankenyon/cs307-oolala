package model.commands;

import javafx.scene.control.TextInputDialog;
import model.TurtleController;
import model.TurtleModel;

public class SetHomeCommand extends Command{
  private int[] home = new int[2];

  public SetHomeCommand(int[] home){
    this.home[0] = home[0];
    this.home[1] = home[1];
  }

  public void runCommand(TurtleController turtleController){
    for(TurtleModel turtleModel : turtleController.getActiveTurtles()){
      turtleModel.setHome(home);
    }
  }
}

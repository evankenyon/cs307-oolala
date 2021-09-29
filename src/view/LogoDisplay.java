package view;

import model.TurtleModel;

import java.net.URL;

public class LogoDisplay {
    private int ID;
    private TurtleDisplay turtleDisplay;
    private TurtleModel turtleModel;

    public LogoDisplay(int id){
        ID = id;
        turtleModel = new TurtleModel();
        turtleDisplay = new TurtleDisplay(turtleModel);
    }

    public void setTurtleImage(URL url){
        turtleDisplay.setImage(url);
    }

    public void move(int dist){
        turtleDisplay.moveTurtle(dist);
    }

    public void rotate(int rot){
        turtleDisplay.rotateTurtle(rot);
    }

    public void penState(boolean bol){
        turtleDisplay.turtleDrawing(bol);
    }


}

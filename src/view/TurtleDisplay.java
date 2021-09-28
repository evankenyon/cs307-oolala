package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.canvas.*;
import model.TurtleModel;


public class TurtleDisplay{
    TurtleModel turtle = new TurtleModel();
    private Canvas board;
    private final double SIZE = 500;
    private int penThickness = 1;
    private Image turtleImg;
/*
    public TurtleDisplay(){
        this());
    }
*/
    public TurtleDisplay(TurtleModel tortoise){
        turtle = tortoise;

    }

    public void moveTurtle(int distance){
        turtle.move(distance);
        updateTurtleScreen();
    }

    public void rotateTurtle(int angle){
        turtle.rotate(angle);
        updateTurtleScreen();
    }

    public void turtleDrawing(boolean isItDrawing){
        turtle.setPen(isItDrawing);
    }

    public void setHomeLocation(int[] coordinates){
        turtle.setHome(coordinates);
    }

    public void setPenThickness(int i){
        penThickness = i;
    }

    public void setImage(Image img){

    }

    public Image getImage(Image img){
        return null;
    }

    public void updateTurtleScreen(){
        int[] turtlePosition = turtle.getPosition();
        if(turtle.getPen()) {

        } else {

        }
    }
}

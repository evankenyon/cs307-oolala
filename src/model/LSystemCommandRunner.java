//package model;
//
//import model.commands.*;
//
//// fix this
//public class LSystemCommandRunner {
//    private TurtleController turtleController;
//    private int movementLength;
//    private int rotationAngle;
//    private boolean stampBranchImage;
//    private int[] location;
//
//    public LSystemCommandRunner(TurtleController inputTurtController, int distance, int angle, boolean branchStamp, int[]
//                                startingLoc){
//        turtleController = inputTurtController;
//        movementLength = distance;
//        rotationAngle = angle;
//        stampBranchImage = branchStamp;
//        location = startingLoc;
//    }
//
//    public void runLsysCommand(String ruleMovement){
//        switch (ruleMovement){
//            case "f" -> move(true, 1);
//            case "g" -> move(false, 1);
//            case "a" -> move(true, -1);
//            case "b" -> move(false, -1);
//            case "+" -> rotate(1);
//            case "-" -> rotate(-1);
//            case "x" -> stamp();
//        }
//    }
//    private void move(boolean penPosition, int direction){
//        new SetPenCommand(penPosition).runCommand(turtleController);
//        new MoveCommand(direction * movementLength).runCommand(turtleController);
//    }
//
//    private void rotate(int direction){
//        new RotateCommand(direction).runCommand(turtleController);
//    }
//
//    private void stamp(){
//        new StampCommand().runCommand(turtleController);
//    }
//
//    public void goToStartLocation(){
//        new SetHomeCommand(location).runCommand(turtleController);
//        new GoHomeCommand().runCommand(turtleController);
//    }
//
//    public void setStartLocation(int[] inputStartLocation){
//        location = inputStartLocation;
//    }
//
//    public void setMovementLength(int distance){
//        this.movementLength = distance;
//    }
//
//    public void setRotationAngle(int rotationAngle) {
//        this.rotationAngle = rotationAngle;
//    }
//
//    public void setStampBranchImage(boolean stampBranchImage) {
//        this.stampBranchImage = stampBranchImage;
//    }
//}

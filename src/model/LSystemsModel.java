package model;

import java.util.ArrayList;
import java.util.List;

public class LSystemsModel {
    private String startingRule;
    private List<LSystemRules> ruleBook;
    private int numLevels;
    private int movementLength;
    private int rotationAngle;
    private boolean stampBranchImage;
    private int[] location;
    private LSystemCommandRunner lsystemCommandRunner;

    public LSystemsModel(){
        this("");
    }

    public LSystemsModel(String beginningRule){
        startingRule = beginningRule.toLowerCase();
        ruleBook = new ArrayList<>();
        numLevels = 1;
        movementLength = 2;
        rotationAngle = 30;
        stampBranchImage = false;
        location = new int[]{0,0};
    }

    public void setStartLocation(int[] inputStartLocation){
        location = inputStartLocation;
    }

    public void setNumLevels(int number){
       this.numLevels = number;
    }

    public void setMovementLength(int distance){
        this.movementLength = distance;
    }

    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public void setStampBranchImage(boolean stampBranchImage) {
        this.stampBranchImage = stampBranchImage;
    }

    public void setStartingRule(String startingRule) {
        this.startingRule = startingRule;
    }

    public void createRule(String rulePassedIn){
        LSystemRules newRule = new LSystemRules(rulePassedIn);
        ruleBook.add(newRule);
    }

    public void run(TurtleController turtleController){
        turtleController.resetActiveTurtles();
        turtleController.addTurtleToActives(1);
        lsystemCommandRunner = new LSystemCommandRunner(turtleController, movementLength, rotationAngle,
                stampBranchImage, location);
        lsystemCommand.goToStartLocation();
        moveTurtleRecursively(numLevels, startingRule);
    }

    public void moveTurtleRecursively(int levelnum, String ruleId){
        if(levelnum < 0){
            lsystemCommandRunner.runLsysCommand(ruleId);
            return;
        }
        String[] rule = findRule(ruleId);
        for(String ruleChar: rule){
            if(ruleChar.equals("+") || ruleChar.equals("-")){
                lsystemCommandRunner.runLsysCommand(ruleChar);
            } else {
                try{
                    moveTurtleRecursively(levelnum - 1, ruleChar);
                } catch(IllegalArgumentException e){
                    throw new IllegalArgumentException("Rule isn't Defined");
                }
            }
        }
    }

    public String[] findRule(String ruleId){
        try{
            for(LSystemRules rule :ruleBook){
                if(rule.getId().equals(ruleId)){
                    return rule.getRule();
                }
            }
        } catch (IllegalCallerException e){
            throw new IllegalArgumentException("Rule Doesn't Exist");
        }
        throw new NullPointerException();
    }
}

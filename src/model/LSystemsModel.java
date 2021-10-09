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
        moveTurtleRecursively(numLevels, startingRule , turtleController);
    }

    public void moveTurtleRecursively(int levelnum, String ruleId, TurtleController turtleController){
        if(levelnum < 0){
            runCommand(ruleId);
            return;
        }
        String[] rule = findRule(ruleId);
        for(String ruleChar: rule){
            if(ruleChar.equals("+")){
                runCommand(ruleChar);
            } else if(ruleChar.equals("-")){
                runCommand(ruleChar);
            } else {
                try{
                    moveTurtleRecursively(levelnum - 1, ruleChar, turtleController);
                } catch(IllegalArgumentException e){
                    throw new IllegalArgumentException("Rule isn't Defined");
                }
            }
        }
    }

    public String[] findRule(String ruleId){
        try{
            for(LSystemRules rule :ruleBook){
                if(rule.getId().equals(ruleId){
                    return rule.getRule();
                }
            }
        } catch (IllegalCallerException){
            throw new IllegalArgumentException("Rule Doesn't Exist");
        }
    }

}

package model;

import java.util.ArrayList;
import java.util.List;

public class LSystemsModel {
    private String startingRule;
    private List<LSystemRules> ruleBook;

    public LSystemModel(String beginningRule){
        startingRule = beginningRule;
        ruleBook = new ArrayList<>();
    }

    public void create(String rulePassedIn){
        LSystemRules newRule = new LSystemRules(rulePassedIn);
        ruleBook.add();
    }

}

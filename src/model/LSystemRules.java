package model;

public class LSystemRules{
    private String id;
    private char[] rule;

    public LSystemRules(String fullRule){
        String[] brokenRuleStr = fullRule.split(" ");
        id = brokenRuleStr[0];
        rule = brokenRuleStr[1].toCharArray();
    }

    public String getId(){
        return id;
    }

    public char[] getRule(){
        return rule;
    }
}

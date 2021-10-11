package model;

// fix this
public class LSystemRules {

  private String id;
  private String rule;

  public LSystemRules(String fullRule) {
    String[] brokenRuleStr = fullRule.toLowerCase().split(" ");
    id = brokenRuleStr[0];
    rule = brokenRuleStr[1];
//    rule = brokenRuleStr[1].split("(?!^)");
  }

  public String getId() {
    return id;
  }

  public String getRule() {
    return rule;
  }
}

package model;

public class LSystemRules {

  private String id;
  private String rule;

  public LSystemRules(String fullRule) {
    String[] brokenRuleStr = fullRule.toLowerCase().split("\\s+");
    id = brokenRuleStr[0];
    rule = brokenRuleStr[1];
  }

  public String getId() {
    return id;
  }

  public String getRule() {
    return rule;
  }
}

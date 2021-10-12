package model;

/**
 * Purpose: Represent a rule for the LSystem application. Maintains separate variables for the
 * rule's symbol and the actual rule itself.
 * Example: Instantiate an object of this class by inputting the arguments to the rule command
 * as a full String, then use in LSystemProgram as an abstraction of an LSystem rule instead of
 * giving that class more than one responsibility.
 *
 * @author Haseeb Chaudhry
 * @author Evan Kenyon
 */
public class LSystemRules {

  private String id;
  private String rule;

  /**
   * Purpose: Construct an LSystemRules object.
   * Assumptions: fullRule's syntatic correctness is checked elsewhere.
   * @param fullRule a String that contains a symbol and the symbols to turn it into.
   */
  public LSystemRules(String fullRule) {
    String[] brokenRuleStr = fullRule.toLowerCase().split("\\s+");
    id = brokenRuleStr[0];
    rule = brokenRuleStr[1];
  }

  /**
   * Purpose: Gets the symbol portion of this rule.
   * @return the symbol portion of this rule.
   */
  public String getId() {
    return id;
  }

  /**
   * Purpose: Gets the rule portion of this rule.
   * @return the rule portion of this rule.
   */
  public String getRule() {
    return rule;
  }
}

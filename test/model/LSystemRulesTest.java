package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LSystemRulesTest {
  LSystemRules lSystemRules;

  @BeforeEach
  void setUp() {
    lSystemRules = new LSystemRules("F F-F++F-F");
  }

  @Test
  public void getIdSimpleCorrect() {
    String expected = "f";
    Assertions.assertEquals(expected, lSystemRules.getId());
  }

  @Test
  public void getRuleSimpleCorrect() {
    String[] expected = new String[]{"f", "-", "f", "+", "+", "f", "-", "f"};
    for(int index = 0; index < lSystemRules.getRule().length; index++) {
      assertEquals(expected[index], lSystemRules.getRule()[index]);
    }
  }
}
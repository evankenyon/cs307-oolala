package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LSystemsModelTest {
  private LSystemsModel lSystemsModel;

  @BeforeEach
  void setUp() {
    lSystemsModel = new LSystemsModel();
  }

  @Test
  void setStartLocation() {
  }

  @Test
  void setNumLevels() {
  }

  @Test
  void setMovementLength() {
  }

  @Test
  void setRotationAngle() {
  }

  @Test
  void setStampBranchImage() {
  }

  @Test
  void setStartingRule() {
  }

  @Test
  void createRule() {
  }

  @Test
  public void testRun() {

  }

  @Test
  void moveTurtleRecursively() {
    lSystemsModel.createRule("F F-F++F-F");
    lSystemsModel.moveTurtleRecursively(4, "f");
  }

  @Test
  void findRule() {
  }
}
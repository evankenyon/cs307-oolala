package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurtleDisplayTest {
    private int[] expectedVal = new int[2];
    private TurtleDisplay testTurtleDisplay;

    @BeforeEach
    public void setUp(){
        testTurtleDisplay = new TurtleDisplay(1);
    }

    @Test
    void moveTurtle() {
        int[] testVal = new int[]{20,20};
        testTurtleDisplay.setPosition(testVal);
        expectedVal[0] = 20;
        expectedVal[1] = 20;
        assertEquals(expectedVal[0], (int) testTurtleDisplay.getImageView().getX());
        assertEquals(expectedVal[1], (int) testTurtleDisplay.getImageView().getY());
    }

    @Test
    void rotateTurtle() {
        int testVal = 20;
        testTurtleDisplay.setAngle(testVal);
        expectedVal[0] = 20;
        assertEquals(expectedVal[0], (int) testTurtleDisplay.getImageView().getRotate());
    }

    @Test
    void setHomeLocation() {
        int[] testVal = new int[]{30, 30};
        testTurtleDisplay.setHomeLocation(testVal);
        expectedVal[0] = 30;
        expectedVal[1] = 30;
        assertEquals(expectedVal[0], (int) testTurtleDisplay.getImageView().getX());
        assertEquals(expectedVal[1], (int) testTurtleDisplay.getImageView().getY());
    }

    @Test
    void moveTurtleToHome() {
    }

    @Test
    void setImage() {
    }
}
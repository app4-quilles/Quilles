package org.app4_quilles;

import org.app4_quilles.model.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Various tests for the {@linkplain Player} class.
 */
public class PlayerTest {

    private int turnAmount = 10;

    @Test
    public void TestPlayerConstructorNormal() {
        Player p = new Player("name", turnAmount);
        Assert.assertEquals("wrong name from the Player constructor", p.getName(), "name");
        if (turnAmount > 0) Assert.assertEquals("wrong turn quantity from the Player constructor", p.getTurns(), turnAmount);
        else                Assert.assertEquals(p.getTurns(), 1);
    }

    @Test
    public void TestPlayerConstructorTooFewTurns() {
        Player p = new Player("name", 0);
        Assert.assertEquals("this value should be 1", p.getTurns(), 1);
    }

    @Test
    public void TestPointsArrayInitialisation() {
        Player p = new Player("name", turnAmount);
        for (int x = 0; x < turnAmount + 1; x++) {
            for (int y = 0; y < 2; y++) {
                Assert.assertEquals("this value should be 0", p.getPoints()[x][y], Integer.valueOf(0));
            }
        }
    }

    @Test
    public void TestGetPoint() {
        Player p = new Player("name", turnAmount);
        for (int x = 0; x < turnAmount; x++) {
            for (int y = 0; y < 2; y++) {
                Assert.assertEquals("the values given by getPoint and getPoints should be coherent", p.getPoint(x, y), p.getPoints()[x][y]);
            }
        }
    }

    @Test
    public void TestSetPoint() {
        Player p = new Player("name", turnAmount);
        Assert.assertEquals("this should be equal to 0", p.getPoint(0, 1), Integer.valueOf(0));
        try { p.setPoint(1, 1, 5); }
        catch (Exception e) {Assert.fail("this call should not make the program crash");}
        Assert.assertEquals("this should be equal to 0", p.getPoint(1, 1), Integer.valueOf(5));
    }

    @Test
    public void TestSetPoints() {
        Player p = new Player("name", turnAmount);
        Integer[][] array = p.getPoints();
        array[0][0] = 5;
        array[0][1] = 6;

        try { p.setPoints(array); }
        catch (Exception e) {Assert.fail("this call should not make the program crash");}
        Assert.assertEquals("this should be equal to 0", p.getPoints(), array);
    }

    @Test
    public void TestSetPointsWrongDimensions() {
        Player p = new Player("name", turnAmount);

        Integer[][] newArray = new Integer[turnAmount][1];
        try {
            p.setPoints(newArray);
            Assert.fail("setPoint() should have crashed because of the wrong dimensions.");
        } catch(Exception e) {}
    }

    @Test
    public void TestGetPointsEncapsulation() {
        Player p = new Player("name", turnAmount);
        Assert.assertEquals("before trying to change the value through the getter", p.getPoints()[turnAmount][1].intValue(), 0);
        p.getPoints()[turnAmount-1][1] = 5;   //this change is ,not supposed to be effective
        Assert.assertEquals("this value should not have changed", p.getPoints()[turnAmount][1].intValue(), 0);
    }

    @Test
    public void TestOutOfBounds() {
        Player p = new Player("name", turnAmount);

        try {
            p.setPoint(0, 2, 5);     //2 is out of bounds
            Assert.fail("setPoint is supposed to have crashed (non existent layer)");
        }
        catch (Exception e) {}

        try {
            p.setPoint(turnAmount + 1, 0, 5);   //turnAmount is out of bounds
            Assert.fail("setPoint is supposed to have crashed (non existent turn)");
        }
        catch (Exception e) {}

    }

}

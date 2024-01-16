package org.app4_quilles;

import org.app4_quilles.model.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Various tests for the {@linkplain Player} class.
 */
public class PlayerTest {

    @Test
    public void TestingUseCase() {

        //testing the constructor
        Player p = new Player("nano", 10);
        Assert.assertEquals("name should be equal to 'nano'", p.getName(), "nano");
        Assert.assertNotEquals("points array should not bez null", p.getPoints(), null);

        //testing the default cell values
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 2; y++) {
                Assert.assertEquals("all the points cells must be 0 after creating the player", p.getPoints()[x][y].intValue(), 0);
            }
        }

        //testing
        Assert.assertEquals("this cell should be 0", p.getPoints()[0][0].intValue(), 0);
        try {
            p.setPoint(0, 0, 1);
        } catch (Exception e) {
            System.err.println("Exception while calling setPoint()");
        }
        Assert.assertEquals("this cell should have been changed to 1", p.getPoints()[0][0].intValue(), 1);

        //Testing the last cells
        Assert.assertEquals("this cell should be 0", p.getPoints()[9][0].intValue(), 0);
        Assert.assertEquals(p.getPoints()[9][1].intValue(), 0);
        try {
            p.setPoint(9, 0, 42);
            p.setPoint(9, 1, 43);
        } catch (Exception e) {
            System.err.println("Exception while calling setPoint()");
        }

        Assert.assertEquals("this cell should be 42", p.getPoints()[9][0].intValue(), 42);
        Assert.assertEquals("this cell should be 43", p.getPoints()[9][1].intValue(), 43);


        p.getPoints()[9][1] = 43;

        //testing the getPoint() method
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 2; y++) {
                Assert.assertEquals("the values given by getPoint and getPoints should be coherent", p.getPoint(x, y), p.getPoints()[x][y]);
            }
        }

    }

    @Test
    public void TestingLimitCase() {
        //testing the getPoints() method
        Player p = new Player("salu", 10);
        try {
            p.setPoint(9, 1, 43);
        }
        catch(Exception e) {
            System.err.println("Error while calling setPoint");
        }
        Assert.assertEquals("Before trying to change the value through the getter", p.getPoints()[9][1].intValue(), 43);
        p.getPoints()[9][1] = 44;   //this change is ,not supposed to be effective
        //Assert.assertEquals("After trying to change the value through the getter", p.getPoints()[9][1].intValue(), 43);


    }

    @Test
    public void TestingFailureCase() {
        Player p = new Player("salu", 10);

        try {
            p.setPoint(0, 3, 44);
        } catch (Exception e) {
            System.err.println("There has effectively been an error while affecting a point to an non-existent layer.");
        }

        Integer[][] newArray = new Integer[10][1];
        try {
            p.setPoints(newArray);
        } catch(Exception er) {
            System.err.println("setPoint() didn't accept the wrong size of newArray and threw an exception.");
        }

    }
}

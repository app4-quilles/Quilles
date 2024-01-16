package org.app4_quilles;

import org.app4_quilles.model.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Various tests for the {@linkplain Player} class.
 */
public class PlayerTest {

    @Test
    public void Testing() {

        //testing the default constructor
        Player p = new Player();
        Assert.assertEquals(p.getName(), "");
        Assert.assertEquals(p.getLastShot().intValue(), 0);

        //testing the main constructor
        p = new Player("nano", 10);
        Assert.assertEquals(p.getName(), "nano");
        Assert.assertNotEquals(p.getPoints(), null);

        //testing the default cell values
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 2; y++) {
                Assert.assertEquals(p.getPoints()[x][y].intValue(), 0);
            }
        }

        //testing
        Assert.assertEquals(p.getPoints()[0][0].intValue(), 0);
        p.setPoint(0, 0, 1);
        Assert.assertEquals(p.getPoints()[0][0].intValue(), 1);

        //Testing the last cells
        Assert.assertEquals(p.getPoints()[9][0].intValue(), 0);
        Assert.assertEquals(p.getPoints()[9][1].intValue(), 0);
        p.setPoint(9, 0, 42);
        p.setPoint(9, 1, 43);
        Assert.assertEquals(p.getPoints()[9][0].intValue(), 42);
        Assert.assertEquals(p.getPoints()[9][1].intValue(), 43);


        p.getPoints()[9][1] = 43;

        //testing the getPoint() method
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 2; y++) {
                Assert.assertEquals(p.getPoint(x, y), p.getPoints()[x][y]);
            }
        }
    }
}

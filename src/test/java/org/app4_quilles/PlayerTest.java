package org.app4_quilles;

import org.app4_quilles.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void Testing() {

        Player p = new Player("nano", 10);
        Assert.assertEquals(p.getName(), "nano");
        Assert.assertNotEquals(p.getPoints(), null);

        Assert.assertEquals(p.getPoints()[0][0], Integer.valueOf(0));
        p.getPoints()[0][0] = 1;
        Assert.assertEquals(p.getPoints()[0][0], Integer.valueOf(1));

        //Testing the last cells
        Assert.assertEquals(p.getPoints()[9][0], Integer.valueOf(0));
        Assert.assertEquals(p.getPoints()[9][1], Integer.valueOf(0));
        p.setPoint(9, 0, 42);
        p.setPoint(9, 1, 43);
        Assert.assertEquals(p.getPoints()[9][0], Integer.valueOf(42));
        Assert.assertEquals(p.getPoints()[9][1], Integer.valueOf(43));


        p.getPoints()[9][1] = 43;

    }
}

package org.app4_quilles;

import java.util.ArrayList;
import java.util.HashMap;

import org.app4_quilles.model.Game;
import org.app4_quilles.model.Player;
import org.app4_quilles.model.Score;
import org.junit.Assert;
import org.junit.Test;

import static org.app4_quilles.model.Game.*;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testConstructor() {
        Game gameTest = new Game();
        assertFalse("started boolean should be false", gameTest.getStarted());
        Assert.assertEquals("amount of turns should be 10", 10, gameTest.getAmountOfTurns());
        Assert.assertEquals("amount of pins should be 10", 10, gameTest.getAmountOfPins());
        Assert.assertEquals("amount of players should be 0", 0, gameTest.getAmountOfPlayers());
        Assert.assertEquals("players list should be empty", new ArrayList<>(), gameTest.getListPlayers());
        Assert.assertEquals("scores map should be empty and of type <Player, Score>", new HashMap<Player, Score>(), gameTest.getScores());
        Assert.assertEquals("scores map should be empty and of type <Player, int[][]>", new HashMap<Player, int[][]>(), gameTest.getPinsMap());
    }

    @Test
    public void testSetters() {
        Game gameTest = new Game();
        gameTest.setStarted(true);
        assertTrue("started boolean should be true", gameTest.getStarted());
        gameTest.setAmountOfTurns(3);
        Assert.assertEquals("amount of turns should be 3", 3, gameTest.getAmountOfTurns());
        gameTest.setAmountOfPins(4);
        Assert.assertEquals("amount of pins should be 4", 4, gameTest.getAmountOfPins());
        gameTest.setAmountOfPlayers(5);
        Assert.assertEquals("amount of players should be 5", 5, gameTest.getAmountOfPlayers());
    }

    @Test
    public void testAddPlayer() {
        Game gameTest = new Game();
        gameTest.setAmountOfTurns(10);
        gameTest.addPlayer("testPlayer");
        Assert.assertEquals("list size should not be 0", 1, gameTest.getListPlayers().size());
        Player testPlayer = gameTest.getListPlayers().get(0);
        Assert.assertEquals("name should be testPlayer", "testPlayer", testPlayer.getName());
        Assert.assertEquals("amount of turns should be 10", 10, testPlayer.getTurns());
    }

    @Test
    public void testisInt() {
        assertTrue("'1' should be considered as an int", isInt("1"));
        assertFalse("'1a' should not be considered as an int", isInt("1a"));
    }

    @Test
    public void testGetNewArray() {
        int[][] compareArray = new int[2][2];
        compareArray[0][0] = 0;
        compareArray[0][1] = 0;
        compareArray[1][0] = 0;
        compareArray[1][1] = 0;

        int[][] newArray = getNewArray(2);
        Assert.assertEquals("same length", compareArray.length, newArray.length);
        Assert.assertEquals("same length inside", compareArray[0].length, newArray[0].length);
        for (int i = 0; i < compareArray.length; i++) {
            for (int j = 0; j < compareArray[i].length; j++) {
                Assert.assertEquals("same value for " + i + "," + j, compareArray[i][j], newArray[i][j]);
            }
        }
    }

    @Test
    public void testIntArrayToIntegerArray() {
        int[][] compareIntArray = new int[2][2];
        Integer[][] compareIntegerArray = new Integer[2][2];
        for(int x = 0; x < 2; x++){
            for(int y = 0; y < 2; y++) {
                compareIntArray[x][y] = 0;
                compareIntegerArray[x][y] = 0;
            }
        }

        Integer[][] newArray = intArrayToIntegerArray(compareIntArray);
        Assert.assertEquals("same length", compareIntArray.length, newArray.length);
        Assert.assertEquals("same length inside", compareIntArray[0].length, newArray[0].length);
        for (int i = 0; i < compareIntegerArray.length; i++) {
            for (int j = 0; j < compareIntegerArray[i].length; j++) {
                Assert.assertEquals("same value for " + i + "," + j, compareIntegerArray[i][j], newArray[i][j]);
            }
        }
    }

    @Test
    public void testPlay() {
    }

}
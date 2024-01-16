package org.app4_quilles;

import java.util.ArrayList;

import org.app4_quilles.model.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testStarted() {
        Game gameTest = new Game();
        String rep = gameTest.getInputString("test");
        System.err.println(rep);
        assertEquals("Game.started should be false", gameTest.getStarted(), false);
        gameTest.setStarted(true);
        assertEquals("Game.started should be true", gameTest.getStarted(), true);
    }

    @Test
    public void testNoPlayersAtStartOfGame() {
        Game gameTest = new Game();
        assertEquals(gameTest.getAmountOfPlayers(), 0);
        assertEquals(gameTest.getListNames().size(), 0);
        assertEquals(gameTest.getPlayersScores(), 0);
    }

    @Test
    public void testAddPlayer() {
        Game gameTest = new Game();
        gameTest.setStarted(true);
        gameTest.addPlayer("Olivier");
        ArrayList<String> emptyList = new ArrayList<>();

        assertEquals("Only Olivier in listNames", emptyList, gameTest.getListNames());
        emptyList.add("Olivier");
        assertEquals("Only Olivier in listNames", gameTest.getListNames().size(), 1);
        assertEquals("initial Olivier score should be 0", gameTest.getPlayerScore("Olivier"), 0);
        gameTest.addScore("Olivier", 10);
        assertEquals("Olivier's score should be 10", gameTest.getPlayerScore("Olivier"), 10);
        System.out.println(gameTest.getPlayerScore("Olivier"));
    }

    @Test
    public void xxxtestAddPlayer() {
        Game gameTest = new Game();
        //assertEquals();
    }

}

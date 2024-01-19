package org.app4_quilles;
import static org.junit.Assert.assertEquals;
import org.app4_quilles.ihm.cli.ScoreDisplay;
import org.junit.Test;

public class ScoreDisplayTest {

    @Test
	public void AnyRoundStrike() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = numberOfPins;
		}
		playerPins[9][1] = numberOfPins;
		int[][][] gameTab = new int[1][11][2];
		gameTab[0] = playerPins;
		ScoreDisplay sd = new ScoreDisplay(gameTab, new String[]{"John"});
		assertEquals("AnyRoundStrike : name Okay", sd.getPlayers(0), "1 - John");
		for (int i = 0; i < playerPins.length-2; i++) {
			assertEquals("AnyRoundStrike : display Strike for "+ i, sd.getScores(0, i, 0), "X");
			assertEquals("AnyRoundStrike : display total for "+ i, sd.getScores(0, i, 1), numberOfPins * (i + 1) * 3 + "");
		}
		assertEquals("AnyRoundStrike : display Strike for 8", sd.getScores(0, 9, 0), "X X X");
		assertEquals("AnyRoundStrike : display total for 8", sd.getScores(0, 9, 1), "300");
    }
    @Test
	public void AnyRoundSpare() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length - 1; i++) {
			playerPins[i][1] = numberOfPins;
		}
		playerPins[9][0] = 5;
		playerPins[9][1] = 5;
		playerPins[10][0] = 5;
		int[][][] gameTab = new int[1][11][2];
		gameTab[0] = playerPins;
		ScoreDisplay sd = new ScoreDisplay(gameTab, new String[]{"John"});
		assertEquals("AnyRoundSpare : name Okay", sd.getPlayers(0), "1 - John");
		for (int i = 0; i < playerPins.length-3; i++) {
			assertEquals("AnyRoundSpare : display Spare for "+ i, sd.getScores(0, i, 0), "- /");
			assertEquals("AnyRoundSpare : display total for "+ i, sd.getScores(0, i, 1), numberOfPins * (i + 1) + "");
		}
		assertEquals("AnyRoundSpare : display Spare for 8", sd.getScores(0, 8, 0), "- /");
		assertEquals("AnyRoundSpare : display total for 8", sd.getScores(0, 8, 1), "95");
		assertEquals("AnyRoundSpare : display Spare for 9", sd.getScores(0, 9, 0), "5 / 5");
		assertEquals("AnyRoundSpare : display total for 9", sd.getScores(0, 9, 1), "110");
    }
    
    @Test
	public void AnyRoundGutterAndSomePins() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		playerPins[0][0] = 3;
		playerPins[0][1] = 4;
		playerPins[1][0] = 3;
		playerPins[1][1] = 0;
		playerPins[2][0] = 0;
		playerPins[2][1] = 8;
		playerPins[3][0] = 3;
		playerPins[3][1] = 7;
		playerPins[4][0] = 0;
		playerPins[4][1] = 10;
		playerPins[5][0] = 10;
		playerPins[5][1] = 0;
		playerPins[6][0] = 3;
		playerPins[6][1] = 6;
		playerPins[7][0] = 0;
		playerPins[7][1] = 0;
		playerPins[8][0] = 0;
		playerPins[8][1] = 10;
		playerPins[9][0] = 10;
		playerPins[9][1] = 7;
		playerPins[10][0] = 2;
		int[][][] gameTab = new int[1][11][2];
		gameTab[0] = playerPins;
		ScoreDisplay sd = new ScoreDisplay(gameTab, new String[]{"John"});
		assertEquals("AnyRoundGutterAndSomePins : name Okay", sd.getPlayers(0), "1 - John");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 0", sd.getScores(0, 0, 0), "3 4");
		assertEquals("AnyRoundGutterAndSomePins : display total for 0", sd.getScores(0, 0, 1), "7");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 1", sd.getScores(0, 1, 0), "3 -");
		assertEquals("AnyRoundGutterAndSomePins : display total for 1", sd.getScores(0, 1, 1), "10");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 2", sd.getScores(0, 2, 0), "- 8");
		assertEquals("AnyRoundGutterAndSomePins : display total for 2", sd.getScores(0, 2, 1), "18");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 3", sd.getScores(0, 3, 0), "3 /");
		assertEquals("AnyRoundGutterAndSomePins : display total for 3", sd.getScores(0, 3, 1), "28");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 4", sd.getScores(0, 4, 0), "- /");
		assertEquals("AnyRoundGutterAndSomePins : display total for 4", sd.getScores(0, 4, 1), "48");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 5", sd.getScores(0, 5, 0), "X");
		assertEquals("AnyRoundGutterAndSomePins : display total for 5", sd.getScores(0, 5, 1), "67");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 6", sd.getScores(0, 6, 0), "3 6");
		assertEquals("AnyRoundGutterAndSomePins : display total for 6", sd.getScores(0, 6, 1), "76");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 7", sd.getScores(0, 7, 0), "- -");
		assertEquals("AnyRoundGutterAndSomePins : display total for 7", sd.getScores(0, 7, 1), "76");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 8", sd.getScores(0, 8, 0), "- /");
		assertEquals("AnyRoundGutterAndSomePins : display total for 8", sd.getScores(0, 8, 1), "96");
		assertEquals("AnyRoundGutterAndSomePins : display pins for 9", sd.getScores(0, 9, 0), "X 7 2");
		assertEquals("AnyRoundGutterAndSomePins : display total for 9", sd.getScores(0, 9, 1), "115");
    }
    
    @Test
	public void MultiplePlayers() {
		int[][] player1Pins = new int[5][2];
		int[][] player2Pins = new int[5][2];
		int[][] player3Pins = new int[5][2];
        int numberOfPins = 10;
		player1Pins[0][0] = 0;
		player1Pins[0][1] = 0;
		player1Pins[1][0] = 0;
		player1Pins[1][1] = 4;
		player1Pins[2][0] = 2;
		player1Pins[2][1] = 3;
		player1Pins[3][0] = 0;
		player1Pins[3][1] = 6;
		player1Pins[4][0] = 0;
		player2Pins[0][0] = 2;
		player2Pins[0][1] = 5;
		player2Pins[1][0] = 8;
		player2Pins[1][1] = 1;
		player2Pins[2][0] = 4;
		player2Pins[2][1] = 6;
		player2Pins[3][0] = 5;
		player2Pins[3][1] = 5;
		player2Pins[4][0] = 7;
		player3Pins[0][0] = 10;
		player3Pins[0][1] = 0;
		player3Pins[1][0] = 10;
		player3Pins[1][1] = 0;
		player3Pins[2][0] = 9;
		player3Pins[2][1] = 1;
		player3Pins[3][0] = 10;
		player3Pins[3][1] = 10;
		player3Pins[4][0] = 9;
		int[][][] gameTab = new int[3][5][2];
		gameTab[0] = player1Pins;
		gameTab[1] = player2Pins;
		gameTab[2] = player3Pins;
		ScoreDisplay sd = new ScoreDisplay(gameTab, new String[]{"John","Johnny","Johnathan"});
		assertEquals("MultiplePlayers : name 0 Okay", sd.getPlayers(0), "1 - John");
		assertEquals("MultiplePlayers : name 1 Okay", sd.getPlayers(1), "2 - Johnny");
		assertEquals("MultiplePlayers : name 2 Okay", sd.getPlayers(2), "3 - Johnathan");
		assertEquals("MultiplePlayers : player 1 display pins for 0", sd.getScores(0, 0, 0), "- -");
		assertEquals("MultiplePlayers : player 1 display total for 0", sd.getScores(0, 0, 1), "0");
		assertEquals("MultiplePlayers : player 1 display pins for 1", sd.getScores(0, 1, 0), "- 4");
		assertEquals("MultiplePlayers : player 1 display total for 1", sd.getScores(0, 1, 1), "4");
		assertEquals("MultiplePlayers : player 1 display pins for 2", sd.getScores(0, 2, 0), "2 3");
		assertEquals("MultiplePlayers : player 1 display total for 2", sd.getScores(0, 2, 1), "9");
		assertEquals("MultiplePlayers : player 1 display pins for 3", sd.getScores(0, 3, 0), "- 6");
		assertEquals("MultiplePlayers : player 1 display total for 3", sd.getScores(0, 3, 1), "15");
		assertEquals("MultiplePlayers : player 2 display pins for 0", sd.getScores(1, 0, 0), "2 5");
		assertEquals("MultiplePlayers : player 2 display total for 0", sd.getScores(1, 0, 1), "7");
		assertEquals("MultiplePlayers : player 2 display pins for 1", sd.getScores(1, 1, 0), "8 1");
		assertEquals("MultiplePlayers : player 2 display total for 1", sd.getScores(1, 1, 1), "16");
		assertEquals("MultiplePlayers : player 2 display pins for 2", sd.getScores(1, 2, 0), "4 /");
		assertEquals("MultiplePlayers : player 2 display total for 2", sd.getScores(1, 2, 1), "31");
		assertEquals("MultiplePlayers : player 2 display pins for 3", sd.getScores(1, 3, 0), "5 / 7");
		assertEquals("MultiplePlayers : player 2 display total for 3", sd.getScores(1, 3, 1), "48");
		assertEquals("MultiplePlayers : player 3 display pins for 0", sd.getScores(2, 0, 0), "X");
		assertEquals("MultiplePlayers : player 3 display total for 0", sd.getScores(2, 0, 1), "29");
		assertEquals("MultiplePlayers : player 3 display pins for 1", sd.getScores(2, 1, 0), "X");
		assertEquals("MultiplePlayers : player 3 display total for 1", sd.getScores(2, 1, 1), "49");
		assertEquals("MultiplePlayers : player 3 display pins for 2", sd.getScores(2, 2, 0), "9 /");
		assertEquals("MultiplePlayers : player 3 display total for 2", sd.getScores(2, 2, 1), "69");
		assertEquals("MultiplePlayers : player 3 display pins for 3", sd.getScores(2, 3, 0), "X X 9");
		assertEquals("MultiplePlayers : player 3 display total for 3", sd.getScores(2, 3, 1), "98");
    }
}

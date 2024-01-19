package org.app4_quilles;
import static org.junit.Assert.assertEquals;
import org.app4_quilles.model.Score;
import org.junit.Test;

public class ScoreTest {

    @Test
	public void StrikeOnly() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = numberOfPins;
		}
		playerPins[9][1] = numberOfPins;
		Score sc = new Score(numberOfPins, playerPins);
		for (int i = 0; i < playerPins.length-1; i++) {
			assertEquals("StrikeOnly : round " + i + " score", sc.getScoreTab()[i][0], numberOfPins * 3);
			assertEquals("StrikeOnly : round " + i + " total score", sc.getScoreTab()[i][1], numberOfPins * 3 * (i + 1));
		}
    }

    @Test
	public void StrikeOnlyLastShotIsZero() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length-1; i++) {
			playerPins[i][0] = numberOfPins;
			playerPins[i][1] = 0;
		}
		playerPins[9][1] = numberOfPins;
		Score sc = new Score(numberOfPins, playerPins);
		for (int i = 0; i < playerPins.length - 2; i++) {
			assertEquals("StrikeOnlyLastShotIsZero : round " + i + " score", sc.getScoreTab()[i][0], numberOfPins * 3);
			assertEquals("StrikeOnlyLastShotIsZero : round " + i + " total score", sc.getScoreTab()[i][1], numberOfPins * 3 * (i + 1));
		}
		assertEquals("StrikeOnlyLastShotIsZero : round 10 score", sc.getScoreTab()[9][0], numberOfPins * 2);
		assertEquals("StrikeOnlyLastShotIsZero : round 10 score", sc.getScoreTab()[9][1], numberOfPins * 3 * (9 + 1) - numberOfPins);
    }

    @Test
	public void StrikeOnlyBeforeLastShotIsZero() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = numberOfPins;
			playerPins[i][1] = 0;
		}
		playerPins[9][1] = 0;
		Score sc = new Score(numberOfPins, playerPins);
		for (int i = 0; i < playerPins.length - 3; i++) {
			assertEquals("StrikeOnlyBeforeLastShotIsZero : round " + i + " score", sc.getScoreTab()[i][0], numberOfPins * 3);
			assertEquals("StrikeOnlyBeforeLastShotIsZero : round " + i + " total score", sc.getScoreTab()[i][1], numberOfPins * 3 * (i + 1));
		}
		assertEquals("StrikeOnlyBeforeLastShotIsZero : round 9 score", sc.getScoreTab()[8][0], numberOfPins * 2);
		assertEquals("StrikeOnlyBeforeLastShotIsZero : round 9 total score", sc.getScoreTab()[8][1], numberOfPins * 3 * (9) - numberOfPins);
		assertEquals("StrikeOnlyBeforeLastShotIsZero : round 10 score", sc.getScoreTab()[9][0], numberOfPins * 2);
		assertEquals("StrikeOnlyBeforeLastShotIsZero : round 10 total score", sc.getScoreTab()[9][1], numberOfPins * 3 * (10) - numberOfPins * 2);
    }

    @Test
	public void EveryShotIsZero() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		Score sc = new Score(numberOfPins, playerPins);
		for (int i = 0; i < playerPins.length-1; i++) {
			assertEquals("EveryShotIsZero : round " + i + " score", sc.getScoreTab()[i][0], 0);
			assertEquals("EveryShotIsZero : round " + i + " total score", sc.getScoreTab()[i][1], 0);
		}
    }

    @Test
	public void EveryShotIsZeroOneSpare() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		playerPins[0][0] = 6;
		playerPins[0][1] = 4;
		Score sc = new Score(numberOfPins, playerPins);
		assertEquals("EveryShotIsZeroOneSpare : round 1 score", sc.getScoreTab()[0][0], 10);
		assertEquals("EveryShotIsZeroOneSpare : round 1 score", sc.getScoreTab()[0][1], 10);
		for (int i = 1; i < playerPins.length-1; i++) {
			assertEquals("EveryShotIsZeroOneSpare : round " + i + " score", sc.getScoreTab()[i][0], 0);
			assertEquals("EveryShotIsZeroOneSpare : round " + i + " total score", sc.getScoreTab()[i][1], 10);
		}
    }

    @Test
	public void EveryShotIsZeroTwoSpares() {
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		playerPins[0][0] = 6;
		playerPins[0][1] = 4;
		playerPins[1][0] = 5;
		playerPins[1][1] = 5;
		Score sc = new Score(numberOfPins, playerPins);
		assertEquals("EveryShotIsZeroTwoSpares : round 1 score", sc.getScoreTab()[0][0], 15);
		assertEquals("EveryShotIsZeroTwoSpares : round 1 total score", sc.getScoreTab()[0][1], 15);
		assertEquals("EveryShotIsZeroTwoSpares : round 2 score", sc.getScoreTab()[1][0], 10);
		assertEquals("EveryShotIsZeroTwoSpares : round 2 total score", sc.getScoreTab()[1][1], 25);
		for (int i = 2; i < playerPins.length-1; i++) {
			assertEquals("EveryShotIsZeroTwoSpares : round " + i + " score", sc.getScoreTab()[i][0], 0);
			assertEquals("EveryShotIsZeroTwoSpares : round " + i + " total score", sc.getScoreTab()[i][1], 25);
		}
	}

    @Test
	public void BadGame() {
        //random game with bad results
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		playerPins[0][0] = 6;
		playerPins[2][1] = 5;
		playerPins[4][0] = 3;
		playerPins[4][1] = 2;
		playerPins[6][0] = 2;
		playerPins[7][1] = 7;
		playerPins[8][0] = 8;
		playerPins[8][1] = 2;
		Score sc = new Score(numberOfPins, playerPins);
		assertEquals("BadGame : round 1 score", sc.getScoreTab()[0][0], 6);
		assertEquals("BadGame : round 1 total score", sc.getScoreTab()[0][1], 6);
		assertEquals("BadGame : round 2 score", sc.getScoreTab()[1][0], 0);
		assertEquals("BadGame : round 2 total score", sc.getScoreTab()[1][1], 6);
		assertEquals("BadGame : round 3 score", sc.getScoreTab()[2][0], 5);
		assertEquals("BadGame : round 3 total score", sc.getScoreTab()[2][1], 11);
		assertEquals("BadGame : round 4 score", sc.getScoreTab()[3][0], 0);
		assertEquals("BadGame : round 4 total score", sc.getScoreTab()[3][1], 11);
		assertEquals("BadGame : round 5 score", sc.getScoreTab()[4][0], 5);
		assertEquals("BadGame : round 5 total score", sc.getScoreTab()[4][1], 16);
		assertEquals("BadGame : round 6 score", sc.getScoreTab()[5][0], 0);
		assertEquals("BadGame : round 6 total score", sc.getScoreTab()[5][1], 16);
		assertEquals("BadGame : round 7 score", sc.getScoreTab()[6][0], 2);
		assertEquals("BadGame : round 7 total score", sc.getScoreTab()[6][1], 18);
		assertEquals("BadGame : round 8 score", sc.getScoreTab()[7][0], 7);
		assertEquals("BadGame : round 8 total score", sc.getScoreTab()[7][1], 25);
		assertEquals("BadGame : round 9 score", sc.getScoreTab()[8][0], 10);
		assertEquals("BadGame : round 9 total score", sc.getScoreTab()[8][1], 35);
		assertEquals("BadGame : round 10 score", sc.getScoreTab()[9][0], 0);
		assertEquals("BadGame : round 10 total score", sc.getScoreTab()[9][1], 35);
	}

    @Test
	public void GoodGame() {
        //random game with good results
		int[][] playerPins = new int[11][2];
        int numberOfPins = 10;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		playerPins[0][0] = 6;
		playerPins[0][1] = 4;
		playerPins[1][0] = 8;
		playerPins[1][1] = 1;
		playerPins[2][1] = numberOfPins;
		playerPins[3][0] = numberOfPins;
		playerPins[4][0] = numberOfPins;
		playerPins[5][0] = numberOfPins;
		playerPins[6][0] = 9;
		playerPins[6][1] = 1;
		playerPins[7][0] = 5;
		playerPins[7][1] = 5;
		playerPins[8][0] = 8;
		playerPins[8][1] = 2;
		playerPins[9][0] = numberOfPins;
		playerPins[9][1] = 7;
		playerPins[10][0] = 3;
		Score sc = new Score(numberOfPins, playerPins);
		assertEquals("GoodGame : round 1 score", sc.getScoreTab()[0][0], 18);
		assertEquals("GoodGame : round 1 total score", sc.getScoreTab()[0][1], 18);
		assertEquals("GoodGame : round 2 score", sc.getScoreTab()[1][0], 9);
		assertEquals("GoodGame : round 2 total score", sc.getScoreTab()[1][1], 27);
		assertEquals("GoodGame : round 3 score", sc.getScoreTab()[2][0], 20);
		assertEquals("GoodGame : round 3 total score", sc.getScoreTab()[2][1], 47);
		assertEquals("GoodGame : round 4 score", sc.getScoreTab()[3][0], 30);
		assertEquals("GoodGame : round 4 total score", sc.getScoreTab()[3][1], 77);
		assertEquals("GoodGame : round 5 score", sc.getScoreTab()[4][0], 29);
		assertEquals("GoodGame : round 5 total score", sc.getScoreTab()[4][1], 106);
		assertEquals("GoodGame : round 6 score", sc.getScoreTab()[5][0], 20);
		assertEquals("GoodGame : round 6 total score", sc.getScoreTab()[5][1], 126);
		assertEquals("GoodGame : round 7 score", sc.getScoreTab()[6][0], 15);
		assertEquals("GoodGame : round 7 total score", sc.getScoreTab()[6][1], 141);
		assertEquals("GoodGame : round 8 score", sc.getScoreTab()[7][0], 18);
		assertEquals("GoodGame : round 8 total score", sc.getScoreTab()[7][1], 159);
		assertEquals("GoodGame : round 9 score", sc.getScoreTab()[8][0], 20);
		assertEquals("GoodGame : round 9 total score", sc.getScoreTab()[8][1], 179);
		assertEquals("GoodGame : round 10 score", sc.getScoreTab()[9][0], 20);
		assertEquals("GoodGame : round 10 total score", sc.getScoreTab()[9][1], 199);
	}

    @Test
	public void ModifiedParametersZeros() {
		int[][] playerPins = new int[8][2];
        int numberOfPins = 5;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		Score sc = new Score(numberOfPins, playerPins);
		for (int i = 2; i < playerPins.length-1; i++) {
			assertEquals("ModifiedParametersZeros : round " + i + " score", sc.getScoreTab()[i][0], 0);
			assertEquals("ModifiedParametersZeros : round " + i + " total score", sc.getScoreTab()[i][1], 0);
		}
	}

    @Test
	public void ModifiedParametersNormalGame() {
		int[][] playerPins = new int[6][2];
        int numberOfPins = 8;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		playerPins[0][0] = 5;
		playerPins[0][1] = 3;
		playerPins[1][0] = 6;
		playerPins[1][1] = 1;
		playerPins[2][1] = numberOfPins;
		playerPins[3][0] = numberOfPins;
		playerPins[4][0] = numberOfPins;
		playerPins[5][0] = 7;
		Score sc = new Score(numberOfPins, playerPins);
		assertEquals("ModifiedParametersNormalGame : round 1 score", sc.getScoreTab()[0][0], 14);
		assertEquals("ModifiedParametersNormalGame : round 1 total score", sc.getScoreTab()[0][1], 14);
		assertEquals("ModifiedParametersNormalGame : round 2 score", sc.getScoreTab()[1][0], 7);
		assertEquals("ModifiedParametersNormalGame : round 2 total score", sc.getScoreTab()[1][1], 21);
		assertEquals("ModifiedParametersNormalGame : round 3 score", sc.getScoreTab()[2][0], 16);
		assertEquals("ModifiedParametersNormalGame : round 3 total score", sc.getScoreTab()[2][1], 37);
		assertEquals("ModifiedParametersNormalGame : round 4 score", sc.getScoreTab()[3][0], 16);
		assertEquals("ModifiedParametersNormalGame : round 4 total score", sc.getScoreTab()[3][1], 53);
		assertEquals("ModifiedParametersNormalGame : round 5 score", sc.getScoreTab()[4][0], 15);
		assertEquals("ModifiedParametersNormalGame : round 5 total score", sc.getScoreTab()[4][1], 68);
	}
    @Test
	public void ModifiedParametersStoppingHalfway() {
		int[][] playerPins = new int[6][2];
        int numberOfPins = 8;
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		playerPins[0][0] = 5;
		playerPins[0][1] = 3;
		playerPins[1][0] = 6;
		playerPins[1][1] = 1;
		playerPins[2][1] = numberOfPins;
		playerPins[3][0] = numberOfPins;
		playerPins[4][0] = numberOfPins;
		playerPins[5][0] = 7;
		Score sc = new Score(numberOfPins, playerPins);
		sc.CalculScore(playerPins, 10);
		assertEquals("ModifiedParametersNormalGame : round 1 score", sc.getScoreTab()[0][0], 14);
		assertEquals("ModifiedParametersNormalGame : round 1 total score", sc.getScoreTab()[0][1], 14);
		assertEquals("ModifiedParametersNormalGame : round 2 score", sc.getScoreTab()[1][0], 7);
		assertEquals("ModifiedParametersNormalGame : round 2 total score", sc.getScoreTab()[1][1], 21);
		assertEquals("ModifiedParametersNormalGame : round 3 score", sc.getScoreTab()[2][0], 16);
		assertEquals("ModifiedParametersNormalGame : round 3 total score", sc.getScoreTab()[2][1], 37);
		assertEquals("ModifiedParametersNormalGame : round 4 score", sc.getScoreTab()[3][0], 23);
		assertEquals("ModifiedParametersNormalGame : round 4 total score", sc.getScoreTab()[3][1], 60);
		assertEquals("ModifiedParametersNormalGame : round 5 score", sc.getScoreTab()[4][0], 0);
		assertEquals("ModifiedParametersNormalGame : round 5 total score", sc.getScoreTab()[4][1], 0);
		assertEquals("ModifiedParametersNormalGame : round 6 score", sc.getScoreTab()[5][0], 0);
		assertEquals("ModifiedParametersNormalGame : round 6 total score", sc.getScoreTab()[5][1], 0);
	}
}

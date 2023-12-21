package org.app4_quilles.model;

public class Score {
	private int numberOfPins;
	private int[][] scoreTab = new int[10][2];
	
	public Score() {
		for (int i = 0; i < scoreTab.length; i++) {
	        for (int j = 0; j < scoreTab[i].length; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = 10;
	}
	public Score(int[][] pins, int lastShot) {
		for (int i = 0; i < scoreTab.length; i++) {
	        for (int j = 0; j < scoreTab[i].length; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = 10;
		calculScore(pins, lastShot);
	}
	public Score(int nb, int[][] pins, int lastShot) {
		for (int i = 0; i < scoreTab.length; i++) {
	        for (int j = 0; j < scoreTab[i].length; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = nb;
		calculScore(pins, lastShot);
	}
	
	public void calculScore(int[][] pins, int lastShot) {
		int total = 0;
		for (int i = 0; i < pins.length; i++) {
			
			//calculating basic round score
			int res = pins[i][0] + pins[i][1];
			
			//if there is a strike, and it's the last round
	        if(pins[i][0] == numberOfPins && i == pins.length - 1) res += pins[i][1] + lastShot;
	        
			//if there is a strike, followed by another and that other strike is the last round
	        else if(pins[i][0] == numberOfPins && pins[i + 1][0] == numberOfPins && i == pins.length) res += numberOfPins + pins[i+1][1];
	        
			//if there is a strike, followed by another
	        else if(pins[i][0] == numberOfPins && pins[i + 1][0] == numberOfPins) res += numberOfPins + pins[i+2][0];
	        
	        //if there is a strike, followed by anything
	        else if(pins[i][0] == numberOfPins) res += pins[i + 1][0] + pins[i + 1][1];
	        
	        //if there is a spare, and it's the last round
			else if(pins[i][0] + pins[i][1] == numberOfPins && i == pins.length - 1) res += lastShot;
	        
	        //if there is a spare
	        else if(pins[i][0] + pins[i][1] == numberOfPins) res += pins[i + 1][0];
	        
	        scoreTab[i][0] = res;
	        total += res;
	        scoreTab[i][1] = total;
	    }
	}
	
	public boolean testing() {
		boolean res = true;
		int[][] playerPins = new int[10][2];
		//every shot is a strike
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = numberOfPins;
		}
		playerPins[9][1] = numberOfPins;
		calculScore(playerPins, numberOfPins);
		for (int i = 0; i < playerPins.length; i++) {
			res = res && scoreTab[i][0] == numberOfPins * 3;
			res = res && scoreTab[i][1] == numberOfPins * 3 * (i + 1);
		}
		
		//every shot is a strike except for last shot, which is 0
		calculScore(playerPins, 0);
		for (int i = 0; i < playerPins.length - 1; i++) {
			res = res && scoreTab[i][0] == numberOfPins * 3;
			res = res && scoreTab[i][1] == numberOfPins * 3 * (i + 1);
		}
		res = res && scoreTab[9][0] == numberOfPins * 2;
		res = res && scoreTab[9][1] == numberOfPins * 3 * (9 + 1) - numberOfPins;
		
		//every shot is a strike, except for before last shot, which is 0.
		playerPins[9][1] = 0;
		calculScore(playerPins, numberOfPins);
		for (int i = 0; i < playerPins.length - 2; i++) {
			res = res && scoreTab[i][0] == numberOfPins * 3;
			res = res && scoreTab[i][1] == numberOfPins * 3 * (i + 1);
		}
		res = res && scoreTab[9][0] == numberOfPins * 2;
		res = res && scoreTab[9][1] == numberOfPins * 3 * (9) - numberOfPins;
		res = res && scoreTab[9][0] == numberOfPins * 2;
		res = res && scoreTab[9][1] == numberOfPins * 3 * (10) - numberOfPins * 2;

		//every shot is a strike, except for before last shot, which is 0.
		playerPins[9][1] = 0;
		calculScore(playerPins, numberOfPins);
		for (int i = 0; i < playerPins.length - 2; i++) {
			res = res && scoreTab[i][0] == numberOfPins * 3;
			res = res && scoreTab[i][1] == numberOfPins * 3 * (i + 1);
		}
		res = res && scoreTab[9][0] == numberOfPins * 2;
		res = res && scoreTab[9][1] == numberOfPins * 3 * (9) - numberOfPins;
		res = res && scoreTab[9][0] == numberOfPins * 2;
		res = res && scoreTab[9][1] == numberOfPins * 3 * (10) - numberOfPins * 2;

		//every shot is 0.
		for (int i = 0; i < playerPins.length; i++) {
			playerPins[i][0] = 0;
			playerPins[i][1] = 0;
		}
		calculScore(playerPins, 0);
		for (int i = 0; i < playerPins.length; i++) {
			res = res && scoreTab[i][0] == 0;
			res = res && scoreTab[i][1] == 0;
		}
		
		//every shot is 0, exept for the first round which is a spare.
		playerPins[0][0] = 6;
		playerPins[0][1] = 4;
		calculScore(playerPins, 0);
		res = res && scoreTab[0][0] == 10;
		res = res && scoreTab[0][1] == 10;
		for (int i = 1; i < playerPins.length; i++) {
			res = res && scoreTab[i][0] == 0;
			res = res && scoreTab[i][1] == 10;
		}
		
		//every shot is 0, exept for the first 2 rounds which are spares.
		playerPins[1][0] = 5;
		playerPins[1][1] = 5;
		calculScore(playerPins, 0);
		res = res && scoreTab[0][0] == 15;
		res = res && scoreTab[0][1] == 15;
		res = res && scoreTab[1][0] == 10;
		res = res && scoreTab[1][1] == 25;
		for (int i = 2; i < playerPins.length; i++) {
			res = res && scoreTab[i][0] == 0;
			res = res && scoreTab[i][1] == 25;
		}
		return res;
	}
}
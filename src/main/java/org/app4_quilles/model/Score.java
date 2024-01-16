package org.app4_quilles.model;

public class Score {
	private int numberOfPins;
	private int numberOfRounds;
	private int[][] scoreTab;
	
	public Score() {
		scoreTab = new int[10][2];
		for (int i = 0; i < scoreTab.length; i++) {
	        for (int j = 0; j < scoreTab[i].length; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = 10;
	}
	public Score(int[][] pins, int lastShot) {
		scoreTab = new int[pins.length][2];
		for (int i = 0; i < pins.length; i++) {
	        for (int j = 0; j < 2; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = 10;
		calculScore(pins, lastShot);
	}
	public Score(int nb, int[][] pins, int lastShot) {
		scoreTab = new int[pins.length][2];
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
}
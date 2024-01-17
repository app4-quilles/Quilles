package org.app4_quilles.model;

/**
 * Class calculating the score.
 * It needs to be called everytime there is a new shot that has been made
 */
public class Score {
	/**
	 * The number of pins used in the game for which we are calculating the score
	 */
	private int numberOfPins;
	//scoreTab will always have the same number of columns as pins[], as there is a score for each round played
	//it will also always have 2 rows, as there is this round's score, and the total score to display.
	
	/**
	 * The result of the score's calculation, with the first row being each round's score,
	 * and the second row the total after each round played
	 * 0 is the default value when creating scoreTab, as well as the result of a gutter shot.
	 */
	private int[][] scoreTab;
	
	/**
	 * @return the scores calculated
	 */
	public int[][] getScoreTab(){return scoreTab;}
	
	/**
	 * The default constructor, with a 10 round game, each shot being a zero, and 10 pins used.
	 */
	public Score() {
		scoreTab = new int[10][2];
		for (int i = 0; i < scoreTab.length; i++) {
	        for (int j = 0; j < scoreTab[i].length; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = 10;
	}
	/**
	 * Constructor with a default number of pins which is 10.
	 * @param pins the number of pins used in the game
	 * @param lastShot the value of the last bonus shot in the tenth round after a strike or a spare (0 if there is none).
	 */
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
	/**
	 * Constructor with no default value
	 * @param nb the number of pins used
	 * @param pins the number of pins used in the game
	 * @param lastShot the value of the last bonus shot in the tenth round after a strike or a spare (0 if there is none).
	 */
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
	/**
	 * The function which calculate the values and save them in scoreTab
	 * @param pins the number of pins used
	 * @param lastShot the value of the last bonus shot in the tenth round after a strike or a spare (0 if there is none).         
	 */
	public void calculScore(int[][] pins, int lastShot) {
		//lastShot is the second shot a player get when they do a strike on their last round.
		int total = 0;
		for (int i = 0; i < pins.length; i++) {
			
			//calculating basic round score
			int res = pins[i][0] + pins[i][1];
			
			//if there is a strike, and it's the last round
	        if(pins[i][0] == numberOfPins && i == pins.length - 1) res += lastShot;
	        
			//if there is a strike, followed by another and that other strike is the last round
	        else if(pins[i][0] == numberOfPins && pins[i + 1][0] == numberOfPins && i + 1 == pins.length - 1) res += pins[i+1][0] + pins[i+1][1];
	        
			//if there is a strike, followed by another
	        else if(pins[i][0] == numberOfPins && pins[i + 1][0] == numberOfPins) res += pins[i+1][0] + pins[i+2][0];
	        
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
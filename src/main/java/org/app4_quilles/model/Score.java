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
	//scoreTab will always have the same number of columns as pins[]-1, as there is the lastrow corresponding to the bonus shot
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
	public int[][] getScoreTab(){return scoreTab.clone();}
	
	/**
	 * The default constructor, with a 10 round game, each shot being a zero, and 10 pins used.
	 */
	public Score() {
		scoreTab = new int[11][2];
		for (int i = 0; i < scoreTab.length; i++) {
	        for (int j = 0; j < scoreTab[i].length; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = 10;
	}
	/**
	 * Constructor with a default number of pins which is 10.
	 * @param pins the tab with each pin fallen in shot 1 or shot 2 for each round
	 */
	public Score(int[][] pins) {
		scoreTab = new int[pins.length-1][2];
		for (int i = 0; i < pins.length-1; i++) {
	        for (int j = 0; j < 2; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = 10;
		CalculScore(pins, pins.length-1);
	}
	/**
	 * Constructor with no default value
	 * @param nb the number of pins used
	 * @param pins the tab with each pin fallen in shot 1 or shot 2 for each round
	 */
	public Score(int nb, int[][] pins) {
		scoreTab = new int[pins.length-1][2];
		for (int i = 0; i < scoreTab.length-1; i++) {
	        for (int j = 0; j < scoreTab[i].length-1; j++) {
	        	scoreTab[i][j] = 0;
	        }
	    }
		numberOfPins = nb;
		CalculScore(pins, pins.length-1);
	}
	/**
	 * The function which calculate the values and save them in scoreTab
	 * @param pins the tab with each pin fallen in shot 1 or shot 2 for each round.
	 * The 3rd shot of the last round is stored in an additional cell, the last cell of the tab.        
	 */
	public void CalculScore(int[][] pins, int numberOfRound) {
		//lastShot is the second shot a player get when they do a strike on their last round.
		int total = 0;
		int lastRoundIndex = numberOfRound - 1; // round 10 in a regular game
		for (int i = 0; i < pins.length - 1; i++) {

			//calculating basic round score
			int res = pins[i][0] + pins[i][1];

			//rounds 1-9 in a 10 round (regular) game
			if (i < lastRoundIndex) {
		        
				//if there is a strike then a strike
				if(pins[i][0] == numberOfPins && pins[i + 1][0] == numberOfPins) {
					// shot 9 in 10 round (regular) game
					if (i == lastRoundIndex - 1) {
						// shot 10 can store 2 strikes!
						res += pins[i+1][0] + pins[i+1][1];
					} else {
						res += pins[i+1][0] + pins[i+2][0];
					}
				}
			 
				//if there is a strike followed by no strike
		        else if(pins[i][0] == numberOfPins) res += pins[i + 1][0] + pins[i + 1][1];

				//if there is a spare
		        else if(pins[i][0] + pins[i][1] == numberOfPins) res += pins[i + 1][0];

				scoreTab[i][0] = res;
		        total += res;
			    scoreTab[i][1] = total;
			}

			//rounds 10 in a 10 round (regular) game
			else if (i == lastRoundIndex) { // 3rd bonus shot
				//if there is a strike, and it's the last round
		        if(pins[i][0] == numberOfPins) res += pins[i+1][0];
		        
				//if there is a strike, followed by another and that other strike is the last round
		        else if(pins[i][0] == numberOfPins && pins[i + 1][0] == numberOfPins && i + 2 == numberOfRound) res += pins[i+1][0] + pins[i+1][1];
		        
				//if there is a strike, followed by another
		        else if(pins[i][0] == numberOfPins && pins[i + 1][0] == numberOfPins) res += pins[i+1][0] + pins[i+2][0];
		        
		        //if there is a strike, followed by anything
		        else if(pins[i][0] == numberOfPins) res += pins[i + 1][0] + pins[i + 1][1];
		        
		        //if there is a spare
		        else if(pins[i][0] + pins[i][1] == numberOfPins) res += pins[i + 1][0];

		        scoreTab[i][0] = res;
		        total += res;
			    scoreTab[i][1] = total;
			}
	    }
	}
}
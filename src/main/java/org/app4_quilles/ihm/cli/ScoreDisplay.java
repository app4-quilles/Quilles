package org.app4_quilles.ihm.cli;

import org.app4_quilles.model.Score;

public class ScoreDisplay {
	private String[] scoreDisplayPlayers;
	private String[][][] scoreDisplayScore;

	public ScoreDisplay (){
		this(new int[3][11][2], new String[]{"John","Johnny","Johnathan"}, 10);
	}
	
	public ScoreDisplay (String[] players){
		this(new int[players.length][11][2], players, 10);
	}
	
	public ScoreDisplay (int [][][] pinTabs, String[] players, int numberOfRound){
		Score sc = new Score();
		scoreDisplayScore = new String [pinTabs.length][pinTabs[0].length][pinTabs[0][0].length];
		scoreDisplayPlayers = new String [players.length];
		for (int i = 0; i < players.length; i ++) {
			scoreDisplayPlayers[i] = i + 1 + " - " + players[i];
			sc.CalculScore(pinTabs[i], numberOfRound);
			for (int j = 0; j < pinTabs[0].length - 1; j ++) {
				scoreDisplayScore[i][j][0] = affichageScoreRound(pinTabs[i], j);
				if (sc.getScoreTab()[j][0] == 0 && j == numberOfRound - 1 && scoreDisplayScore[i][j][0] != "- -") scoreDisplayScore[i][j][1] = "";
				else scoreDisplayScore[i][j][1] = sc.getScoreTab()[j][1] + "";
			}
		}
	}
	
	public String[] getPlayers() {return scoreDisplayPlayers;}
	public String getPlayers(int i) {return scoreDisplayPlayers[i];}
	
	public String[][][] getScores() {return scoreDisplayScore;}
	public String[][] getScores(int i) {return scoreDisplayScore[i];}
	public String[] getScores(int i, int j) {return scoreDisplayScore[i][j];}
	public String getScores(int i, int j, int k) {return scoreDisplayScore[i][j][k];}
	
	private String affichageScoreRound(int[][] pinTab, int j) {
		String res = "";
		//cas du strike :
		if (pinTab[j][0] == 10) {
			res += "X";
			//cas d'un strike de fin de partie
			if (j == pinTab.length- 2) {
				res += " ";
				//un autre strike ensuite
				if (pinTab[j][1] == 10) res += "X";
				//ou un spare
				else if (pinTab[j][1] + pinTab[j+1][0] == 10) {
					if (pinTab[j][0] == 0) res += "-";
					else res += pinTab[j][0];
					res += " /";
					//return préemptif pour éviter d'ajouter à nouveau la dernière ligne.
					return res;
				}
				else res += pinTab[j][1];
				res += " ";
				if (pinTab[j+1][0] == 10) res += "X";
				else res += pinTab[j+1][0];
			}
		}
		//cas du spare :
		else if (pinTab[j][0] + pinTab[j][1] == 10) {
			if (pinTab[j][0] == 0) res += "-";
			else res += pinTab[j][0];
			res += " /";
			//cas d'un spare de fin de partie
			if (j == pinTab.length- 2) res += " " + pinTab[j+1][0];
		}
		//autre cas :
		else {
			if (pinTab[j][0] == 0) res += "-";
			else res += pinTab[j][0];
			res += " ";
			if (pinTab[j][1] == 0) res += "-";
			else res += pinTab[j][1];
		}
		return res;
	}
	
	public void Display () {
		int lenName = 0;
		for(int i = 0; i < scoreDisplayScore.length; i ++) {
			if (scoreDisplayPlayers[i].length() > lenName) lenName = scoreDisplayPlayers[i].length();
		}
		String res = "";
		for(int i = 0; i < lenName + 7 * (scoreDisplayScore[0].length - 1); i ++) {
			res += "=";
		}
		System.out.println(res);
		for(int i = 0; i < scoreDisplayScore.length; i ++) {
			String line1 = scoreDisplayPlayers[i];
			String line2 = "";
			for (int j = 0; j < lenName - scoreDisplayPlayers[i].length(); j ++) {
				line1 += " ";
				line2 += " ";
			}
			for (int j = 0; j < scoreDisplayPlayers[i].length(); j ++) {
				line2 += " ";
			}
			for(int j = 0; j < scoreDisplayScore[0].length-1; j ++) {
				line1 += "|";
				for (int h = 0; h < 6 - scoreDisplayScore[i][j][0].length(); h ++) {
					line1 += " ";
				}
				line1 += scoreDisplayScore[i][j][0];
				line2 += "|";
				for (int h = 0; h < 6 - scoreDisplayScore[i][j][1].length(); h ++) {
					line2 += " ";
				}
				line2 += scoreDisplayScore[i][j][1];
			}
			System.out.println(line1);
			System.out.println(line2);
			String c = "-";
			if (i == scoreDisplayScore.length - 1) c = "=";
			res = "";
			for(int j = 0; j < line1.length(); j ++) {
				res += c;
			}
			System.out.println(res);
		}
	}
}
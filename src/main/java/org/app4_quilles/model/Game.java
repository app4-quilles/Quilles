package org.app4_quilles.model;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class Game {
    //Attributes
    private boolean started;
    private int amountOfPlayers;
    private ArrayList listNames;
    private HashMap<String, Integer> scores;

    //Constructors
    public Game(){
        this.started = false;
        this.amountOfPlayers = 0;
        this.listNames = new ArrayList<>();
        this.scores = new HashMap<String, Integer>();
    }

    //Getters
    public boolean getStarted(){
        return this.started;
    }
    public int getAmountOfPlayers(){
        return this.amountOfPlayers;
    }
    public ArrayList getListNames(){
        return this.listNames;
    }
    public HashMap<String, Integer> getPlayersScores(){
        return new HashMap<String, Integer>();
    }
    public int getPlayerScore(String name){
        return 0;
    }

    //Setters
    public void setStarted(boolean bool_start){
         this.started = bool_start;
    }
    //Methods
    public void addPlayer(String name){}
    public void addScore(String name, Integer score){}

    public void startGame(){}
}

package org.app4_quilles.model;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    //Scanner for standard input file (a temp method that will be replaced by Charly's one
    public String getInputString(String promptMsg) {
        String response = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print(promptMsg + ": ");

        response = scanner.nextLine();

        return response;
    }

    //Setters
    public void setStarted(boolean bool_start){
         this.started = bool_start;
    }

    //Methods
    public void addPlayer(String name){
        
    }
    public void addScore(String name, Integer score){}

    public boolean isInt(String s){
        {
            try
            { int i = Integer.parseInt(s); return true; }

            catch(NumberFormatException er)
            { return false; }
        }
    }
    public void play(){
        System.out.println("Game started.");
        String amount = "";
        while(! isInt(amount)){
            System.out.println("Enter amount of players (must be a number)");
            amount = this.getInputString("");
        }
        this.amountOfPlayers = Integer.valueOf(amount);
        System.out.println("Amount of players : "+this.amountOfPlayers);
        for (int i = 0; i <  this.amountOfPlayers; i++) {
            String newPlayerName = this.getInputString("Enter new player ");
            System.out.println(newPlayerName);
            this.addPlayer(newPlayerName);
        }

    }

}
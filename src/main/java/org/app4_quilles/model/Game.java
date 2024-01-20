package org.app4_quilles.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.app4_quilles.ihm.cli.CLI;
import org.app4_quilles.ihm.cli.ScoreDisplay;

public class Game {
    //Attributes
    private int amountOfPins;
    private int amountOfTurns;
    private boolean started;
    private int amountOfPlayers;
    private ArrayList<Player> listPlayers;
    private HashMap<Player, Score> scores;
    private HashMap<Player, int[][]> pinsMap; //contains the history of touched pins

    //Constructors
    public Game(){
        this.started = false;
        this.amountOfTurns = 10;
        this.amountOfPins = 10;
        this.amountOfPlayers = 0;
        this.listPlayers = new ArrayList<>();
        this.scores = new HashMap<Player, Score>();
        this.pinsMap = new HashMap<Player, int[][]>();
    }

    //Getters
    public int getAmountOfPins() {
        return amountOfPins;
    }

    public int getAmountOfTurns() {
        return amountOfTurns;
    }

    public boolean getStarted() {
        return started;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public ArrayList<Player> getListPlayers() {
        return listPlayers;
    }

    public HashMap<Player, Score> getScores() {
        return scores;
    }

    public HashMap<Player, int[][]> getPinsMap() {
        return pinsMap;
    }

    //Setters
    public void setAmountOfPins(int amountOfPins) {
        this.amountOfPins = amountOfPins;
    }

    public void setAmountOfTurns(int amountOfTurns) {
        this.amountOfTurns = amountOfTurns;
    }

    public void setStarted(boolean bool_start) {
        this.started = bool_start;
    }

    public void setAmountOfPlayers(int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }




    //Methods
    public void addPlayer(String name){
        Player newPlayer = new Player(name, this.amountOfTurns);
        this.listPlayers.add(newPlayer);
    }

    public static boolean isInt(String s){
        {
            try
            { int i = Integer.parseInt(s); return true; }

            catch(NumberFormatException er)
            { return false; }
        }
    }

    public static int[][] getNewArray(int turns){
        int[][] res = new int[turns][2];
        for(int x = 0; x < turns; x++){
            for(int y = 0; y < 2; y++) {
                res[x][y] = 0;
            }
        }
        return res;
    }

    //Useful for debugging  -> no tests needed as it is never used in final build
    public int[][] integerArrayToIntArray(Integer[][] input){
        int[][] res = new int[input.length][input[0].length];
        for(int x = 0; x < input.length; x++){
            for(int y = 0; y < input[0].length; y++) {
                res[x][y] = input[x][y];
            }
        }
        return res;
    }

    public static Integer[][] intArrayToIntegerArray(int[][] input){
        Integer[][] res = new Integer[input.length][input[0].length];
        for(int x = 0; x < input.length; x++){
            for(int y = 0; y < input[0].length; y++) {
                res[x][y] = input[x][y];
            }
        }
        return res;
    }

    //Useful for debugging
    public void stringIntArray(int[][] input){
        for(int x = 0; x < input.length; x++){
            for(int y = 0; y < input[0].length; y++) {
                System.err.print(input[x][y]);
            }
            System.err.println("");
        }
    }

    //Useful for debugging
    public void stringIntegerArray(Integer[][] input){
        for(int x = 0; x < input.length; x++){
            for(int y = 0; y < input[0].length; y++) {
                System.err.print(input[x][y]);
            }
            System.err.println("");
        }
    }

    /**
     * show the scoreboard in the command line interface
     */
    private void printScore() {
        String[] players = new String[this.listPlayers.size()];
        int[][][] pins = new int[this.listPlayers.size()][this.amountOfTurns + 1][2];

        for (int i = 0; i < this.listPlayers.size(); i++) {
            Player p = this.listPlayers.get(i);
            players[i] = p.getName();
            pins[i] = this.pinsMap.get(p);
        }

        ScoreDisplay scoreDisplay = new ScoreDisplay(pins, players, this.amountOfTurns);
        scoreDisplay.Display();
    }

    public void play(){
        //Game setup
        System.out.println("Game setup.");

        CLI cli = new CLI();

        this.amountOfTurns = cli.getInputInt("Enter amount of turns (must be a number)", 1, Integer.MAX_VALUE);
        System.out.println("Amount of turns : "+this.amountOfTurns);

        this.amountOfPins = cli.getInputInt("Enter amount of pins (must be a number)", 1, Integer.MAX_VALUE);
        System.out.println("Amount of pins : "+this.amountOfPins);

        this.amountOfPlayers = cli.getInputInt("Enter amount of players (must be a number)", 1, Integer.MAX_VALUE);
        System.out.println("Amount of players : "+this.amountOfPlayers);

        //Asking user to enter players names. Those are turned into Player instances and stored in this.listPlayers
        for (int i = 0; i <  this.amountOfPlayers; i++) {
            String newPlayerName = cli.getInputString("Enter new player", (str) -> {
                // string validation
                boolean exists = false;
                
                for (Player p : this.listPlayers) {
                    if (p.getName().equals(str)) exists = true;
                }

                return !exists;
            });
            this.addPlayer(newPlayerName);
        }


        //Initializing score calculator for each player
        for (Player p : this.listPlayers) {
            Score sc = new Score(this.amountOfPins, getNewArray(this.amountOfTurns + 1));
            this.scores.put(p, sc);

            this.pinsMap.put(p, getNewArray(this.amountOfTurns + 1));
        }

        //Game rounds
        System.out.println("Game started.");
        int firstPinsTouched = 0;
        int secondPinsTouched = 0;
        
        for (int turn = 0; turn < this.amountOfTurns; turn++) {
            for (Player p : this.listPlayers) {
                this.printScore();

                // init turn
                System.out.println("Beginning turn " + (turn+1) + " : " + p.getName());
                firstPinsTouched = 0;
                secondPinsTouched = 0;

                // first shot
                firstPinsTouched = cli.getInputInt("First shot of " + p.getName() + " : enter pins touched (must be a number)", 0, this.amountOfPins);
                this.pinsMap.get(p)[turn][0] = firstPinsTouched;

                //if there is a strike, there is no second shot, except for last round
                if (firstPinsTouched == this.amountOfPins && turn != this.amountOfTurns-1) {                    
                    // no 2nd shot after a strike
                    this.pinsMap.get(p)[turn][1] = 0;
                    System.out.println("Ending turn " + (turn+1) + " : " + p.getName());
                    continue;
                }

                // second shot
                secondPinsTouched = cli.getInputInt("Second shot of " + p.getName() + " : enter pins touched (must be a number)", 0, this.amountOfPins);
                this.pinsMap.get(p)[turn][1] = secondPinsTouched;

                // third shot on last round with strike or spare
                int totalPins = firstPinsTouched + secondPinsTouched;
                if ((firstPinsTouched == this.amountOfPins || totalPins == this.amountOfPins) && turn == this.amountOfTurns-1) {
                    int thirdPinsTouched = cli.getInputInt("Third shot of " + p.getName() + " : enter pins touched (must be a number)", 0, this.amountOfPins);
                    this.pinsMap.get(p)[turn+1][0] = thirdPinsTouched;
                }

                System.out.println("Ending turn " + (turn+1) + " : " + p.getName());
            }
        }

        this.printScore();

        // find winner
        ArrayList<Player> winners = new ArrayList<>();

        for (Player p : this.listPlayers) {
            
            // update player object score to final state
            Score sc = this.scores.get(p);
            sc.CalculScore(this.pinsMap.get(p), this.amountOfTurns);
            try {
                p.setPoints(intArrayToIntegerArray(sc.getScoreTab()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Something went wrong, we are sorry for the inconvenience.");
                return;
            }

            // compare with the winner
            if (winners.size() == 0 || p.getPoint(this.amountOfTurns - 1, 1) > winners.get(0).getPoint(this.amountOfTurns - 1, 1)) { 
                winners = new ArrayList<>();
                winners.add(p);
            } else if (p.getPoint(this.amountOfTurns - 1, 1) == winners.get(0).getPoint(this.amountOfTurns - 1, 1)) {
                winners.add(p);
            }
        }

        System.out.println("Winner(s) with " + winners.get(0).getPoint(this.amountOfTurns - 1, 1) + " points:");
        for (Player p : winners) {
            System.out.println("- " + p.getName());
        }
    }
}
package org.app4_quilles.model;

import java.util.Arrays;

public class Player {

    //----------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Attributes---------------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------//

    /**The player's name*/
    private String name;

    /**The array that will be displayed in the scoreboard*/
    private Integer[][] points;

    /**The last shot ?*/
    private Integer lastShot;

    /**The amount of turns in the game*/
    private int turns = 0;


    //----------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------Constructors--------------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------//

    /**Basic constructor. Initializes the points array to 10 rounds and the name to "".*/
    public Player() {
        this.name = "";
        this.points = new Integer[10][2];
        for(int i = 0; i < 10; i++) {
            this.points[i][0] = 0;
            this.points[i][1] = 0;
        }
    }

    /**
     * The real constructor to be used.
     * @param name : The name of the player.
     * @param turns : The amount of turns of the game the player is in.
     */
    public Player(String name, int turns) {
        this.name = name;
        this.points = new Integer[turns][2];
        this.turns = turns;
        for(int i = 0; i < turns; i++) {
            this.points[i][0] = 0;
            this.points[i][1] = 0;
        }
    }


    //----------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------Various methods------------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------//
    
    @Override
    public String toString() {
        String res = "Player[\n\tname = " + this.name + "\n\tpoints =\n\t\t";
        for (Integer[] tab : this.points) {
            res += tab[0] + ", ";
        }
        res += "\n\t\t";
        for (Integer[] tab : this.points) {
            res += tab[1] + ", ";
        }

        res += "\n\tlastShot = " + lastShot + "]";
        return res;
    }

    /**
     * Returns a value in the 2*n points array.
     * @param x : The round ( > turns)
     * @param y : The layer of the array ( > 2)
     * @return : The value at the specified cell.
     */
    public Integer getPoint(int x, int y) {
        if (y < 2 && x < turns) {
            return this.getPoints()[x][y];
        }
        return null;
    }

    /**
     * Sets a value in the 2*n points array.
     * @param x : The round ( > turns)
     * @param y : The layer of the array ( > 2)
     * @param value : The value ton be inserted.
     */
    public void setPoint(int x, int y, int value) {
        if (y < 2 && x < turns) {
            this.getPoints()[x][y] = value;
        } else {
            System.out.println("Insertion error : Incorrectes coordinates.");
        }
    }

    //----------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------Getters an setters-----------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * @return : The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name : The player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return : The whole 2*n array that contains the player's score
     */
    public Integer[][] getPoints() {
        return points;
    }

    /*public void setPoints(Integer[][] points) {
        this.points = points;
    }*/

    public Integer getLastShot() {
        return lastShot;
    }

    public void setLastShot(Integer lastShot) {
        this.lastShot = lastShot;
    }
}

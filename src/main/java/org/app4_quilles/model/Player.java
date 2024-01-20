package org.app4_quilles.model;

/**
 * This class represents a player.
 */
public class Player {

    //----------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Attributes---------------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------//

    /**The player's name*/
    private String name;

    /**The array that will be displayed in the scoreboard*/
    private Integer[][] points;

    /**The amount of turns in the game*/
    private int turns = 0;


    //----------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------Constructors--------------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * The real constructor to be used.
     * @param name : The name of the player.
     * @param turns : The amount of turns of the game the player is in.
     */
    public Player(String name, int turns) {
        this.name = name;
        this.points = new Integer[turns + 1][2];
        this.turns = Math.max(turns, 1);        //the game needs to have at least one turn
        for(int i = 0; i < turns + 1; i++) {    //initializing the cells to 0
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

        res += "]";
        return res;
    }


    //----------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------Getters an setters-----------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Returns a value in the 2*n points array.
     * @param round : The round ( < turns)
     * @param layer : The layer of the array ( < 2)
     * @return : The value at the specified cell.
     */
    public Integer getPoint(int round, int layer) {
        if (layer < 2 && round < turns) {
            return this.getPoints()[round][layer];
        }
        return null;
    }

    /**
     * Sets a value in the 2*n points array.
     * @param round : The round ( < turns)
     * @param layer : The layer of the array ( < 2)
     * @param value : The value ton be inserted.
     */
    public void setPoint(int round, int layer, int value) throws Exception {
        if (layer >= 0 && round >= 0 && layer <= 2 && round < turns + 1) {
            this.getPoints()[round][layer] = value;
        } else {
            throw new Exception("Insertion error : Incorrect coordinates.");
        }
    }

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
        Integer[][] res = this.points.clone();
        return res.clone();
    }

    /**Sets the whole points 2D array. */
    public void setPoints(Integer[][] points) throws Exception {
        if (points[0].length != 2) {
            throw new Exception("Wrong size for 2D array (there must be exactly two layers).");
        }
        this.points = points;
    }

    public int getTurns() {
        return turns;
    }

    /**
     * This method is to be called when the amount of turns of the game changes.
     * This will change the size of the 2D array accordingly.
     * It should not be called DURING a game.
     * @param turns
     */
    public void setTurns(int turns) {
        int max = Integer.max(this.turns, turns);
        this.turns = turns;
        Integer[][] newPoints = new Integer[turns][2];

        for (int i = 0; i < max; i++) {
            newPoints[i][0] = this.points[i][0];
            newPoints[i][1] = this.points[i][1];
        }
        this.points = newPoints;
    }

}
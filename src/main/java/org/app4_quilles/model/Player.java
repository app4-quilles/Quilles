package org.app4_quilles.model;

import java.util.Arrays;

public class Player {

    private String name;

    private Integer[][] points;

    private Integer lastShot;

    private int turns = 0;

    public void setPoint(int x, int y, int value) {
        if (y < 2 && x < turns) {
            this.getPoints()[x][y] = value;
        } else {
            System.out.println("Insertion error : Incorrectes coordinates.");
        }
    }

    public Player() {
        this.name = "";
        this.points = new Integer[10][2];
        for(int i = 0; i < 10; i++) {
            this.points[i][0] = 0;
            this.points[i][1] = 0;
        }
    }

    public Player(String name, int turns) {
        this.name = name;
        this.points = new Integer[turns][2];
        this.turns = turns;
        for(int i = 0; i < turns; i++) {
            this.points[i][0] = 0;
            this.points[i][1] = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[][] getPoints() {
        return points;
    }

    public void setPoints(Integer[][] points) {
        this.points = points;
    }

    public Integer getLastShot() {
        return lastShot;
    }

    public void setLastShot(Integer lastShot) {
        this.lastShot = lastShot;
    }
}

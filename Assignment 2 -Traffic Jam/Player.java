/*
 * CEN4025C - Software Engineering 2
 * SWEII Programming Projects
 * 
 * Assignment 2: Traffic Jam
 * Due Date: February 18, 2024
 * 
 * Programmers: Ava Adams, Andres Castellanos, Emely Gadea, Arturo Escobar Valdes
 * Team Name: Enemy of Syntax
 * 
 * Description:   This file contains the source code for Player.java
 */

public class Player {

    // Attributes
    private char playerID;
    private int initialPosition;
    private int currPosition;
    private String lastMove;
    private boolean isSorted;

    //Methods
    // Constructor
    Player (char id, int pos) {
        this.playerID = id;
        this.initialPosition = pos;
        this.currPosition = pos;
        this.isSorted = false;
    }

    Player (int id, int pos) {
        this.playerID = (char)(48 + id);
        this.initialPosition = pos;
        this.currPosition = pos;
    }

    public char getPlayerID() {
        return playerID;
    }

    public int getPosition() {
        return currPosition;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setPosition(int pos) {

    }

    public void setLastMove() {

    }

    public void setTeam(String t) {

    }

    @Override
    public String toString() {
        return playerID + "-" + currPosition;
    }
}

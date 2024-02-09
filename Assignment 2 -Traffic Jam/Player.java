/*
 * CEN4025C - Software Engineering 2
 * SWEII Programming Projects
 * 
 * Assignment 2: Traffic Jam
 * Due Date: February 8, 2024
 * 
 * Programmers: Ava Adams, Andres Castellanos, Emely Gadea, Arturo Escobar Valdes
 * Team Name: Enemy of Syntax
 * 
 * Description:   This file contains the source code for Player.java
 */

public class Player {

    // Attributes
    private char playerID;
    private int position;

    //Methods
    // Constructor
    Player (char id, int pos) {
        this.playerID = id;
        this.position = pos;
    }

    Player (int id, int pos) {
        this.playerID = (char)(48 + id);
        this.position = pos;
    }

    public char getPlayerID() {
        return playerID;
    }

    public int getPosition() {
        return position;
    }
    
    public String toString() {
        return (String)(playerID + "-" + position);
    }

    public void setPosition(int pos) {

    }

    public void setTeam(String t) {

    }
}

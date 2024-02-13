/*
 * CEN4025C - Software Engineering 2
 * SWEII Programming Projects
 * 
 * Assignment 2: Traffic Jam
 * Due Date: February 18, 2024
 * 
 * Programmer: Ava Adams
 * Team Name: Enemy of Syntax
 * 
 * Description:   This file contains the source code for Player.java
 */

public class Player {

    // Attributes
    private String teamID;
    private char playerID;
    private int initialPosition;
    private int currPosition;
    private boolean isSorted;

    //Methods
    // Constructors
    // Constructor for a Player on Team "alpha"
    Player (String tID, char id, int pos) {
        this.teamID = tID;
        this.playerID = id;
        this.initialPosition = pos;
        this.currPosition = pos;
    }

    // Constructor for a Player on Team "num"
    Player (String tID, int id, int pos) {
        this.teamID = tID;
        this.playerID = (char)(48 + id);
        this.initialPosition = pos;
        this.currPosition = pos;
    }

    // Determines if Player is sorted into the correct position
    // Takes team size as an argument
    public boolean isSorted(int tSize) {
        if (teamID == "alpha" && 
                currPosition == initialPosition + (tSize + 1)) {
            isSorted = true;
        }
        else if (teamID == "num" && 
                    currPosition == initialPosition - (tSize + 1)) {
            isSorted = true;
        }
        else {
            isSorted = false;
        }
        return isSorted;
    }

    public String getTeamID () {
        return teamID;
    }

    public char getPlayerID() {
        return playerID;
    }

    public int getPosition() {
        return currPosition;
    }

    public void setPosition(int pos) {
        currPosition = pos;
    }

    @Override
    public String toString() {
        return playerID + "";
    }
}

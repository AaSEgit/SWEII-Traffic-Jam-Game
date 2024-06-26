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
 * Description:   This file contains the source code for Square.java
 */

public class Square {
    
    // Attributes
    private int squareID;
    private boolean isOccupied;
    private Player currentOccupant;

    // Methods
    // Constructor
    Square (int sqID, Player currOcc) {
        this. squareID = sqID;
        this.currentOccupant = currOcc;
    }

    public int getSquareID() {
        return squareID;
    }

    public boolean isOccupied() {
        if (currentOccupant != null)
            isOccupied = true;
        else
            isOccupied = false;
        return isOccupied;
    }

    public Player getCurrentOccupant() {
        return currentOccupant;
    }

    public void setCurrentOccupant(Player currOcc) {
        this.currentOccupant = currOcc;
    }

    public void clearOccupant() {
        currentOccupant = null;
    }

    @Override
    public String toString() {
        return currentOccupant + "";
    }
}

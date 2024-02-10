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
 * Description:   This file contains the source code for GameBoard.java
 */

import java.util.ArrayList;

public class GameBoard {

    // Attributes
    private ArrayList<Square> squares;
    private ArrayList<Square> sortedSquares;

    // Methods

    // Constructor - creates a GameBoard object with Squares for each Player,
    // plus one unoccupied Square in the middle
    GameBoard (int tSize, Team t1, Team t2) {
        squares = new ArrayList<>();
        sortedSquares = new ArrayList<>();
        int i;

        for (i = 0; i < tSize; i++) {
            squares.add(new Square(i, t1.getPlayers().get(i)));
        }
        squares.add(new Square(tSize, null));
        for (i = 0; i < tSize; i++) {
            squares.add(new Square(i+tSize+1, t2.getPlayers().get(i)));
        }
    }

    // Returns the index of the unoccupied Square
    public int searchUnoccupiedSquare() {

        for (int i = 0; i < squares.size(); i++) {
            if(squares.get(i).getCurrentOccupant() == null)
            return i; // found
        }
        return -1;  // not found
    }

    // Adds a Player/Square to the list of sorted Players
    public int addToSortedSquares(Square sq) {
        sortedSquares.add(sq);
        return sortedSquares.size();
    }

    public int getBoardSize() {
        return squares.size();
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public ArrayList<Square> getSortedSquares() {
        return sortedSquares;
    }

    public void displaySquares() {
        System.out.print("Game Board: ");
        for (int i = 0; i < squares.size(); i++) {
            System.out.print(squares.get(i) + " ");
        }
    }
}

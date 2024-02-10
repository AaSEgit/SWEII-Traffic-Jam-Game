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
 * Description:   This file contains the source code for GameBoard.java
 */

import java.util.ArrayList;

public class GameBoard {

    // Attributes
    private ArrayList<Square> squares;
    private int boardSize;

    // Methods
    // Constructor - creates a GameBoard object with squares for each player, plus one extra in the middle
    GameBoard (int tSize, Team t1, Team t2) {
        squares = new ArrayList<>();
        boardSize = 2 * tSize + 1;
        int i;

        for (i = 0; i < tSize; i++) {
            squares.add(new Square(i, t1.getPlayers().get(i)));
        }
        squares.add(new Square(tSize, null));
        for (i = 0; i < tSize; i++) {
            squares.add(new Square(i+tSize+1, t2.getPlayers().get(i)));
        }
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void displaySquares() {
        for (int i = 0; i < squares.size(); i++) {
            System.out.print(squares.get(i) + " ");
        }
    }
}

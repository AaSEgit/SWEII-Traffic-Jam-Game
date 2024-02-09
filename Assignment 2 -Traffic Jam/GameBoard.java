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

    // Methods
    // Constructor - creates a GameBoard object with squares for each player, plus one extra in the middle
    GameBoard (int tSize) {
        squares = new ArrayList<>();

        for (int i = 0; i < 2 * tSize + 1; i++) {
            Square sq = new Square(i);
            squares.add(sq);
        }
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
}

/*
 * CEN4025C - Software Engineering 2
 * SWEII Programming Projects
 * 
 * Assignment 2: Traffic Jam
 * Due Date: February 8, 2024
 * 
 * Programmers: Ava Adams, Andres Castellanos, sEmely Gadea, Arturo Escobar Valdes
 * Team Name: Enemy of Syntax
 * 
 * Description:   This file contains the source code for the controller Game.java
 */

public class Game {

    // Attributes
    private GameBoard gameBoard;
    private Team team1;
    private Team team2;
    private int teamSize = 3;
    private Team currentTurn;

    // Methods
    // Constructor
    Game() {
        gameBoard = new GameBoard(teamSize);
        System.out.println();
        team1 = new Team(teamSize, "alpha");
        team1.displayPlayers();
        team2 = new Team(teamSize, "num");
        team2.displayPlayers();
    }

    public boolean movePlayer(Player plyr, Square sqr) {
        return false;
    }

    public boolean checkWinCondition() {
        return false;
    }

   public void resetGame() {

    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Team t) {
        currentTurn = t;
    }

    // BEGINNING OF PROGRAM LOGIC
    public static void main(String[] args) {
        Game game = new Game();
    }
}

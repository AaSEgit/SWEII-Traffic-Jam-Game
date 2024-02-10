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
 * Description:   This file contains the source code for the controller Game.java
 */

public class Game {

    // Attributes
    private GameBoard gameBoard;
    private Square unoccupiedSquare;
    private int unOccupiedPosition;
    private Team team1;
    private Team team2;
    private enum Move {SHIFT, JUMP, FINAL_SORT};
    private enum Direction {LEFT, RIGHT};
    private Move lastMove;
    private Direction lastDirection;
    private Player currentPlayer;

    // Methods
    // Constructor
    Game(int tSize) {
        // Create Teams
        team1 = new Team(tSize, "alpha");
        team2 = new Team(tSize, "num");

        // Load GameBoard
        gameBoard = new GameBoard(tSize, team1, team2);

        // Set last move
        lastMove = null;
    }

    // Game will run automatically and show solution
    public void automaticGame() {
        while(gameBoard.getBoardSize() != gameBoard.getSortedSquares().size()) {
            // Show list of Players on the GameBoard
            gameBoard.displaySquares();

            // Search for unoccupied Square
            unOccupiedPosition = gameBoard.searchUnoccupiedSquare();
            unoccupiedSquare = 
                new Square(unOccupiedPosition, gameBoard.getSquares().get(unOccupiedPosition).getCurrentOccupant());
            // Check movement options and attempt to move a Player
            checkUnouccupiedSquare(unOccupiedPosition);    
            movePlayer(currentPlayer, Move.SHIFT);
        }
        System.out.print("\nFinal: ");
        gameBoard.displaySquares();
    }

    // User can play the game step-by-step
    public void stepByStepGame() {

    }

    // Decide which Player to move
    public void checkUnouccupiedSquare (int pos) {
        // Initial Check (start of game)
        if ((pos == gameBoard.getBoardSize()/2) && (lastMove == null)) {
            // check direction of choice (left or right)
            lastDirection = Direction.LEFT;
        }
        // Post-initial checks
        else if (pos == 0) {
            // check right
            lastDirection = Direction.RIGHT;
        }
        else if (pos == gameBoard.getBoardSize() - 1) {
            // check left
            lastDirection = Direction.LEFT;
        }
        else if (pos > 0 && pos < gameBoard.getBoardSize()) {
            if (lastMove == Move.SHIFT) {
                // check opposite direction
                changeDirection();
            }
            else if (lastMove == Move.FINAL_SORT) {
                // FIXME: Sort remainning Players on each Team
            }
        }
        checkDirection(lastDirection, pos);
    }

    // Selects currentPlayer
    public void checkDirection(Direction d, int pos) {
        if (d == Direction.LEFT) {
            currentPlayer = gameBoard.getSquares().get(pos-1).getCurrentOccupant();
        }
        else {
            currentPlayer = gameBoard.getSquares().get(pos+1).getCurrentOccupant();
        }
    }

    public void changeDirection() {
        if (lastDirection == Direction.LEFT) {
            lastDirection = Direction.RIGHT;
        }
        else  {
            lastDirection = Direction.LEFT; 
        }
    }

    // Move selected Player
    public void movePlayer(Player plyr, Move move) {
        int currPos = plyr.getPosition();
        int newPos = currPos;

        if (shift(plyr) != -1 || jump(plyr) != -1) {
            // Attempt SHIFT
            if (move == Move.SHIFT && shift(plyr) != -1) {
                newPos = shift(plyr);
                plyr.setPosition(newPos);
                gameBoard.getSquares().get(newPos).setCurrentOccupant(plyr);
                gameBoard.getSquares().get(currPos).setCurrentOccupant(null);
                unoccupiedSquare = gameBoard.getSquares().get(currPos);
                lastMove = Move.SHIFT;
            }
            // Attempt JUMP
            else if (jump(plyr) != -1) {
                newPos = jump(plyr);
                plyr.setPosition(newPos);
                gameBoard.getSquares().get(newPos).setCurrentOccupant(plyr);
                gameBoard.getSquares().get(currPos).setCurrentOccupant(null);
                unoccupiedSquare = gameBoard.getSquares().get(currPos);
                lastMove = Move.JUMP;
            }
            System.out.println("\nPlayer " + plyr +  " " +
                            lastMove + "ED to position " + newPos);
        }
        else {
            // Move is invalid, skip to next player
            System.out.println("\nCannot move Player " + plyr);
            if (currPos == 0 || currPos == gameBoard.getBoardSize() - 1) {
                changeDirection();
            }
            else {
                checkDirection(lastDirection, currPos);
                movePlayer(currentPlayer, Move.SHIFT);
            }
        }
        
        // Check if the Player was sorted to the correct Square
        Square sq = gameBoard.getSquares().get(newPos);
        if (plyr.isSorted(team1.getTeamSize())) {
            if (gameBoard.addToSortedSquares(sq) == 2) {
                lastMove = Move.FINAL_SORT;
            }
            //System.out.println("Player " + plyr + " has been sorted.");
        }

        
    }

    // Shift: move Player forward one square
    public int shift(Player plyr) {
        int currPos = plyr.getPosition();
        int resultPos;
        if (plyr.getTeamID() == "alpha")
            resultPos = currPos + 1;
        else
            resultPos = currPos - 1;

        if (resultPos == unoccupiedSquare.getSquareID()) {
            return resultPos;
        }
        else {
            return -1;
        }
    }

    // Jump: pass another Player
    public int jump(Player plyr) {
        int currPos = plyr.getPosition();
        int resultPos;
        if (plyr.getTeamID() == "alpha")
            resultPos = currPos + 2;
        else
            resultPos = currPos - 2;

        if (resultPos == unoccupiedSquare.getSquareID()) {
            return resultPos;
        }
        else {
            return -1;
        }
    }

    public void setLastMove (Move move) {
        lastMove = move;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player plyr) {
        currentPlayer = plyr;
    }

    public boolean checkWinCondition() {
        return false;
    }

   public void resetGame() {

    }
}

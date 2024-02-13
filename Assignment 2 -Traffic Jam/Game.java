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
 * Description:   This file contains the source code for the controller Game.java
 */

public class Game {

    // Attributes
    private GameBoard gameBoard;
    private Square unoccupiedSquare;
    private Team team1;
    private Team team2;
    private enum Move {SHIFT, JUMP};
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
        // Show list of Players on the GameBoard
        gameBoard.displaySquares();
        System.out.println();

        while(gameBoard.getSortedSquares().size() < 2) {
            // Check movement options and attempt to move a Player
            //if it is not already sorted
            checkUnouccupiedSquare();
            movePlayer(currentPlayer, Move.SHIFT);

            // Show list of Players on the GameBoard
            gameBoard.displaySquares();
            System.out.println();
        }
        
        //Players are now alternating, sort remaining Players on gameBoard
        finalSort();

        System.out.println("DONE");
    }

    // User can play the game step-by-step
    public void stepByStepGame() {
        System.out.println("TO DO: Step by step gameplay");
    }

    // Finds the unoccupied Square on gameBoard
    public void checkUnouccupiedSquare () {
        // Search for unoccupied Square
        int pos = gameBoard.searchUnoccupiedSquare();
        unoccupiedSquare = 
            new Square(pos, gameBoard.getSquares().get(pos).getCurrentOccupant());

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
        }
        // Select a Player (currentPlayer) to move
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

    // Reverses current search direction
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

            // Check if the Player was sorted to the correct Square
            Square sq = gameBoard.getSquares().get(newPos);
            if (plyr.isSorted(team1.getTeamSize())) {
                gameBoard.addToSortedSquares(sq);
            }
        }
        else {
            // Move is invalid, skip to next player
            if (currPos == 0 || currPos == gameBoard.getBoardSize() - 1) {
                changeDirection();
            }
            else {
                checkDirection(lastDirection, currPos);
                movePlayer(currentPlayer, Move.SHIFT);
            }
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

    // Sort remaining Players on gameBoard
    public void finalSort() {
        int numSorted = gameBoard.getSortedSquares().size();
        //int i = 0; i <= gameBoard.getBoardSize() * numUnsorted; i++
        while (numSorted < gameBoard.getBoardSize()-1) {
            lastMove = Move.JUMP;   // Do not reverse check direction from Move.SHIFT
            checkUnouccupiedSquare();   // selects currentPlayer to move
            movePlayer(currentPlayer, Move.SHIFT);    // Attempt move

            gameBoard.displaySquares();
            System.out.println();

            numSorted = gameBoard.getSortedSquares().size();
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

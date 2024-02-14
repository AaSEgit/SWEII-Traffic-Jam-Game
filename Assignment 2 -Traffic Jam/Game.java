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

 import java.util.Scanner;

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

        // Show list of Players on the GameBoard
        gameBoard.displaySquares();
        System.out.println();
    }

    // Game will run automatically and show solution
    public void automaticGame() {
        while(gameBoard.getSortedSquares().size() < 2) {
            // Check movement options and attempt to move a Player
            //if it is not already sorted
            checkUnouccupiedSquare();
            movePlayer(currentPlayer, Move.SHIFT);

            // Show list of Players on the GameBoard
            gameBoard.displaySquares();
            System.out.println("\n");
        }
        
        //Players are now alternating, sort remaining Players on gameBoard
        finalSort();

        System.out.println("DONE");
    }

    // User can attempt to solve the game
    public boolean userGame() {
        boolean badData = true;
        int userInput;
        int unOccPos, currPos;
        int maxPlayerIndex = gameBoard.getBoardSize()-1;
        Scanner s = new Scanner(System.in);
        
        try {
            // Do until all Players are sorted
            while (gameBoard.getSortedSquares().size() < maxPlayerIndex) { 
                // Search for unoccupied Square
                unOccPos = gameBoard.searchUnoccupiedSquare();
                unoccupiedSquare = 
                    new Square(unOccPos, gameBoard.getSquares().get(unOccPos).getCurrentOccupant());
                
                //Let user select a player
                badData = true;
                while (badData) {
                    System.out.print("\n\nEnter the index/position of " +
                                        "the Player you wish to move " + 
                                        "(0-" + (maxPlayerIndex) + "): ");
                    userInput = s.nextInt();

                    if (userInput >= 0 && userInput <= maxPlayerIndex) {
                        currentPlayer = gameBoard.getSquares().get(userInput).getCurrentOccupant();
                        currPos = currentPlayer.getPosition();

                        //Let user choose a move
                        badData = true;
                        while (badData) {
                            System.out.print("SHIFT(1) or JUMP(2)? Select your move: ");
                            userInput = s.nextInt();
                                if (userInput == 1 && shift(currentPlayer) == unOccPos) {
                                    lastMove = Move.SHIFT;
                                    badData = false;
                                }     
                                else if (userInput == 2 && jump(currentPlayer) == unOccPos) {
                                    lastMove = Move.JUMP;
                                    badData = false;
                                }
                                else {
                                    System.out.println("Illegal move, game over!");
                                    System.out.println("Game restarting...");
                                    return false;
                                }
                            movePlayer(currentPlayer, lastMove);

                            // Display the updated gameBoard
                            gameBoard.displaySquares();
                        }
                    }
                    else {
                        System.out.println("Invalid position.");
                    }
                } 
            }
            System.out.println("\nYOU WIN!");
        } catch (Exception e) {
            System.out.println("Invalid input. Game restarting...");
            gameBoard.displaySquares();
            userGame();
        }
        
        return true;    // game completed with no issues
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
            System.out.println("Player " + plyr +  " " +
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
    //  returns position after shift
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
            return -1;  // illegal move
        }
    }

    // Jump: pass another Player
    //  returns position after jump
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
            return -1;  // illegal move
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
            System.out.println("\n");

            numSorted = gameBoard.getSortedSquares().size();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}

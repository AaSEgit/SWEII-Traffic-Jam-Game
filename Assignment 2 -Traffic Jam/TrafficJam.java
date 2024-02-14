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
 * Description:   This file contains the main method for the Traffic Jam game.
 *                  The user will run this file to play the game.
 */

import java.util.Scanner;

public class TrafficJam {
    
    // BEGINNING OF PROGRAM LOGIC
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int teamSize, gameMode;
        boolean badData = true;
        Game game;

        try {
            // Get teamSize from user
            while (badData) {
                System.out.print("\nEnter the size of each team (1 to 9): ");
                teamSize = scanner.nextInt();
                if (teamSize >= 1 && teamSize <= 9) {
                    game = new Game(teamSize);    // create Game object

                    // Select game mode
                    while (badData) {
                        System.out.print("\nSee solution (1) or attempt to solve step-by-step (2)? ");
                        gameMode = scanner.nextInt();
                        if (gameMode == 1) {
                            badData = false;
                            game.automaticGame();   // run automatic solution
                        }
                        else if (gameMode == 2) {
                            badData = false;
                            while (game.userGame() == false) {
                                game = new Game(teamSize);    // create Game object
                                game.userGame();  // user can attempt solving the game
                            }
                        }
                        else {
                            System.out.println("Invalid input.");
                        }
                    }
                }
                else {
                    System.out.println("Invalid input. Please enter a valid team size (1 to 9): ");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Game restarting...");
            main(args);
        }
        
        scanner.close();
    }
}

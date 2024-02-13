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
        int userInput;
        boolean badData = true;

        System.out.print("Enter the size of each team: ");
        userInput = scanner.nextInt();
        Game game = new Game(userInput);
        while (badData) {
                System.out.print("\nSee solution (1) or attempt to solve step-by-step (2)? ");
                userInput = scanner.nextInt();
                if (userInput != 1 && userInput != 2) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
                else 
                    badData = false;
        }

        System.out.println();

        if (userInput == 1)
            game.automaticGame();   // run automatic solution
        else if (userInput == 2) {
            game.stepByStepGame();  // user can play step-by-step
        }
        
        scanner.close();
    }
}

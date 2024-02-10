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
 * Description:   This file contains the main method for the Traffic Jam game.
 *                  The user will run this file to play the game.
 */

import java.util.Scanner;

public class TrafficJam {
    
    // BEGINNING OF PROGRAM LOGIC
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput;

        System.out.print("Enter the size of each team: ");
        userInput = scanner.nextInt();
        Game game = new Game(userInput);

        System.out.print("\nSee solution (1) or attempt to solve step-by-step (2)? ");
        userInput = scanner.nextInt();
        System.out.println();

        if (userInput == 1)
            game.automaticGame();   // run automatic solution
        else {
            game.stepByStepGame();  // user can play step-by-step
        }
    }
}

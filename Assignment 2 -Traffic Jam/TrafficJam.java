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
 * Description:   This file contains the main method for the Traffic Jam game.
 *                  The user will run this file to play the game.
 */

import java.util.Scanner;

public class TrafficJam {
    
    // BEGINNING OF PROGRAM LOGIC
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int teamSize;

        System.out.print("Enter the size of each team: ");
        teamSize = scanner.nextInt();
        Game game = new Game(teamSize);
    }
}

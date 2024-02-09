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
 * Description:   This file contains the source code for Team.java
 */

import java.util.ArrayList;

public class Team {

    // Attributes
    private ArrayList<Player> players;
    private String teamID;
    private int teamSize;

    //Methods
    //Constructor
    Team (int tSize, String id) {
        this.teamSize = tSize;
        this.teamID = id;
        players = new ArrayList<>();

        // Team player IDs will be entered in backwards alphabetical order
        if (teamID == "alpha") {
            for (int i = 0; i < tSize; i++) {
                Player plyr = new Player((char)((64 + tSize) - i), i);
                players.add(plyr);
            }
        }
        // Team player IDs will be entered in numerical order
        else if (teamID == "num") {
            for (int i = 0; i < tSize; i++) {
                Player plyr = new Player(i + 1, tSize + 1 + i);
                players.add(plyr);
            }
        }
    }

    public String getTeamID () {
        return teamID;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Displays a list of players on a team 
    // with their ID and current position
    public void displayPlayers() {
        for (int i = 0; i < teamSize; i++) {
            System.out.print(getPlayers().get(i).toString() + " ");
        }
    }
}

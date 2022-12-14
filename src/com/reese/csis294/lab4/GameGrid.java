// Game Grid
// Author: Reese Norris
// CSIS-294 Lab 4

package com.reese.csis294.lab4;

import java.security.SecureRandom;
import java.util.Scanner;

public class GameGrid {

    private final int[][] grid;
    private final LinkedList playerHistory;
    private int totalMoves;
    private int playerYPos;
    private int playerXPos;

    /**
     * Builds and configures a game grid
     * @param rows Number of rows in the grid
     * @param columns Number of columns in the grid
     * @param wallChance Percent chance of a wall being generated in the grid
     */

    public GameGrid(int rows, int columns, int wallChance) {
        this.grid = generateGrid(rows, columns, wallChance);
        this.totalMoves = 0;
        this.playerYPos = 0;
        this.playerXPos = 0;
        this.playerHistory = new LinkedList(this.playerYPos, this.playerXPos);
    }

    /**
     * Generates a 2D grid for GameGrid use-cases
     * @param rows Number of rows in the grid
     * @param columns Number of columns in the grid
     * @param wallChance Percent chance of a wall being generated in any given element
     * @return 2D array containing the generated grid ([0] = y values, [1] = x values)
     */

    private static int[][] generateGrid(int rows, int columns, int wallChance) {
        SecureRandom rand = new SecureRandom();
        int temp;
        int[][] outGrid = new int[rows][columns];

        // Iterate through each grid cell, assign each to 1 or 0
        for (int row = 0; row < outGrid[0].length; row++) {
            for (int column = 0; column < outGrid[1].length; column++) {
                temp = rand.nextInt(100);
                if (temp <= wallChance) {
                    outGrid[row][column] = 1;
                }
                else {
                    outGrid[row][column] = 0;
                }
            }
        }

        // Change [0, 0] to "0" to eliminate the player from starting on a "1"
        outGrid[0][0] = 0;

        return outGrid;
    }

    /**
     * Renders an ASCII grid of a GameGrid's 2D array's elements including a player's location
     * @param grid The grid
     * @param playerYPos The row of the player
     * @param playerXPos The column of the player
     * @return The rendered grid, ready to print
     */

    private static String renderGrid(int[][] grid, int playerYPos, int playerXPos) {
        int gridWidth = grid[1].length;

        StringBuilder result = new StringBuilder();
        result.append(" ");
        result.append("   ".repeat(gridWidth));
        result.append(" \n");

        for (int row = 0; row < grid[0].length; row++) {
            result.append(" ");
            for (int column = 0; column < grid[1].length; column++) {
                if (playerYPos == row && playerXPos == column) {
                    result.append(" X ");
                }
                else {
                    result.append(" " + grid[row][column] + " ");
                }
            }
            result.append(" \n");
        }

        result.append("  ");
        result.append("   ".repeat(gridWidth));

        return result.toString();
    }

    /**
     * Writes values from a player history linked list onto a grid to display move history
     * @param grid The grid to modify
     * @param playerHistory The linked list from which to reference moves
     * @return A new grid with player history elements valued as "5"'s
     */

    private int[][] getPlayerHistoryGrid(int[][] grid, LinkedList playerHistory) {
        Node tempNode;

        while (true) {
            tempNode = playerHistory.removeHeadNode();
            if (tempNode == null) {
                break;
            }
            grid[tempNode.yPos][tempNode.xPos] = 5;
        }

        return grid;
    }

    /**
     * Runs a game session
     */

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            // Print game header
            System.out.println("Enter 'r' to go right; 'd' to go down.");

            // Render a grid then print
            System.out.println(renderGrid(this.grid, this.playerYPos, this.playerXPos));

            // If game finished, print result & history grid then exit
            if (this.grid[this.playerYPos][this.playerXPos] == 1) {
                System.out.println("You lost!");
                System.out.println("You finished in " + this.totalMoves + " moves.");
                System.out.println("Here's your history:");
                System.out.println(
                        renderGrid(getPlayerHistoryGrid(this.grid, this.playerHistory), this.playerYPos, this.playerXPos)
                );
                break;
            }
            if (this.playerYPos == (this.grid[0].length - 1) || this.playerXPos == (this.grid[1].length - 1)) {
                System.out.println("You won!");
                System.out.println("You finished in " + this.totalMoves + " moves.");
                System.out.println("Here's your history:");
                System.out.println(
                        renderGrid(getPlayerHistoryGrid(this.grid, this.playerHistory), this.playerYPos, this.playerXPos)
                );
                break;
            }

            // Player choice
            input_loop:
            while (true) {
                System.out.print("> ");
                choice = scanner.nextLine();
                switch (choice.toLowerCase()) {
                    case "d":
                        this.playerYPos++;
                        break input_loop;
                    case "r":
                        this.playerXPos++;
                        break input_loop;
                    default:
                        System.out.println("Try again!");
                }
            }

            // Write current location to the linked list
            this.playerHistory.addHeadNode(this.playerYPos, this.playerXPos);

            // Increment total moves
            this.totalMoves++;
        }
    }
}

// Game Grid
// Author: Reese Norris
// CSIS-294 Lab 3

package com.reese.csis294.lab3;

import java.security.SecureRandom;
import java.util.Scanner;

public class GameGrid {

    final int[][] grid;
    int playerXPos;
    int playerYPos;

    /**
     * Builds and configures a game grid
     * @param rows Number of rows in the grid
     * @param columns Number of columns in the grid
     * @param wallChance Percent chance of a wall being generated in the grid
     */

    public GameGrid(int rows, int columns, int wallChance) {
        this.grid = generateGrid(rows, columns, wallChance);
        this.playerXPos = 0;
        this.playerYPos = 0;
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
     * Renders an ASCII grid of a GameGrid's 2D array's elements including a player's location and a border
     * @param grid The grid
     * @param playerYPos The row of the player
     * @param playerXPos The column of the player
     * @return The rendered grid, ready to print
     */

    private static String renderGrid(int[][] grid, int playerYPos, int playerXPos) {
        int gridWidth = grid[1].length;

        StringBuilder result = new StringBuilder();
        result.append("/ ");
        result.append("---".repeat(gridWidth));
        result.append(" \\\n");

        for (int row = 0; row < grid[0].length; row++) {
            result.append("| ");
            for (int column = 0; column < grid[1].length; column++) {
                if (playerYPos == row && playerXPos == column) {
                    result.append(" X ");
                }
                else {
                    result.append(" " + grid[row][column] + " ");
                }
            }
            result.append(" |\n");
        }

        result.append("\\ ");
        result.append("---".repeat(gridWidth));
        result.append(" /");

        return result.toString();
    }

    /**
     * Runs a game session
     */

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            // "Clear" console
            System.out.println("\n".repeat(this.grid[0].length * 5));

            // Print game header
            System.out.println("Enter 'r' to go right; 'd' to go down.");

            // Print rendered grid
            System.out.println(renderGrid(this.grid, this.playerYPos, this.playerXPos));

            // Print game status and exit if required
            if (this.grid[this.playerYPos][this.playerXPos] == 1) {
                System.out.println("You lost!");
                break;
            }
            if (this.playerYPos == 9 || this.playerXPos == 9) {
                System.out.println("You won!");
                break;
            }

            // Get input
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
        }
    }
}

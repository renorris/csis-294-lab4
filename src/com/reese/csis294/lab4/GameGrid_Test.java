// Game Grid Test
// Author: Reese Norris
// CSIS-294 Lab 4

package com.reese.csis294.lab4;

public class GameGrid_Test {

    public static void main(String[] args) {

        // Functionality described in Lab 3 Part 2.b.ii.1 has been
        // distributed across GameGrid in its constructor and
        // other utility methods

        // "Updating run method in GameGrid class" changes
        // can be found in both GameGrid.run() and GameGrid.getPlayerHistoryGrid()

        GameGrid gameGrid = new GameGrid(10, 10, 20);
        gameGrid.run();
    }
}

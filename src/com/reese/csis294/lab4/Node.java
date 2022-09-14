// Node for Linked List
// Author: Reese Norris
// CSIS-294 Lab 4

package com.reese.csis294.lab4;

public class Node {

    public final int yPos;
    public final int xPos;
    public final Node nextNode;

    public Node(int yPos, int xPos, Node nextNode) {
        this.yPos = yPos;
        this.xPos = xPos;
        this.nextNode = nextNode;
    }
}

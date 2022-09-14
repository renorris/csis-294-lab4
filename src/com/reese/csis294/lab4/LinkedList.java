// Linked List for Game Grid
// Author: Reese Norris
// CSIS-294 Lab 4

package com.reese.csis294.lab4;

public class LinkedList {

    private Node headNode;

    public LinkedList(int initYPos, int initXPos) {
        this.headNode = new Node(initYPos, initXPos, null);
    }

    public void addHeadNode(int yPos, int xPos) {
        Node newNode = new Node(yPos, xPos, this.headNode);
        this.headNode = newNode;
    }

    public Node removeHeadNode() {
        Node removedHeadNode = this.headNode;

        if (this.headNode != null) {
            this.headNode = this.headNode.nextNode;
        }

        return removedHeadNode;
    }
}

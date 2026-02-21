/*
MIDN 3/C Soren Patel (284932)
MIDN 3/C Siddharth Swarup (286354)
Lab07
ThingList.java
*/

import java.util.*;

/**
 * class ThingList
 */
public class ThingList{

    /**
     * class Node (implicit to ThingList class)
     */
    private class Node {
        //conventional Node fields, contains a ThingA
        ThingA data;
        Node next;

        /**
         * constructor
         */
        Node(ThingA data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    //head node field
    private Node head;

    /**
     * constructor
     */
    public ThingList() {
        this.head = null;
    }

    /**
     * method to add new node containing parameter ThingA to list end
     */
    public void add(ThingA t){
        //handle empty list
        if(head == null) {
            head = new Node(t, null);
            return;
        }

        //traverse list to find end
        Node curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(t, null);
    }

    /**
     * method to print out all Things in current list
     */
    public void printList() {
        Node curr = head;
        while(curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    /**
     * iterate through list and call maybeTurn on each thing
     */
    public void moveEachThing(){
        for (Node T = this.head; T != null; T = T.next) {
            ThingA temp = T.data;
            temp.maybeTurn();
            temp.step();
        }
    }

}
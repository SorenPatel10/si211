/*
MIDN 3/C Soren Patel
Lab 04
Queue.java
*/

import java.util.*;
public class Queue{
    
    /**
     * class Node def
     */
    private static class Node{
        private String data;
        private Node next;
        private Node(String d, Node n) {
            data = d;
            next = n;
        }
    }

    private Node head;
    private Node tail;

    /**
     * adds s to the back of the queue
     */
    public void enqueue(String s) {
        
        //create temporary node
        Node temp = new Node(s, null);

        //if (empty queue) else (add new tail node)
        if(head == null){
            head = temp;
            tail = temp;
        }
        else{
            tail.next = temp;
            tail = temp;
        }   
    }

    /**
     * removes and returns string from the front of the queue
     */
    public String dequeue() {
        
        //case empty queue
        if(head == null)
            return ""; 
        
        //extract string to return
        String toRet = head.data;

        //insert new node at the front
        head = head.next;
        if(head == null){
            tail = null;
        }
        return toRet;
    }

    /**
     * returns true if the queue is empty
     */
    public boolean empty() {
        return head == null;
    }
}
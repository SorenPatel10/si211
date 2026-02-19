import java.util.*;

public class ThingList{

    private class Node {
        ThingA data;
        Node next;

        Node(ThingA data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private Node head;

    public ThingList() {
        this.head = null;
    }

    public void add(ThingA t){
        if(head == null) {
            head = new Node(t, null);
            return;
        }

        Node curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(t, null);
    }

    public void printList() {
        Node curr = head;
        while(curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public void moveEachThing(){
        for (Node T = this.head; T != null; T = T.next) {
            ThingA temp = T.data;
            temp.maybeTurn();
            temp.step();
        }
    }

}
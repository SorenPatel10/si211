/*
MIDN 3/C Soren Patel (284932)
Proj01
SectionList.java
*/

/**
 * Linked list for storing Sections
 */
public class SectionList {

    /**
     * private Node class embedded into SectionList
     */
    private class Node {
        Section data;
        Node next;

        Node(Section data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    //head field
    private Node head;

    /**
     * SectionList constructor
     */
    public SectionList() {
        head = null;
    }

    /**
     * adds a Section to back of list
     */
    public void add(Section s) {
        if (head == null) {
            head = new Node(s, null);
            return;
        }

        //traverse until end, add new node
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(s, null);
    }

    /**
     * Print all sections according to course number
     */
    public void printByCourse(String course) {
        Node curr = head;
        //loop through list, find matches
        while (curr != null) {
            if (curr.data.getCourse().equals(course)) {
                System.out.println(curr.data);
            }
            curr = curr.next;
        }
    }

    /**
     * find section in list and return it, else ret null
     */
    public Section find(String course, String section){
        Node curr = head;
        while(curr != null){
            //check for course and section match
            if(curr.data.getCourse().equals(course) && curr.data.getSec().equals(section)){
                return curr.data;
            }
            curr = curr.next;
        }
        //nothing found
        return null;
    }

    /**
     * print sections that fit the parameter schedule
     */
    public void printFits(Schedule sched, String str){
        Node curr = head;

        while(curr != null){
            Section s = curr.data;

            //filter by course
            if(str.equals("any") || s.getCourse().equals(str)){
                if(sched.fits(s)){
                    System.out.println(s);
                }
            }

            curr = curr.next;
        }
    }

}

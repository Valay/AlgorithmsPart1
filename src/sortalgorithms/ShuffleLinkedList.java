package sortalgorithms;

import edu.princeton.cs.algs4.StdRandom;
import linkedlist.Node;
import util.Util;

/**
 * Also sorting a linked list
 *  idea from - https://massivealgorithms.blogspot.com/2019/03/shuffling-linked-list.html
 */
public class ShuffleLinkedList {

    private static Node merge(Node first, Node second){
        Node head = new Node(0);

        Node current = head;
        while(first != null && second != null){
            if(StdRandom.uniform() >= 0.5) {
                current.next = first;
                first = first.next;
            } else {
                current.next = second;
                second = second.next;
            }
            current = current.next;
        }
        if(first != null){
            current.next = first;
        }
        if(second != null){
            current.next = second;
        }

        return head.next;
    }

    public static Node shuffle(Node head){

        if(head == null || head.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;

        Node second = null;

        if(fast == null){
            second = slow.next;
            slow.next = null;
        } else{
            while(fast.next != null && fast.next.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }

            second = slow.next;
            slow.next = null;
        }

        // shuffle the left side
        head = shuffle(head);
        // shuffle the right side
        second = shuffle(second);

        //Merge randomly
        return merge(head, second);
    }

    public static void main(String[] args){

        int n = Integer.parseInt(args[0]);
        Node head = null;
        Node current = null;
        for(int i=0 ; i<n; i++){
            int rand = StdRandom.uniform(100);
            Node newNode = new Node(rand);
            if(head == null){
                head = newNode;
                current = head;
            } else{
                current.next = newNode;
                current = current.next;
            }
        }

        Util.printLL(head);
        head = shuffle(head);
        Util.printLL(head);
    }


}

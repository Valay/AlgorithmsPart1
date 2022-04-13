package util;

import linkedlist.Node;
import sortalgorithms.ShuffleLinkedList;

import java.util.List;

public class Util {

    private static void exchInt(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void printIntArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void exch(List<Object> items, int a, int b) {
        Object temp = items.get(a);
        items.set(a, items.get(b));
        items.set(b, temp);
    }

    public static class Point implements Comparable<Point> {

        private final int x; // x-coordinate of this point
        private final int y; // y-coordinate of this point

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point that) {
            // corner cases
            if (that == null) {
                throw new NullPointerException();
            }
            // same
            if (this.x == that.x && this.y == that.y) {
                return 0;
            }
            //less
            if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
                return -1;
            }
            //bigger
            return 1;
        }

        public String toString() {
            return "(" + x + ", " + y + ") ";
        }
    }

    public static void printLL(Node head){
        Node current = head;
        while(current != null){
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println("");
    }
}

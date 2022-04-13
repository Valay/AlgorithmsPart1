package sortalgorithms;

import Sort.InsertionSort;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class IntersectionPoints {

    static class Point implements Comparable<Point> {

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

        public String toString(){
            return "(" +x + ", " + y + ") ";
        }
    }
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        List<Point> a = new ArrayList<>();
        fill(a, n);

        List<Point> b = new ArrayList<>();
        fill(b, m);

        System.out.println(a);
        System.out.println(b);

        List<Point> intersection = intersect(a, b);
        System.out.println(intersection);
    }

    private static void fill(List<Point> arr, int k){
        for(int i=0; i<k; i++){
            int x = StdRandom.uniform(10) - 5;
            int y = StdRandom.uniform(10) - 5;
            arr.add(new Point(x, y));
        }
    }

    private static List<Point> intersect(List<Point> a, List<Point> b){
        InsertionSort<Point> insertionSort = new InsertionSort<>();

        insertionSort.sort(a);
        insertionSort.sort(b);

        List<Point> intersection = new ArrayList<>();

        int i=0;
        int j=0;
        while(i < a.size() && j < b.size()){
            if(a.get(i).compareTo(b.get(j)) < 0){
                i++;
            } else if (a.get(i).compareTo(b.get(j)) > 0){
                j++;
            } else {
                intersection.add(a.get(i));
                i++;
                j++;
            }
        }

        return intersection;
    }
}

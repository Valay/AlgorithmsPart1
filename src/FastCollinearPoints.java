import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;
    private Point[] points;

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        if (points == null) {
            throw new IllegalArgumentException("Points is null!");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Point is null!");
            }
        }
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException("Point is repeated!");
            }
        }
        this.points = points;
        this.lineSegments = new ArrayList<>();
    }

    public int numberOfSegments() { // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() { // the line segmentsØ
        for (int i = 0; i < points.length - 3; i++) {
            Point[] slopes = new Point[points.length];

            for (int j = 0; j < points.length; j++) {
                slopes[j] = points[j];
            }
            Arrays.sort(slopes);
            Arrays.sort(slopes, points[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < slopes.length; last++) {
                while (last < slopes.length && slopes[p].slopeTo(slopes[first]) == slopes[p].slopeTo(slopes[last])) {
                    last++;
                }
                if (last - first >= 3 && slopes[p].compareTo(slopes[first]) < 0) {
                    lineSegments.add(new LineSegment(slopes[p], slopes[last - 1]));
                }
                first = last;
            }
        }
        return lineSegments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
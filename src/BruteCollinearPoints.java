import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private int count = 0;
    private ArrayList<LineSegment> lineSegments;
    private Point[] points;

    public BruteCollinearPoints(Point[] points){ // finds all line segments containing 4 points
        if(points == null){
            throw new IllegalArgumentException("Points is null!");
        }

        for(int i=0; i< points.length; i++){
            if(points[i] == null){
                throw new IllegalArgumentException("Point is null!");
            }
        }
        Arrays.sort(points);
        for(int i=1; i< points.length; i++){
            if(points[i].compareTo(points[i-1]) == 0){
                throw new IllegalArgumentException("Point is repeated!");
            }
        }
        this.points = points;
        this.lineSegments = new ArrayList<>();
    }

    public int numberOfSegments(){ // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments(){ // the line segments
        for(int p=0; p<points.length; p++){
            for(int q=p+1; q< points.length; q++){
                for(int r=q+1; r< points.length; r++){
                    for(int s=r+1; s< points.length; s++){
                        double slopePQ = points[p].slopeTo(points[q]);
                        double slopePR = points[p].slopeTo(points[r]);
                        double slopePS = points[p].slopeTo(points[s]);
                        if(Double.compare(slopePQ, slopePR) == 0 && Double.compare(slopePQ, slopePS) == 0){
                            count++;
                            int distancePQ = points[p].compareTo(points[q]);
                            int distancePR = points[p].compareTo(points[r]);
                            int distancePS = points[p].compareTo(points[s]);

                            if(Double.compare(distancePQ, distancePR) > 1 && Double.compare(distancePQ, distancePS) > 1){
                                lineSegments.add(new LineSegment(points[p], points[q]));
                            } else if (Double.compare(distancePR, distancePQ) > 1 && Double.compare(distancePR, distancePS) > 1){
                                lineSegments.add(new LineSegment(points[p], points[r]));
                            } else{
                                lineSegments.add(new LineSegment(points[p], points[s]));
                            }
                        }

                    }
                }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
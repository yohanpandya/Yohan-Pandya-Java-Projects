import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> LS;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {


        if (points == null) {
            throw new IllegalArgumentException("null argument");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("null point");
            }
        }
        LS = new ArrayList<LineSegment>();
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (isLine(points[i], points[j], points[k], points[l])) {
                            LineSegment L = new LineSegment(points[i], points[l]);
                            LS.add(L);
                        }

                    }
                }
            }
        }
    }


    private boolean isLine(Point a, Point b, Point c, Point d) {
        double slope = a.slopeTo(b);
        if (slope == b.slopeTo(c) && slope == c.slopeTo(d)) {
            return true;

        } else {
            return false;
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return LS.size();
    }

    public LineSegment[] segments() {

        LineSegment[] arrayofLS = new LineSegment[LS.size()];
        for (int i = 0; i < LS.size(); i++) {
            arrayofLS[i] = LS.get(i);
        }
        return arrayofLS;
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

            StdDraw.show();
        }
    }
}


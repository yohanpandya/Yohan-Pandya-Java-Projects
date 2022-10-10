import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> LS = new ArrayList<LineSegment>();
    private Point p;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("null argument");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("null point");
            }
        }


        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points);
            Arrays.sort(points, points[i].slopeOrder());
            for (int m = 1; m < points.length - 2; m++) {
                if (isLine(points[0], points[m], points[m + 1], points[m + 2])) {
                    if (min(points[0], points[m], points[m + 1], points[m + 2])) {
                        LineSegment L = new LineSegment(points[0], max(points[0], points[m], points[m + 1], points[m + 2]));
                        LS.add(L);
                        m += 3;
                    }

                }
            }

        }
    }


    private boolean min(Point a, Point b, Point c, Point d) {
        int ab = a.compareTo(b);
        int ac = a.compareTo(c);
        int ad = a.compareTo(d);
        if (ab <= 0 && ac <= 0 && ad <= 0) {
            return true;
        }
        return false;
    }

    /*private Point min(Point a, Point b, Point c, Point d) {
        int ab = a.compareTo(b);
        int ac = a.compareTo(c);
        int ad = a.compareTo(d);
        int bc = b.compareTo(c);
        int bd = b.compareTo(d);
        int cd = c.compareTo(d);

        if (ab <= 0 && ac <= 0 && ad <= 0) {
            return a;
        }
        if (bc <= 0 && bd <= 0) {
            return b;
        }
        if (cd <= 0) {
            return c;
        }
        return d;

    }*/

    private Point max(Point a, Point b, Point c, Point d) {
        int ab = a.compareTo(b);
        int ac = a.compareTo(c);
        int ad = a.compareTo(d);
        int bc = b.compareTo(c);
        int bd = b.compareTo(d);
        int cd = c.compareTo(d);

        if (ab >= 0 && ac >= 0 && ad >= 0) {
            return a;
        }
        if (bc >= 0 && bd >= 0) {
            return b;
        }
        if (cd >= 0) {
            return c;
        }
        return d;

    }


    private boolean isLine(Point a, Point b, Point c, Point d) {
        double slope = a.slopeTo(b);
        if (slope == a.slopeTo(c) && slope == a.slopeTo(d)) {
            return true;

        } else {
            return false;
        }
    }

    public int numberOfSegments() {
        return LS.size();
    }

    public LineSegment[] segments() {
        LineSegment[] LS1 = new LineSegment[LS.size()];
        for (int i = 0; i < LS.size(); i++) {
            LS1[i] = LS.get(i);
        }
        return LS1;
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

            StdDraw.show();
        }
    }

}

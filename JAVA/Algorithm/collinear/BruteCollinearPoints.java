import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private int nos = 0;
	private final LineSegment[] lineSegment;
	private final Point[] points;
	public BruteCollinearPoints(Point[] inputPoints) {
	// finds all line segments containing 4 points
		if (inputPoints == null ) {
			throw new IllegalArgumentException("argument is null");
		}
		points = Arrays.copyOf(inputPoints, inputPoints.length);
		for (Point p : points) {
			if (p == null) {
				throw new IllegalArgumentException("argument is null");
			}
		}
		Arrays.sort(points);
		for (int i = 0; i < points.length - 1; ++i) {
			if (points[i].compareTo(points[i+1]) == 0) {
				throw new IllegalArgumentException("argument contains repeated element");
			}
		}
		ArrayList<LineSegment> lineSegmentList = new ArrayList<LineSegment>();
		for (int i = 0; i < points.length; ++i) {
			for (int j = i+1; j < points.length; ++j) {
				for (int k = j+1; k < points.length; ++k) {
					for (int l = k+1; l < points.length; ++l) {
						Point[] tempPoints = {points[i],points[j],points[k],points[l]};
						double slope1 = tempPoints[0].slopeTo(tempPoints[1]);
						double slope2 = tempPoints[0].slopeTo(tempPoints[2]);
						double slope3 = tempPoints[0].slopeTo(tempPoints[3]);
						
						if (Double.valueOf(slope1).equals(slope2)) {
							if (Double.valueOf(slope1).equals(slope3)) { 
								LineSegment tempLS = new LineSegment(tempPoints[0],tempPoints[3]);
								boolean flag = true;
								for (LineSegment iter : lineSegmentList) {
									if (iter.toString().equals(tempLS.toString())) {
										flag = false;
										break;
									}
								}
								if (flag) {
									lineSegmentList.add(tempLS);
									++nos;
								}
							}
						}
					}
				}
			}
		}
		this.lineSegment = lineSegmentList.toArray(new LineSegment[0]);
	}    
	public int numberOfSegments() {
	// the number of line segments
		return this.nos;
	}       
	public LineSegment[] segments() {	
	// the line segments
		return this.lineSegment;
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
			// StdOut.println(p);
		}
		StdDraw.show();
    // print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		StdOut.println(collinear.numberOfSegments());
		// FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}

}
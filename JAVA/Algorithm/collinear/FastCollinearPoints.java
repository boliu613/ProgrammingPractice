import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private int nos = 0;
	private final LineSegment[] lineSegment;
	private final Point[] points;
	public FastCollinearPoints(Point[] inputPoints) {
   	// finds all line segments containing 4 or more points
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
		// for (Point p : points) {
		// 	StdOut.println(p);
		// }
		ArrayList<LineSegment> lineSegmentList = new ArrayList<LineSegment>();
		for (int i = 0; i < points.length; ++i) {
			Point[] currentPoints = Arrays.copyOf(points, points.length);
			Arrays.sort(currentPoints, points[i].slopeOrder());
			// StdOut.println("--------");
			// StdOut.println(i);
			// StdOut.println(points[i]);
			// StdOut.println("**");
			// for (Point p : currentPoints) {
			// 	StdOut.println(p);
			// }
			double currentSlope = points[i].slopeTo(currentPoints[0]);
			int count = 0;
			for (int j = 1; j < currentPoints.length; ++j) {
				double tempSlope = points[i].slopeTo(currentPoints[j]);
				if (Double.valueOf(tempSlope).equals(currentSlope)) {
					++count;
				}
				else {
					if (count >= 3) {
						Point[] sortPoints = new Point[count+1];
						sortPoints[0] = points[i];
						int index = 1;
						for (int k = j-count; k < j; ++k) {
							sortPoints[index] = currentPoints[k];
							++index;
						}
						Arrays.sort(sortPoints);
						LineSegment tempLS = new LineSegment(sortPoints[0],sortPoints[sortPoints.length-1]);
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
					currentSlope = tempSlope;
					count = 1;
				}
			}
			if (count >= 3) {
				Point[] sortPoints = new Point[count+1];
				sortPoints[0] = points[i];
				int index = 1;
				for (int k = currentPoints.length-count; k < currentPoints.length; ++k) {
					sortPoints[index] = currentPoints[k];
					++index;
				}
				Arrays.sort(sortPoints);
				LineSegment tempLS = new LineSegment(sortPoints[0],sortPoints[sortPoints.length-1]);
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
		FastCollinearPoints collinear = new FastCollinearPoints(points);

		StdOut.println(collinear.numberOfSegments());
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
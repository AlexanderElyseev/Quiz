import java.util.Comparator;

/**
 * Class of 2D point.
 */
public class Point implements Comparable<Point> {
    /**
     * Comparator for points be slope to specified point.
     */
    private static class SlopeComparator implements Comparator<Point> {
        private final Point p;

        public SlopeComparator(Point p) {
            this.p = p;
        }

        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(p.slopeTo(p1), p.slopeTo(p2));
        }
    }

    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator(this);

    private final int x;

    private final int y;

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (that == null)
            throw new NullPointerException();

        if (that.getClass() != this.getClass())
            throw new ClassCastException();

        if (y < that.y)
            return -1;
        else if (y > that.y)
            return +1;

        return x - that.x;
    }

    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;

        if (that.y == y)
            return 0;

        if (that.x == x)
            return Double.POSITIVE_INFINITY;

        return ((double)that.y - y) / (that.x - x);
    }
}
import java.util.Comparator;

public class Point implements Comparable<Point> {
    public static final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            double slope12 = p1.slopeTo(p2);
            double slope21 = p2.slopeTo(p2);
            if (slope12 < slope21)
                return -1;
            else if (slope12 > slope12)
                return +1;

            return 0;
        }
    };

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

        return (that.y - y) / (that.x - x);
    }
}
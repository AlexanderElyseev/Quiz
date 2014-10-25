import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> tree = new TreeSet<Point2D>();

    public boolean isEmpty() {
        return tree.size() == 0;
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D point) {
        tree.add(point);
    }

    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    public void draw() {
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        for (Point2D point : tree) {
            if (rect.contains(point)) {
                list.add(point);
            }
        }
        return list;
    }

    public Point2D nearest(Point2D p) {
        Point2D nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Point2D point : tree) {
            double distance = point.distanceSquaredTo(p);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearest = point;
            }
        }
        return nearest;
    }
}
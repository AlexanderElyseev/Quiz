import java.util.ArrayList;

public class KdTree {

    private static class Node {
        Point2D point;
        Node left;
        Node right;
        boolean horizontal;
    }

    private static class NearestPointSearchPair {
        Node first;
        Node second;
    }

    private static class NearestPointSearchData {
        Point2D nearestPoint;
        double nearestDistance;
    }

    private Node root;

    private int size;

    public KdTree() {
        root = new Node();
        root.horizontal = false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        if (root.point != null) {
            if (contains(point)) {
                return;
            }

            insertNodeRecursive(root, point);
        } else {
            root.point = point;
        }

        size++;
    }

    private static void insertNodeRecursive(Node current, Point2D point) {
        if (current.horizontal) {
            insertHorizontal(current, point);
        } else {
            insertVertical(current, point);
        }
    }

    private static void insertHorizontal(Node current, Point2D point) {
        if (current.point.y() <= point.y()) {
            insertRight(current, point);
        } else {
            insertLeft(current, point);
        }
    }

    private static void insertVertical(Node current, Point2D point) {
        if (current.point.x() <= point.x()) {
            insertRight(current, point);
        } else {
            insertLeft(current, point);
        }
    }

    private static void insertRight(Node current, Point2D point) {
        if (current.right == null) {
            current.right = createNode(current, point);
        } else {
            insertNodeRecursive(current.right, point);
        }
    }

    private static void insertLeft(Node current, Point2D point) {
        if (current.left == null) {
            current.left = createNode(current, point);
        } else {
            insertNodeRecursive(current.left, point);
        }
    }

    private static Node createNode(Node parent, Point2D point) {
        Node node = new Node();
        node.horizontal = !parent.horizontal;
        node.point = point;
        return node;
    }

    public boolean contains(Point2D p) {
        if (size == 0)
            return false;

        Node current = root;
        while (true) {
            if (current == null) {
                return false;
            }

            if (current.point.equals(p)) {
                return true;
            }

            if (current.horizontal) {
                if (current.point.y() <= p.y()) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            } else {
                if (current.point.x() <= p.x()) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }
        }
    }

    public void draw() {
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
        StdDraw.rectangle(500, 500, 500, 500);

        if (size == 0)
            return;

        draw(root, null, null);
    }

    private void draw(Node node, Node previous, Node previousparallel) {
        if (node.horizontal) {
            double x1 = previous == null
                ? 0
                : (previous.right == node ? previous.point.x() : 0);

            double x2 = previous == null
                ? 1000
                : (previous.right == node
                ? 1000
                : previous.point.x());

            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x1, node.point.y(), x2, node.point.y());
        } else {
            double y1 = previous == null
                ? 0
                : (previous.right == node ? previous.point.y() : 0);

            double y2 = previous == null
                ? 1000
                : (previous.right == node
                ? 1000
                : previous.point.y());

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), y1, node.point.x(), y2);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(node.point.x(), node.point.y(), 8);

        if (node.left != null) {
            draw(node.left, node, previous);
        }

        if (node.right != null) {
            draw(node.right, node, previous);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> result = new ArrayList<Point2D>();
        if (size == 0) {
            return result;
        }

        if (rect.contains(root.point)) {
            result.add(root.point);
            searchRect(rect, root, result);
        }

        return result;
    }

    private static void searchRect(RectHV rect, Node node, ArrayList<Point2D> result) {
        if (node.left != null && rect.contains(node.left.point)) {
            result.add(node.left.point);
            searchRect(rect, node.left, result);
        }

        if (node.right != null && rect.contains(node.right.point)) {
            result.add(node.right.point);
            searchRect(rect, node.right, result);
        }
    }

    public Point2D nearest(Point2D target) {
        if (size == 0) {
            return null;
        }

        if (size == 1) {
            return root.point;
        }

        NearestPointSearchData data = new NearestPointSearchData();
        data.nearestPoint = root.point;
        data.nearestDistance = root.point.distanceSquaredTo(target);
        searchNearest(root, target, data);

        return data.nearestPoint;
    }

    private static void searchNearest(Node node, Point2D target, NearestPointSearchData data) {
        double distance = node.point.distanceSquaredTo(target);
        if (distance < data.nearestDistance) {
            data.nearestPoint = node.point;
            data.nearestDistance = distance;
        }

        NearestPointSearchPair pair = getSearchPair(node, target);
        if (pair.first != null) {
            searchNearest(pair.first, target, data);
        }

        if (pair.second != null) {
            Point2D norm = node.horizontal
                ? new Point2D(data.nearestPoint.x(), node.point.y())
                : new Point2D(node.point.x(), data.nearestPoint.y());
            if (data.nearestDistance > data.nearestPoint.distanceSquaredTo(norm)) {
                searchNearest(pair.second, target, data);
            }
        }
    }

    private static NearestPointSearchPair getSearchPair(Node parent, Point2D target) {
        NearestPointSearchPair pair = new NearestPointSearchPair();
        if (parent.horizontal) {
            if (parent.point.y() >= target.y()) {
                pair.first = parent.left;
                pair.second = parent.right;
            } else {
                pair.first = parent.right;
                pair.second = parent.left;
            }
        } else {
            if (parent.point.x() >= target.x()) {
                pair.first = parent.left;
                pair.second = parent.right;
            } else {
                pair.first = parent.right;
                pair.second = parent.left;
            }
        }
        return pair;
    }
}